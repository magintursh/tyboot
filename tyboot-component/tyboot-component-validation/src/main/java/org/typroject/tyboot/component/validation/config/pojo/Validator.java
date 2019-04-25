package org.typroject.tyboot.component.validation.config.pojo;

public class Validator {
	private String name;
	private String className;
	private boolean useSpring;
	private String beanId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getBeanId() {
		return beanId;
	}
	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}
	public boolean getUseSpring() {
		return useSpring;
	}
	public void setUseSpring(boolean useSpring) {
		this.useSpring = useSpring;
	}
	
}
