$(function(){
	//加载t_user表事故隐患有，在easyui的datagrid中显示
	$('#btnsave').on('click',function(){
		var isValid=$('form').form('validate');
		if(isValid){
			$.ajax({
				type:'post',
				url:'/webproject/useruontroller.do?type=add',
				data:$('#f1').serialize(),
				success:function(data){
					if(data=="1"){
						//清空input
						$('#f1').form('clear')
						//关闭dialog
						$('#dialog').dialog('close');
						//刷新datagrid
						$('#tab').datagrid('reload');
					}else{
						$.messager.alert('失败','错误','info');    
					}
				}
			})		
		}
		else {
			$.messager.alert('失败1','错误1','info');  
		}
	})
	
	$('#btncancel').on('click',function(){
		$('#f1').form('clear')
		//关闭dialog
		$('#dialog').dialog('close');
		//刷新datagrid
		$('#tab').datagrid('reload');
	})
	$('#tab').datagrid({
		url:'/webproject/useruontroller.do',
		title:'用户信息',
		iconCls:'icon-edit',
		collapsible:true,
		idField:'userid',//主键
		queryParams: {type:'list'},//传参
		loadMag:'正在加载，请稍后....',	
		rownumbers:true,//是否显示行号
		singleSelect:true,//设置只能同时选择一行数据
		toolbar:[
		         {text:'编辑',iconCls:'icon-edit',handler:function(){
		        	 //非空判断
		        	 var  row =$('#tab').datagrid('getSelected');
		        	 if(row==null){
		        		 $.messager.alert('编辑提示','请选择要编辑的行','info')
		        	 }else{
		        		 //设置默认值
		        		 $('#uemail').textbox('setValue',row.uemail)
		        		 $('#uloginid').textbox('setValue',row.uloginid)
		        		 $('#upassword').textbox('setValue',row.upassword)
		        		 $('#utel').textbox('setValue',row.utel)
		        		 $('#uaddress').textbox('setValue',row.uaddress)    
		        		 $('#usex').textbox('setValue',row.usex)    		        		 
		        		 $('#dialog').dialog({
		        			 closed:false,
		        			 title:'编辑用户',
		        			 iconCls:'icon-edit',
		        			 buttons:[{text:'保存修改',iconCls:'icon-save',handler:function(){
		        				 $.ajax({
		        						type:'post',
		        						url:'/webproject/useruontroller.do?type=edit&userid='+row.userid,
		        						data:$('#f1').serialize(),
		        						success:function(data){
		        							if(data=="1"){
		        								$.messager.alert('修改提示','修改成功','info')
		        								$('#dialog').dialog('close');
		        								$('#tab').datagrid('reload');
		        							}else{
		        								$.messager.alert('修改提示','修改失败','info')
		        							}
		        						}
		        					})
		        			 }
		        			 }]
		        		 
		        		 })
		        	 }
		        	 
		        	 
		         }},'-',
		         {text:'删除',iconCls:'icon-remove',handler:function(){
//		        	 $.messager.alert('删除','实现删除功能','info')
		        	 //拿到删除的数据
		        	 var  row =$('#tab').datagrid('getSelected');
		        	 if(row==null){
		        		 $.messager.alert('删除提示','请选择要删除的行','info')
		        	 }else{
		        		 $.messager.confirm('删除确认', '您想要删除数据:'+row.userid+'吗？', function(r){
		        				if (r){//实现删除
		        					$.ajax({
		        						type:'post',
		        						url:'/webproject/useruontroller.do',
		        						data:{type:'remove',userid:row.userid},
		        						success:function(data){
		        							if(data=="1"){
		        								$.messager.alert('删除提示','删除成功','info')
		        								$('#tab').datagrid('reload');
		        							}else{
		        								$.messager.alert('删除提示','删除失败','info')
		        							}
		        						}
		        					})
		        				}
		        			});
		        	 }
		         }},'-',
		         {text:'添加',iconCls:'icon-add',handler:function(){	
		        	 	$('#dialog').dialog('open');
		         }},
		         {text:'<input id="searchtext" style="width:150px" list="ds" ><datalist id="ds"></datalist>'},
		         {text:'搜索',iconCls:'icon-search',handler:function(){
	            		var val = $('#searchtext').val();
//	            		console.log(val)
	            		if (val!="") {
	            			$.ajax({
	            				type:'post',
	            				url:'/webproject/searchdatacontroller.do',
	            				data:{key:val,type:'user'},
	            				success:function(data){
	            					loadUser(data,val);
	            				}
	            				
	            			})

						}
	            		else{
	            			loadUser(0,'')
						}
	            	 }},'-',
	            {text:'显示全部',iconCls:'icon-reload',handler:function(){loadUser(0,'')}}	         	
		         ],
		columns:[[
		    {
		    	field:'chk',checkbox:true
		    },{
				field:'userid',title:'编号'
			},{
				field:'uemail',title:'邮箱'
			},{
				field:'uloginid',title:'帐号'
			},{
				field:'upassword',title:'密码'
			},{
				field:'usex',title:'性别'			
			},{
				field:'uaddress',title:'地址'
			},{
				field:'utel',title:'电话'
			},{
				field:'ustateid',title:'状态编号'
			},{
				field:'uroleid',title:'角色编号'
			}]]
	});
})
function loadUser(json,val){
	if(val!=''){
		$('#tab').datagrid({
			url:'',
			data:json
		})
	}
	else{
		$('#tab').datagrid({
			url:'/webproject/useruontroller.do'
		})
	}	
	$('#searchtext').on('input',function(){
		var val = $("#searchtext").val();
		$.ajax({
				type:'post',
				url:'/webproject/searchdatacontroller.do',
				data:{key:val,type:'user'},
				success:function(data){
					if(data!=null){
						var options="";
						var length = data.length;
						if(length>5){
							length=5
						}
						for(var i=0;i<length;i++){
							options+="<option value='"+ data[i].uloginid +"'/>";
						}
						var ds = $("#ds");
						$("#ds").html(options);
					}
				}
				
			})
	})
	
}