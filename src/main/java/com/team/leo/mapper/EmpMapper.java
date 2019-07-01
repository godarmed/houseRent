package com.team.leo.mapper;

import com.team.leo.entity.Emp;
import com.team.leo.util.Params;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {
    List<Emp> selectEmpAll(Params params);
    Emp selectEmpById(Integer id);
    void saveEmp(Emp emp);
    void deleteEmp(Integer id);
    void updateEmp(Emp emp);
}
