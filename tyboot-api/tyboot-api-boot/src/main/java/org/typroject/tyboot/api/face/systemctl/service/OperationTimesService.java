package org.typroject.tyboot.api.face.systemctl.service;

import org.springframework.stereotype.Component;
import org.typroject.tyboot.api.face.systemctl.model.OperationTimesModel;
import org.typroject.tyboot.api.face.systemctl.orm.dao.OperationTimesMapper;
import org.typroject.tyboot.api.face.systemctl.orm.entity.OperationTimes;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.rdbms.service.BaseService;


/**
 * <p>
 * 操作计数 服务类
 * </p>
 *
 * @author 子杨
 * @since 2018-12-26
 */
@Component
public class OperationTimesService extends BaseService<OperationTimesModel, OperationTimes, OperationTimesMapper>
{


    /**
     *
     * @param entityType
     * @param entityId
     * @return
     * @throws Exception
     */
    public OperationTimesModel incrementRecordCount(String entityType,String entityId,String userId,String operationType,Integer count) throws Exception{

        OperationTimesModel model = this.queryForTimesRecord(entityType,entityId,userId,operationType);
        if(!ValidationUtil.isEmpty(model)){
            model.setOperationTimes(model.getOperationTimes()+count);

            if(model.getOperationTimes() < 0)
                model.setOperationTimes(0);

        }else{

            model = new OperationTimesModel();

            model.setEntityType(model.getEntityId());
            model.setEntityId(model.getEntityId());
            model.setOperationTimes(count);
            model= this.createWithModel(model);
        }
        return model;
    }





    public OperationTimesModel queryForTimesRecord(String entityType,String entityId,String userId,String operationType) throws Exception{

        return this.queryModelByParams(entityId,entityType,userId,operationType);
    }


    public Integer queryForTimes(String entityType,String entityId,String userId,String operationType) throws Exception{

        Integer times = 0;
        OperationTimesModel timesModel  =  this.queryForTimesRecord(entityId,entityType,userId,operationType);
        if(!ValidationUtil.isEmpty(timesModel))
            times = timesModel.getOperationTimes();
        return times;
    }





}
