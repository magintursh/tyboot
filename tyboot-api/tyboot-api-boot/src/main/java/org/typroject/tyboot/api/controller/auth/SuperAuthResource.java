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
import org.typroject.tyboot.core.foundation.enumeration.UserType;
import org.typroject.tyboot.core.restful.doc.TycloudOperation;
import org.typroject.tyboot.core.restful.doc.TycloudResource;
import org.typroject.tyboot.core.restful.utils.ResponseHelper;
import org.typroject.tyboot.core.restful.utils.ResponseModel;

import java.util.HashMap;


/**
 * Created by magintursh on 2017-05-03.
 */
@TycloudResource(name = "auth-超级管理员登录验证",module = "auth", resource = "super")
@RequestMapping(value = "/v1/auth/super")
@RestController
public class SuperAuthResource {
    private final Logger logger = LogManager.getLogger(SuperAuthResource.class) ;


    @Autowired
    LoginAuthenticator loginAuthenticator;


    /**
     *
     * @param model
     * @return
     * @throws Exception
     */
    @TycloudOperation( name = "平台用户名密码登录",ApiLevel = UserType.ANONYMOUS,needAuth = false)
    @RequestMapping(value = "/super/idpassword", method = RequestMethod.POST)
    public ResponseModel<LoginInfoModel> idPasswordAuthForSuper(@RequestBody IdPasswordAuthModel model) throws Exception
    {
        return  this.doAuthenticate(IdType.userName, AuthType.ID_PASSWORD, UserType.SUPER_ADMIN,model);
    }



    private ResponseModel doAuthenticate(IdType idType, ProvidedAuthType authType, UserType userType, AuthModel authModel) throws Exception
    {
        HashMap<String, Object> result = loginAuthenticator.authLogin(idType, authType, userType, authModel);
        return ResponseHelper.buildResponse(result);
    }










}