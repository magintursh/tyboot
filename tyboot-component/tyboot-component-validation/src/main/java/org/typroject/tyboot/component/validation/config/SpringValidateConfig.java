package org.typroject.tyboot.component.validation.config;
 
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import org.typroject.tyboot.component.validation.IValidator;
import org.typroject.tyboot.component.validation.config.pojo.Validator;

/**
 * 基础验证配置
 * @author jimmysong
 *
 */
public class SpringValidateConfig extends BasicValidateConfig implements ApplicationContextAware {
	@SuppressWarnings("rawtypes")
	@Override
	public IValidator instanceValidator(Validator v)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		String className = v.getClassName();
		String beanId = v.getBeanId();
		boolean useSpring = v.getUseSpring();
		
		if(!useSpring) return super.instanceValidator(v);
		
		Class clazz = null;
		IValidator validator = null;
		
		if(StringUtils.isBlank(beanId)) {
			clazz = Class.forName(className);
			validator = (IValidator)this.getApplicationContext().getBean(clazz);
		} else {
			validator = (IValidator) this.getApplicationContext().getBean(beanId);
		}
		return validator;
	}
	private ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
}
