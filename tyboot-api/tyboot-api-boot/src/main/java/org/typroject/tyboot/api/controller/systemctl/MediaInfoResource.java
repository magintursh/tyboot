package org.typroject.tyboot.api.controller.systemctl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.typroject.tyboot.api.face.systemctl.service.MediaInfoService;
import org.typroject.tyboot.core.foundation.enumeration.UserType;
import org.typroject.tyboot.core.foundation.utils.StringUtil;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.restful.doc.TycloudOperation;
import org.typroject.tyboot.core.restful.doc.TycloudResource;
import org.typroject.tyboot.core.restful.exception.instance.BadRequest;
import org.typroject.tyboot.core.restful.utils.ResponseHelper;
import org.typroject.tyboot.core.restful.utils.ResponseModel;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 多媒体信息关联 前端控制器
 * </p>
 *
 * @author 子杨
 * @since 2018-12-04
 */

@RestController
@TycloudResource(name = "多媒体信息关联",module = "systemctl", resource = "mediainfo")
@RequestMapping(value = "/v1/systemctl/mediainfo")
public class MediaInfoResource {

    private final Logger logger = LogManager.getLogger(FeedbackResource.class);

    @Autowired
    private MediaInfoService mediaInfoService;

    @TycloudOperation( operation = "删除媒体文件信息",ApiLevel = UserType.PUBLIC)
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public ResponseModel deleteMediaInfo(@PathVariable String ids) throws Exception
    {
        List<Long> seqs = StringUtil.String2LongList(ids);
        return ResponseHelper.buildResponse(mediaInfoService.deleteMediaInfo(seqs));
    }


    @TycloudOperation( operation = "封禁指定图片",ApiLevel = UserType.PUBLIC)
    @RequestMapping(value = "/media/disabled", method = RequestMethod.PUT)
    public ResponseModel disabled(@RequestBody Map<String,String> map) throws Exception
    {
        if(ValidationUtil.isEmpty(map) || ValidationUtil.isEmpty(map.get("url")))
            throw new BadRequest("操作失败.");
        return ResponseHelper.buildResponse(this.mediaInfoService.disabledMedia(map.get("url")));
    }

	
}
