package org.typroject.tyboot.core.restful.limit;


import java.util.concurrent.TimeUnit;


/**
 * 请求频次定义
 */
public class Frequency {

    /**
     * 周期单位
     */
    private TimeUnit timeUnit;



    /**
     * 周期长度
     */
    private Long  period;


    /**
     *限制请求数量
     */
    private Long quantity;



    public Frequency() {
    }

    public Frequency(TimeUnit timeUnit, Long period, Long quantity) {
        this.timeUnit = timeUnit;
        this.period = period;
        this.quantity = quantity;
    }

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
