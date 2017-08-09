package com.spzwl.web.formbean;

import java.util.HashMap;
import java.util.Map;
public class LoginForm {

	private String username;
	private String password;
	private String client_checkcode;
	private String service_checkcode;
	private String autologintime;
	private Map<String, String> errors = new HashMap<String, String>();
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getClient_checkcode() {
		return client_checkcode;
	}

	public void setClient_checkcode(String client_checkcode) {
		this.client_checkcode = client_checkcode;
	}

	public String getService_checkcode() {
		return service_checkcode;
	}

	public void setService_checkcode(String service_checkcode) {
		this.service_checkcode = service_checkcode;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	// 对数据进行校验的方法;
	public boolean validate() {
		boolean isOk = true;
		// 对用户名进行校验 规则为必须是字母 或者 数字4~12位
		if(username==null || username.trim().equals("")){
			errors.put("username", "用户名称不能为空!");
			isOk = false;
			
		}else if (!username.matches("\\w{4,12}")) {
			errors.put("username", "用户名填写有误!");
			isOk = false;
			 
		}
//		密码6~18位 数字 或 字母 
		if(password==null || password.trim().equals("")){
			errors.put("password", "用户密码称不能为空!");
			isOk = false;
		 
		}else if (!password.matches("\\w{4,16}")) {
			errors.put("password", "用户密码填写有误!");
			isOk = false;
		 
		}

//		验证码不能为空 
	 //System.out.println(client_checkcode+"******************");
		// System.out.println(service_checkcode+"******************");
		if(client_checkcode==null || client_checkcode.trim().equals("")){
			errors.put("client_checkcode", "验证码不能为空!");
			isOk = false;
			
		}/*else if (!client_checkcode.matches("[\u4e00-\u9fa5]+")) {
			errors.put("client_checkcode", "验证码写有误!");
			isOk = false;
			
		}*/else if(!client_checkcode.trim().equals(service_checkcode)){
			errors.put("client_checkcode", "验证码写有误!");
			isOk = false;
		}
		return isOk;
	}

	public String getAutologintime() {
		return autologintime;
	}

	public void setAutologintime(String autologintime) {
		this.autologintime = autologintime;
	}
	
	

}
