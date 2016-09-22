package com.bluesky.iplatform;

import static org.junit.Assert.*;

import com.bluesky.iplatform.commons.utils.TypeUtils;

public class Test {

	@org.junit.Test
	public void test() {
		char[] xxChar = new char[]{'E','l','w','i','n'};
		String str = TypeUtils.charArrayToString(xxChar);
		
		System.out.println("====== str : "+ str);
		
	}

}
