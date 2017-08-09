package com.spzwl.admin.custromer.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 * 订单类
 * 
 * @author mac
 */
public class Order {

	// 订单 orderid 订单id，数据是uuid
	/**
	orderid VARCHAR (40) NOT NULL,
	productname VARCHAR (40) NOT NULL,
	orderDetail VARCHAR (40) NOT NULL,
	ordernum VARCHAR (20) NOT NULL,
	price VARCHAR (20) NOT NULL,
	address VARCHAR (80) NOT NULL,
	name VARCHAR (40) NOT NULL,
	qq VARCHAR (40),
	phone VARCHAR (40) NOT NULL,
	paytype VARCHAR (20),
	guestremark VARCHAR (20),
	referer VARCHAR (20),
	device VARCHAR (20),
	page VARCHAR (80),
	createdate datetime NOT NULL
	 */
	private String orderid;
	private String productname;
	private String orderDetail;
	private String ordernum;
	private String price;
	private String address;
	private String name;
	private String qq;
	private String phone;
	private String paytype;
	private String guestremark;
	private String referer;
	private String device;
	private String page;
	private Date createdate;
	
	
	//非持久化属性
	//错误信息
	private Map<String, String> errors = new HashMap<String, String>();
//为了简化操作前端直接使用 省市县的地址文字，没有使用地区代码，并且将省市县，三个字段合并到了address字段内方便查询
	private String 	province;
	private String  city;
	private String  zone;
	
	
	//返回
	public String getProvince() {
		if(this.province!=null && !"".equals(this.province)){
			
			return province;
		}
		
		return "";
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
	if(this.city!=null && !"".equals(this.city)){
			
			return city;
		}
		return "";
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZone() {
		if(this.zone!=null && !"".equals(this.zone)){
					
		return zone;
		}
		return "";
	}

	public void setZone(String zone) {
		this.zone = zone;
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
/*		if(username==null || username.trim().equals("")){
			errors.put("username", "用户名称不能为空!");
			isOk = false;
		}else if (!username.matches("\\w{4,12}")) {
			errors.put("username", "用户名填写有误!");
			isOk = false;
		}*/
//		密码6~18位 数字 或 字母 
/*		if(tell==null || tell.trim().equals("")){
			errors.put("password", "手机号码不能为空!");
			isOk = false;
		 
		}else if (!tell.matches("\\w{4,16}")) {
			errors.put("password", "手机号码填写有误!");
			isOk = false;
		}
			*/
		return isOk;
	}
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		
		this.address = this.getProvince()+" "+this.getCity()+" "+this.getZone()+" "+address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getGuestremark() {
		return guestremark;
	}

	public void setGuestremark(String guestremark) {
		this.guestremark = guestremark;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
}
