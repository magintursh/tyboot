package org.typroject.tyboot.api.controller.systemctl;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.typroject.tyboot.api.face.systemctl.model.DictionarieModel;
import org.typroject.tyboot.component.activemq.ActiveMqConfig;
import org.typroject.tyboot.component.activemq.JMSSender;
import org.typroject.tyboot.component.activemq.JmsMessage;
import org.typroject.tyboot.core.restful.doc.TycloudOperation;
import org.typroject.tyboot.core.restful.doc.TycloudResource;
import org.typroject.tyboot.core.restful.utils.APILevel;
import org.typroject.tyboot.core.restful.utils.ResponseHelper;
import org.typroject.tyboot.core.restful.utils.ResponseModel;

@TycloudResource(module = "systemctl",value = "JmsTestResource")
@RequestMapping(value = "/v1/systemctl/jms")
@Api(value = "systemctl-字典管理")
@RestController
public class JmsTestResource {


    @Autowired
    JMSSender jmsSender;


    @TycloudOperation( ApiLevel = APILevel.ALL ,needAuth =  false)
    @ApiOperation(value="jmsTest")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseModel<DictionarieModel> createDictionary(@RequestBody DictionarieModel dictionaryModel) throws Exception
    {
        JmsMessage message  =  ActiveMqConfig.buildMessage(ActiveMqConfig.DEFAULT_QUEUE,"testJmsHandler","测试消息");
        jmsSender.sendQueueMessage(message);
        return ResponseHelper.buildResponse(dictionaryModel);
    }
}
