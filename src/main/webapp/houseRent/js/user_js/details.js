//用户查询所有房屋展示
Vue.component('house-details', {
    props:['houseinfo'],
    template:
        '<DIV>' +
        '        <DIV class=lefter>\n' +
        '            <H1>{{houseinfo.title}}</H1>\n' +
        '            <DIV class=subinfo>{{houseinfo.pubdate}}</DIV>\n' +
        '            <DIV class=houseinfo>\n' +
        '                <P>户　　型：<SPAN>{{houseinfo.tname}}</SPAN></P>\n' +
        '                <P>面　　积：<SPAN>{{houseinfo.floorage}}m<SUP>2</SUP></SPAN></P>\n' +
        '                <P>位　　置：<SPAN>{{houseinfo.dname}}区{{houseinfo.sname}}</SPAN></P>\n' +
        '                <P>联系方式：<SPAN>{{houseinfo.contact}}</SPAN></P></DIV>\n' +
        '        </DIV>\n' +
        '        <DIV class=side>\n' +
        '            <P><A class=bold href="http://localhost:8080/House-2/#">北京青鸟房地产经纪公司</A></P>\n' +
        '            <P>资质证书：有</P>\n' +
        '            <P>内部编号:1000</P>\n' +
        '            <P>联 系 人：</P>\n' +
        '            <P>联系电话：<SPAN></SPAN></P>\n' +
        '            <P>手机号码：<SPAN>暂无</SPAN></P></DIV>\n' +
        '        <DIV class=clear></DIV>\n' +
        '        <DIV class=introduction>\n' +
        '            <H2><SPAN><STRONG>房源详细信息</STRONG></SPAN></H2>\n' +
        '            <DIV class=content>\n' +
        '                <P>{{houseinfo.description}}</P>' +
        '        </DIV>\n' +
        '        </DIV>' +
        '</DIV>'
});

var app = new Vue({
    el: '#app',
    data:{
        path:'http://localhost:80/',
        houseInfo:{}
    }
});

//查询房屋的详细信息
function searchHouseDetails(){
    $.ajax({
        url:"/user/houseEx/selectHouseExByHouseId.do",
        type:"post",
        data:{houseId:GetQueryString('houseId')},
        dataType:"json",
        xhrFields:{
            withCredentials: true
        },
        success:function(result) {
            result = result.houseInfo;
            if (result != null) {
                result.pubdate = current2Date(result.pubdate);
                app.houseInfo = result;
            } else {
                alert("系统提示", "<font color=red>查找房屋详细信息失败</font>");
            }
        }
    });
}
