package org.typroject.tyboot.api.face.systemctl.service;

import org.springframework.stereotype.Service;
import org.typroject.tyboot.api.face.systemctl.model.LocationInfoModel;
import org.typroject.tyboot.api.face.systemctl.orm.dao.LocationInfoMapper;
import org.typroject.tyboot.api.face.systemctl.orm.entity.LocationInfo;
import org.typroject.tyboot.component.cache.Redis;
import org.typroject.tyboot.component.cache.enumeration.CacheType;
import org.typroject.tyboot.core.rdbms.service.BaseService;

import java.util.List;

/**
 * Created by magintursh on 2017-06-21.
 */
@Service("locationInfoService")
public class LocationInfoService extends BaseService<LocationInfoModel,LocationInfo,LocationInfoMapper> {



    public static final String LOCATIONINFO_LIST="LOCATIONINFO_LIST";



    public static final String cacheKeyForLocationList(String parentCode)
    {
        return Redis.genKey(CacheType.ERASABLE.name(),LOCATIONINFO_LIST,parentCode);
    }


    public List<LocationInfoModel> getByParent(String  parentCode) throws Exception
    {
       return this.queryForListWithCache(cacheKeyForLocationList(parentCode),null,false,parentCode);
    }


    public LocationInfoModel getByCode(String  locationCode) throws Exception
    {
        return queryModelByParamsWithCache(locationCode,locationCode);
    }




}
