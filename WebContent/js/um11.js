$(function(){ 
	
	$('#btnedit').on('click',function(){
		var isValid=$('form').form('validate')
		if(isValid){
			 $.ajax({
				type:'post',
				data:$('#f1').serialize(),
				url:'/webproject/usercontroller.do?type=edit',
				success:function(data){
					if(data=="1"){
						$('#win').window('close');
						$('#f1').form('clear');
						$.messager.alert('提示','修改成功!!','info');
						$('#tab').datagrid('reload');
					}else{
						$.messager.alert('提示','修改失败1!!','info');
					}
				}
			});
		 
	 }else{
		 $.messager.alert('提示','修改失败2','info');
	 }
		
	})
	
	$('#btnsave').on('click',function(){
   	 var isValid=$('form').form('validate')
		 if(isValid){
				 $.ajax({
					type:'post',
					data:$('#f1').serialize(),
					url:'/webproject/usercontroller.do?type=add',
					success:function(data){
						if(data=="1"){
							$('#win').window('close');
							$('#f1').form('clear');
							$.messager.alert('提示','添加成功!!','info');
							$('#tab').datagrid('reload');
						}else{
							$.messager.alert('提示','添加失败!!','info');
						}
					}
				});
			 
		 }else{
			 $.messager.alert('提示','添加失败','info');
		 }
		 });
	//加载t_user数据 在easyui的datagrid中显示
	$('#tab').datagrid({    
	    url:'/webproject/usercontroller.do', 
	    title:'用户信息',
	    collapsible:true,
	    iconCls:'icon-ok',
	    idField:'userid',
	    rownumbers:true,
	    loadMsg:'数据正在加载，请稍候',
	    queryParams:{type:'list'},
	    singleSelect:true,
	    toolbar:[
	             {text:'编辑',iconCls:'icon-edit',handler:function(){
	            	 var row = $('#tab').datagrid('getSelected');
	         		if(row==null){
	         			$.messager.alert('提示','请选择要修改的行','info');
	         		}else{
	         			$('#footer1').hide();
	         			$('#uemail').textbox('setValue',row.uemail);
	         			$('#uname').textbox('setValue',row.uloginid);
	         			$('#uname').textbox({readonly:true});
	         			$('#upassword').textbox('setValue',row.upassword);
	         		    $("input:radio[value='"+row.usex+"']").prop("checked",'true');
	         			$('#utel').textbox('setValue',row.utel);
	         			$('#uaddress').textbox('setValue',row.uaddress);
	         			$('#win').dialog({iconCls:'icon-edit',title:'修改用户',buttons:'#footer2'})
	         			$('#win').window('open');	
	         		}
	             }},'-',
	             {text:'添加',iconCls:'icon-add',handler:function(){
	            	 $('#footer2').hide()
	            	 $('#uname').textbox({readonly:false});
	            	 $('#win').dialog({iconCls:'icon-add',title:'添加用户',buttons:'#footer1'}) 
	            	 $('#win').window('open');  
	            	 
	             }},'-',
	             {text:'删除',iconCls:'icon-remove',handler:function(){
	            	 var row = $('#tab').datagrid('getSelected');
	            	 if(row==null){
	            		 $.messager.alert('删除提示','请选择要删除的行','info');
	            	 }else{
	            		 $.messager.confirm("删除确认", "您确认要删除"+row.uloginid+"数据吗？", function(r){
	            			 if (r){//r返回的是布尔型，确定删除就是true反之为false
	            				    // 退出操作;
	            					$.ajax({
	            						type:'post',
	            						url:'/webproject/usercontroller.do',
	            						data:{type:'remove',userid:row.userid},
	            						success:function(data){
	            							if(data=="1"){
	            								$.messager.alert('删除提示','删除成功！！','info');
	            								$('#tab').datagrid('reload');
	            							}
	            						}
	            					})
	            			 }
	            			 
	            		 });
	            	 }
	             }},'-',{text:'<input id="searchtext" style="width:150px" list="ds" ><datalist id="ds"></datalist>'},
	             {text:'搜索',iconCls:'icon-search',
	            	 handler:function(){
	            		var val = $('#searchtext').val();
	         			$.ajax({
	         					type:'post',
	         					url:'/webproject/searchdatacontroller.do',
	         					data:{key:val,type:'user'},
	         					success:function(data){
	         							loadUser(data,val);
	         					}
	         					
	         				})
	            	 }}
	             ,'-',
	             {text:'显示全部',iconCls:'icon-reload',handler:function(){loadUser(0,'')}}
	             ],
	    columns:[[    
	        {field:'chk',checkbox:true},   
	        {field:'userid',title:'编号',width:200},    
	        {field:'uemail',title:'邮箱',width:100},    
	        {field:'uloginid',title:'账号',width:100,align:'right'}, 
	        {field:'upassword',title:'密码',width:100,align:'right'},
	        {field:'usex',title:'性别',width:50,align:'right'},
	        {field:'uaddress',title:'地址',width:100,align:'right'},
	        {field:'utel',title:'电话',width:100,align:'right'},
	        {field:'ustateid',title:'状态编号',width:200,align:'right'},
	        {field:'uroleid',title:'角色编号',width:200,align:'right'},
	    ]]    
	});
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
			url:'/webproject/usercontroller.do',
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