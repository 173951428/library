package com.library.system.utils;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author lisujie
 *
 */
public class SqlClient extends SqlSessionDaoSupport {
	final Logger logger = LoggerFactory.getLogger(SqlClient.class);
	public SqlClient(){
		logger.debug("------>>> SqlClient Initialization.................................");
	}
	
	/**
	 * 閺冪姴寮琲nsert
	 * @param statement insert閸烆垯绔撮弽鍥┿仛缁楋拷
	 * @return insert閻ㄥ嫬濂栭崫宥堫攽閺侊拷
	 */
	public Integer insert(String statement){
		logger.debug("------>>> SqlClient.insert statement : " + statement);
		return getSqlSession().insert(statement);
	}
	
	/**
	 * 閺堝寮琲nsert
	 * @param statement insert閸烆垯绔撮弽鍥┿仛缁楋拷
	 * @param parameter 閸欏倹鏆�
	 * @return insert閻ㄥ嫬濂栭崫宥堫攽閺侊拷
	 */
	public Integer insert(String statement,Object parameter){
		logger.debug("------>>> SqlClient.insert statement : " + statement + " parameter : " + parameter != null ? parameter.toString() : "null");
		return getSqlSession().insert(statement, parameter);
	}
	
	/**
	 * 閺冪姴寮瑄pdate
	 * @param statement update閸烆垯绔撮弽鍥┿仛缁楋拷
	 * @return update閻ㄥ嫬濂栭崫宥堫攽閺侊拷
	 */
	public Integer update(String statement){
		logger.debug("------>>> SqlClient.update statement : " + statement);
		return getSqlSession().update(statement);
	}
	
	/**
	 * 閺堝寮瑄pdate
	 * @param statement update閸烆垯绔撮弽鍥┿仛缁楋拷
	 * @param parameter 閸欏倹鏆�
	 * @return update閻ㄥ嫬濂栭崫宥堫攽閺侊拷
	 */
	public Integer update(String statement,Object parameter){
		logger.debug("------>>> SqlClient.update statement : " + statement + " parameter : " + parameter != null ? parameter.toString() : "null");
		return getSqlSession().update(statement, parameter);
	}
	
	/**
	 * 閺冪姴寮琩elete
	 * @param statement delete閸烆垯绔撮弽鍥┿仛缁楋拷
	 * @return delete閻ㄥ嫬濂栭崫宥堫攽閺侊拷
	 */
	public Integer delete(String statement){
		logger.debug("------>>> SqlClient.delete statement : " + statement);
		return getSqlSession().delete(statement);
	}
	
	/**
	 * 閺堝寮琩elete
	 * @param statement delete閸烆垯绔撮弽鍥┿仛缁楋拷
	 * @param parameter 閸欏倹鏆�
	 * @return delete閻ㄥ嫬濂栭崫宥堫攽閺侊拷
	 */
	public Integer delete(String statement,Object parameter){
		logger.debug("------>>> SqlClient.delete statement : " + statement + " parameter : " + parameter != null ? parameter.toString() : "null");
		return getSqlSession().delete(statement, parameter);
	}
	
	/**
	 * 閺冪姴寮琿uery閸烆垯绔寸拋鏉跨秿
	 * @param statement query閸烆垯绔撮弽鍥┿仛缁楋拷
	 * @return T 鏉╂柨娲栭弫鐗堝祦鐎电钖�
	 */
	public <T> T queryForObject(String statement ){
		logger.debug("------>>> SqlClient.queryForObject statement : " + statement);
		return getSqlSession().selectOne(statement);
	}
	
	/**
	 * 閺堝寮琿uery閸烆垯绔寸拋鏉跨秿
	 * @param statement query閸烆垯绔撮弽鍥┿仛缁楋拷
	 * @param parameter 閺屻儴顕楅崣鍌涙殶
	 * @return T 鏉╂柨娲栭弫鐗堝祦鐎电钖�
	 */
	public<T> T queryForObject(String statement,Object parameter){
		logger.debug("------>>> SqlClient.queryForObject statement : " + statement + " parameter : "+ parameter != null ? parameter.toString() : "null");
		return getSqlSession().selectOne(statement, parameter);
	}
	
