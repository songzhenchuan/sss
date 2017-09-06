<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <meta content="all" name="robots" />
    <meta name="author" content="Fisher" />
    <meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
    <meta name="description" content="reefdesign" />
    <meta name="keywords" content="reefdesign" />
    <title>电子书城</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>
<body class="main">
    <div id="divhead">
        <table cellspacing="0" class="headtable">
            <tr>
                <td>
                    <a href="index.html">
                        <img src="images/logo.gif" width="95" height="30" border="0" /></a>
                </td>
                <td style="text-align: right">
                    <img src="images/cart.gif" width="26" height="23" style="margin-bottom: -4px" />&nbsp;<a
                        href="cart.html">购物车</a> | <a href="#">帮助中心</a> | <a href="login.html">我的帐户</a>
                    | <a href="register.html">新用户注册</a>
                </td>
            </tr>
        </table>
    </div>
    <div id="divmenu">
        <a href="product_list.html">文学</a> <a href="product_list.html">生活</a> <a href="product_list.html">
            计算机</a> <a href="product_list.html">外语</a> <a href="product_list.html">经管</a>
        <a href="product_list.html">励志</a> <a href="product_list.html">社科</a> <a href="product_list.html">
            学术</a> <a href="product_list.html">少儿</a> <a href="product_list.html">艺术</a>
        <a href="product_list.html">原版</a> <a href="product_list.html">科技</a> <a href="product_list.html">
            考试</a> <a href="product_list.html">生活百科</a> <a href="product_list.html" style="color: #FFFF00">
                全部商品目录</a></div>
    <div id="divsearch">
        <table width="100%" border="0" cellspacing="0">
            <tr>
                <td style="text-align: right; padding-right: 220px">
                    Search
                    <input type="text" name="textfield" class="inputtable" />
                    <!--<input name="searchbutton" type="image" src="images/serchbutton.gif" style=" margin-bottom:-4px"/>-->
                    <a href="search.html">
                        <img src="images/serchbutton.gif" border="0" style="margin-bottom: -4px" /></a>
                </td>
            </tr>
        </table>
    </div>
    <div id="divpagecontent">
        <table width="100%" border="0" cellspacing="0">
            <tr>
                <td>
                    <div style="text-align: right; margin: 5px 10px 5px 0px">
                        <a href="${pageContext.request.contextPath}">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;文章标题</div>
                    <table cellspacing="0" class="infocontent">
                        <tr>
                            <td>
                                <table width="100%" border="0" cellspacing="0">
                                    <tr>
                                        <td style="padding: 10px">
                                            以下 <strong>${pageinfo.totalnumber }</strong> 条结果   此页显示<strong>${fn:length(pageinfo.data)}</strong>条<hr />
										
											<c:forEach items="${pageinfo.data }" var="goods">
											<c:set var="cid" value="${goods.cid }"></c:set>
                                            <table border="0" cellspacing="0" class="searchtable">
                                                <tr>
                                                    <td width="20%" rowspan="2">
                                                        <div class="divbookpic">
                                                            <p>
                                                                <a href="info.html">
                                                                    <img src="${pageContext.request.contextPath }/bookcover/dayongxiaohua.jpg" width="115" height="129" border="0" /></a></p>
                                                        </div>
                                                    </td>
                                                    <td colspan="2">
                                                        <font class="bookname">${goods.gtitle }</font><br />
                                                        作者：${goods.gauthor } 著<br />
                                                      ${goods.gdesc }
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        售价：<font color="#FF0000">${goods.gsaleprice }</font>&nbsp;&nbsp;&nbsp;&nbsp;原价：<s>${goods.ginprice }</s>
                                                    </td>
                                                    <td style="text-align: right">
                                                        <a href="cart.html">
                                                            <img src="images/buy.gif" width="91" height="27" border="0" style="margin-bottom: -8px" /></a>
                                                    </td>
                                                </tr>
                                            </table>
                                          </c:forEach>
                                           
                                         <div class="pagination" >
                                                <tr>
													<td colspan="4">
													<c:if test="${pageinfo.isfirstpage }">
														首页 上一页
													</c:if>
													<c:if test="${!pageinfo.isfirstpage }">
															<a href="${pageContext.request.contextPath}/list1.do?pageindex=1">首页</a>
															<a href="${pageContext.request.contextPath}/list1.do?cid=${cid}&pageindex=${pageinfo.pageindex-1}">上一页</a>
													</c:if>
													<c:forEach begin="0" end="5" var="i">
															
															<c:if test="${pageinfo.pageindex<(pageinfo.totalpage-i)}">
														<a href="${pageContext.request.contextPath}/list1.do?cid=${cid}&pageindex=${pageinfo.pageindex+1+i}">${pageinfo.pageindex+1+i}</a>
														</c:if>
													</c:forEach>
													<c:if test="${pageinfo.islastpage }">
													下一页 末页
													</c:if>
													<c:if test="${!pageinfo.islastpage }">													
													<a href="${pageContext.request.contextPath}/list1.do?cid=${cid}&pageindex=${pageinfo.pageindex+1}">下一页</a>
													<a href="${pageContext.request.contextPath}/list1.do?pageindex=${pageinfo.totalpage }">末页</a>
													</c:if>
													</td>
                                               </tr>
                                            </div>

                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
    <div id="divfoot">
        <table width="100%" border="0" cellspacing="0">
            <tr>
                <td rowspan="2" style="width: 10%">
                    <img src="images/bottomlogo.gif" width="195" height="50" style="margin-left: 175px" />
                </td>
                <td style="padding-top: 5px; padding-left: 50px">
                    <a href="#"><font color="#747556"><b>CONTACT US</b></font></a>
                </td>
            </tr>
            <tr>
                <td style="padding-left: 50px">
                    <font color="#CCCCCC"><b>COPYRIGHT 2008 &copy; eShop All Rights RESERVED.</b></font>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
