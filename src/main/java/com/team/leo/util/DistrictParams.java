package com.team.leo.util;

public class DistrictParams {
    //分页条件
    private Integer page;
    private Integer rows = 5;

    public DistrictParams() {
    }

    public DistrictParams(Integer page, Integer rows) {
        this.page = page;
        this.rows = rows;
    }

    //查询条件
    private String name;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DistrictParams{" +
                "page=" + page +
                ", rows=" + rows +
                ", name='" + name + '\'' +
                '}';
    }
}
