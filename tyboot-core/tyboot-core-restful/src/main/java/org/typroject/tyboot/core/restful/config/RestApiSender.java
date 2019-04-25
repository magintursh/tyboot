package org.typroject.tyboot.core.restful.config;

import com.baomidou.mybatisplus.core.toolkit.Sequence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.typroject.tyboot.core.foundation.context.RequestContext;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.restful.utils.ResponseModel;
import org.typroject.tyboot.core.restful.utils.RestfulConstans;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class RestApiSender {


    private final Logger logger =  LogManager.getLogger(RestApiSender.class);


    private RestTemplate restTemplate;



    private RedisTemplate redisTemplate;



    private LoadBalancerClient loadBalancerClientl;




    private String serverName;


    private  static Sequence sequence = new Sequence();

    private static List<String>  contentType;
    private static List<String>  product;

    private static HttpHeaders httpHeaders;


    public RestApiSender (RestTemplate restTemplate,RedisTemplate redisTemplate,LoadBalancerClient loadBalancerClient,String serverName)
    {
        this.restTemplate = restTemplate;
        this.redisTemplate= redisTemplate;
        this.loadBalancerClientl = loadBalancerClient;
        this.serverName = serverName;
    }






    static {
        httpHeaders = new HttpHeaders();
        contentType = new ArrayList<>();
        product     = new ArrayList<>();
        contentType.add("application/json");
        product.add("INTERNAL_CALLS");
        httpHeaders.put("Content-Type",contentType);
        httpHeaders.put("product",product);
    }


    /**
     * 发送远程调用请求
     * @param apiCode
     * @param serverName
     * @param pathParams
     * @param requestParams
     * @param body
     * @return
     * @throws Exception
     */
    public Object exchange(String apiCode, String serverName, Map<String,String> pathParams,Map<String,String> requestParams,Map<String,Object> body) throws Exception
    {
        Object returnObj = null;
        ServiceInstance serviceInstance = loadBalancerClientl.choose(serverName);

        TyApiInfo tyApiInfo = (TyApiInfo)redisTemplate.opsForHash().get(TyApiInfo.getCacheKeyForTyApiInfoOfHash(),apiCode);

        if(!ValidationUtil.isEmpty(tyApiInfo))
        {
            String url = this.getUrl(tyApiInfo,pathParams,requestParams,serviceInstance.getHost(),serviceInstance.getPort());


            RequestEntity requestEntity = new RequestEntity(body,  httpHeaders, tyApiInfo.getMethod(), new URI(url));
            requestEntity.getHeaders().add(RestfulConstans.TOKEN, RequestContext.getToken());
            ResponseEntity<ResponseModel> responseEntity  = restTemplate.exchange(requestEntity,ResponseModel.class);

            returnObj = this.analysisResult(responseEntity);

        }
        return returnObj;
    }



    public Object exchange(String apiCode, String serverName, ApiPrams apiPrams) throws Exception
    {
        return this.exchange(apiCode,serverName,apiPrams.getPathParmas(),apiPrams.getRequestParmas(),apiPrams.getRequestBody());
    }


    private Object analysisResult(ResponseEntity<ResponseModel> responseEntity) throws Exception
    {
        Object returnObj = null;
        if(responseEntity.getStatusCode().value() == 200)
        {
            ResponseModel responseModel = responseEntity.getBody();
            if(responseModel.getStatus() == 200)
            {
                returnObj = responseModel.getResult();
            }
           else{
                logger.error("message:"+responseModel.getMessage()+";devMessage:"+responseModel.getDevMessage());
                throw  new Exception("远程调用出错: "+ responseModel.getMessage());
            }
        }else{
            logger.error("远程调用发生未知异常.");
            throw new Exception("远程调用发生未知异常.");
        }
        return returnObj;
    }



    private String getUrl(TyApiInfo tyApiInfo ,Map<String,String> pathParams,Map<String,String> requestParams,String host,int port)
    {
        String url ="http://"+host+":"+port+tyApiInfo.getUrl();

        //处理地址参数
        if(!ValidationUtil.isEmpty(tyApiInfo.getUriParamsName())
                &&  !ValidationUtil.isEmpty(pathParams)
                && tyApiInfo.getUriParamsName().size() == pathParams.size())
        {
            for(String paramName:tyApiInfo.getUriParamsName())
            {
                url = url.replaceAll("\\{"+paramName+"}",pathParams.get(paramName));
            }

        }

        //处理查询条件参数

        if(!ValidationUtil.isEmpty(requestParams)
                && ValidationUtil.isEmpty(tyApiInfo.getRequestParamsName())
                && tyApiInfo.getRequestParamsName().size() == requestParams.size())
        {
            url = url+"?";
            for(String paramName:requestParams.keySet())
            {
                url = url +paramName+"=" +requestParams.get(paramName)+"&";
            }
            url  = url +"_rd="+String.valueOf(sequence.nextId());
        }

        return url;
    }









}
