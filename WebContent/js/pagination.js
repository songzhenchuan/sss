$(function(){
	$('#pagination').pagination({
		pageSize:5,
		pageIndex:0,
		firstBtnText:'首页',
		lastBtnText:'尾页',
		prevBtnText:'上一页',
		nextBtnText:'下一页',
		showInfo:true,
		pageSizeItems:[10,20,30],
		showJump:true,
		showPageSizes:true,
		remote:{
				url:'/webproject/admin.do?cid'+'2',
				totalName:'totalnumber',
				success:function(pageinfo){
					$('#content').empty();
					var ul="<ul>";
					$(pageinfo.data).each(function(index,goods){
						ul+="<li>"+goods.gtitle+"</li>";
					})
					ul+="</ul>";
					$('#content').append(ul);
				}
		}	
		
		
	})
	
	
})