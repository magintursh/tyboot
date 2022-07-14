package org.typroject.tyboot.prototype.trade.channel;

import org.typroject.tyboot.prototype.trade.channel.alipay.AlipayChannel;
import org.typroject.tyboot.prototype.trade.channel.apple.AppleChannel;
import org.typroject.tyboot.prototype.trade.channel.cash.CashChannelImpl;
import org.typroject.tyboot.prototype.trade.channel.virtual.VirtualChannelImpl;
import org.typroject.tyboot.prototype.trade.channel.wx.WxChannel;

/**
 * <pre>
 *  Tyrest
 *  File: DefaultChannelType.java
 *
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 *
 *  Description:
 *
 *  Notes:
 *  $Id: DefaultChannelType.java  Tyrest\magintrursh $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public enum DefaultChannelType implements ChannelType {


    /**
     * 支付渠道：现金
     */
    CASH("现金", CashChannelImpl.class),

    /**
     * 支付渠道：虚拟账户支付
     */
    VIRTUAL("本地虚拟账户", VirtualChannelImpl.class),

    /**
     * 苹果支付
     */
    APPLE("苹果支付", AppleChannel.class),

    /**
     * 支付渠道：银行卡
     */
    BANK_CARD("银行卡", null),

    WEIXIN("微信", WxChannel.class),

    ALIPAY("支付宝", AlipayChannel.class);


    private DefaultChannelType(String parseString, Class<? extends ChannelProcessor> channelProcess) {
        this.parseString = parseString;
        this.channelProcess = channelProcess;
    }


    private Class<? extends ChannelProcessor> channelProcess;

    private String parseString;


    public Class<? extends ChannelProcessor> getChannelProcess() {
        return channelProcess;
    }


    public static DefaultChannelType getInstance(String str) {
        DefaultChannelType channel = null;

        for (DefaultChannelType ut : DefaultChannelType.values()) {
            if (ut.getChannel().equals(str)) {
                channel = ut;
            }
        }

        return channel;

    }

    public String getChannel() {
        return this.name();
    }

    @Override
    public String parseString() {
        return this.parseString;
    }
}

/*
 *$Log: av-env.bat,v $
 */