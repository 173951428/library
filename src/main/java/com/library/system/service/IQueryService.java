package com.library.system.service;

import java.util.Map;

import com.library.system.entity.PageEntity;


/**
 * 查询服务的接口
 * @author lisujie
 *
 */
public interface IQueryService {
	 /**
	  * 分页查询的接口
	  * @param parameter 查询参数
	  * @return PageEntity
	  */
      public PageEntity queryBusinessListByPage(Map<String,Object>parameter);
} 
