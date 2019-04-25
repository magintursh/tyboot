package org.typroject.tyboot.component.notification.sms;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaohelang on 2017/9/18.
 */
public class SmsTemplate implements Serializable{

       private HashMap<String,String> params;
       private String templateId;
       private String mobile;
       private String templateContent;
       private boolean multiMobile = false;
       private SmsType smsType;

    public SmsTemplate(SmsType smsType ,HashMap<String,String> params, String templateId, String mobile, String templateContent) {
        this.params = params;
        this.templateId = templateId;
        this.mobile = mobile;
        this.templateContent = templateContent;
        this.smsType = smsType;
    }

    public SmsTemplate(SmsType smsType ,HashMap<String,String> params, String templateId, String mobile, String templateContent, boolean multiMobile) {
        this.params = params;
        this.templateId = templateId;
        this.mobile = mobile;
        this.templateContent = templateContent;
        this.multiMobile = multiMobile;
        this.smsType = smsType;
    }


    public SmsType getSmsType() {
        return smsType;
    }

    public void setSmsType(SmsType smsType) {
        this.smsType = smsType;
    }


    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public boolean isMultiMobile() {
        return multiMobile;
    }

    public void setMultiMobile(boolean multiMobile) {
        this.multiMobile = multiMobile;
    }



    public static  String replaceContentPrams(String templateContent, Map<String,String> smsParams)
    {

        for(String key:smsParams.keySet())
        {
            if(templateContent.contains(key))
            {
                templateContent = templateContent.replaceAll("\\$\\{"+key+"\\}",smsParams.get(key));
            }
        }

        return templateContent;
    }




}