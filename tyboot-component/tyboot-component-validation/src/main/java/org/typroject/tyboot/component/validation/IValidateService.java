package org.typroject.tyboot.component.validation;

import java.util.Map;

/**
 * 验证处理管理器
 * @author jimmysong
 *
 */
public interface IValidateService {
	/**
	 * 初始化
	 */
	public void init();
	/**
	 * 对某个对象执行按组验证操作
	 * @param object 执行对象
	 * @param groupName 验证组
	 * @return 返回验证结果，如果数量为0，则表示正确无误
	 */
	public Map<String,String> validate(Object object, String groupName);
}
