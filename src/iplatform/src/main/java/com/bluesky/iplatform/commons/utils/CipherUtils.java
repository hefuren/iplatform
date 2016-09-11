package com.bluesky.iplatform.commons.utils;

import java.security.MessageDigest;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.bluesky.iplatform.commons.config.Config;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CipherUtils {

	private static CipherUtils cu = new CipherUtils();

	private String algorithm = "DES";
	private MessageDigest md;
	private KeyGenerator keygen;
	private SecretKey deskey;
	private Cipher c;
	private byte[] cipherByte;
	private BASE64Encoder encoder = new BASE64Encoder();

	private BASE64Decoder decoder = new BASE64Decoder();

	private String seed = "ceruleansoft1234%^";

	private CipherUtils() {
		long before = System.currentTimeMillis();
		init();
		long after = System.currentTimeMillis();
		System.out.println("initialization takes: " + (after - before) + " ms");
	}

	public static CipherUtils getInstance() {
		if (cu == null) {
			cu = new CipherUtils();
		}
		return cu;
	}

	private void init() {
		try {
			String className = "com.sun.crypto.provider.SunJCE";
			String appServer = Config.getConfig("CONFIG", "APP-SERVER");
			if (appServer.equalsIgnoreCase("WEBSPHERE")) {
				className = "com.ibm.crypto.provider.IBMJCE";
			}
			Provider provider = (Provider) Class.forName(className)
					.newInstance();
			Security.addProvider(provider);
			this.keygen = KeyGenerator.getInstance(this.algorithm);
			this.keygen.init(new SecureRandom(this.seed.getBytes()));
			this.deskey = this.keygen.generateKey();
			this.c = Cipher.getInstance(this.algorithm);

			this.md = MessageDigest.getInstance("MD5");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setSeed(String seed) {
		this.seed = seed;
		this.keygen.init(new SecureRandom(seed.getBytes()));
		this.deskey = this.keygen.generateKey();
	}

	public SecretKey getDeskey() {
		return this.deskey;
	}

	public void setDeskey(SecretKey deskey) {
		this.deskey = deskey;
	}

	public synchronized String encrypt(String s) {
		try {
			this.c.init(1, this.deskey);
			this.cipherByte = this.c.doFinal(s.getBytes());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return this.encoder.encode(this.cipherByte);
	}

	public synchronized String decrypt(String s) throws Exception {
		try {
			byte[] ba = this.decoder.decodeBuffer(s);
			this.c.init(2, this.deskey);
			this.cipherByte = this.c.doFinal(ba);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return new String(this.cipherByte);
	}

	public String digest(String s) {
		this.md.update(s.getBytes());
		return byte2Hex(this.md.digest());
	}

	private static String byte2Hex(byte[] b) {
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < b.length; i++) {
			String tmp = Integer.toHexString(0xFF & b[i]);
			if (tmp.length() == 1)
				hexString.append("0");
			hexString.append(tmp);
		}
		return hexString.toString();
	}

	public static String toMD5(String source) {
		String md5String = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source.getBytes());
			md5String = byte2Hex(md.digest());
		} catch (Exception ex) {
		}
		return md5String;
	}
}
