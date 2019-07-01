package com.team.leo.util;

public class StreetParams {
    //分页条件
    private Integer page;
    private Integer rows = 5;

    public StreetParams() {
    }

    public StreetParams(int pageNum, int pageSize) {
        this.page = pageNum;
        this.rows = pageSize;
    }

    //查询条件
    private String name;
    private Integer districtId;

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

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    @Override
    public String toString() {
        return "StreetParams{" +
                "page=" + page +
                ", rows=" + rows +
                ", name='" + name + '\'' +
                ", districtId=" + districtId +
                '}';
    }
}
