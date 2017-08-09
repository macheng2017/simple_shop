package com.spzwl.web.formbean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
public class RegisterForm {
	private String userid;
	private String username;
	private String password;
	private String password2;
	private String email;
	private String qq;
	private String address;
	private String birthday;
	private String phoneid;
	private String nickname;
	private String client_checkcode;
	private String service_checkcode;
	private Map<String, String> errors = new HashMap<String, String>();

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
			errors.put("username", "用户名填写有误!请使用字母或者数字,或者字母+数字例如:abc123");
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
//		重复密码
		if(password2==null || password2.trim().equals("")){
			errors.put("password2", "用户密码称不能为空!");
			isOk = false;
		 
		}else if (!password.equals(password2)) {
			errors.put("password2", "两次密码不一样!");
			isOk = false;
			 
		}
		
//		Email6~18位 数字 或 字母 
		if(email==null || email.trim().equals("")){
			errors.put("email", "email不能为空!");
			isOk = false;
			 
		}else if (!email.matches("\\w+@\\w+(\\.\\w+)+")) {
			errors.put("email", "email填写有误!Email的格式,例如:fanbingbing@163.com");
			isOk = false;
		}
		
//		qq 数字 6~16
		if(qq==null || qq.trim().equals("")){
			errors.put("qq", "qq称不能为空!");
			isOk = false;
			 
		}else if (!qq.matches("\\d{6,16}")) {
			errors.put("qq", "qq填写有误!");
			isOk = false;
			 
		}
//		手机号:  必须为11位
		if(phoneid==null || phoneid.trim().equals("")){
			errors.put("phoneid", "手机号不能为空!");
			isOk = false;
			
		}else if (!phoneid.matches("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$")) {
			errors.put("phoneid", "手机号填写有误!");
			isOk = false;
			
		}
//		地址 必须是汉字 且不能小于6个汉字大于40个汉字
		if(address==null || address.trim().equals("")){
			errors.put("address", "地址称不能为空!");
			isOk = false;
		 
		}else if (!address.matches("[\u4e00-\u9fa5]+{12,80}")) {
			errors.put("address", "地址填写有误!");
			isOk = false;
			
		}
		
//		生日 可以为空,但不为空的时候必须按照 1989- 09-08
		if(birthday!=null && !birthday.trim().equals("")){
			try {
				DateLocaleConverter converter = new DateLocaleConverter();
				converter.convert(birthday, "yyyy-MM-dd");
			} catch (Exception e) {
				errors.put("birthday", "生日格式错误,请重新填写!");
				isOk=false;
				
			}
				
		 }
//		昵称 必须是汉字 
		if(nickname==null || nickname.trim().equals("")){
			errors.put("nickname", "昵称不能为空!");
			isOk = false;
		
		}else if (!nickname.matches("[\u4e00-\u9fa5]+")) {
			errors.put("nickname", "昵称填写有误!");
			isOk = false;
		
		}
//		验证码不能为空 
	/*	 System.out.println(client_checkcode+"******************");
		 System.out.println(service_checkcode+"******************");*/
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

}
