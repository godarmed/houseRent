package com.team.leo.util;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HouseParams {
    //分页条件
    private Integer page;
    private Integer rows = 5;

    public HouseParams() {
    }

    public HouseParams(int pageNum, int pageSize) {
        this.page = pageNum;
        this.rows = pageSize;
    }

    //查询条件
    private Integer userId;

    private Integer typeId;

    private String title;

    private String description;

    private Long minPrice;

    private Long maxPrice;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enddate;

    private Integer minFloorage;

    private Integer maxFloorage;

    private String contact;

    private String districtId;

    private Integer streetId;

    private Integer ispass;

    private Integer isdel;

    private String path;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getMinFloorage() {
        return minFloorage;
    }

    public void setMinFloorage(Integer minFloorage) {
        this.minFloorage = minFloorage;
    }

    public Integer getMaxFloorage() {
        return maxFloorage;
    }

    public void setMaxFloorage(Integer maxFloorage) {
        this.maxFloorage = maxFloorage;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public Integer getIspass() {
        return ispass;
    }

    public void setIspass(Integer ispass) {
        this.ispass = ispass;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //额外属性
    private String dname;

    private String sname;

    private String tname;

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "HouseParams{" +
                "page=" + page +
                ", rows=" + rows +
                ", userId=" + userId +
                ", typeId=" + typeId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", minFloorage=" + minFloorage +
                ", maxFloorage=" + maxFloorage +
                ", contact='" + contact + '\'' +
                ", districtId='" + districtId + '\'' +
                ", streetId=" + streetId +
                ", ispass=" + ispass +
                ", isdel=" + isdel +
                ", path='" + path + '\'' +
                ", dname='" + dname + '\'' +
                ", sname='" + sname + '\'' +
                ", tname='" + tname + '\'' +
                '}';
    }
}
