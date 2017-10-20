package com.cretin.intercepter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Title: Login
 * </p>
 * <p>
 * Description:如果是需要登录的接口则在对应的方法上面加上这个注解
 * </p>
 * <p>
 * Company: www.cretin.com
 * </p>
 * 
 * @author cretin
 * @date 2017年10月19日
 */
@Target(ElementType.METHOD) // 注明注解中有方法
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
	/**
	 * 默认json
	 * 
	 * @return
	 */
	ResultType value() default ResultType.json;
}