package com.infinitePossibilities.controller;


import com.infinitePossibilities.domain.AopEx;
import com.infinitePossibilities.domain.Result;
import com.infinitePossibilities.repository.AopExRepository;
import com.infinitePossibilities.service.AopExService;
import com.infinitePossibilities.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Example;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class AopExController {

    private final static Logger logger = LoggerFactory.getLogger(AopExController.class);

    @Autowired
    private AopExRepository aopExRepository;

    @Autowired
    private AopExService aopExService;

    /**
     * 查询所有数据列表
     * @return
     */
    @GetMapping(value = "/list")
    public List<AopEx> list() {
        logger.info("AopExList");

        return aopExRepository.findAll();
    }

    @GetMapping(value="/list2")
    public Result<AopEx> list2() {
        logger.info("AopExList");

        return ResultUtil.success(aopExRepository.findAll());
    }




    /**
     * 添加一个
     * @return
     */
    @GetMapping(value = "/add")
    public Result<AopEx> add(@Valid AopEx aopEx, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }

//        aopEx.setCupSize(aopEx.getCupSize());
//        aopEx.setAge(aopEx.getAge());
        aopEx.setId(2);
        aopEx.setAge(13);
        aopEx.setCupSize("666");
        aopEx.setMoney(777.00);

        return ResultUtil.success(aopExRepository.save(aopEx));
    }

    /**
     * 添加一个
     * @return
     */
    @GetMapping(value = "/adds")
    public Result<AopEx> adds() {

        AopEx aopEx = new AopEx();

        aopEx.setId(2);
        aopEx.setAge(13);
        aopEx.setCupSize("666");
        aopEx.setMoney(777.00);
        return ResultUtil.success(aopExRepository.save(aopEx));
    }

    //查询一个
    @GetMapping(value = "/find/{id}")
    public Optional<AopEx> FindOne(@PathVariable("id") Integer id) {

        AopEx aopEx = new AopEx();
        aopEx.setId(id);
        Example<AopEx> find = Example.of(aopEx);
        return aopExRepository.findOne(find);
    }

    //更新
    @GetMapping(value = "/update/{id}")
    public AopEx update(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age,
                           @RequestParam("money")Double money) {
        AopEx aopEx = new AopEx();
        aopEx.setId(id);
        aopEx.setCupSize(cupSize);
        aopEx.setAge(age);
        aopEx.setMoney(money);
        return aopExRepository.save(aopEx);
    }

    //删除
    @GetMapping(value = "/del/{id}")
    public void delete(@PathVariable("id") Integer id) {
        List<Integer> list = new ArrayList<>();
        list.add(id);
        aopExRepository.deleteByIdIn(list);
    }

    //通过年龄查询
    @GetMapping(value = "/sel/age/{age}")
    public List<AopEx> listByAge(@PathVariable("age") Integer age) {
        return aopExRepository.findByAge(age);
    }

    @GetMapping(value = "/in/two")
    public void girlTwo() {
        aopExService.insertTwo();
    }

    @GetMapping(value = "/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception{
        aopExService.getAge(id);
    }




}
