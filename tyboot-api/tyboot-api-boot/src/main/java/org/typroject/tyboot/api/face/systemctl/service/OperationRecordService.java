package org.typroject.tyboot.api.face.systemctl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Component;
import org.typroject.tyboot.api.face.systemctl.enumeration.OperateType;
import org.typroject.tyboot.api.face.systemctl.model.OperationRecordModel;
import org.typroject.tyboot.api.face.systemctl.orm.dao.OperationRecordMapper;
import org.typroject.tyboot.api.face.systemctl.orm.entity.OperationRecord;
import org.typroject.tyboot.component.cache.Redis;
import org.typroject.tyboot.component.cache.enumeration.CacheType;
import org.typroject.tyboot.component.event.RestEventHandler;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.rdbms.service.BaseService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 子杨
 * @since 2018-12-04
 */
@Component
public class OperationRecordService extends BaseService<OperationRecordModel, OperationRecord, OperationRecordMapper> {


    public static String OPERATE_COLLECT = "COLLECT";
    public static String OPERATE_LIKE = "LIKE";
    public static String OPERATE_COMMENT = "COMMENT";


    /**
     * 创建记录
     * @param entityType
     * @param entityId
     * @param operationType
     * @return
     * @throws Exception
     */
    public OperationRecordModel createRecord(String entityType,String entityId,String userId,String operationType)throws Exception
    {
        OperationRecordModel recordModel = new OperationRecordModel();
        recordModel.setCreateTime(new Date());
        recordModel.setEntityId(entityId);
        recordModel.setEntityType(entityType);
        recordModel.setOperationType(operationType);
        recordModel.setUserId(userId);
        return this.createWithModel(recordModel);
    }


    /**
     * 删除记录
     * @param entityType
     * @param entityId
     * @param operationType
     * @return
     * @throws Exception
     */
    public OperationRecordModel deleteRecord(String entityType,String entityId,String userId,String operationType)throws Exception
    {
        OperationRecordModel model   = this.queryForRecord(entityType,entityId,userId,operationType);

        if(!ValidationUtil.isEmpty(model))
            this.deleteBySeq(model.getSequenceNbr());
        return model;
    }


    /**
     * 删除/取消点赞
     * @param entityType
     * @param entityId
     * @param operationType
     * @return
     * @throws Exception
     */
    public Boolean deleteOrCreate(String entityType,String entityId,String userId,String operationType) throws Exception
    {
        OperationRecordModel model   = this.queryForRecord(entityType,entityId,userId,operationType);

        if(!ValidationUtil.isEmpty(model))
        {
            this.deleteBySeq(model.getSequenceNbr());

            RestEventHandler.attachEventSource("deleteOperateRecordHandler",model);
        }
        else{
            model = this.createRecord(entityType,entityId,userId,operationType);

            RestEventHandler.attachEventSource("createOperateRecordHandler",model);
        }
        return true;
    }



    public List<OperationRecordModel> queryByEntity(String entityType, String entityId,String userId,String operationType)throws Exception
    {
        return  this.queryForList("",false,entityId,entityType,userId,operationType);
    }


    public Page<OperationRecordModel> queryForRecordPage(Page page, String entityType, String entityId, String userId, String operationType)throws Exception
    {
        return  this.queryForPage(page,"",false,entityType,entityId,userId,operationType);
    }



    public OperationRecordModel  queryForRecord(String entityType,String entityId,String userId,String operationType) throws Exception
    {
        return this.queryModelByParams(entityType,entityId,userId,operationType);
    }






































    public Map<OperateType,Integer> queryPostOperationMapInCache(Long postSeq, String entityType) throws Exception
    {
        String cacheKey = Redis.genKey(CacheType.ERASABLE.name(),entityType,String.valueOf(postSeq));

        Map<OperateType,Integer> postOperateMap  = (Map<OperateType,Integer>)Redis.getRedisTemplate().opsForValue().get(cacheKey);

        if(ValidationUtil.isEmpty(postOperateMap))
        {
            postOperateMap = new HashMap();
            if(ValidationUtil.isEmpty(postOperateMap))
            {
                postOperateMap = new HashMap<>();
                for(OperateType type : OperateType.values())
                {
                    postOperateMap.put(type,0);
                }
            }
        }
        return postOperateMap;
    }






   /* private Map<OperateType,Integer> queryOperateMapFromMysql(Long sequenceNbr) throws Exception
    {

        Map<OperateType,Integer> returnMap = new HashMap<>();

        Map params = this.assemblyMapParams(
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                this.getClass(),
                sequenceNbr);
        List<OperationRecord> entitis = this.selectByMap(params);
        if(!ValidationUtil.isEmpty(entitis))
        {
            for(OperationRecord entity: entitis)
            {
                returnMap.put(OperateType.valueOf(entity.getOperationType()),entity.getOperationTimes());
            }
        }
        return returnMap;
    }
*/



    public void increamentOperateTimes(OperateType operateType, int count, Long postSeq, String entityType) throws Exception
    {
        Map<OperateType,Integer> postOperateMap  = queryPostOperationMapInCache(postSeq,entityType);

        postOperateMap.put(operateType,postOperateMap.get(operateType)+count);

        String cacheKey = Redis.genKey(CacheType.ERASABLE.name(),entityType,String.valueOf(postSeq));
        Redis.getRedisTemplate().opsForValue().set(postOperateMap,cacheKey);
    }


 /*   public void increamentOperateTimesToMysql(OperateType operateType,int count,Long sequenceNbr) throws Exception{

        Map<OperateType,Integer> postOperateMap = queryOperateMapFromMysql(sequenceNbr);

        postOperateMap.put(operateType,postOperateMap.get(operateType)+count);

        ResponseHelper.buildRespons(postOperateMap);
    }*/



}
