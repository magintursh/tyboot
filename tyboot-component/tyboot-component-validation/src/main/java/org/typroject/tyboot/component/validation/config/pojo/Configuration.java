package org.typroject.tyboot.component.validation.config.pojo;

import java.util.HashMap;
import java.util.Map;

import org.typroject.tyboot.component.validation.IValidator;

/**
 * 配置类
 * @author jimmysong
 *
 */
public class Configuration {
	/**
	 * 组映射 
	 */
	private Map<String,Group> groups;
	/**
	 * 验证器映射
	 */
	private Map<String,IValidator> validators;
	
	/**
	 * 添加验证组
	 * @param name 组名
	 * @param group 组对象
	 */
	public void addGroup(String name, Group group){
		this.groups.put(name, group);
	}
	
	/**
	 * 获得验证组
	 * @param name 验证组名称
	 * @return 验证组对象
	 */
	public Group getGroup(String name){
		return this.groups.get(name);
	}
	
	/**
	 * 添加验证器
	 * @param name 验证器名称
	 * @param validator 验证器对象
	 */
	public void addValidator(String name,IValidator validator){
		this.validators.put(name, validator);
	}
	
	/**
	 * 获得验证器对象
	 * @param name 验证器名称
	 * @return 验证器对象
	 */
	public IValidator getValidator(String name){
		return this.validators.get(name);
	}
	
	public Configuration() {
		// TODO Auto-generated constructor stub
		this.groups = new HashMap<String,Group>();
		this.validators = new HashMap<String,IValidator>();
	}
	
	public Map<String, IValidator> getValidators() {
		return validators;
	}

	public void setValidators(Map<String, IValidator> validators) {
		this.validators = validators;
	}

	public Map<String, Group> getGroups() {
		return groups;
	}

	public void setGroups(Map<String, Group> groups) {
		this.groups = groups;
	}
}
