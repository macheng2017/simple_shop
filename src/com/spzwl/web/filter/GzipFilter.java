package com.spzwl.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 1. 使用filter 拦截response 使用自己写的包装过的response方法 2. 控制response的 两个输入方法
 * getOutputStream()与getWrite()方法将数据写到一个缓冲内. 3. 将数据通过GzipOutputStream 压缩后输出
 */
public class GzipFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//
		MyResponse myResponse = new MyResponse(response);
		chain.doFilter(request, myResponse);
		byte[] byteBuff = myResponse.getByteArray();
		// 得到压缩后的字节数组
		byte[] gbyte = this.gzip(byteBuff);
		response.setHeader("content-encoding", "gzip");
		response.setContentLength(gbyte.length);
		response.getOutputStream().write(gbyte);
	}

	// 定一个方法用于压缩数据
	public byte[] gzip(byte[] bytebuff) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(bout);
		gout.write(bytebuff);
		gout.close();
		return bout.toByteArray();
	}

	/***
	 * 
	 * 增强response方法的 两个想浏览器输出的方法,使其将数据写到一个字节缓冲中 其实就是利用一个变量一级一级向上传递, 要注意:
	 * 整个变量只有在最上层那一级被实例化了. 一级一级传递的是一个最上层实例化的引用
	 *
	 */
	class MyResponse extends HttpServletResponseWrapper {
		// 定义好一个字节缓冲
		private ByteArrayOutputStream byteout = new ByteArrayOutputStream();
		private PrintWriter printWriter = null;
		private HttpServletResponse response;

		public MyResponse(HttpServletResponse response) {
			super(response);
			this.response = response;
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			// 定义一个自己的包装输出流对象
			return new MyServletOutputStream(byteout);
		}

		// 重写另外一个方法,getWriter()
		// 注意: 由于printwriter是一个包装类,当数据量太小或太大的时候我们需要手动关闭(刷新关闭)
		// 所以需要在MyResponse中维护一个PrintWriter对象.
		@Override
		public PrintWriter getWriter() throws IOException {
			// 让调用者使用getWriter时将数据通过bout 传递到上层ByteArrayOutputStream缓冲区中
			// 注意:PrintWriter本身也是一个包装流,其内部也有缓冲区,需要在调用者那里手动关闭刷新.
			printWriter = new PrintWriter(new OutputStreamWriter(byteout, response.getCharacterEncoding())); // 将数据读取到传入的字节数组中
			return printWriter;
		}

		// 提供一个获取内部byte数组方法获取 已经缓存,的byte数组
		public byte[] getByteArray() {
			if (printWriter != null) {
				printWriter.close();
			}
			return byteout.toByteArray();
		}
	}

	class MyServletOutputStream extends ServletOutputStream {
		private ByteArrayOutputStream byteout;

		public MyServletOutputStream(ByteArrayOutputStream byteout) {
			super();
			this.byteout = byteout;
		}

		@Override
		public void write(int b) throws IOException {
			// 将调用者传入的数据,存放到已经写好的字节数组缓冲区当中
			byteout.write(b);
		}

		@Override
		public boolean isReady() {
			return false;
		}

		@Override
		public void setWriteListener(WriteListener arg0) {

		}
	}

	@Override
	public void destroy() {

	}

}
