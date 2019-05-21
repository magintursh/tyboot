package org.typroject.tyboot.api.controller.systemctl;

import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.typroject.tyboot.core.restful.doc.TycloudResource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 子杨
 * @since 2018-12-04
 */

@RestController
@TycloudResource(module = "systemctl",value = "operationrecord")
@RequestMapping(value = "/v1/systemctl/operationrecord")
@Api(value = "systemctl-操作计数")
public class OperationRecordResource {

        private final Logger logger = LogManager.getLogger(FeedbackResource.class);

}
