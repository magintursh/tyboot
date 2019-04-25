package org.typroject.tyboot.component.validation;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.typroject.tyboot.component.validation.config.IValidateConfig;
import org.typroject.tyboot.component.validation.config.pojo.Configuration;
import org.typroject.tyboot.component.validation.config.pojo.Field;
import org.typroject.tyboot.component.validation.config.pojo.Group;
import org.typroject.tyboot.component.validation.config.pojo.Rule;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础验证服务
 * @author jimmysong
 *
 */
public class BasicValidateService implements IValidateService {
	private static final Logger logger = LogManager.getLogger(BasicValidateService.class);
	public void init() {
		// TODO Auto-generated method stub
		//读取配置
		this.configuration = config.readConfiguration();
		
		//设定已经被初始化
		this.isInited = true;
	}
	@SuppressWarnings("rawtypes")
	public Map<String, String> validate(Object object, String groupName) {
		// TODO Auto-generated method stub
		
		//判断是否被初始化
		if(!this.isInited) this.init();
		
		Map<String,String> results = new LinkedHashMap<String,String>();
		if(object == null) {return results;}
		
		Group group = this.configuration.getGroup(groupName);
		if(group == null) {return results;}
		
		Map<String,IValidator> validators = this.configuration.getValidators();
		if(validators == null || validators.isEmpty()) return results;
		
		List<Field> fields = group.getFields();
		if(fields == null || fields.isEmpty()) return results;
		Iterator<Field> fs = fields.iterator();
		while(fs.hasNext()){
			Field field = fs.next();
			String fname = field.getName();
			Object value = null;
			Class type = null;
			List<Rule> rules = field.getRules();
			if(rules == null || rules.isEmpty()) continue;
			try {
				value = PropertyUtils.getProperty(object, fname);
				type = PropertyUtils.getPropertyType(object, fname);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.warn(e.getMessage());
			} finally {
				Iterator<Rule> rs = rules.iterator();
				while(rs.hasNext()){
					Rule rule = rs.next();
					String rname = rule.getName();
					IValidator validator = validators.get(rname);
					if(validator == null) continue;
					boolean r = validator.execute(object,type,value,rule);
					if(!r) {
						results.put(fname, rule.getMessage());
						break;
					}
				}
			}
		}
		return results;
	}
	
	private Configuration configuration;
	private IValidateConfig config;
	private boolean isInited;
	public BasicValidateService() {
		// TODO Auto-generated constructor stub
	}
	public BasicValidateService(IValidateConfig config) {
		// TODO Auto-generated constructor stub
		this.setConfig(config);
	}
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	public IValidateConfig getConfig() {
		return config;
	}
	public void setConfig(IValidateConfig config) {
		this.config = config;
	}
	
}
