package org.typroject.tyboot.api.controller.systemctl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.typroject.tyboot.api.face.systemctl.model.LocationInfoModel;
import org.typroject.tyboot.api.face.systemctl.service.LocationInfoService;
import org.typroject.tyboot.core.foundation.enumeration.UserType;
import org.typroject.tyboot.core.restful.doc.TycloudOperation;
import org.typroject.tyboot.core.restful.doc.TycloudResource;
import org.typroject.tyboot.core.restful.utils.ResponseHelper;
import org.typroject.tyboot.core.restful.utils.ResponseModel;

import java.util.List;


/**
 * Created by magintursh on 2017-05-03.
 */

@TycloudResource(name = "位置信息管理",module = "systemctl", resource = "location")
@RequestMapping(path = "/v1/systemctl/location")
@RestController
public class LocationResource {
    private final Logger logger = LogManager.getLogger(LocationResource.class);


    @Autowired
    LocationInfoService locationInfoService;


    @TycloudOperation( name = "获取所有省份信息",ApiLevel = UserType.ANONYMOUS,needAuth = false)
    @RequestMapping(value = "/provinces", method = RequestMethod.GET)
    public ResponseModel<List<LocationInfoModel>> getProvince() throws Exception {
        return ResponseHelper.buildResponse(locationInfoService.getByParent("0"));
    }


    @TycloudOperation(name = "获得省辖区所有城市",ApiLevel = UserType.ANONYMOUS)
    @RequestMapping(value = "/{provinceCode}/cities", method = RequestMethod.GET)
    public ResponseModel<List<LocationInfoModel>> getCitiesByProvince(
            @PathVariable String provinceCode) throws Exception {
        return ResponseHelper.buildResponse(locationInfoService.getByParent(provinceCode));
    }

    @TycloudOperation( name = "获得市辖区所有县区",ApiLevel = UserType.ANONYMOUS)
    @RequestMapping(value = "/{cityCode}/regions", method = RequestMethod.GET)
    public ResponseModel<List<LocationInfoModel>> getRegionsByCity(
            @PathVariable String cityCode) throws Exception {
        return ResponseHelper.buildResponse(locationInfoService.getByParent(cityCode));
    }

    @TycloudOperation(name = "根据位置编码获取位置信息",ApiLevel = UserType.ANONYMOUS,needAuth = false)
    @RequestMapping(value = "/{locationCode}", method = RequestMethod.GET)
    public ResponseModel<LocationInfoModel> getByCode(@PathVariable String locationCode)
            throws Exception {
        return ResponseHelper.buildResponse(locationInfoService.getByCode(locationCode));
    }

    @TycloudOperation( name = "获取所辖地区",ApiLevel = UserType.ANONYMOUS)
    @RequestMapping(value = "/{parentCode}/areas", method = RequestMethod.GET)
    public ResponseModel<List<LocationInfoModel>> getByParentCode(@PathVariable String parentCode)
            throws Exception {
        return ResponseHelper.buildResponse(locationInfoService.getByParent(parentCode));
    }

}