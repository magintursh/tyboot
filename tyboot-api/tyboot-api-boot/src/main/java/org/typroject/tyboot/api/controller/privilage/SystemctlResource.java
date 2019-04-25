package org.typroject.tyboot.api.controller.privilage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.restful.doc.TycloudOperation;
import org.typroject.tyboot.core.restful.doc.TycloudResource;
import org.typroject.tyboot.core.restful.utils.APILevel;
import org.typroject.tyboot.core.restful.utils.ResponseHelper;
import org.typroject.tyboot.core.restful.utils.ResponseModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by yaohelang on 2019/1/19.
 */


@TycloudResource(module = "systemctl", value = "systemctlresource")
@RequestMapping(path = "/v1/systemctl/systemctl")
@Api(value = "systemctl-系统信息")
@RestController
public class SystemctlResource  {


    private static WebApplicationContext appContext ;

    private static Map<String, HandlerMapping> allRequestMappings;

    @TycloudOperation(ApiLevel = APILevel.ALL, needAuth = false)
    @ApiOperation(value = "根据URL获取匹配到的HandlerMethod信息")
    @RequestMapping(value = "/handlermethod", method = RequestMethod.GET)
    public ResponseModel getHandlerMethod(@RequestParam String url,@RequestParam String requestMethod, HttpServletRequest request) throws Exception {

        //RequestToMethodItem item = null;
        String controllerMethodName = "";
        //请求url和处理方法的映射
        if(ValidationUtil.isEmpty(appContext))
            appContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());

        //获取所有的RequestMapping
        if(ValidationUtil.isEmpty(allRequestMappings))
            allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext,
                HandlerMapping.class, true, false);
        for (HandlerMapping handlerMapping : allRequestMappings.values()) {
            //本项目只需要RequestMappingHandlerMapping中的URL映射
            if (handlerMapping instanceof RequestMappingHandlerMapping) {
                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
                for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {
                    RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();

                    if(!requestMappingInfo.getMethodsCondition().getMethods().contains(RequestMethod.valueOf(requestMethod.toUpperCase())))
                        continue;

                    HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry.getValue();

                    PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
                    List<String> result = patternsCondition.getMatchingPatterns(url);
                    if (result.size() == 1) {
                        //String controllerName = mappingInfoValue.getBeanType().toString().replace("class", "").trim();
                        String requestMethodName = mappingInfoValue.getMethod().getName();
                        //Class<?>[] methodParamTypes = mappingInfoValue.getMethod().getParameterTypes();
                        //item = new RequestToMethodItem(controllerName, requestMethodName, methodParamTypes);
                        controllerMethodName = requestMethodName;
                        break;
                    }
                }
                break;

            }
        }


        return ResponseHelper.buildRespons(controllerMethodName);
    }


    class RequestToMethodItem {
        private String controllerName;
        private String requestMethodName;
        private Class<?>[] methodParamTypes;


        public RequestToMethodItem(String controllerName, String requestMethodName, Class<?>[] methodParamTypes) {
            this.controllerName = controllerName;
            this.requestMethodName = requestMethodName;
            this.methodParamTypes = methodParamTypes;
        }

        public String getControllerName() {
            return controllerName;
        }

        public void setControllerName(String controllerName) {
            this.controllerName = controllerName;
        }

        public String getRequestMethodName() {
            return requestMethodName;
        }

        public void setRequestMethodName(String requestMethodName) {
            this.requestMethodName = requestMethodName;
        }

        public Class<?>[] getMethodParamTypes() {
            return methodParamTypes;
        }

        public void setMethodParamTypes(Class<?>[] methodParamTypes) {
            this.methodParamTypes = methodParamTypes;
        }
    }


}
