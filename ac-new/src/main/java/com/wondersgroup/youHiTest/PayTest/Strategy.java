package com.wondersgroup.youHiTest.PayTest;

import java.math.BigDecimal;

public interface Strategy {

	BigDecimal  calRecharge(Integer channelId, Integer goodsId, String zhekouId);
	
}
