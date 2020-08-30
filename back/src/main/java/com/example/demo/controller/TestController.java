package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.FtlToDocDowload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TestController {
	private  static  final Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	private FtlToDocDowload ftlToDocDowload;

	@RequestMapping("test/downLoadFile_doc")
	public void downLoadDoc(HttpServletResponse response, HttpServletRequest request){
 	    JSONObject param = new JSONObject();
 	    param.put("name" , "李寻欢");
 	    param.put("position" , "探花");
        ftlToDocDowload.downLoadDoc("word下载.doc","wordftl.ftl",param,request,response);
	}



}
