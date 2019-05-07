package org.typroject.tyboot.prototype.trade.channel;

/** 
 * 
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
	 * 支付渠道：PINGXX-支付宝
	 */
	PINGXX_ALIPAY("alipay","pingxxChannel"),
	/**
	 * 支付渠道：PINGXX-支付宝手机网页支付
	 */
	PINGXX_ALIPAY_WAP("alipay_wap","pingxxChannel"),
	
	/**
	 * 支付渠道：PINGXX-微信APP
	 */
	PINGXX_WX("wx","pingxxChannel"),
	/**
	 * 支付渠道：PINGXX-微信公众账号支付
	 */
	PINGXX_WX_PUB("wx_pub","pingxxChannel"),

	/**
	 * 支付渠道：PINGXX-微信小程序支付
	 */
	PINGXX_WX_XCX("wx_lite","pingxxChannel"),

	/**
	 * 支付渠道：现金
	 */
	CASH("cash","cashChannel"),
	
	/**
	 * 支付渠道：虚拟账户支付
	 */
	VIRTUAL("virtual","virtualChannel"),

	/**
	 * 苹果支付
	 */
	APPLE("apple","appleChannel"),
	
	/**
	 * 支付渠道：银行卡
	 */
	BANK_CARD("bank","cashChannel"),

		WX_APP("APP","wxChannel"),

	//JSAPI支付（或小程序支付）
	WX_JSAPI("JSAPI","wxChannel"),
	WX_MWEB("MWEB","wxChannel"),
	WX_NATIVE("NATIVE","wxChannel"),

	ALIPAY_APP("alipay_app","alipayChannel");
	
	
	DefaultChannelType(String channel,String channelProcess)
	{
		this.channel = channel;
		this.channelProcess = channelProcess;
	}

	private String channel;
	
	private String channelProcess;
	
	
	public String getChannelProcess() {
		return channelProcess;
	}

	public String parseString()
	{
		return this.name();
	}
	
	public static DefaultChannelType getInstance(String str)
	{
		DefaultChannelType channel = null;
		
		for(DefaultChannelType ut : DefaultChannelType.values())
		{
			if(ut.getChannel().equals(str))
			{
				channel = ut;
			}
		}
		
		return channel;
		
	}

	public String getChannel() {
		return channel;
	}

	
}

/*
*$Log: av-env.bat,v $
*/