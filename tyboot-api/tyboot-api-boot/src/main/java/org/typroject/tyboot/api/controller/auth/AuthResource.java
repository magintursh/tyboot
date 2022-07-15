package org.typroject.tyboot.api.controller.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.typroject.tyboot.core.auth.authentication.LoginAuthenticator;
import org.typroject.tyboot.core.auth.enumeration.AuthType;
import org.typroject.tyboot.core.auth.enumeration.IdType;
import org.typroject.tyboot.core.auth.enumeration.ProvidedAuthType;
import org.typroject.tyboot.core.auth.face.model.AuthModel;
import org.typroject.tyboot.core.auth.face.model.IdPasswordAuthModel;
import org.typroject.tyboot.core.auth.face.model.LoginInfoModel;
import org.typroject.tyboot.core.auth.face.model.SmsAuthModel;
import org.typroject.tyboot.core.foundation.enumeration.UserType;
import org.typroject.tyboot.core.restful.doc.TycloudOperation;
import org.typroject.tyboot.core.restful.doc.TycloudResource;
import org.typroject.tyboot.core.restful.utils.ResponseHelper;
import org.typroject.tyboot.core.restful.utils.ResponseModel;

import java.util.HashMap;

/**
 * privilege-登录验证
 */
@TycloudResource(name = "privilege-登录验证",module = "privilege", resource = "AuthResource")
@RequestMapping(value = "/v1/privilege/auth")
@RestController
public class AuthResource {
    private final Logger logger = LogManager.getLogger(AuthResource.class) ;


    @Autowired
    LoginAuthenticator loginAuthenticator;


    /**
     * 公网用户名密码登录
     * @param model
     * @return
     * @throws Exception
     */
    @TycloudOperation(name = "公网用户名密码登录",ApiLevel = UserType.ANONYMOUS,needAuth = false)
    @RequestMapping(value = "/public/idpassword", method = RequestMethod.POST)
    public ResponseModel<LoginInfoModel> idPasswordAuthForPublic(@RequestBody IdPasswordAuthModel model)
    {
        return  this.doAuthenticate(IdType.userName, AuthType.ID_PASSWORD, UserType.PUBLIC,model);
    }

    /**
     * 机构用户名密码登录
     * @param model
     * @return
     */
    @TycloudOperation( name = "机构用户名密码登录",ApiLevel = UserType.ANONYMOUS,needAuth = false)
    @RequestMapping(value = "/agency/idpassword", method = RequestMethod.POST)
    public ResponseModel<LoginInfoModel> idPasswordAuthForAgency(@RequestBody IdPasswordAuthModel model)
    {
        return  this.doAuthenticate(IdType.userName, AuthType.ID_PASSWORD, UserType.AGENCY,model);
    }

    /**
     * 平台用户名密码登录
     * @param model
     * @return
     */
    @TycloudOperation( name = "平台用户名密码登录",ApiLevel = UserType.ANONYMOUS,needAuth = false)
    @RequestMapping(value = "/super/idpassword", method = RequestMethod.POST)
    public ResponseModel<LoginInfoModel> idPasswordAuthForSuper(@RequestBody IdPasswordAuthModel model)
    {
        return  this.doAuthenticate(IdType.userName, AuthType.ID_PASSWORD, UserType.SUPER_ADMIN,model);
    }


    /**
     * 短信登录
     * @param model
     * @return
     */
    @TycloudOperation(name = "短信登录", ApiLevel = UserType.ANONYMOUS,needAuth = false)
    @RequestMapping(value = "/sms", method = RequestMethod.POST)
    public ResponseModel<LoginInfoModel> smsAuth(@RequestBody SmsAuthModel model)
    {
        return null;
    }


    /**
     * 第三方登录
     * @param model
     * @return
     */
    @TycloudOperation( name = "第三方登录",ApiLevel = UserType.ANONYMOUS,needAuth = false)
    @RequestMapping(value = "/openId", method = RequestMethod.POST)
    public ResponseModel<LoginInfoModel> thirdPartyAuth(@RequestBody IdPasswordAuthModel model)
    {
        return null;
    }


    /**
     * 匿名用户登录
     * @param model
     * @return
     * @throws Exception
     */
    @TycloudOperation(name = "匿名用户登录", ApiLevel = UserType.ANONYMOUS,needAuth = false)
    @RequestMapping(value = "/anonymous", method = RequestMethod.POST)
    public ResponseModel<LoginInfoModel> anonymousAuth(@RequestBody IdPasswordAuthModel model)
    {
        return null;
    }


    private ResponseModel doAuthenticate(IdType idType, ProvidedAuthType authType, UserType userType, AuthModel authModel)
    {
        HashMap<String, Object> result = loginAuthenticator.authLogin(idType, authType, userType, authModel);
        return ResponseHelper.buildResponse(result);
    }










}