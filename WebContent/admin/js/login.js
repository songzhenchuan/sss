$(function(){
	$('#login').dialog({
		width:400,
		height:200,
		title:'用户登录',
		collapsiible:true,
		iconCls:'icon-man',
		buttons:[{
			text:'登陆',
			iconCls:'icon-ok',
			handler:function(){
				//需要判断整个表单是否通过验证
				var isValid=$('form').form('validate');
				if(isValid){
					$.ajax({
						type:'post',
						url:'/webproject/loginController.do',
						data:$('form').serialize(),
						success:function(data){
							if(data=="1"){
								window.location='index.html'
							}else{
								$.messager.alert('登录失败','帐号密码错误','info');    
							}
						}
					});		
				}else{
					$.messager.alert('登录失败','表单错误','info');    
				}
			}
		},{
			text:'取消',
			handler:function(){alert('cancel')}
		}]
	});
})