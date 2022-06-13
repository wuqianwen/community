$(function(){
	$("#sendBtn").click(send_letter);
	$(".close").click(delete_msg);
});

function send_letter() {
	$("#sendModal").modal("hide");
	// 获取私信用户名和内容
	var toName = $('#recipient-name').val();
	var content = $('#message-text').val();
	// 发送异步请求
	$.post(
		CONTEXT_PATH + "/letter/send",
		{"toName":toName, "content":content},
		function (data){
			data = $.parseJSON(data);
			// 提示框中显示返回消息
			$("#hintBody").text(data.msg);
			// 显示提示框
			$("#hintModal").modal("show");
			// 2秒后自动隐藏提示框
			setTimeout(function(){
				$("#hintModal").modal("hide");
				// 刷新页面
				if(data.code == 0){
					window.location.reload();
				}
			}, 2000);
		}
	)


}

function delete_msg() {
	// TODO 删除数据
	$(this).parents(".media").remove();
}