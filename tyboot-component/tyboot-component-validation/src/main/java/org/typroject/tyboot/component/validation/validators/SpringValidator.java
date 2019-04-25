package org.typroject.tyboot.component.validation.validators;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.typroject.tyboot.component.validation.IValidator;
import org.typroject.tyboot.component.validation.config.pojo.Rule;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过spring的方式进行验证
 * @author jimmysong
 *
 */
public class SpringValidator implements IValidator,ApplicationContextAware{
	private static final Logger logger = LogManager.getLogger(SpringValidator.class);
	@SuppressWarnings("rawtypes")
	public boolean execute(Object context,Class type, Object value, Rule rule) {
		// TODO Auto-generated method stub
		if(value == null) return false;
		Object parameter = value;
		
		String beanName = rule.getParameter("beanName");
		String methodName = rule.getParameter("methodName");
		String parameterName = rule.getParameter("parameter");
		try {
			parameter = this.makeParameter(parameterName, context, value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("The parameter missed , "+e.getMessage());
			return false;
		}
		if(this.applicationContext == null) {
			logger.error("The spring application context is not be instanced.");
			return false;
		}
		Object object = this.applicationContext.getBean(beanName);
		if(object == null) {
			logger.error("Can not load the spring bean named "+beanName);
			return false;
		}
		Method method = MethodUtils.getAccessibleMethod(object.getClass(), methodName, parameter.getClass());
		if(method == null) {
			logger.error("Can not find the method from spring bean, which named "+methodName);
			return false;
		}
		Class returnType = method.getReturnType();
		if(returnType != boolean.class){
			logger.error("The return type of the spring bean that named \""+beanName+"\" is not boolean.");
			return false;
		}
		boolean returnValue = false;
		try {
			returnValue = (Boolean) method.invoke(object, parameter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("The method invoke error for message "+e.getMessage());
			return false;
		}
		return returnValue;
	}

	/**
	 * @param parameterName
	 * @param context
	 * @param value
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private Object makeParameter(String parameterName,Object context,Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Object parameter = value;
		if(parameterName != null) parameterName = parameterName.trim();
		if(StringUtils.isBlank(parameterName)) parameter = value;
		else if("this".equals(parameterName)) parameter = context;
		else {
			parameter = PropertyUtils.getProperty(context, parameterName);
		}
		return parameter;
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
