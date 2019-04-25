package org.typroject.tyboot.component.validation.validators;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.typroject.tyboot.component.validation.IValidator;
import org.typroject.tyboot.component.validation.config.pojo.Rule;

import java.sql.Timestamp;

/**
 * 当前时间需要大于等于
 * @author jimmysong
 *
 */
public class TimestampCreaterEqualValidator implements IValidator {
	private static final Logger logger = LogManager.getLogger(TimestampCreaterEqualValidator.class);
	@SuppressWarnings("rawtypes")
	public boolean execute(Object context,Class type, Object value, Rule rule) {
		// TODO Auto-generated method stub
		if(value == null || !(value instanceof Timestamp)) return false;
		String toName = rule.getParameter("target");
		if(StringUtils.isBlank(toName)) {
			logger.warn("Creater And Equal target parameter missed");
			return false;
		}
		
		Object toValue = null;
		try {
			toValue = PropertyUtils.getProperty(context, toName);
			if(!(toValue instanceof Timestamp)) return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.warn("Creater And Equal target value missed , "+toName);
		}
		Timestamp timestamp = (Timestamp) value;
		Timestamp toTimestamp = (Timestamp) toValue;
		return timestamp.getTime() >= toTimestamp.getTime();
	}

}
