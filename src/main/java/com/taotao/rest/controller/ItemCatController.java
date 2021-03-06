package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	// http://localhost:8081/rest/itemCat/list?callback=category.getDataService
	@RequestMapping(value = "/itemCat/list",produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	@ResponseBody
	public String getItemCatList(String callback){
		CatResult catResult = itemCatService.getItemCatList();
		String json = JsonUtils.objectToJson(catResult);
		String result = callback + "(" + json + ")";
		return result;
	}
	
	// 方法二(解决字符乱码问题)
	/*@RequestMapping(value = "/itemCat/list")
	public Object getItemCatList(String callback){
		CatResult catResult = itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);  
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}*/
}
