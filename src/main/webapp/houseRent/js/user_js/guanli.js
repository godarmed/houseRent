var pageInfo = {
    pageNum:1,
    pageSize:$("#pageSize").val(),
    pages:{},
    houseList:[]
};

//用户查询所有房屋展示
Vue.component('all-houses', {
    props:['todo','path'],
    template:
    '   <TR>\n' +
    '            <TD class=house-thumb>' +
    '               <span>' +
    '                   <A v-bind:href="\'details.htm?houseId=\'+todo.id" target="_blank">' +
    '                       <img v-bind:src=" path + todo.path" width="100" height="75" alt="">' +
    '                   </A>' +
    '               </span>' +
    '            </TD>\n' +
    '            <TD>\n' +
    '                <DL>\n' +
    '                    <DT><A v-bind:href="\'details.htm?houseId=\'+todo.id" >{{todo.title}}</A></DT>\n' +
    '                    <DD>{{ todo.dname }}区{{todo.sname}},{{todo.floorage}}平米<BR/>联系方式：{{todo.contact}} </DD>' +
    '               </DL>' +
    '            </TD>\n' +
    '            <TD class=house-type>{{todo.tname}}</TD>\n' +
    '            <TD class=house-price><SPAN>{{todo.price}}</SPAN>元/月</TD>\n' +
    '   </TR>'
});

//用户自身房屋展示
Vue.component('user-houses', {
    props:['todo','path'],
    methods:{
        /*修改房屋方法*/
        update(){
            var url = "update.htm?houseId=" + this.todo.id;//此处拼接内容
            window.location.href = url;
        },
        /*删除房屋方法*/
        deleteOneHouse(){
            //删除房屋
            $.ajax({
                url:"/user/house/delHouse.do",
                type:"post",
                data: {
                    ids:this.todo.id
                },
                dataType:"json",
                xhrFields:{
                    withCredentials: true
                },
                success:function(result) {
                    if (result.success) {
                        console.log("删除房屋成功!")
                    } else {
                        alert("系统提示", "<font color=red>删除房屋失败</font>");
                    }
                    searchHouses(0,null);    //更新房屋信息
                }
            });
        }
    },
    template:
        '<TR>\n' +
        '    <TD class=house-thumb>' +
        '       <SPAN>' +
        '          <A :href="\'details.htm?houseId=\'+todo.id">' +
        '               <img :src=" path + todo.path" width="100" height="75" alt="">' +
        '           </A>' +
        '       </SPAN>' +
        '    </TD>\n' +
        '    <TD>\n' +
        '      <DL>\n' +
        '        <DT><A :href="\'details.htm?houseId=\'+todo.id">{{todo.title}}</A></DT>\n' +
        '        <DD>{{ todo.dname }}区{{todo.sname}},{{todo.floorage}}平米<BR/>联系方式：{{todo.contact}} </DD></DL></TD>\n' +
        '    <TD>{{todo.ispass==0?"待审核":"已审核"}}</TD>' +
        '    <TD class=house-type><LABEL class=ui-green><INPUT @click="update()" value="修    改" type=button name=search /></LABEL></TD>\n' +
        '    <TD class=house-price><LABEL class=ui-green><INPUT @click="deleteOneHouse()" value="删    除" type=button name=search /></LABEL></TD>' +
        '</TR>'
});

//Vue对象
var app = new Vue({
    el: '#app',
    data:{
        isRouterShow: true,
        path:'http://localhost:80/',
        groceryList:pageInfo.houseList
    },
    methods: {
        /*重置房屋方法*/
        reload () {
            this.groceryList = pageInfo.houseList;
            this.isRouterShow = false;
            this.$nextTick(() => (this.isRouterShow = true));
        }
    }
});

//用户查询自身的房屋
function searchUserHouses(num){
    var data = {
        page:num==undefined?pageInfo.pageNum:num,   //查询的页码
        rows:pageInfo.pageSize,                     //每页的条数
        isdel:0,
        ispass:null
    };
    searchHouses(data,"/user/houseEx/selectHouseExByUserId.do");
}

//用户按条件查询所有已发布的房屋(带页数)
function searchAllHouses(num){
    var data = {
        title:$('#add_action_title').val(),  //标题
        minPrice:string2Secton($('#add_action_price').val())[0],  //起始价格
        maxPrice:string2Secton($('#add_action_price').val())[1],  //结束价格
        minFloorage:string2Secton($('#add_action_floorage').val())[0],  //结束价格
        maxFloorage:string2Secton($('#add_action_floorage').val())[1],  //结束价格
        districtId:$('#add_action_districtId').val(),  //所在区域
        streetId:$('#add_action_streetId ').val(),  //所在街道
        typeId:$('#add_action_typeId ').val(),  //户型
        page:num==undefined?pageInfo.pageNum:num,
        rows:pageInfo.pageSize,
        isdel:0,
        ispass:1
    };
    searchHouses(data,"/user/houseEx/selectHouseEx.do");
}

/*按条件查询房屋*/
function searchHouses(data,url){
    //查询用户发布的所有房屋
    $.ajax({
        url:url,
        type:"post",
        data:data ,
        dataType:"json",
        xhrFields:{
            withCredentials: true
        },
        success:function(result) {
            result = result.pageInfo;
            if (result != null) {
                resetInfo(result); //重置分页及房屋信息
                app.reload();   //房屋数据更新
            } else {
                alert("系统提示", "<font color=red>查找房屋失败</font>");
            }
        }
    });
}

/*重置分页和房屋信息*/
function resetInfo(result){
    $("#pageNum").html(result.pageNum);
    $("#pageSize").val(result.pageSize);
    $("#pages").html(result.pages);
    pageInfo.pageNum = result.pageNum;
    pageInfo.pageSize = result.pageSize;
    pageInfo.pages = result.pages;
    pageInfo.houseList = result.list;  //重新获取房屋数据
}

//翻页方法
function toPage(pageNum){
    switch(pageNum){
        case 0:
            pageInfo.pageNum = 1;
            break;
        case -1:
            pageInfo.pageNum = pageInfo.pageNum>1?pageInfo.pageNum-1:pageInfo.pageNum;
            break;
        case 1:
            pageInfo.pageNum = pageInfo.pageNum<pageInfo.pages?pageInfo.pageNum+1:pageInfo.pageNum;
            break;
        case 2:
            pageInfo.pageNum = pageInfo.pages;
            break;
        default:
    }
    //判断是哪个页面调用该方法
    if(window.location.pathname.indexOf("guanli") != -1){
        searchUserHouses();
    }else{
        searchAllHouses();
    }
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