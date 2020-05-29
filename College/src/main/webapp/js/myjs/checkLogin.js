function checkLogin(myfunction) {
	$.post("../memberInfo/checkLogin", null, function(data) {
		if (data.code != 200) { // 说明没有登录
			alert("请先登录...");
			location.href = "login.html";
		} else { // 说明登陆了
			$("#loginInfo").val(data.data.nickName);
			$("#login_usid").val(data.data.mno);
			$("#login_academic").val(data.data.academic);
		}
		myfunction();
	}, "json");
}