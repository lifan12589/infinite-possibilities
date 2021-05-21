package com.infinitePossibilities.wyc.service.impl;


import com.infinitePossibilities.wyc.dao.TblPointsDao;
import com.infinitePossibilities.wyc.entity.OrderBase;
import com.infinitePossibilities.wyc.entity.TblPoints;
import com.infinitePossibilities.wyc.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointsServiceImpl implements PointsService {

    @Autowired
    TblPointsDao pointsMapper;

    @Override
    public void increasePoints(OrderBase order) {
        
        //入库之前先查询，实现幂等
//        if (pointsMapper.getByOrderNo(order.getOrderNo())>0){
//            log.info("积分添加完成，订单已处理。{}",order.getOrderNo());
//        }else{
            TblPoints points = new TblPoints();
            points.setId(order.getId());
            points.setUserId("11");
            points.setOrderNo("wo");
            points.setPoints("10");
            points.setRemarks("商品消费共10元，获得积分"+points.getPoints());
            pointsMapper.insert(points);
//            log.info("已为订单号码{}增加积分。",points.getOrderNo());
//        }
    }
}