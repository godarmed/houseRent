package com.team.leo.util;

public class UsersParams {
    //分页条件
    private Integer page;
    private Integer rows = 5;

    public UsersParams() {
    }

    public UsersParams(int pageNum, int pageSize) {
        this.page = pageNum;
        this.rows = pageSize;
    }

    //查询条件
    private String name;
    private Integer startAge;
    private Integer endAge;

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

    public Integer getStartAge() {
        return startAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }

    @Override
    public String toString() {
        return "UsersParams{" +
                "page=" + page +
                ", rows=" + rows +
                ", name='" + name + '\'' +
                ", startAge=" + startAge +
                ", endAge=" + endAge +
                '}';
    }
}
