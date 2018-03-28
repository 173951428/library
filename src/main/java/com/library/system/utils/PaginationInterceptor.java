package com.library.system.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.library.system.entity.PageEntity;


/**
 * 分页拦截器
 * @Description: 通过实现MyBatis的Interceptor达到统一处理分页的效果
 * @author zuohongda   
 * @Company: Newtouch
 * @since 2015年11月10日 上午10:22:32 
 * @version V0.1
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PaginationInterceptor implements Interceptor {
	final Logger logger = LoggerFactory.getLogger(PaginationInterceptor.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//被拦截的对象
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		/*
		 * MyBatis配置文件中的SQL语句属性都是保存到 MappedStatement对象中的。
		 * 通过MyBatis封装好了反射方法可以获取到
		 */
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
				SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		String id = mappedStatement.getId();
		
		logger.debug("------>>>PaginationInterceptor.intercept  SqlId: " + id);
		
		//只拦截mapper文件中SQL语句ID以ByPage结尾
		if (id.matches(".+ByPage$")) {
			BoundSql boundSql = statementHandler.getBoundSql();
			//原始的SQL语句
			String sql = boundSql.getSql();
			// 查询总条数的SQL语句
			String countSql = "select count(1) from (" + sql + ") a";
			Connection connection = (Connection) invocation.getArgs()[0];
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			// 为统计语句赋查询参数值，参数信息在ParameterHandler对象中
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet rs = countStatement.executeQuery();

			// 获取传入的参数
			Map<?, ?> parameter = (Map<?, ?>) boundSql.getParameterObject();
			PageEntity page = (PageEntity) parameter.get("pageEntity");
			if (rs.next()) {
				page.setTotalNumber(rs.getInt(1));
			}
			
			StringBuilder pageSql = new StringBuilder(sql);
			
			//排序
			List<String> sorts = (ArrayList<String>)parameter.get("sorts");
			if(sorts != null && sorts.size() > 0){
				//select * from (" + sql + ") a order by
				pageSql.insert(0, "select * from  (").append(") a order by ");
				for(String sort : sorts){
					pageSql.append(sort).append(",");
				}
				pageSql = new StringBuilder(pageSql.substring(0, pageSql.length()-1));
				
				pageSql.append(" desc");
				
				logger.debug("------>>> PaginationInterceptor.intercept sort sql :" + pageSql);
			}
			
			//带分页查询的SQL语句
			pageSql.append(" limit ");
			pageSql.append(page.getDbIndex());
			pageSql.append(",");
			pageSql.append(page.getDbNumber());
			/*
			 * 修改原本不可修改的值，原理和获取MappedStatement对象一样，参数也是OGNL表达式
			 * 这里就是修改sql属性的值为分页SQL
			 */
			metaObject.setValue("delegate.boundSql.sql", pageSql.toString());
		}
		// 执行分页语句
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	/**
	 * 设置额外的属性
	 */
	public void setProperties(Properties properties) {
		
	}
}
