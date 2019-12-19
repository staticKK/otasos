package com.jointcorp.otasos.controller;

import com.jointcorp.base.MsgInterpreter;
import com.jointcorp.common.util.JsonUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@Scope("prototype")
public class BaseController {

	protected static void print(HttpServletResponse response, String string) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter print=null;
		try {
			print = response.getWriter();
			print.write(string);
			print.flush();
		}finally{
			if(print!=null){
				print.close();
			}
		}
	}

	protected static void print(HttpServletResponse response, String string, String callback) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter print=null;
		try {
			print = response.getWriter();
			print.write(callback+"("+string+")");
			print.flush();
		}finally{
			if(print!=null){
				print.close();
			}
		}
	}

	@ExceptionHandler(Exception.class)
	public void sysErrorMsg(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception{
		e.printStackTrace();
		String string = JsonUtils.objectToJson(MsgInterpreter.error());
		print(response,string);
	}
}
