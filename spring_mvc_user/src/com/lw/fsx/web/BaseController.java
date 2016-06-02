package com.lw.fsx.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lw.fsx.util.BaseUtil;
import com.lw.fsx.util.EhCache;
import com.lw.fsx.util.PropertiesUtil;
import com.lw.fsx.util.ScriptUtil;
import com.lw.fsx.vo.CommonDAO;


/**
 * 所有controller的基类
 *
 */
public class BaseController {
	@Autowired
	protected BaseUtil baseUtil;

	@Autowired
	protected CommonDAO commonDAO;
	@Autowired
	protected ScriptUtil scriptUtil ;
	@Autowired
	protected PropertiesUtil propUtil ;
	@Autowired
	protected EhCache ehCache ;
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	public String getTxtPath() {
		try {
			return propUtil.readValue("txtPath").toString();
		} catch (IOException e) {
			log.debug("读取文件路径的配置文件错误***");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 功能：相当于 response.getWriter().print  <br/>
	 * 时间：Oct 29, 2011 9:11:43 AM
	 * @param request
	 * @param printStr
	 * @throws IOException 
	 */
	protected void print(HttpServletResponse response,String printStr) throws IOException{
		response.getWriter().print(printStr);
	}
	
	public BaseController(){
		
	}
}
