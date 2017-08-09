package com.spzwl.admin.custromer.domain;

import java.util.Date;

public class Upfile {
	private String id;
	private String uuidname; // 上传文件的名称，文件的uuid名
	private String realname; // 上传文件的真实名称
	private String savepath; // 记住文件的位置
	private Date uptime; // 文件的上传时间
	private String filesize; // 上传文件的大小
	private String filesuffix; // 上传文件的类型后缀
	private String description; // 文件的描述
	private String users_id; // 上传人外键 来自user表

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUuidname() {
		return uuidname;
	}

	public void setUuidname(String uuidname) {
		this.uuidname = uuidname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSavepath() {
		return savepath;
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getFilesuffix() {
		return filesuffix;
	}

	public void setFilesuffix(String filesuffix) {
		this.filesuffix = filesuffix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsers_id() {
		return users_id;
	}

	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}
	
	 
}
