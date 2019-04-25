package org.typroject.tyboot.component.validation.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import org.typroject.tyboot.component.validation.IValidator;
import org.typroject.tyboot.component.validation.config.pojo.Configuration;
import org.typroject.tyboot.component.validation.config.pojo.Field;
import org.typroject.tyboot.component.validation.config.pojo.Group;
import org.typroject.tyboot.component.validation.config.pojo.Rule;
import org.typroject.tyboot.component.validation.config.pojo.Validator;

/**
 * 基础验证配置
 * @author jimmysong
 *
 */
public class BasicValidateConfig implements IValidateConfig {
	private static final Logger logger = LogManager.getLogger(BasicValidateConfig.class);
	public Configuration readConfiguration() {
		// TODO Auto-generated method stub
		Configuration configuratoin = new Configuration();
		configuratoin.setGroups(this.readGroups());
		configuratoin.setValidators(this.readValidators());
		return configuratoin;
	}
	
	/**
	 * 读取组配置
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Group> readGroups(){
		logger.info("read validation main file , "+this.getRules());
		Map<String,Group> groups = new HashMap<String,Group>();
		SAXReader reader = null;
		Document document = null;
		try {
			reader = new SAXReader();
			document = reader.read(BasicValidateConfig.class.getResource("/"+this.getRules()));
			Element root = document.getRootElement();
			//读取引入列表
			List<Element> includeList = root.elements("include");
			if(includeList != null && !includeList.isEmpty()) {
				Iterator<Element> il = includeList.iterator();
				while(il.hasNext()){
					Element includeE = il.next();
					String file = includeE.attributeValue("file");
					if(StringUtils.isBlank(file)) continue;
					this.readGroups(groups,file);
				}
			}
			this.readGroups(groups, root);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		} finally {
			document = null;
			reader = null;
		}
		return groups;
	}
	
	@SuppressWarnings("unchecked")
	private void readGroups(Map<String, Group> groups, String path) {
		// TODO Auto-generated method stub
		logger.info("read include validation file , "+path);
		SAXReader reader = null;
		Document document = null;
		try {
			reader = new SAXReader();
			document = reader.read(BasicValidateConfig.class.getResource("/"+path));
			Element root = document.getRootElement();
			//读取引入列表
			List<Element> includeList = root.elements("include");
			if(includeList != null && !includeList.isEmpty()) {
				Iterator<Element> il = includeList.iterator();
				while(il.hasNext()){
					Element includeE = il.next();
					String file = includeE.attributeValue("file");
					if(StringUtils.isBlank(file)) continue;
					this.readGroups(groups,file);
				}
			}
			this.readGroups(groups, root);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		} finally {
			document = null;
			reader = null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void readGroups(Map<String,Group> groups,Element root){
		//读取Group
		List<Element> groupList = root.elements("group");
		Iterator<Element> gl = groupList.iterator();
		while(gl.hasNext()){
			Element groupE = gl.next();
			String gname = groupE.attributeValue("name").trim();
			
			//读取字段
			List<Field> fields = new ArrayList<Field>();
			List<Element> fieldList = groupE.elements("field");
			Iterator<Element> fl = fieldList.iterator();
			while(fl.hasNext()){
				Element fieldE = fl.next();
				String fname = fieldE.attributeValue("name").trim();
				Field field = new Field();
				field.setName(fname);
				
				//读取规则
				List<Rule> rules = new ArrayList<Rule>();
				List<Element> ruleList = fieldE.elements("rule");
				Iterator<Element> rl = ruleList.iterator();
				while(rl.hasNext()){
					Element ruleE = rl.next();
					String rname = ruleE.attributeValue("name").trim();
					String rmessage = ruleE.elementTextTrim("message");
					rmessage = rmessage == null? ruleE.attributeValue("message"):rmessage;
					Rule rule = new Rule();
					rule.setName(rname);
					rule.setMessage(rmessage);
					
					List<Element> params = ruleE.elements("param");
					Iterator<Element> ps = params.iterator();
					while(ps.hasNext()){
						Element paramE = ps.next();
						String pname = paramE.attributeValue("name").trim();
						String pvalue = paramE.elementTextTrim("value");
						pvalue = pvalue == null ? paramE.attributeValue("value").trim() : pvalue;
						rule.addParameter(pname, pvalue);
					}
					
					rules.add(rule);
				}
				
				field.setRules(rules);
				fields.add(field);
			}
			Group group = new Group();
			group.setName(gname);
			group.setFields(fields);
			groups.put(gname,group);
		}
	}
	
	/**
	 * 读取验证器
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,IValidator> readValidators(){
		Map<String,IValidator> validators = new HashMap<String,IValidator>();
		SAXReader reader = null;
		Document document = null;
		try {
			reader = new SAXReader();
			document = reader.read(BasicValidateConfig.class.getResource("/"+this.getValidators()));
			Element root = document.getRootElement();
			List<Element> validatorsList = root.elements("validator");
			Iterator<Element> vl = validatorsList.iterator();
			while(vl.hasNext()){
				Element validatorE = vl.next();
				String vname = validatorE.attributeValue("name").trim();
				String vclass = validatorE.attributeValue("class").trim();
				String vbeanId = validatorE.attributeValue("beanId");
				String useSpring = validatorE.attributeValue("useSpring");
				boolean vuseSpring = Boolean.parseBoolean(useSpring);
				Validator v = new Validator();
				v.setName(vname);
				v.setClassName(vclass);
				v.setBeanId(vbeanId);
				v.setUseSpring(vuseSpring);
				try {
					IValidator validator = this.instanceValidator(v);
					validators.put(vname, validator);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					continue;
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					continue;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					continue;
				} finally {
					v = null;
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		} finally {
			document = null;
			reader = null;
		}
		return validators;
	}
	
	/**
	 * 实例化验证器
	 * @param v 验证对象
	 * @return 验证器
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("unchecked")
	public IValidator instanceValidator(Validator v) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<IValidator> vclassor = (Class<IValidator>) Class.forName(v.getClassName());
		IValidator validator = vclassor.newInstance();
		return validator;
	}
	
	private String validators,rules;
	public BasicValidateConfig(){
		this("validators.fo.xml","rules.fo.xml");
	}
	
	public BasicValidateConfig(String validators, String rules) {
		super();
		this.validators = validators;
		this.rules = rules;
	}

	public String getValidators() {
		return validators;
	}

	public void setValidators(String validators) {
		this.validators = validators;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}
	
}
