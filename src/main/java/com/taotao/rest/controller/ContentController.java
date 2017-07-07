package com.taotao.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.Content;
import com.taotao.rest.service.ContentService;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;

	// http://localhost:8081/rest/content/list/95
	@RequestMapping("/content/list/{contentCategoryId}")
	@ResponseBody
	public TaotaoResult getContentList(@PathVariable Long contentCategoryId) {
		try {
			List<Content> list = contentService.getContentList(contentCategoryId);
			return TaotaoResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	// -----------------------------------测试httpClient----------------------------------------------
	// 不带参数 post 请求
	@RequestMapping("/httpClient/post")
	@ResponseBody
	public String doPost(){
		return "不带参数的 post 请求";
	}
	
	// 带有参数 post 请求
	@RequestMapping("/httpClient/post/param")
	@ResponseBody
	public String doPost(String username,String password){
		return "username:" + username + ",password:" + password;
	}
	// -----------------------------------测试httpClient----------------------------------------------
	
}
