package com.infinitePossibilities.domain;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="aopex")
public class AopEx {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "cupSize这个字段必传")
    private String cupSize;

    @Min(value = 18, message = "年龄段不在办理范围")
//    @NotNull
//    @Max()
//    @Length()
    private Integer age;

    @NotNull(message = "money必传")
    private Double money;

    public AopEx() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "AopEx{" +
                "id=" + id +
                ", cupSize='" + cupSize + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
