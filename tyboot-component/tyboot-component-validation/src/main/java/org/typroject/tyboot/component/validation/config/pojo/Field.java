package org.typroject.tyboot.component.validation.config.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证字段
 * @author jimmysong
 *
 */
public class Field {
	/**
	 * 验证字段的名称
	 */
	private String name;
	/**
	 * 验证规则列表
	 */
	private List<Rule> rules;
	
	/**
	 * 添加规则，暂不不处理去重
	 * @param rule 规则
	 */
	public void addRule(Rule rule){
		rules.add(rule);
	}
	
	public Field() {
		// TODO Auto-generated constructor stub
		this.rules = new ArrayList<Rule>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[ name="+name+", rules="+rules+" ]";
	}
}
