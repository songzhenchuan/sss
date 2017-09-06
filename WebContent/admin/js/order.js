$(function(){
	$('#tab').datagrid({
		url:'/webproject/useruontroller.do',
		title:'订单信息',
		iconCls:'icon-edit',
		collapsible:true,
		idField:'orderid',
		queryParams: {type:'order'},//传参
		loadMag:'正在加载，请稍后....',	
		rownumbers:true,//是否显示行号
		singleSelect:true,//设置只能同时选择一行数据
		toolbar:[
		         {text:'添加',iconCls:'icon-add',handler:function(){
		        	 alert("添加功能")
		         }},'-',
		         {text:'删除',iconCls:'icon-remove',handler:function(){
		        	 alert("删除功能")
		         }},'-',
		         {text:'编辑',iconCls:'icon-edit',handler:function(){
		        	 alert("编辑功能")
		         }}
		        	 
		         ],
		  columns:[[
		           {field:'chk',checkbox:true},
		           {field:'orderid',title:'订单编号'},
		           {field:'gid',title:'商品编号'},
		           {field:'userid',title:'用户'},
		           {field:'totalprice',title:'总价'},
		           {field:'orderdate',title:'订单资料'}
		           ]]
	})
	
})