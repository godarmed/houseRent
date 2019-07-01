//登录功能
function login() {
    $.post("/user/users/userLogin.do", {name:$("#user_name").val(),password:$("#user_password").val()}, function (result) {
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