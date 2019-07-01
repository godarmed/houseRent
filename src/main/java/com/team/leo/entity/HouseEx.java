package com.team.leo.entity;

public class HouseEx extends House {
    //额外属性
    private String dname;

    private String sname;

    private String tname;

    private Integer did;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

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

        return  super.toString() +
                "HouseEx{" +
                "dname='" + dname + '\'' +
                ", sname='" + sname + '\'' +
                ", tname='" + tname + '\'' +
                '}';
    }
}
