package com.library.system.entity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实体对象基类
 * @Description: 所有实体类都继承该基类，实现序列化和重写toString方法
 * @author 小素素
 * @Company: Newtouch
 * @since 2015年11月10日 上午11:27:51 
 * @version V0.1
 */
public abstract class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4672241933246215511L;
	
	final Logger logger = LoggerFactory.getLogger(BaseEntity.class);
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
