//请求路径
var requestUrl = {
    save:"/admin/district/saveDistrict.do",
    update:"/admin/district/modifyDistrict.do",
    delete:"/admin/district/delDistrict.do",
    search:"/admin/district/selectDistrict.do"
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
                return 0;
            },"json");
        }
    });
}

//查询街道弹窗
function searchStreet(districtId){
    //打开添加窗口
    $("#StreetDialog").dialog('open').dialog('setTitle',"街道操作");
    //调用iframe中的方法
    $("#streets")[0].contentWindow.getByDistrictPage(districtId);
}

//查询区域方法
function searchDistrict(){
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
            "name": $('#name').val()
        },
        columns: [[
            { field:'cb', checkbox:'true'},
            { field: 'id', title: '编号', width: 80,sortable:true},
            { field: 'name', title: '区域名', width: 80,sortable:true },
            { field: 'operation', title: '操作', width: 80,
                formatter:function(value,row,index){
                    return "<a href='javascript:DeleteOne("+ row.id +")'>删除</a>" +
                        " | <a href='javascript:searchStreet("+ row.id +")'>查看街道</a>";
                }
            }
        ]]
    });
}



