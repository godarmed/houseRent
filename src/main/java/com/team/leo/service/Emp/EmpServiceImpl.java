package com.team.leo.service.Emp;

import com.team.leo.entity.Emp;
import com.team.leo.mapper.EmpMapper;
import com.team.leo.util.Params;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Emp> selectEmpAll(Params params) {
        if(params == null || params.getPageNum() == null){
            params = new Params(1,6);
        }
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        return empMapper.selectEmpAll(params);
    }

    @Override
    public Emp selectEmpById(Integer id) {
        return empMapper.selectEmpById(id);
    }

    @Override
    public void saveEmp(Emp emp) {
        empMapper.saveEmp(emp);
    }

    @Override
    public void deleteEmp(Integer id) {
        empMapper.deleteEmp(id);
    }

    @Override
    public void updateEmp(Emp emp) {
        empMapper.updateEmp(emp);
    }
}
