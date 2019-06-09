package com.library.system.service.imple;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.library.system.entity.PageEntity;
import com.library.system.exception.QueryParamException;
import com.library.system.service.IQueryService;
import com.library.system.utils.SqlClient;
import com.library.system.utils.ZcefStringUtils;

/**
 * 查询接口的服务实现类
 * @author lisujie
 *
 */
@Service
public class QueryServiceImp implements IQueryService {
	final Logger logger=LoggerFactory.getLogger(QueryServiceImp.class);
	@Autowired
    private SqlClient sqlClient;
	
	@Override
	public PageEntity queryBusinessListByPage(Map<String, Object> parameter) {
		PageEntity pageEntity = new PageEntity();
		pageEntity.setCurrentPage((Integer) parameter.get("currentPage"));
		pageEntity.setPageNumber((Integer) parameter.get("pageNumber"));

		parameter.put("pageEntity", pageEntity);

		String sqlStatement = ZcefStringUtils.objToStr(parameter.get("sqlStatement"));

		if ("".equals(sqlStatement)) {
			throw new QueryParamException("sqlStatement is null!");
		}

		List<?> resultList = sqlClient.queryForList(sqlStatement, parameter);

		logger.debug("------>>>QueryServiceImpl.queryBusinessListByPage   sqlStatement : " + sqlStatement
				+ " resultList.size : " + (resultList == null ? "null" : resultList.size()));

		pageEntity.setPageList(resultList);

		return pageEntity;
	}

}
