package com.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.ContentMapper;
import com.taotao.pojo.Content;
import com.taotao.pojo.ContentExample;
import com.taotao.pojo.ContentExample.Criteria;
import com.taotao.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentMapper contentMapper;

	@Override
	public List<Content> getContentList(long contentCatId) {
		// 根据内容分类id查询内容列表
		ContentExample example = new ContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCatId);
		// 执行查询
		List<Content> list = contentMapper.selectByExampleWithBLOBs(example);   // 注意，大文本数据

		return list;
	}

}
