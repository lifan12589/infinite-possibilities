package com.wondersgroup.youHiTest.PayTest.impl;

import java.math.BigDecimal;
import javax.annotation.Resources;

import com.wondersgroup.youHiTest.PayTest.Pay;
import com.wondersgroup.youHiTest.PayTest.Strategy;

import tw.tool.util.BeanUtils;

@Pay(channelId=2)
public class Impl2 extends BeanUtils implements Strategy{

//	@Resources
//	private ElegantCodeChannelMapper legantCodeChannelMapper;
//	
//	@Resources
//	private ElegantCodeGoodsMapper legantCodeGoodsMapper;
	
	@Override
	public BigDecimal calRecharge(Integer channelId, Integer goodsId ,String zhekouId) {
		
		BigDecimal bigDecimal =null;
		BigDecimal bigDecimal1 = new BigDecimal(goodsId.toString());
		BigDecimal bigDecimal2 = new BigDecimal(zhekouId);
		if(null!=channelId&&null!=goodsId){
		 bigDecimal = bigDecimal1.multiply(bigDecimal2);
		}
		return bigDecimal;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