	/**
	 * 閺冪姴寮琿uery婢舵碍娼拋鏉跨秿
	 * @param statement query閸烆垯绔撮弽鍥┿仛缁楋拷
	 * @return List<T> 鏉╂柨娲栭弫鐗堝祦閸掓銆�
	 */
	public <T> List<T> queryForList(String statement){
		logger.debug("------>>> SqlClient.queryForList statement : " + statement);
		return getSqlSession().selectList(statement);
	}
	
	/**
	 * 閺堝寮琿uery婢舵碍娼拋鏉跨秿
	 * @param statement query閸烆垯绔撮弽鍥┿仛缁楋拷
	 * @return List<T> 鏉╂柨娲栭弫鐗堝祦閸掓銆�
	 */
	public <T> List<T> queryForList(String statement,Object parameter){
		logger.debug("------>>> SqlClient.queryForList statement : " + statement + " parameter : "+ (parameter != null ? parameter.toString() : "null"));
		return getSqlSession().selectList(statement, parameter);
	}
	
	/**
	 * 閺冪姴寮弻銉嚄鏉╂柨娲栨禒顧砤pKey娑撹桨瀵岄柨顔炬畱鐎电钖�
	 * @param statement query閸烆垯绔撮弽鍥┿仛缁楋拷
	 * @param mapKey key閻ㄥ嫬鎮曠粔锟�
	 * @return 鏉╂柨娲栨禒顧砤pKey娑撴椽鏁惃鍑猘p鐎电钖�
	 */
	public <T> Map<String,T> queryForMap(String statement,String mapKey){
		logger.debug("------>>> SqlClient.queryForMap statement : " + statement + " mapKey : " + mapKey);
		return getSqlSession().selectMap(statement, mapKey);
	}
	
	/**
	 * 閺堝寮弻銉嚄鏉╂柨娲栨禒顧砤pKey娑撹桨瀵岄柨顔炬畱鐎电钖�
	 * @param statement query閸烆垯绔撮弽鍥┿仛缁楋拷
	 * @param parameter 閺屻儴顕楅崣鍌涙殶
	 * @param mapKey key閻ㄥ嫬鎮曠粔锟�
	 * @return 鏉╂柨娲栨禒顧砤pKey娑撴椽鏁惃鍑猘p鐎电钖�
	 */
	public <T> Map<String,T> queryForMap(String statement,Object parameter,String mapKey){
		logger.debug("------>>> SqlClient.queryForMap statement : " + statement + " mapKey : " + mapKey + " parameter : " + parameter !=null ? parameter.toString() : "null");
		return getSqlSession().selectMap(statement, parameter, mapKey);
	}
	
	/**
	 * @param countSql 缂佺喕顓搁弫浼村櫤鐠囶厼褰�
	 * @param sql 閺屻儴顕楅弰搴ｇ矎鐠囶厼褰�
	 * @param currentPage 瑜版挸澧犳い锟�
	 * @param pageSize 妞ょ敻娼版径褍鐨�
	 * @param parameter 閺屻儴顕楅弶鈥叉
	 * @return PageBean 閸掑棝銆夌�圭偘缍�
	 */
	/*public <T> PageBean<T> pagingQuery(String countSql,String sql,String currentPage, String pageSize,Object parameter){
		Long countTotal = queryForObject(countSql,parameter);
		PageBean<T> bean = new PageBean<T>(currentPage, pageSize, countTotal);
		//閸掑棝銆夐弻銉嚄
		List<T> pageSimilar = getSqlSession().selectList(sql, parameter, new RowBounds(bean.getStartPage(), bean.getPageSize()));
		bean.setPageSimilar(pageSimilar);
		return bean;
	}*/
}
