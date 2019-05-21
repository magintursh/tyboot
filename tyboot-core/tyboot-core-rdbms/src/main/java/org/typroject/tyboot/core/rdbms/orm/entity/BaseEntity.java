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


    private static final String SEQUENCE_NBR    = "SEQUENCE_NBR";
    private static final String REC_DATE        = "REC_DATE";
    private static final String REC_USER_ID     = "REC_USER_ID";


    @TableId(value = "SEQUENCE_NBR",type = IdType.ID_WORKER)
    protected Long sequenceNbr;

    @TableField("REC_DATE")
    protected Date recDate;

    @TableField("REC_USER_ID")
    protected String recUserId;


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

}
