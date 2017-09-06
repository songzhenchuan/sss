<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#uname').on('input',function(){
			//通过ajax请求服务器资源
			$.ajax({
					data:{'uname':$(this).val()},
					dataType:'text',//接收服务器传来的数据的类型
					type:'GET',
					url:'/webproject/CheckName.do',//必须设置
					success:function(a,b,c,d){
						console.log(a);
						console.log(b);
						console.log(c);
						console.log(d);
						if (a=="1") {
							$('span:first').html('已经被注册')
						}
						else if(a=="0"){
							$('span:first').html('可以注册')
						}
					}
			})
			/*  $.get('/webproject/CheckName.do',{'uname':$(this).val()},
			function(data){
				if (data=="1") {
					$('span:first').html('已经被注册')
				}
				else if(data=="0"){
					$('span:first').html('可以注册')
				
				}
			})  */
			/*  $.post('/webproject/CheckName.do',{'uname':$(this).val()},
						function(data){
							if (data=="1") {
								$('span:first').html('已经被注册')
							}
							else if(data=="0"){
								$('span:first').html('可以注册')
							
							}
						})  */

		})
	})
</script>
<title>Insert title here</title>
</head>
<body>
	帐号：<input type="text" id="uname"><span></span><br>
	邮箱：<input type="text" id="uemail"><span></span><br>
</body>
</html>