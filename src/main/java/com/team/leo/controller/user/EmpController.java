package com.team.leo.controller.user;

import com.team.leo.entity.Emp;
import com.team.leo.service.Emp.EmpService;
import com.team.leo.util.Params;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller("UserEmpController")
@RequestMapping("/user/emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    @RequestMapping(value = "/selectEmpAllWithoutParams")
    public String selectEmpAll(Model model) {
        List<Emp> empList = empService.selectEmpAll(null);
        PageInfo pageInfo = new PageInfo(empList);
        model.addAttribute("pageInfo",pageInfo);
        return "emp/list";
    }

    @RequestMapping(value = "/selectEmpAll")
    public String selectEmpAll(Model model, Params params) {
        List<Emp> empList = empService.selectEmpAll(params);
        PageInfo pageInfo = new PageInfo(empList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("params",params);
        return "emp/list";
    }

    //save empInfo
    @RequestMapping(value = "/savePageEmp")
    public String toSavePage() {
        return "emp/save";
    }

    @RequestMapping(value = "/saveEmp")
    public String save(Emp emp) {
        empService.saveEmp(emp);
        return "forward:selectEmpAllWithoutParams";
    }

    //update empInfo
    @RequestMapping(value = "/updatePageEmp/{empId}")
    public String toUpdatePage(@PathVariable Integer empId,Model model) {
        Emp emp = empService.selectEmpById(empId);
        model.addAttribute("emp",emp);
        return "emp/edit";
    }

    @RequestMapping(value = "/updateEmp")
    public String update(Emp emp) {
        empService.updateEmp(emp);
        return "forward:selectEmpAllWithoutParams";
    }

    //delete empInfo
    @RequestMapping(value = "/deleteEmpById/{empId}")
    public String deleteById(@PathVariable Integer empId) {
        empService.deleteEmp(empId);
        return "redirect:/emp/selectEmpAllWithoutParams";
    }
}
