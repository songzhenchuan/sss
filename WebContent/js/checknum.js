window.onload=function(){
	calCart();
}
function calCart(){
	var row =document.querySelectorAll('table tr td input[type=text]')
//	console.log(row)
//	document.cookie="userId=828"; 
//	document.cookie="userName=hulk";
//	删除cookie
//	var exp=new Date();
//	exp.setTime(exp.getTime()-10000);
//	document.cookie="userId=828; expires="+date.toGMTString();
	
	//分割cookie
//	var strCookie=document.cookie;
//	var arrCookie=strCookie.split("; "); 	
//	var userId; 
//	for(var i=0;i<arrCookie.length;i++){ 
//		var arr=arrCookie[i].split("="); 
//		if("userId"==arr[0]){ 
//			userId=arr[1]; 
//			break; 
//			} 
//			}
//	console.log(userId)
	var total =0;
	for (var i=1;i<row.length;i++) {
		var price1 = parseFloat(row[i].parentNode.previousElementSibling.innerHTML);		
		var price2 = row[i].value*price1
		row[i].parentNode.nextElementSibling.innerHTML=price2;
		total+=price2;
		document.querySelector('#sum1').innerHTML = total
//		var name='cart';
//		var cval=getCookie(name);
//		console.log(cval);
//		if(cval!=null){
//			document.cookie= name + "="+cval+";expires="+exp.toGMTString();
////		document.cookie="cart="; 
//			
//		}
		row[i].oninput = calCart;
	}
}
function getCookie(name)
{
 var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
  
 if(arr=document.cookie.match(reg))
  
  return (arr[2]);
 else
  return null;
}