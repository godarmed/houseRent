package com.team.leo.util;

public class TypeParams {
    //分页条件
    private Integer page;
    private Integer rows = 5;

    public TypeParams() {
    }

    public TypeParams(int pageNum, int pageSize) {
        this.page = pageNum;
        this.rows = pageSize;
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
        return "TypeParams{" +
                "page=" + page +
                ", rows=" + rows +
                ", name='" + name + '\'' +
                '}';
    }
}
