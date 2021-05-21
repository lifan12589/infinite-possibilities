package com.infinitePossibilities.model.param;

import com.infinitePossibilities.annotation.QueryMethod;
import com.infinitePossibilities.constant.QueryMethodConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户查询条件类（需要根据哪些字段查询就添加哪些字段）
 * @author 154594742@qq.com
 * @date 2021/2/22 17:24
 */

@Data
@ApiModel(value = "UserParam", description = "用户查询条件")
public class UserParam extends PageParam {

    /**
     * 通过@QueryMethod注解来控制匹配的方式，这里查询条件为 name like ‘%值%’
     */
    @ApiModelProperty(value = "姓名")
    @QueryMethod(field = "name", method = QueryMethodConstant.LIKE)
    private String name;

    /**
     * 这里没有@QueryMethod注解则如果age有值，则默认查询条件为 age=值
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 假如要查询 (值1 < age < 值2)则可以采用如下方式添加两个属性minAge和maxAge，
     * ‘ @QueryMethod 注解的field是数据表字段名，method是查询方式
     * 假如minAge = 18，maxAge=25，则通过CommonQueryPageUtils工具类会构建出的sql为 18<age AND age>25
     */
    @ApiModelProperty(value = "年龄下限")
    @QueryMethod(field = "age", method = QueryMethodConstant.GT)
    private String minAge;

    @ApiModelProperty(value = "年龄上限")
    @QueryMethod(field = "age", method = QueryMethodConstant.LT)
    private String maxAge;
}
