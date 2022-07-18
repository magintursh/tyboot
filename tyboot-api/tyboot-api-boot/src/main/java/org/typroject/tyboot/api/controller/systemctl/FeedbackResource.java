package org.typroject.tyboot.api.controller.systemctl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.typroject.tyboot.api.face.systemctl.model.FeedbackModel;
import org.typroject.tyboot.api.face.systemctl.service.FeedbackService;
import org.typroject.tyboot.core.foundation.context.RequestContext;
import org.typroject.tyboot.core.foundation.enumeration.UserType;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.restful.doc.TycloudOperation;
import org.typroject.tyboot.core.restful.doc.TycloudResource;
import org.typroject.tyboot.core.restful.exception.instance.BadRequest;
import org.typroject.tyboot.core.restful.utils.ResponseHelper;
import org.typroject.tyboot.core.restful.utils.ResponseModel;

/**
 * <p>
 * 用户反馈 前端控制器
 * </p>
 *
 * @author 子杨
 * @since 2017-12-01
 */

@RestController
@TycloudResource(name = "用户反馈",module = "systemctl", resource = "feedback")
@RequestMapping(value = "/v1/systemctl/feedback")
public class FeedbackResource {


    private final Logger logger = LogManager.getLogger(FeedbackResource.class);
    @Autowired
    private FeedbackService feedbackService;


    @TycloudOperation(name = "创建",ApiLevel = UserType.PUBLIC)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseModel<FeedbackModel> create(@RequestBody FeedbackModel model) throws Exception {
        if (ValidationUtil.isEmpty(model) || ValidationUtil.isEmpty(model.getContent()))
            throw new BadRequest("参数校验失败");

        model.setUserId(RequestContext.getExeUserId());
        model = feedbackService.createWithModel(model);
        return ResponseHelper.buildResponse(model);
    }


    @TycloudOperation(name = "删除",ApiLevel = UserType.PUBLIC)
    @RequestMapping(value = "/{sequenceNbr}", method = RequestMethod.DELETE)
    public ResponseModel create(@PathVariable Long  sequenceNbr) throws Exception {
        return ResponseHelper.buildResponse(feedbackService.deleteBySeq(sequenceNbr));
    }


    /**
     * 分页查询
     * @param contact
     * @param userId
     * @param current
     * @param size
     * @return
     * @throws Exception
     */
    @TycloudOperation(name = "分页查询",ApiLevel = UserType.PUBLIC)
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseModel<Page> queryForPage(
            @RequestParam(value = "contact", required = false) String contact,
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "current") int current,
            @RequestParam(value = "size") int size) throws Exception {

        Page page = new Page(current,size);
        return ResponseHelper.buildResponse(feedbackService.queryForfeedPage(page, userId, contact));
    }


}
