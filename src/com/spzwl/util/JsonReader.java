package com.spzwl.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
/**
 * 读取页面传递过来的json数据流
 * @author mac
 *
 */
public class JsonReader {  
    public static JSONObject receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {  
  
        // 读取请求内容  
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));  
        String line = null;  
        StringBuilder sb = new StringBuilder();  
        while ((line = br.readLine()) != null) {  
            sb.append(line);  
        }  
        //将json字符串转换为json对象  
        JSONObject json=JSONObject.fromObject(sb.toString());  
        return json;  
    }  
    
    
  //读取请求传递过来的JSON格式数据，返回JSON字符串
//  	private String readJSONData(HttpServletRequest request) {
//  	        StringBuffer json=new StringBuffer();
//  	        String lineString=null;
//  	        try {
//  	            BufferedReader reader=request.getReader();
//  	            while ((lineString=reader.readLine())!=null) {
//  	                json.append(lineString);                
//  	            }
//  	        } catch (Exception e) {
//  	            System.out.println(e.toString());
//  	        }
//  	        return json.toString();
//  	    }
}  