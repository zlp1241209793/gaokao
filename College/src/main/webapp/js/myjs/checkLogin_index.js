$(
	function checkLogin() {
		$.post("memberInfo/checkLogin", null, function(data) {
			if (data.code != 200) { // 说明没有登录
				$("#myem").html("未登录");
			} else { // 说明登陆了
				$("#myem").html("欢迎您 " + data.data.nickName);
			}
		}, "json");
	}
)