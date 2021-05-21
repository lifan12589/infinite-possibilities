package com.infinitePossibilities.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.infinitePossibilities.model.entity.UserEntity;
import com.infinitePossibilities.model.param.UserParam;
import com.infinitePossibilities.model.vo.ResponseVo;
import com.infinitePossibilities.service.UserService;
import com.infinitePossibilities.util.CommonQueryPageUtils;
import com.infinitePossibilities.util.BuildResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @author https://www.cnblogs.com/wqp001/p/14436895.html#4824860
 */

@RestController
@Api(tags = "用户控制器")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("新增")
    @PostMapping("user")
    public ResponseVo<?> add(UserEntity entity) {
        return userService.save(entity) ? BuildResponseUtils.success() : BuildResponseUtils.error();
    }

    @ApiOperation("通过id查询")
    @GetMapping("user/{id}")
    public ResponseVo<UserEntity> getById(@PathVariable String id) {
        return BuildResponseUtils.buildResponse(userService.getById(id));
    }

    @ApiOperation("修改")
    @PutMapping("user")
    public ResponseVo<?> update(UserEntity entity) {
        return userService.updateById(entity) ? BuildResponseUtils.success() : BuildResponseUtils.error();
    }

    @ApiOperation("通过id删除")
    @DeleteMapping("user/{id}")
    public ResponseVo<?> delete(@PathVariable String id) {
        return userService.removeById(id) ? BuildResponseUtils.success() : BuildResponseUtils.error();
    }


    @ApiOperation("分页查询")
    @GetMapping("userPage")
    public ResponseVo<IPage<UserEntity>> selectPage(UserParam param) {
        return BuildResponseUtils.buildResponse(CommonQueryPageUtils.commonQueryPage(param, userService));
    }

}
