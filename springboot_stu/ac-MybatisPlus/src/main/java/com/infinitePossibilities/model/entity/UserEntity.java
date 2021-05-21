    package com.infinitePossibilities.model.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableField;
    import com.baomidou.mybatisplus.annotation.TableId;
    import com.baomidou.mybatisplus.annotation.TableName;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.io.Serializable;

    /**
     * 用户信息实体类
     *
     * @author 154594742@qq.com
     * @date: 2021/2/22 10:02:00
     */

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "UserEntity", description = "用户实体")
    @TableName("t_user")
    public class UserEntity implements Serializable {

        private static final long serialVersionUID = 6928834261563057243L;

        /**
         * 唯一标识，用户输入，重复id 会报异常
         * //唯一标识，自增主键
         */
        @ApiModelProperty(value = "id")
        @TableId(value = "id", type = IdType.INPUT)// IdType.AUTO  为数据库 id 自增
        private Long id;

        /**
         * 姓名
         */
        @ApiModelProperty(value = "姓名")
        @TableField("name")
        private String name;

        /**
         * 年龄
         */
        @ApiModelProperty(value = "年龄")
        @TableField("age")
        private Integer age;
    }
