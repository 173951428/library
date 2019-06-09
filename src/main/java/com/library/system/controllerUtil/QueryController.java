package com.library.system.controllerUtil;

import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.system.entity.PageEntity;
import com.library.system.service.IQueryService;
import com.library.system.utils.ZcefStringUtils;

/**
 * 查询控制器 
 * @author lisujie
 *
 */
@Controller 
@RequestMapping("/query")
public class QueryController {
	
	final Logger logger = LoggerFactory.getLogger(QueryController.class);
	@Autowired
    private IQueryService queryService;
    /**
     * 分页查询   
     * @param queryParamMap
     * @return
     */
    @RequestMapping(value = "/pagingQuery")
	public @ResponseBody PageEntity pagingQuery(@RequestBody Map<String, Object> queryParamMap) {
		String currentPage = ZcefStringUtils.objToStr(queryParamMap.get("currentPage"));
		String pageNumber = ZcefStringUtils.objToStr(queryParamMap.get("pageNumber"));
		String userName = ZcefStringUtils.objToStr(queryParamMap.get("userName"));

		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if (!pattern.matcher(currentPage).matches()) {
			queryParamMap.put("currentPage", PageEntity.CURRENT_PAGE);
		}
		if (!pattern.matcher(pageNumber).matches()) {
			queryParamMap.put("pageNumber", PageEntity.PAGE_NUMBER);
		}

		logger.debug("------>>> QueryController.pagingQuery queryMap : " + queryParamMap);

		// GlobalInfoEntity globalEntity = (GlobalInfoEntity)
		// WebRequestHolder.obtainSession()
		// .getAttribute(Constants.GLOBAL_USER);
		queryParamMap.put("accountNumber", userName);

		PageEntity page = queryService.queryBusinessListByPage(queryParamMap);
		return page;
	}
}
