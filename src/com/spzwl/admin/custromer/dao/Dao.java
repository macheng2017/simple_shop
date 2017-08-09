package com.spzwl.admin.custromer.dao;

import com.spzwl.admin.custromer.domain.QueryResult;
import com.spzwl.admin.custromer.domain.Upfile;
import com.spzwl.web.formbean.QueryInfo;

public interface Dao {

	// 添加功能 将上传文件信息添加到数据库中
	/** 
	 * @param upfile
	 *            create table upfile( id varchar(40) primary key, uuidname
	 *            varchar(100) not null unique, realname varchar(100) not null,
	 *            savepath varchar(100) not null, uptime date not null, filesize
	 *            varchar(200) not null, filesuffix varchar(40) not null,
	 *            description varchar(250), upfileuser_id varchar(40),
	 *            constraint upfileuser_id_FK foreign key (upfileuser_id)
	 *            references upfileuser (userid) );
	 */
	public abstract void addFile(Upfile upfile);

	//修改
	public abstract void updateFile(Upfile upfile);

	//	查找某个文件的信息
	public abstract Upfile findFile(String id);

	//分页显示 所有文件
	public abstract QueryResult pageQuery(QueryInfo queryInfo);

	//删除文件需要开启事务 不但要删除数据库记录,也要删除硬盘中的文件,还要保证这两件事情同时完成,需要先删除记录再删文件
	public abstract void delete(String id);

}