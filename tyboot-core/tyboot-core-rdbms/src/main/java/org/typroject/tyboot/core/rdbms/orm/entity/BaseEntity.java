package org.typroject.tyboot.core.rdbms.orm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;
/**
 * Created by magintursh on 2017-06-17.
 */
public class   BaseEntity implements Serializable{

    private static final long serialVersionUID = 5354351431289739L;


    @TableId(value = "SEQUENCE_NBR",type = IdType.ID_WORKER)
    protected Long sequenceNbr;

    @TableField("REC_DATE")
    protected Date recDate;

    @TableField("REC_USER_ID")
    protected String recUserId;

    @TableField("REC_STATUS")
    protected String recStatus;


    public Long getSequenceNbr() {
        return sequenceNbr;
    }

    public void setSequenceNbr(Long sequenceNbr) {
        this.sequenceNbr = sequenceNbr;
    }

    public Date getRecDate() {
        return recDate;
    }

    public void setRecDate(Date recDate) {
        this.recDate = recDate;
    }

    public String getRecUserId() {
        return recUserId;
    }

    public void setRecUserId(String recUserId) {
        this.recUserId = recUserId;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

}
