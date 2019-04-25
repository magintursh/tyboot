package org.typroject.tyboot.component.validation;

import org.typroject.tyboot.component.validation.config.pojo.Rule;

/**
 * 校验框架中的执行接口
 * 该接口为重要接口，处理每个验证的处理方法
 * @author jimmysong
 */
public interface IValidator {
	
	/**
	 * 要处理的对象
	 * @param context 上下文
	 * @param type 对象类型
	 * @param value 对象值
	 * @param rule 对象参数
	 * @return 是否成功
	 */
	@SuppressWarnings("rawtypes")
	public boolean execute(Object context, Class type, Object value, Rule rule);
}
