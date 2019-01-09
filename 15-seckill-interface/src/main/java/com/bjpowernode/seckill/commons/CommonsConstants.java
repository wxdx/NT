package com.bjpowernode.seckill.commons;

import java.io.Serializable;

/**
 * @Author
 */
public class CommonsConstants implements Serializable {

    public static final String ZERO = "0";

    public static final String ONE = "1";

    public static final String SESSION_USER = "SESSION_USER";

    /**库存的redis key**/
    public static final String SECKILL_STORE = "store:";

    /**已经抢购过*/
    public static final String HANDLE_USER = "handel:user:";

    /**限流的列表key*/
    public static final String LIMITING_LIST = "limit_list:";

    /**限流为商品库存的倍数，100倍**/
    public static final Integer LIMIT_GOODS_MULTIPLE = 100;


    /***秒杀订单结果key*/
    public static final String ORDER_RESULT = "result:";
}
