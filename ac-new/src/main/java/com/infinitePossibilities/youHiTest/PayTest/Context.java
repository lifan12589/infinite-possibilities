package com.infinitePossibilities.youHiTest.PayTest;

import java.math.BigDecimal;

public class Context {

	public BigDecimal calRecharge(Integer channelId, Integer goodsId,String zhekouId) throws Exception{
		
		StrategyFactory strategyFactory = StrategyFactory.getInstance();
		//得到相应的实现类--strategy
		Strategy strategy= strategyFactory.create(channelId);
		return strategy.calRecharge(channelId, goodsId,zhekouId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
