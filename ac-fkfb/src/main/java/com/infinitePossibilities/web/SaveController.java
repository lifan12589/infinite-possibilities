package com.infinitePossibilities.web;

import com.infinitePossibilities.dao.Save;
import com.infinitePossibilities.service.SaveInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;


@RestController
public class SaveController {


//    @Autowired
//    private KeyGenerator keyGenerator;

    @Autowired
    private SaveInfoMapper saveInfoMapper;

    @GetMapping("save")
    public String save(){
        long time = System.currentTimeMillis();
        for(int i= 1 ; i <= 40 ; i ++){
            Save save = new Save();
            save.setId((long) i);
            save.setUsername( "user" + i);
            save.setApplyno(i+1+"");
            save.setType((long) (i+1));
            Save save1= saveInfoMapper.save(save);
            System.out.println(save1);
        }
        return "success";
    }

    @GetMapping("selectAll")
    public List<Save> select(){
        return saveInfoMapper.findAll();
    }

    //http://localhost:8080/select
    @GetMapping("select")
    public List<Save> select(String id){
        return saveInfoMapper.findAllByIdBetween(21L,39L);
    }

    //http://localhost:8080/find?id=1
    @GetMapping("find")
    public Object find(@Param("id") Long id){
        System.out.println(id);
    List<Long> list = new ArrayList<>();
        list.add(id);
        return saveInfoMapper.findAllByIdIn(list);
    }

    @GetMapping("delete")
    public void delete(String id){
         saveInfoMapper.deleteAll();
    }



}







