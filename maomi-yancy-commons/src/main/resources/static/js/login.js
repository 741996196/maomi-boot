$(function () {
    alert("欢迎来看王祖贤！")
})


$("#btnSubmit").click(function(){
        var username = $("input[name=username]").val();
        var password = $("input[name=password]").val();

        $.ajax({
            type: "post",
            url: "/user/login",
            // contentType : 'application/json',
            // dataType:JSON,
            data:{"username": username, "password": password, "roleId":1,"rememberMe":false},
            success: function (r) {
                if (r != undefined) {
                    location.href="/success";
                }

            }
        })
}
)