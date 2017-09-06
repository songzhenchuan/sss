<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta content="all" name="robots"/>
<meta name="author" content="Fisher" />
<meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
<meta name="description" content="reefdesign" />
<meta name="keywords" content="reefdesign" />
<title>电子书城</title>
<link rel="shortcut icon" href="favicon.ico" >
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>

<body class="main">


<%@include file="header.jsp" %>>


<div id="divpagecontent">
  <table width="100%" border="0" cellspacing="0">
    <tr>
     
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="${pageContext.request.contextPath}/index.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a href="my.html">&nbsp;我的帐户</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;修改成功</div>
        
		
		
		
		
        <table cellspacing="0" class="infocontent">
        <tr>
          <td style="padding:20px; text-align:center"><table width="80%" border="0" cellspacing="0" style="margin-top:70px">
            <tr>
              <td style="width:98"><img src="images/IconTexto_WebDev_009.jpg" width="128" height="128" /></td>
              <td style="padding-top:30px"><font style="font-weight:bold; color:#FF0000">您的购买已经成功！请耐心等待您的货品，谢谢惠顾！</font><br />
                <br />
                <a href="${pageContext.request.contextPath}/index.do">返回首页</a></td>
            </tr>
          </table>          
            <p>&nbsp;</p>
            </td>
        </tr>
      </table>
	  
	  
	  </td>
    </tr>
  </table>
</div>



<%@include file="footer.jsp" %>>


</body>
</html>
