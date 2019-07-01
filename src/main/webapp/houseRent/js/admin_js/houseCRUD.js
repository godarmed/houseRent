//请求路径
var requestUrl = {
    save:"/admin/house/saveHouse.do",
    update:"/admin/house/modifyHouse.do",
    delete:"/admin/house/delHouse.do",
    search:"/admin/house/selectHouse.do"
};

//清空窗口中的值
function ResetValues(dialogId){
    $("#"+dialogId+" input").val('');
}

//关闭窗口方法
function CloseDialog(dialogId){
    ResetValues(dialogId);
    $("#" + dialogId).dialog('close');
}

//添加方法
function OpenSaveDialog(){
    //打开添加窗口
    $("#AddDialog").dialog('open').dialog('setTitle',"添加数据");
}
function Save(){
    //表单异步提交添加
    $("#SaveDialogForm").form('submit',{
        url:requestUrl.save,
        onSubmit:function(){
            return $(this).form('validate');
        },
        success:function(result){
            console.log(result)
            result = eval('('+result+')');
            if(result.success){
                $.messager.alert("系统提示","保存成功");
                ResetValues("AddDialog");//清空窗口中的数据
                $("#AddDialog").dialog('close'); //关闭
                $("#userList").datagrid('reload'); //刷新
            }else{
                $.messager.alert("系统提示","保存失败");
                return;
            }
        }
    });
}

//修改方法
function OpenModifyDialog(){
    //获取选中的行
    var SelectRows = $("#userList").datagrid('getSelections');
    if( SelectRows.length != 1 ){
        $.messager.alert("系统提示", "请选择一行要修改的数据");
        return;
    }
    var SelectRow = SelectRows[0];

    //打开修改窗口
    $("#ModifyDialog").dialog('open').dialog('setTitle',"修改数据");

    //获得行对象的数据加载到表单中显示
    $("#ModifyDialogForm").form('load',SelectRow);
}
function Modify(){
    //表单异步提交修改
    $("#ModifyDialogForm").form('submit',{
        url:requestUrl.update,
        onSubmit:function(){
            return $(this).form('validate');
        },
        success:function(result){
            result = eval('('+result+')');
            if(result.success){
                $.messager.alert("系统提示","修改成功");
                ResetValues("ModifyDialog");//清空窗口中的数据
                $("#ModifyDialog").dialog('close'); //关闭
                $("#userList").datagrid('reload'); //刷新
            }else{
                $.messager.alert("系统提示","修改失败");
                return;
            }
        }
    });
}

//删除方法
function Delete(){
    //获取选择行
    var SelectRows = $("#userList").datagrid('getSelections');
    if( SelectRows.length == 0 ){
        $.messager.alert("系统提示", "请选择要删除的数据");
        return;
    }
    //组装json对象
    var SelectIndexArr = [];
    for( var i = 0 ; i < SelectRows.length; i++ ){
        SelectIndexArr.push(SelectRows[i].id);
    }
    var SelectIndexToString = {ids:SelectIndexArr.join(",")};

    //确定是否要删除
    $.messager.confirm("系统提示", "你确定要删除<font color=red> " + SelectRows.length + " </font>条数据吗?", function(xo){
        if( xo ){
            $("#userList").datagrid('reload');
            //支持多项删除
            $.post(requestUrl.delete,SelectIndexToString,function(result){
                if(result.success){
                    $.messager.alert("系统提示", "你已成功删除 <font color=green> " + SelectRows.length + " </font>条数据!~");
                    $("#data").datagrid('reload');
                }else{
                    $.messager.alert("系统提示", "<font color=red>删除失败</font>");
                }
            },"json");
        }
    });
    $("#userList").datagrid('reload'); //刷新
}

//单项删除方法
function DeleteOne(id){
    console.log(1);
    //确定是否要删除
    $.messager.confirm("系统提示", "你确定要删除该条数据吗?", function(xo){
        if( xo ){
            //支持多项删除
            $.post(requestUrl.delete,{ids:id},function(result){
                if(result.success){
                    $.messager.alert("系统提示", "你已成功删除 <font color=green>1</font>条数据!~");
                    $("#userList").datagrid('reload'); //刷新
                }else{
                    $.messager.alert("系统提示", "<font color=red>删除失败</font>");
                }
                return;
            },"json");
        }
    });
}

//查询房屋类型方法
function searchHouse(){
    //加载用户信息
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
            "name": $('#name').val(),
        },
        columns: [[
            { field: 'cb', checkbox:'true'},
            { field: 'id', title: '编号', width: 80,sortable:true},
            { field: 'name', title: '房屋类型', width: 80,sortable:true },
            { field: 'operation', title: '操作', width: 80,
                formatter:function(value,row,index){
                    return "<a href='javascript:DeleteOne("+ row.id +")'>删除</a>";
                }
            }
        ]]
    });
}

//查询街道
function searchStreets(districtId){
    //查询某区域的街道
    $.post("/admin/street/selectStreet.do",{page:1,rows:100,districtId:districtId},function(result){
        if(result != null){
            //console.log(result);
            var options = [];
            for(var row in result.rows){
                options.push("<option value='");
                options.push(result.rows[row].id);
                options.push("'>");
                options.push(result.rows[row].name);
                options.push("</option>");
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
            var options = [];
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
            var options = [];
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



