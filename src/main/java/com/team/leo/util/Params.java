package com.team.leo.util;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Params {
    //分页条件
    private Integer pageNum;
    private Integer pageSize = 5;

    public Params() {
    }

    public Params(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    //条件查询
    private String name;
    private String address;
    private String birthdayFrom;
    private String birthdayTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdayFrom() {
        return birthdayFrom;
    }

    public void setBirthdayFrom(String birthdayFrom) {
        this.birthdayFrom = birthdayFrom;
    }

    public String getBirthdayTo() {
        return birthdayTo;
    }

    public void setBirthdayTo(String birthdayTo) {
        this.birthdayTo = birthdayTo;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Params{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthdayFrom='" + birthdayFrom + '\'' +
                ", birthdayTo='" + birthdayTo + '\'' +
                '}';
    }
}
