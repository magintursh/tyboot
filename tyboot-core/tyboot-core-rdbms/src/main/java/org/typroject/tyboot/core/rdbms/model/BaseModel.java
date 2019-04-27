package org.typroject.tyboot.core.rdbms.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by magintursh on 2017-06-17.
 */
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 457432767545432436L;


    protected Long  sequenceNbr;

    @JsonIgnore
    protected Date recDate;

    @JsonIgnore
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
