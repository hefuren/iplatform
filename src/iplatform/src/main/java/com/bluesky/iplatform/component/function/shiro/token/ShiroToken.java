package com.bluesky.iplatform.component.function.shiro.token;

import lombok.Data;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component(value = "ShiroToken")
@Scope(value = "prototype")
public class ShiroToken extends UsernamePasswordToken  implements java.io.Serializable{

	private static final long serialVersionUID = 3708727575604977037L;
	
	/**
	 * 公司/租户 ID
	 */
	private int companyID ;
	
	/**
	 * 登录密码
	 */
	private String pswd ;

	public ShiroToken(String username, String pswd, int companyID) {
		super(username,pswd);
		this.pswd = pswd;
		this.companyID = companyID ;
	}
	
	
	
}
