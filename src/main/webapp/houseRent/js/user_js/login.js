//获取验证码功能
$("#getVercode").click(function () {
    $.post("/user/users/getVercode.do",
        {
            userPhone:$("#user_phone").val()
        },
        function (result) {
            if (result.success) {
                alert("验证码已发出!");
            } else {
                alert("手机号错误!请重新输入手机号!");
            }
        }, "json");
});

//登录功能
function login() {
    $.post("/user/users/userLogin.do",
        {
            name:$("#user_name").val(),
            password:$("#user_password").val(),
            userVercode:$("#user_vercode").val()
        },
        function (result) {
            if (result.success) {
                document.location = "guanli.htm";
            } else {
                alert("用户名或者密码错误！");
        }
    }, "json");
}

//根据cookie自动登录
function autoLogin() {
    if($.cookie("userInfo") != undefined){
        $.post("/user/users/isUserExist.do", {name:$.cookie("userInfo")}, function (result) {
            if (result.success) {
                document.location = "guanli.htm";
            } else {
                console.log("cookie格式错误或过期！");
            }
        }, "json");
    }
}