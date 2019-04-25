package org.typroject.tyboot.core.auth.face.service;

/**
 * Created by yaohelang on 2017/12/2.
 */
public interface Product {

    //产品编号
    String getProductCode();

    //产品名称
    String getProductName();

    /**
     * 互斥的产品标识，即不能同时在线
     * @return
     */
    Product [] mutexes();



    Product [] getProductList();
}
