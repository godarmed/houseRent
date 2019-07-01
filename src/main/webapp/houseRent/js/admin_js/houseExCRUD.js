//请求路径
var requestUrl = {
    update:"/admin/houseEx/setHousePass.do",
    search:"/admin/houseEx/selectHouseByAdmin.do"
};



//查询房屋方法
function searchHouse(isdel,ispass){
    //加载房屋信息
    $('#userList').datagrid({
        toolbar: '#ToolBar', //自定义工具栏
        url:requestUrl.search,
        method: 'post',
        fitColumns: true,
        sortName: 'id',
        sortOrder: 'desc',
        idField: 'id',
        pagination: true,
        pageSize: 5,
        pageList: [2, 5, 10, 15, 20, 25],
        striped: true, //奇偶行是否区分
        singleSelect: false,//单选模式
        //rownumbers: true,//行号
        queryParams: {//查询条件
            "typeId":$("#add_action_typeId").val(),
            "minPrice":$("#minPrice").val(),
            "maxPrice":$("#maxPrice").val(),
            "streetId":$("#add_action_streetId").val(),
            "isdel":isdel,  //删除状态码为0
            "ispass":ispass,  //审核状态码为0
        },
        columns: [[
            { field: 'cb', checkbox:'true'},
            { field: 'id', title: '房屋编号', width: 80,sortable:true},
            { field: 'userId', title: '用户编号', width: 80,sortable:true },
            { field: 'tname', title: '户型', width: 80,sortable:true },
            { field: 'title', title: '标题', width: 80,sortable:true },
            { field: 'description', title: '描述', width: 80,sortable:true },
            { field: 'price', title: '价格', width: 80,sortable:true },
            { field: 'pubdate', title: '发布日期', width: 80,sortable:true ,
                formatter:function (value,row,index) {
                    return "<input readonly style='border:0;' type='date' value='" + current2Date(row.pubdate) + "' />";
                }
            },
            { field: 'dname', title: '区域名称', width: 80,sortable:true },
            { field: 'sname', title: '街道名称', width: 80,sortable:true },
            { field: 'ispass', title: '审核情况', width: 80,sortable:true,
                formatter:function(value,row,index){
                    return row.ispass==0?"未通过":"已通过";
                }
            },
            { field: 'operation', title: '操作', width: 80,
                formatter:function(value,row,index){
                    return "<a href='javascript:changeHouseStatus("+ row.id +"," + row.ispass + ")'>审核状态变更</a>";
                }
            }
        ]]
    });
}

//修改房屋审核状态
function changeHouseStatus(id,ispass){
    //查询某区域的街道
    $.post(requestUrl.update,{id:id,ispass:ispass},function(result){
        if(result.success){
            console.log("修改审核状态成功!")
        }else{
            alert("系统提示", "<font color=red>查找街道失败</font>");
        }
        searchHouse(0,ispass);
    },"json");
}

//查询街道
function searchStreets(districtId){
    //查询某区域的街道
    $.post("/admin/street/selectStreet.do",{page:1,rows:100,districtId:districtId},function(result){
        if(result != null){
            //console.log(result);
            var options = ["<option value=''>所有街道</option>"];
            if(districtId != ''){   //不是所有区域则添加街道
                for(var row in result.rows){
                    options.push("<option value='");
                    options.push(result.rows[row].id);
                    options.push("'>");
                    options.push(result.rows[row].name);
                    options.push("</option>");
                }
            }
            //添加街道的下拉框
            $("#add_action_streetId").html(options.join(""));
        }else{
            alert("系统提示", "<font color=red>查找街道失败</font>");
        }
        return;
    },"json");
}

//查询区域
function searchDistricts(){
    //查询所有区域
    $.post("/admin/district/selectDistrict.do",{page:1,rows:100},function(result){
        if(result != null){
            //console.log(result);
            var options = ["<option value=''>所有区域</option>"];
            for(var row in result.rows){
                options.push("<option value='");
                options.push(result.rows[row].id);
                options.push("'>");
                options.push(result.rows[row].name);
                options.push("</option>");
            }
            //添加区域的下拉框
            $("#add_action_districtId").html(options.join(""));
        }else{
            alert("系统提示", "<font color=red>查找区域失败</font>");
        }
        /*查询街道*/
        searchStreets($("#add_action_districtId").val());
        return;
    },"json");
}

//查询户型
function searchTypes(){
    //查询所有户型
    $.post("/admin/type/selectType.do",{page:1,rows:100,name:''},function(result){
        if(result != null){
            var options = ["<option value=''>所有户型</option>"];
            for(var row in result.rows){
                options.push("<option value='");
                options.push(result.rows[row].id);
                options.push("'>");
                options.push(result.rows[row].name);
                options.push("</option>");
            }
            //添加户型的下拉框
            $("#add_action_typeId").html(options.join(""));
        }else{
            alert("系统提示", "<font color=red>查找户型失败</font>");
        }
        return;
    },"json");
}

//时间戳转换(回显)
function current2Date(currentTime){
    var date = new Date(currentTime);
    //添加0
    function addZero(num){
        if(num<10){
            num = '0' + num;
        }
        return num;
    }
    date = (date.getFullYear()) +"-" + addZero(date.getMonth() + 1 ) + "-" + addZero(date.getDate());
    return date;
}

