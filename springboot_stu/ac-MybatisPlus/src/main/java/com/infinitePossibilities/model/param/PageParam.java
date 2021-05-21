package com.infinitePossibilities.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一的分页查询请求参数
 *
 * @author 154594742@qq.com
 * @date 2021/2/22 17:24
 */

@Data
@ApiModel(value = "PageParam", description = "分页参数")
public class PageParam {
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码，不传则默认1")
    private Integer page = 1;

    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数，不传则默认10")
    private Integer limit = 10;

    /**
     * 排序字段(格式：字段名:排序方式，字段名:排序方式 （asc正序，desc倒序） 示例:id:desc,age:asc)
     */
    @ApiModelProperty(value = "排序字段(格式：字段名:排序方式，字段名:排序方式 （asc正序，desc倒序） 示例:id:desc,age:asc)")
    private String orders;
}
