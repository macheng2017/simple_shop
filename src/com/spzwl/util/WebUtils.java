package com.spzwl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.spzwl.admin.custromer.dao.excption.NoFileUploadException;
import com.spzwl.admin.custromer.domain.Upfile;

public class WebUtils {
	public static <T> T request2Form(HttpServletRequest request,
			Class<T> formBean) {
		// 先声明一个 T 类型的 FormBean
		T form = null;
		// 使用反射创建该传进的formBean对象字节码的实例;
		try {
			form = formBean.newInstance();
			// 批量取出request 中的jsp页面提交过来的form表单中的值
			for (Enumeration<String> em = request.getParameterNames(); em
					.hasMoreElements();) {
				String name = em.nextElement();
				String value = request.getParameter(name);
				BeanUtils.setProperty(form, name, value);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return form;
	}
	// 将数据从formBean中copy到 javaBean中
	public static void copy2Bean(Object formBean, Object javaBean) {
		// BeanUtils可以自动转换8种基本数据类型,其中 birthday 的Date数据必须自己配置转换器
		ConvertUtils.register(new Converter() {
			@Override
			public Object convert(Class type, Object value) {
				// 检查是否为空
				if (value == null) {
					return null;
				}
				// 检查类型是否是String
				if (!(value instanceof String)) {
					throw new ConversionException("不支持除String以外的类型!");
				}
				String str = (String) value;
				// 检查是否为空字符串.
				if (str.trim().equals("")) {
					return null;
				}
				// 开始转换
				SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return date.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
		}, Date.class);
		try {
			BeanUtils.copyProperties(javaBean, formBean);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 上传文件工具方法
	 * @param userid 登录用户的userid 作为外键 
	 * @param request
	 * @param path
	 * @return 
	 * @return
	 * @throws FileSizeLimitExceededException 
	 * @throws NoFileUploadException 
	 * @throws Exception 
	 */
	public static List<Upfile> doUpload(String userid, HttpServletRequest request, String uppath) throws FileSizeLimitExceededException, NoFileUploadException
			 {
		try {
			List<Upfile> listBean = new ArrayList<Upfile>();
			// 使用组件上传文件
			DiskFileItemFactory factory = new DiskFileItemFactory();
//			设置缓存大小为1M
			factory.setSizeThreshold(1024*1024);
			// 设置临时文件存放的目录
			factory.setRepository(new File(request.getServletContext()
					.getRealPath("/WEB-INF/temp/")));
			// 解析request方法所封装的上传数据
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 限制上传文件的大小,超过大小则抛出编译时异常(当你希望调用者必须处理的的时候)
			// 当异常作为一个返回值返回的时候.
			
			upload.setFileSizeMax(1024 * 1024 * 500);
			// 设置中文文件编码格式
			
			upload.setHeaderEncoding("utf-8");
			List<FileItem> listItem = upload.parseRequest(request);
			//判断用户是否上传文件 定义一个变量记录 上传普通项
			int up_normal = 0;
			//记录总上传项目
			int up_item =0;
			int up_all =  listItem.size();
			System.out.println("++++++++up_all="+up_all);
			for (FileItem item : listItem) {
				Upfile upfile = new Upfile();
				// 判断是不是普通输入项
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					// 这里在项目开发的时候需要对数据进行validate
					BeanUtils.setProperty(upfile, name, value);
//					记录普通上传项
					up_normal++;
				} else {
					InputStream in =null;
					FileOutputStream out =null;
						
						// 由于浏览器的问题,所获取的文件名长度不一致
						String name = item.getName();
						if(name==null||name.trim().equals("")){
							//记录空上传项
							up_item++;
							continue;
						}
						try {
						name = name.substring(name.lastIndexOf("\\") + 1);
						String[] uuidname = generateName(name);
						String savepath = generatePath(uppath, uuidname[0]);
						in = item.getInputStream();
						out = new FileOutputStream(savepath
								+ File.separator + uuidname[0]);
						int len = 0;
						byte[] buffer = new byte[1024];
						while ((len = in.read(buffer)) > 0) {
							out.write(buffer, 0, len);
						}
//						获取当前时间值
//						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//						format.
						out.flush();
						upfile.setId(UUID.randomUUID().toString());
						upfile.setRealname(name);
						upfile.setSavepath(savepath);
						upfile.setUptime(new Date()); 
						upfile.setUuidname(uuidname[0]);
						upfile.setFilesize(String.valueOf(item.getSize()));
						upfile.setFilesuffix(uuidname[1]);
						upfile.setUsers_id(userid); 
						listBean.add(upfile);
						
					} finally {
						if(in!=null){
							
							in.close();
						}
						if(out!=null){
							
							out.close();
						}
						item.delete();
					}
				}
			}
//					 如果空上传项+普通项=上传项 则用户没有上传文件
			if(up_all==(up_normal+up_item)){
				throw new NoFileUploadException("没有上传文件.");
			}
			return listBean;
		}catch (FileUploadBase.FileSizeLimitExceededException e) {
			throw e;
		}catch(NoFileUploadException e){
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 生成唯一文件名称  
	 * @param name 
	 * @return 文件名(包括后缀)[0]  后缀名(仅后缀)[1]
	 */
	public static String[] generateName(String name) {
		// 由于使用的数据库存储所以uuid名称后边不需要再跟上原始文件名称,只需要加上后缀即可
		String ext = name.substring(name.lastIndexOf(".") + 1);
		String str[] ={UUID.randomUUID().toString() + "." + ext,ext};
		return str;
	}

	// 为防止同时上传过多文件,将文件按hash码分散到文件夹中
	public static String generatePath(String realPath, String name) {
		int code = name.hashCode();
		// 取得hashcode二进制位最后4位,所表示的数字
		int part1 = code & 0xf;// code&15;
		// 取得向右移动4位后的,后4位所表示的数字.
		int part2 = (code >>> 4) & 0xf;
		// 将生成的链接组合起来
		String path = realPath + File.separator + part1 + File.separator
				+ part2 + File.separator;
		// 如果文件路径不存在则创建出来,这里不用返回直接在硬盘上创建完成
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}
}
