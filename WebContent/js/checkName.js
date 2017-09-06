var  xmlhttp=null;
window.onload = function(){
	//生成验证码
	document.querySelector("#img1").onclick=function(){
		this.src='Code.do?id='+Math.random();
	}
	//检查邮箱(post请求)
	document.querySelector('#check_email').onclick=function(){
		//将帐号拿到，再传到服务器去验证，验证后拿到服务器返回的验证结果
		var email =document.querySelector('#uemail').value;
		var sp=document.querySelector('#spemail');	
		if (uemail=="" || uemail==null) {
			sp.innerHTML='邮箱不为空哦!';
			sp.style.color='red';
		}else{
			//1、创建一个XMLHttpRequest对象
			xmlhttp=new XMLHttpRequest();
			//2、创建一个http请求
			xmlhttp.open("POST","CheckEmail.do",true);
			//3、发送请求
			var data="email="+email+"&name="+"abc";
			//设置http请求头;
			xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
			xmlhttp.send(data);
			//4、接收服务器数据
//			alert(xmlhttp.readyState);
			xmlhttp.onreadystatechange=emailcallback;
			
		}
	}
	//检查账号；(get请求)
	document.querySelector('#check_user').onclick=function(){
		//将帐号拿到，再传到服务器去验证，验证后拿到服务器返回的验证结果
		var uname =document.querySelector('#uname').value;
		var sp=document.querySelector('#sp');
//		alert(uname);
		if (uname=="" || uname==null) {
			sp.innerHTML='会员名不为空哦!';
			sp.style.color='red';
		}else{
//			alert(sp);
			//AJAX与服务器通信是通过XMLHttpRequest实现的
			//1、得到一个异步通信的组件对象
			 xmlhttp=new XMLHttpRequest();//省去针对IE浏览器(<7.0)创建此对象的兼容判断
			//2、使用此对象与服务器通信
			//A、建立与服务器某一资源的通信
			xmlhttp.open("GET","CheckName.do?uname="+uname,true);
			//B、发送请求
			xmlhttp.send(null);
			//C、接受服务器返回的数据;
//			alert(xmlhttp.readyState)

			xmlhttp.onreadystatechange=namecallback;

			
		}
	}
}
//邮箱
function emailcallback(){
	 if (xmlhttp.readyState==4) {//数据传输完成
		 if (xmlhttp.status==200) {//成功在客户端接收到数据;
			 //可以取服务器返回的数据
			 var data =xmlhttp.responseText;//接收服务器返回的字符串
			var sp=document.querySelector('#spemail');
			 if (data=="1") {//1:表示存在;
				 sp.innerHTML='邮箱已被使用';
				 sp.style.color='red';
			 }else if(data=="0"){
				 sp.innerHTML='可以使用';
				 sp.style.color='green';
			 }
			 
		 }
	 }
	
}
//会员名
function namecallback(){
	 if (xmlhttp.readyState==4) {//数据传输完成
		 if (xmlhttp.status==200) {//成功在客户端接收到数据;
			 //可以取服务器返回的数据
			 var data =xmlhttp.responseText;//接收服务器返回的字符串
			 var sp=document.querySelector('#sp');
			 if (data=="1") {//1:表示存在;
				 sp.innerHTML='用户名已被注册';
				 sp.style.color='red';
			 }else if(data=="0"){
				 sp.innerHTML='名字不错哦!赶紧注册!';
				 sp.style.color='green';
			 }
			 
		 }
	 }
}