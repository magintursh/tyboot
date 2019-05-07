package org.typroject.tyboot.face.trade.service;

import org.springframework.stereotype.Component;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.rdbms.service.BaseService;
import org.typroject.tyboot.face.trade.model.TransactionsRecordModel;
import org.typroject.tyboot.face.trade.model.TransactionsSerialModel;
import org.typroject.tyboot.face.trade.orm.dao.TransactionsRecordMapper;
import org.typroject.tyboot.face.trade.orm.entity.TransactionsRecord;

import java.util.Date;

/**
 * <p>
 * 交易记录表 服务类
 * </p>
 *
 * @author 子杨
 * @since 2017-08-31
 */
@Component
public class TransactionsRecordService extends BaseService<TransactionsRecordModel, TransactionsRecord, TransactionsRecordMapper> {




    public TransactionsRecordModel selectBillNo(String billNo) throws Exception {
        TransactionsRecordModel record = new TransactionsRecordModel();
        record.setBillNo(billNo);
        return this.queryByModel(record);
    }

    public TransactionsRecordModel selectSerialNo(String serialNo) throws Exception {
        TransactionsRecordModel record = new TransactionsRecordModel();
        record.setSerialNo(serialNo);
        return this.queryByModel(record);
    }


    public TransactionsRecordModel createTransactionsRecord(TransactionsSerialModel serialModel) throws Exception {

        TransactionsRecordModel record = this.selectBillNo(serialModel.getBillNo());
        if (ValidationUtil.isEmpty(record)) {
            record = new TransactionsRecordModel();
            record.setAgencyCode(serialModel.getAgencyCode());
            record.setBillNo(serialModel.getBillNo());
            record.setFinishedTime(new Date());
            record.setPayMethod(serialModel.getPayMethod());
            record.setSerialNo(serialModel.getSerialNo());
            record.setTradeAmount(serialModel.getTradeAmount());
            record.setTradeType(serialModel.getTradeType());
            record.setUserId(serialModel.getUserId());
            record.setBillType(serialModel.getBillType());
            this.createWithModel(record);
        } else {
            throw new Exception("重复的交易记录.");
        }
        return record;
    }


}
