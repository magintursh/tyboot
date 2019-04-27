package org.typroject.tyboot.api.face.systemctl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.typroject.tyboot.api.face.systemctl.model.DictionarieModel;
import org.typroject.tyboot.api.face.systemctl.orm.dao.DictionarieMapper;
import org.typroject.tyboot.api.face.systemctl.orm.entity.Dictionarie;
import org.typroject.tyboot.component.cache.Redis;
import org.typroject.tyboot.component.cache.enumeration.CacheType;
import org.typroject.tyboot.core.foundation.utils.Bean;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.rdbms.annotation.Condition;
import org.typroject.tyboot.core.rdbms.annotation.Operator;
import org.typroject.tyboot.core.rdbms.service.BaseService;
import org.typroject.tyboot.core.restful.exception.instance.DataNotFound;

import java.util.List;

/**
 * <p>
 * 系统字典表 服务类
 * </p>
 *
 * @author magintursh
 * @since 2017-06-27
 */
@Component
public class DictionarieService extends BaseService<DictionarieModel,Dictionarie,DictionarieMapper> implements InitializingBean {


    public static final String CACHEKEY_DICTIONARY_LIST="DICTIONARY_LIST";



    public void afterPropertiesSet() throws Exception
    {
        this.setCacheExpire(24*60*60*60L);//设置字典的缓存时间为24小时
    }

    public static String getCachekeyDictionaryList(String agencyCode)
    {
        return Redis.genKey(CacheType.ERASABLE.name(),CACHEKEY_DICTIONARY_LIST,agencyCode);
    }

    public DictionarieModel createDict(DictionarieModel model)throws Exception
    {
        return this.createWithCache(model,
                Redis.genKey(model.getAgencyCode(),model.getDictCode()),
                getCachekeyDictionaryList(model.getAgencyCode()));
    }

    public boolean deleteDicts(List<Long > seqs)throws Exception
    {
        for(Long seq:seqs)
        {
            DictionarieModel model = this.queryBySeq(seq);
            if(ValidationUtil.isEmpty(model))
                throw new DataNotFound("找不到指定的字典.");
            this.deleteBySeqWithCache(seq,
                Redis.genKey(model.getAgencyCode(),model.getDictCode()));
                getCachekeyDictionaryList(model.getAgencyCode());
        }
        return true;
    }

    public DictionarieModel updateDict(DictionarieModel model)throws Exception
    {
        DictionarieModel oldModel = this.queryBySeq(model.getSequenceNbr());
        if(ValidationUtil.isEmpty(oldModel))
            throw new DataNotFound("找不到指定的字典.");
        oldModel  = Bean.copyExistPropertis(model,oldModel);
        return this.updateWithCache(oldModel,
                Redis.genKey(oldModel.getAgencyCode(),oldModel.getDictCode()),
                getCachekeyDictionaryList(model.getAgencyCode()));
    }


    public DictionarieModel queryByCode(String agencyCode,String dictCode) throws Exception
    {
        return this.queryModelByParamsWithCache(Redis.genKey(agencyCode,dictCode),agencyCode,dictCode);
    }

    public List<DictionarieModel> queryByAgencyCode(String agencyCode) throws Exception
    {
        return this.queryForListWithCache(getCachekeyDictionaryList(agencyCode),"REC_DATE", false,agencyCode);
    }


    public IPage<DictionarieModel> queryDictPage(Page page, String agencyCode ,
                                                 String buType,
                                                 String dictAlias,
                                                 @Condition(Operator.like) String dictName,
                                                 String dictCode) throws Exception
    {
        return this.queryForPage(page,null,false,
                agencyCode,buType,dictAlias,dictName,dictCode);
    }



    public List<DictionarieModel> queryDictList(String agencyCode ,
                                               String buType,
                                               String dictAlias,
                                                @Condition(Operator.like)String dictName,
                                               String dictCode) throws Exception
    {
        return this.queryForList(null,false,
                agencyCode,buType,dictAlias,dictName,dictCode);

    }


}
