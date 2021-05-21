package com.wondersgroup.youHiTest.PayTest.impl;

import java.math.BigDecimal;

import com.wondersgroup.youHiTest.PayTest.Pay;
import com.wondersgroup.youHiTest.PayTest.Strategy;

import tw.tool.helper.LogHelper;
import tw.tool.util.BeanUtils;

@Pay(channelId=1)
public class Impl1 extends BeanUtils implements Strategy{

//	@Resources
//	private ElegantCodeChannelMapper legantCodeChannelMapper;
//	
//	@Resources
//	private ElegantCodeGoodsMapper legantCodeGoodsMapper;
	
	@Override
	public BigDecimal calRecharge(Integer channelId, Integer goodsId ,String zhekouId) {
		
		BigDecimal bigDecimal =null;
		//总数应从数据库获取
		BigDecimal bigDecimal1 = new BigDecimal(goodsId.toString());
		//应从数据库获取
		BigDecimal bigDecimal2 = new BigDecimal(zhekouId);
		if(null!=channelId&&null!=goodsId){
		//相乘
		 bigDecimal = bigDecimal1.multiply(bigDecimal2);
		 LogHelper.info("相乘结果："+bigDecimal);
		}
		return bigDecimal;
	}

}
