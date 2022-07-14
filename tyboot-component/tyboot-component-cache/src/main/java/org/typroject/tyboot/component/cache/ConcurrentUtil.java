package org.typroject.tyboot.component.cache;

import org.typroject.tyboot.core.foundation.exception.BaseException;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *  Tyrest
 *  File: ConcurrentUtil.java
 *
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 *
 *  Description:
 *  用redis实现的全局锁,可实现并发控制
 *
 *  Notes:
 *  $Id: ConcurrentUtil.java  Tyrest\magintrursh $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class ConcurrentUtil {


    public static final long OPERATION_TOKEN_EXPIRE = 60L;

    public static final int WAIT_COUNT_FOR_ASYNLOCK = 100;//同步锁等待列表长度
    private static final Set<String> WAIT_LIST_FOR_ASYNLOCK = new HashSet<>();//同步锁等待列表

    private static String MONITOR = "MONITOR";


    private static void addAsynWaitList(String cacheKey) {

		if (WAIT_LIST_FOR_ASYNLOCK.size() >= WAIT_COUNT_FOR_ASYNLOCK) {
			throw new BaseException("系统繁忙请稍后再试", 400, "同步锁等待数量超限");
		}

        WAIT_LIST_FOR_ASYNLOCK.add(cacheKey);

    }

    private static void removeAsynWaitList(String cacheKey) {
        WAIT_LIST_FOR_ASYNLOCK.remove(cacheKey);
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * TODO.以全局分布式同步锁的方式执行操作,用于控制规模较小的并发
     *
     * @param entityKey 全局同步锁的Key
     * @param callee
     * @return
     */
    public static <T> T runWithAsynLock(String entityKey, Callable<T> callee) {
        addAsynWaitList(entityKey);
        asynLock(entityKey);
        T result = null;
        try {
            try {
                result = callee.call();
            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof BaseException) {
                    BaseException exception = (BaseException) e;
                    throw exception;
                } else {
                    throw new RuntimeException(e.getMessage(), e.getCause());
                }

            }
        } finally {
            unlock(entityKey);
            removeAsynWaitList(entityKey);
        }
        return result;
    }


    /**
     * TODO.以全局分布式同步锁的方式执行操作,用于控制规模较小的并发
     *
     * @param entityKey 全局同步锁的Key
     * @param callee
     * @return
     */
    public static <T> T runWithExclusiveLock(String entityKey, Callable<T> callee) {
        exclusiveLock(entityKey);
        T result = null;
        try {
            try {
                result = callee.call();
            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof BaseException) {
                    BaseException exception = (BaseException) e;
                    throw exception;
                } else {
                    throw new RuntimeException(e.getMessage(), e.getCause());
                }
            }
        } finally {
            unlock(entityKey);
        }
        return result;
    }


    /**
     * TODO.获取全局锁
     *
     * @param entityKey 全局锁的Key
     * @throws InterruptedException
     */
    private static void asynLock(String entityKey) {
        while (true) {
            if (Redis.getRedisTemplate().opsForValue().setIfAbsent(entityKey, MONITOR)) {
                Redis.getRedisTemplate().expire(entityKey, OPERATION_TOKEN_EXPIRE, TimeUnit.SECONDS);
                break;
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage(), e.getCause());
                }
            }
        }
    }


    /**
     * TODO.获取全局排他锁
     *
     * @param entityKey 全局排他锁的Key
     * @throws InterruptedException
     */
    private static void exclusiveLock(String entityKey) {

        if (Redis.getRedisTemplate().opsForValue().setIfAbsent(entityKey, MONITOR)) {
            Redis.getRedisTemplate().expire(entityKey, OPERATION_TOKEN_EXPIRE, TimeUnit.SECONDS);
        } else {
            throw new RuntimeException("重复的操作.");
        }
    }

    /**
     * TODO.释放全局锁
     *
     * @param entityKey 全局锁的Key
     */
    private static void unlock(String entityKey) {
        Redis.getRedisTemplate().delete(entityKey);
    }
}
