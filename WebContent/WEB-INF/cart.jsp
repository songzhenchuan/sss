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
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css" media="all" />
</head>
<body class="main">

<%@include file="header.jsp" %>
<script type ="text/javascript" src="${pageContext.request.contextPath }/js/checknum.js"></script>

<div id="divpagecontent">
  <table width="100%" border="0" cellspacing="0">
    

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
      </table></td>
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="${pageContext.request.contextPath }/index.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车</div>
        
		
		
		
		
        <table cellspacing="0" class="infocontent">
        <tr>
          <td><img src="ad/page_ad.jpg" width="666" height="89" />
            <table width="100%" border="0" cellspacing="0">
              <tr>
                <td><img src="images/buy1.gif" width="635" height="38" /></td>
              </tr>
              <tr>
                <td><table cellspacing="1" class="carttable">
                  <tr>
                    <td width="10%">序号</td>
                    <td width="40%">商品名称</td>
                    <td width="10%">市场价</td>
                    <td width="10%">优惠价</td>
                    <td width="10%">数量</td>
                    <td width="10%">小计</td>
             
                    <td width="10%">取消</td>
                  </tr>
                </table>
				   <c:if test="${cart==null||cart.size()==0}">
	                 <table width="100%" border="0" cellspacing="0">
	                    <tr>
	                      <td align="center"><font color='red'>购物车为空</font></td>
	                    </tr>
	                  </table>
                 </c:if>
                 <c:set var="sum" value="0"></c:set>
                 <c:if test="${cart!=null }">
                 	<c:forEach items="${cart }" var="item">
						<table width="100%" border="0" cellspacing="0">
		                    <tr>
		                      <td width="10%">${item.gid }</td>
		                      <td width="40%">${item.gtitle }</td>
		                      <td width="10%">${item.gsaleprice }</td>
		                      <td width="10%">${item.ginprice }</td>
		                      <td width="10%"><input name="num" id="num" type="text" value="1" style="width:20px"/></td>
		                      <td width="10%">${item.ginprice*item.amount }</td>
		                       <c:set var="sum"  value="${sum+item.ginprice*item.amount }"></c:set>
		                      <td width="10%"><a href="${pageContext.request.contextPath}/cart.do?type=remove&gid=${item.gid}" style="color:#FF0000; font-weight:bold">X</a></td>
		                    	<c:if test="${cart==null||cart.size()==0 }">
	                 					<table width="100%" border="0" cellspacing="0">
	                   					 <tr>
	                     		 		<td align="center"><font color='red'>购物车为空</font></td>
	                    				</tr>
	                 					 </table>
                				 </c:if>
		                    </tr>
		                  </table>
	                  </c:forEach>
                  </c:if>
				  
				   <table cellspacing="1" class="carttable">
                     <tr>
                       <td style="text-align:right; padding-right:40px;"><font style="color:#FF6600; font-weight:bold">合计：&nbsp;&nbsp;<span id='sum1'>0</span>元</font></td>
                      </tr>
                   </table>
				   <div style="text-align:right; margin-top:10px"><a href="${pageContext.request.contextPath}/list.do?type=goods"><img src="images/gwc_jx.gif" border="0" /></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/cart.do?type=final"><img src="images/gwc_buy.gif" border="0" /></a></div>
				  
				  </td>
              </tr>
            </table></td>
        </tr>
      </table></td>
    </tr>
  </table>
</div>



<%@include file="footer.jsp" %>

</body>
</html>
    