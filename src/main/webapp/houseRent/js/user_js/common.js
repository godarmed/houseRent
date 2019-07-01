$(function(){
    /*判断用户是否登陆并获取用户信息*/
    getUserName();
});

/*获取用户信息*/
function getUserName(){
    $.ajax({
        url:"/user/users/getUserName.do",
        type:"get",
        data:"",
        dataType:"json",
        xhrFields:{
            withCredentials: true
        },
        success:function(result){
            if(result.userName != null){
                $("#welcomeUser").html("欢迎" + result.userName);
            }else{
                console.log("用户名不存在,请重新登陆!");
                location.href='login.htm';
            }
        }
    })
    //console.log($.cookie('userInfo','',{ expires: -1,path:'/users'}).val());
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

//字符串拆分
function string2Secton(str){
    return str.split('-');
}

//退出登录方法
function quit(){
    //确定是否要退出
    if(confirm("您真的要退出么?")) {
        //删除cookie
        $.cookie("userInfo", null);
        //页面重定向
        document.location = "login.htm"
    }
}

//获取url参数
function GetQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
    if(r!=null)return  unescape(r[2]); return null;
}