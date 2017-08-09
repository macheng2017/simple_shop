package com.spzwl.admin.custromer.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.spzwl.admin.custromer.dao.UpfileDao;
import com.spzwl.admin.custromer.dao.excption.DaoException;
import com.spzwl.admin.custromer.domain.QueryResult;
import com.spzwl.admin.custromer.domain.Upfile;
import com.spzwl.util.JdbcUtils;
import com.spzwl.web.formbean.QueryInfo;

public class UpfileDaoImpl implements UpfileDao {

	// 添加功能 将上传文件信息添加到数据库中
	/* (non-Javadoc)
	 * @see com.yydtk.dao.impl.Dao#addFile(com.yydtk.domain.Upfile)
	 */
	@Override
	public void addFile(List<Upfile> listBean){
		try {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "insert into upfile (id,uuidname,realname,savepath,uptime,filesize,filesuffix,description,"
				+ "users_id) values(?,?,?,?,?,?,?,?,?)";
		Object params[][] = new Object[listBean.size()][9]; 
		int i=0;
		for(Upfile upfile:listBean){
			params[i++]= new Object[]{ upfile.getId(), upfile.getUuidname(),
					upfile.getRealname(), upfile.getSavepath(), upfile.getUptime(),
					upfile.getFilesize(), upfile.getFilesuffix(),
					upfile.getDescription(), upfile.getUsers_id()};
		}
			qr.batch(sql, params);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	//修改
	/* (non-Javadoc)
	 * @see com.yydtk.dao.impl.Dao#updateFile(com.yydtk.domain.Upfile)
	 */
	@Override
	public void updateFile(Upfile upfile){
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "update upfile set uuidname=?,realname=?,savepath=?,uptime=?,filesize=?,filesuffix=?,description=?,"
				+ "users_id=? where id=?";
		Object params[] = {  upfile.getUuidname(),
				upfile.getRealname(), upfile.getSavepath(), upfile.getUptime(),
				upfile.getFilesize(), upfile.getFilesuffix(),
				upfile.getDescription(), upfile.getUsers_id(),upfile.getId()};
		try {
			qr.update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
//	查找某个文件的信息
	/* (non-Javadoc)
	 * @see com.yydtk.dao.impl.Dao#findFile(java.lang.String)
	 */
	@Override
	public Upfile findFile(String id){
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql ="select * from upfile where id = ?";
		try {
			return 	(Upfile) qr.query(sql, new BeanHandler(Upfile.class), new Object[]{id});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	//分页显示 所有文件
	/* (non-Javadoc)
	 * @see com.yydtk.dao.impl.Dao#pageQuery(com.yydtk.web.formbean.QueryInfo)
	 */
	@Override
	public QueryResult  pageQuery(QueryInfo queryInfo,String users_id){
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql ="select * from upfile  where users_id=? order by uptime desc limit ?,?";
		try {
			Object params[]={users_id,queryInfo.getStartindex(),queryInfo.getPagesize()};
			List<Upfile> list = (List<Upfile>) qr.query(sql, new BeanListHandler(Upfile.class), params);
			sql ="select count(*)from upfile where users_id=?";
//			得到总记录数
			long len =(Long)qr.query(sql,new ScalarHandler(),params= new Object[]{users_id});
			QueryResult queryResult = new QueryResult();
			queryResult.setList(list);
//			这样处理会丢失精度(注意)
			queryResult.setTotalrecord((int) len);
			return queryResult;
		} catch (Exception e){
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	//删除文件需要开启事务 不但要删除数据库记录,也要删除硬盘中的文件,还要保证这两件事情同时完成,需要先删除记录再删文件
	/* (non-Javadoc)
	 * @see com.yydtk.dao.impl.Dao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id){
/*		try {
		QueryRunner qr = new QueryRunner();
		Connection conn =JdbcUtils.getConnection();
//		开启事务
		JdbcUtils.startTransaction();
		String sql ="delete table upfile where id=?";
			qr.update(conn,sql, new Object[]{id});
//			删除文件,查找文件
			Upfile upfile = findFile(id);
			String savepath = upfile.getSavepath();
			File file = new File (savepath);
			if(!file.exists()){
				//这样处理有问题,文件找不到抛异常事务回滚,数据库记录删不掉
				throw new RuntimeException("文件已被删除!");
			}
			file.delete();
			JdbcUtils.commitTransaction();
		} catch (Exception e){
			e.printStackTrace();
			throw new DaoException(e);
		}finally{
			//关闭连接,从ThreadLocal中移除Connection
			JdbcUtils.closeConnection();
		}*/
		//觉得这个事务还是不放到service层比较好,因为删除文件的时候肯定是数据库 文件两个一块删除.
		try {
			QueryRunner qr = new QueryRunner();
			Connection conn =JdbcUtils.getConnection();
			String sql ="delete from upfile where id=?";
			qr.update(conn,sql, new Object[]{id});
		} catch (Exception e){
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
}
