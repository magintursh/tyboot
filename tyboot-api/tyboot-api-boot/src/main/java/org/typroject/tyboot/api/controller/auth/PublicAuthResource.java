package org.typroject.tyboot.api.controller.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.typroject.tyboot.core.auth.authentication.LoginAuthenticator;
import org.typroject.tyboot.core.auth.enumeration.IdType;
import org.typroject.tyboot.core.auth.enumeration.ProvidedAuthType;
import org.typroject.tyboot.core.auth.face.model.AuthModel;
import org.typroject.tyboot.core.auth.face.model.IdPasswordAuthModel;
import org.typroject.tyboot.core.auth.face.model.LoginInfoModel;
import org.typroject.tyboot.core.foundation.enumeration.UserType;
import org.typroject.tyboot.core.restful.doc.TycloudOperation;
import org.typroject.tyboot.core.restful.doc.TycloudResource;
import org.typroject.tyboot.core.restful.utils.APILevel;
import org.typroject.tyboot.core.restful.utils.ResponseHelper;
import org.typroject.tyboot.core.restful.utils.ResponseModel;

import java.util.HashMap;


/**
 * Created by magintursh on 2017-05-03.
 */
@TycloudResource(module = "auth",value = "public")
@RequestMapping(value = "/v1/auth/public")
@Api(value = "privilege-第三方授权验证")
@RestController
public class PublicAuthResource {
    private final Logger logger = LogManager.getLogger(PublicAuthResource.class) ;


    @Autowired
    LoginAuthenticator loginAuthenticator;




    @TycloudOperation( ApiLevel = APILevel.ALL,needAuth = false)
    @ApiOperation(value="第三方登录")
    @RequestMapping(value = "/openId", method = RequestMethod.POST)
    public ResponseModel<LoginInfoModel> thirdPartyAuth(@RequestBody IdPasswordAuthModel model) throws Exception
    {
        return null;
    }

    private ResponseModel doAuthenticate(IdType idType, ProvidedAuthType authType, UserType userType, AuthModel authModel) throws Exception
    {
        HashMap<String, Object> result = loginAuthenticator.authLogin(idType, authType, userType, authModel);
        return ResponseHelper.buildResponse(result);
    }










}