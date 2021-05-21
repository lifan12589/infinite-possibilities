package com.infinitePossibilities.service;


import com.infinitePossibilities.domain.AopEx;
import com.infinitePossibilities.enums.ResultEnum;
import com.infinitePossibilities.exception.AopExException;
import com.infinitePossibilities.repository.AopExRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AopExService {

    @Autowired
    private AopExRepository aopExRepository;

    @Transactional
    public void insertTwo() {
        AopEx aopExA = new AopEx();

        aopExA.setId(2);
        aopExA.setCupSize("A");
        aopExA.setAge(18);
        aopExA.setMoney(111.00);
        aopExRepository.save(aopExA);


        AopEx aopExB = new AopEx();
        aopExB.setId(3);
        aopExB.setCupSize("BBBB");
        aopExB.setAge(19);
        aopExB.setMoney(222.00);
        aopExRepository.save(aopExB);
    }

    public void getAge(Integer id) throws Exception{
        List<Integer> list = new ArrayList<>();
        list.add(id);
        List<AopEx> aopEx = aopExRepository.findByIdIn(list);
        Integer age=null;
        for(int i =0;i<aopEx.size();i++){
            AopEx a = aopEx.get(i);
            if(id==a.getId()){
                age = a.getAge();
            }
        }

        if (age < 10) {
            //返回"" code=100
            throw new AopExException(ResultEnum.PRIMARY_SCHOOL);
        }else if (age > 10 && age < 16) {
            //返回"" code=101
            throw new AopExException(ResultEnum.MIDDLE_SCHOOL);
        }


    }

    /**
     * 通过Id查询一个的信息
     * @param id
     * @return
     */
    public Optional<AopEx> findOne(Integer id) {

        return aopExRepository.findById(id);
    }
}
