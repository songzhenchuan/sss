$(function(){
	$('#cate').combobox({
		url:'/webproject/catecontroller.do',
		valueField:'cid',
		textField:'cname',
		onSelect:function(data){
//			var cid =$(this).combobox('getValue');
			loadGoods(data.cid);
		},
		onLoadSuccess:function(){//设置默认项
			var datas =$(this).combobox('getData');
			if(datas.length>0){//表示已绑定了数据(已获得了数据)
				$(this).combobox('setValue',datas[0].cid);
				var cid =$('#cate').combobox('getValue');
				loadGoods(cid);//加载默认类别数据
			}
		}
	});
	$('#btnsave').on('click',function(){
		var isValid=$('form').form('validate');
		if(isValid){
			$.ajax({
				type:'post',
				url:'/webproject/goodscontroller.do?type=add',
				data:$('#f1').serialize(),
				success:function(data){
					if(data=="1"){
						//清空input
						$('#f1').form('clear')
						//关闭dialog
						$('#dialog1').dialog('close');
						//刷新datagrid
						$('#tb').datagrid('reload');
					}else{
						$.messager.alert('失败','错误','info');    
					}
				}
			})		
		}
		else {
			$.messager.alert('错误','请填写完整数据','info');  
		}
	});
	$('#btncancel').on('click',function(){
		$('#f1').form('clear')
		//关闭dialog
		$('#dialog1').dialog('close');
		//刷新datagrid
		$('#tb').datagrid('reload');
	})
})
function loadGoods(cid){
	$('#tb').datagrid({
		url:'/webproject/goodscontroller.do?type=cate',
		queryParams:{cid:cid},
		title:'商品数据',
		iconCls:'icon-ok',
		conllapsible:true,
		pagination:true,
		loadMag:'正在加载，请稍后....',	
		pageSize:10,
		rownumbers:true,
		singleSelect:true,
		pageList:[5,10,15,20],
		toolbar:[
		         {text:'编辑',iconCls:'icon-edit',handler:function(){
		        	 //非空判断
		        	 var  row =$('#tb').datagrid('getSelected');
		        	 if(row==null){
		        		 $.messager.alert('编辑提示','请选择要编辑的行','info')
		        	 }else{
		        		 //设置默认值
		        		 $('#gid').textbox('setValue',row.gid)
		        		 $('#gtitle').textbox('setValue',row.gtitle)
		        		 $('#gauthor').textbox('setValue',row.gauthor)
		        		 $('#gsaleprice').textbox('setValue',row.gsaleprice)
		        		 $('#ginprice').textbox('setValue',row.ginprice)    
		        		 $('#gimg').textbox('setValue',row.gimg)  
		        		 if(row.gclicks==0){
		        			 row.gclicks ="0"
		        		 }
		        		 $('#gclicks').textbox('setValue',row.gclicks) 
		        		 $('#cid').textbox('setValue',row.cid)    		        		 
		        		 $('#pid').textbox('setValue',row.pid)    		        		 	        		 
		        		 $('#dialog1').dialog({
		        			 closed:false,
		        			 title:'编辑商品',
		        			 iconCls:'icon-edit',
		        			 buttons:[{text:'保存修改',iconCls:'icon-save',handler:function(){
		        				 $.ajax({
		        						type:'post',
		        						url:'/webproject/goodscontroller.do?type=edit&gid='+row.gid,
		        						data:$('#f1').serialize(),
		        						success:function(data){
		        							if(data=="1"){
		        								$.messager.alert('修改提示','修改成功','info')
		        								$('#dialog1').dialog('close');
		        								$('#tb').datagrid('reload');
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
		        	 var  row =$('#tb').datagrid('getSelected');
//		        	 console.log(row)
		        	 if(row==null){
		        		 $.messager.alert('删除提示','请选择要删除的行','info')
		        	 }else{
		        		 $.messager.confirm('删除确认', '您想要删除编号:'+row.gid+'的数据吗？', function(r){
		        				if (r){//实现删除
		        					$.ajax({
		        						type:'post',
		        						url:'/webproject/goodscontroller.do?type=remove&gid='+row.gid,
		        						success:function(data){
//		        							console.log(row.gid)
		        							if(data=="1"){
		        								$.messager.alert('删除提示','删除成功','info')
		        								$('#tb').datagrid('reload');
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
		        	 $('#dialog1').dialog('open');
		         }},
		         {text:'<input id="searchtext" style="width:150px" list="ds" ><datalist id="ds"></datalist>'},
		         {text:'搜索',iconCls:'icon-search',handler:function(){
		        	 var val = $('#searchtext').val();
		        	 if (val!="") {
	            			$.ajax({
	            				type:'post',
	            				url:'/webproject/searchdatacontroller.do',
	            				data:{key:val,type:'goods'},
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
		        
	columns:[
		         [{"title":"全选","colspan":1},{"title":"编号与商品名称","colspan":2},{"title":"作者到访问量","colspan":5},{"title":"类别出版社编号","colspan":2}],
		         [
		         {field:'chk',checkbox:true},
		         {field:'gid',title:'编号',"rowspan":1},
		         {field:'gtitle',title:'商品名称',"rowspan":1},
		         {field:'gauthor',title:'作者',"rowspan":1},
		         {field:'gsaleprice',title:'售价',"rowspan":1},
		         {field:'ginprice',title:'进价',"rowspan":1},
//		         {field:'gdesc',title:'内容',"rowspan":1},
		         {field:'gimg',title:'图片名称',"rowspan":1},
		         {field:'gclicks',title:'访问量',"rowspan":1},
		         {field:'cid',title:'类别编号',"rowspan":1},
		         {field:'pid',title:'出版社编号',"rowspan":1}
		         ]],
//视图
	view: detailview,
	detailFormatter:function(index,row){
		           return '<div style="padding:2px"><table class="ddv"></table></div>';
		         },
	onExpandRow: function(index,row){
		           var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
		           ddv.datagrid({
		        	 data:[row],
		        	 iconCls:'icon-ok',
		        	 collapsible:true,
		             singleSelect:true,
		             nowrap: false,
		             columns:[[
		               {field:'gdesc',title:'书本内容'},
		               {field:'gimg',title:'图片预览'
		            	   ,formatter:imgFormatter
		            	   },
		             ]],
		             onLoad:function(){
		 				$('#tb').datagrid('fixDetailRowHeight',index);
		 			},
		             onResize:function(){
		               $('#tb').datagrid('fixDetailRowHeight',index);
		             },
		             onLoadSuccess:function(){
		               setTimeout(function(){
		                 $('#tb').datagrid('fixDetailRowHeight',index);
		               },0);
		             }		 			
		           });
		           $('#tb').datagrid('fixDetailRowHeight',index);
		         }
		         
	});
}
//图片预览
function imgFormatter(value){
	if('' != value && null != value){    
	    value = "<img onclick=download(\""+value+"\") style='width:66px; height:60px;margin-left:3px;' src='/webproject/bookcover/" + value + ".jpg' title='点击查看图片'/>";     
	    return  value;
	}
}
function download(img){  
    var simg =  "/webproject/bookcover/"+ img+".jpg";  
    $("#simg").attr("src",simg);  
    $('#dlg').dialog({  
    	title: '预览',  
    	width: $("#simg").attr("width"),  
    	height:$("#simg").attr("heighht"),  
    	resizable:true,  
    	closed: false,  
    	cache: false,  
    	modal: true  
    });  
      
} 
function loadUser(json,val){
	if(val!=''){
		$('#tb').datagrid({
			url:'',
			data:json
		})
	}
	else{
		$('#tb').datagrid({
			url:'/webproject/goodscontroller.do?type=cate'
		})
	}	
	$('#searchtext').on('input',function(){
		var val = $("#searchtext").val();
		$.ajax({
				type:'post',
				url:'/webproject/searchdatacontroller.do',
				data:{key:val,type:'goods'},
				success:function(data){
					if(data!=null){
						var options="";
						var length = data.length;
						if(length>5){
							length=5
						}
						for(var i=0;i<length;i++){
							options+="<option value='"+ data[i].gtitle +"'/>";
						}
						var ds = $("#ds");
						$("#ds").html(options);
					}
				}
				
			})
	})
	
}