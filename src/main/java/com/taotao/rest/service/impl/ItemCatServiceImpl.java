package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.ItemCatMapper;
import com.taotao.pojo.ItemCat;
import com.taotao.pojo.ItemCatExample;
import com.taotao.pojo.ItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Override
	public CatResult getItemCatList() {
		CatResult result = new CatResult();
		result.setData(getCatList(0)); 
		return result;
	}

	private List<?> getCatList(long parentid){
		ItemCatExample example = new ItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentid);
		List<ItemCat> itemCatList = itemCatMapper.selectByExample(example);
		List resultList = new ArrayList<>();
		int count = 0;
		for(ItemCat itemCat : itemCatList){
			if(itemCat.getIsParent()){   // 是父节点
				CatNode catNode = new CatNode();
				catNode.setUrl("/products/"+ itemCat.getId() +".html");
				if(parentid == 0){       // 一级类目
					catNode.setName("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
				} else {
					catNode.setName(itemCat.getName());
				}
				// 递归调用
				catNode.setItem(getCatList(itemCat.getId()));
				resultList.add(catNode);
				count++;
				if(parentid == 0 && count >= 14){
					break;
				}
			} else {
				resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName() + "");
			}
		}
		return resultList;
	}
	
}
