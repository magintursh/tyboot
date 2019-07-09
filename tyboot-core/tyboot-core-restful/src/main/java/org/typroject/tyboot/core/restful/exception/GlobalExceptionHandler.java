package org.typroject.tyboot.core.restful.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.typroject.tyboot.core.foundation.exception.BaseException;
import org.typroject.tyboot.core.restful.utils.ResponseHelper;
import org.typroject.tyboot.core.restful.utils.ResponseModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by magintursh on 2017-07-05.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    private static Boolean alwaysOk = true;//http返回码永远都是200


    @ExceptionHandler(value = {Exception.class,BaseException.class,RuntimeException.class,Throwable.class})
    @ResponseBody
    public ResponseModel<String> jsonErrorHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
        ResponseModel responseModel = ResponseHelper.buildResponse("");
        responseModel.setUrl(req.getRequestURL().toString());
        if(e instanceof BaseException)
        {
            BaseException baseException = (BaseException)e;
            responseModel.setStatus(baseException.getHttpStatus());
            responseModel.setMessage(baseException.getMessage());
            responseModel.setDevMessage(baseException.getDevMessage());
            logger.error(baseException.getDevMessage());
            logger.error(e.getMessage(),e);
            response.setStatus(baseException.getHttpStatus());
        }else{
            responseModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseModel.setDevMessage(e.getMessage());
            responseModel.setMessage("未知错误,请联系管理员.");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

            logger.error(e.getMessage(),e);
        }

        if(alwaysOk)
            response.setStatus(HttpStatus.OK.value());//http返回码永远都是200，真是的返回码见responseBody中的status字段

        return responseModel;
    }

    public static Boolean getAlwaysOk() {
        return alwaysOk;
    }

    public static void setAlwaysOk(Boolean flag) {
        alwaysOk = flag;
    }
}