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
    <!-- 导入header.jsp -->
<%@include file="header.jsp"%>
    <div id="divpagecontent">
        <table width="100%" border="0" cellspacing="0">
            <tr>
                <td>
                    <div style="text-align: right; margin: 5px 10px 5px 0px">
                        <a href="${pageContext.request.contextPath}/index.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;文章标题</div>
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
                                                                <a href="${pageContext.request.contextPath }/dispacher.do?type=goods&gimg=${goods.gimg }&pid=${goods.pid}">
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
                                                        <a href="${pageContext.request.contextPath }/cart.do?type=add&gid=${goods.gid}">
                                                            <img src="images/buy.gif" width="91" height="27" border="0" style="margin-bottom: -8px" /></a>
                                                    </td>
                                                </tr>
                                            </table>
                                          </c:forEach>
                                           
                                         <div class="pagination">
                                                <ul>
                                                	<c:set var="currentPageIndex" value="${pageinfo.pageindex }"></c:set>
													<c:set var="startIndex" value="${currentPageIndex-5 }"></c:set>
													<c:set var="endPage" value="${startIndex +9 }"></c:set>
													
													<c:if test="${startIndex<=0 }">
														<c:set var="startIndex" value="1"></c:set>
														<c:set var="endPage" value="${startIndex +9 }"></c:set>
													</c:if>
													
													<c:if test="${endPage>=pageinfo.totalpage }">
														<c:set var="endPage" value="${pageinfo.totalpage }"></c:set>
														<c:set var="startIndex" value="${endPage-9 }"></c:set>
														
													</c:if>
                                                    <c:if test="${pageinfo.isfirstpage }">
                                                		<li class="disablepage">首页</li>
                                                		<li class="disablepage">上一页 </li>
                                                	</c:if>
                                               		 <c:if test="${!pageinfo.isfirstpage}">
                                                    	<li><a href="${pageContext.request.contextPath }/list.do?pageindex=1">首页</a></li>
                                                   		<li><a href="${pageContext.request.contextPath }/list.do?cid=${cid}&pageindex=${pageinfo.pageindex-1}">*--上一页 </a></li>
                                                    </c:if>
                                                    
                                                    <c:forEach begin="${startIndex}" end="${endPage }" var="pId">
														<c:if test="${pId==pageinfo.pageindex }">
															${pId }
														</c:if>
														<c:if test="${pId!=pageinfo.pageindex }">
															[<a href="list.do?pageindex=${pId }">${pId }</a>]
														</c:if>
													</c:forEach>
                                                    
                                                    <c:if test="${pageinfo.islastpage }">
                                                		<li class="disablepage">下一页  </li>
                                                		<li class="disablepage">末页</li>
                                                	</c:if>
                                                	<c:if test="${!pageinfo.islastpage }">
                                                    	<li class="nextpage"><a href="${pageContext.request.contextPath }/list.do?cid=${cid}&pageindex=${pageinfo.pageindex+1}">下一页 --*</a></li>
                                                    	<li><a href="${pageContext.request.contextPath }/list.do?pageindex=${pageinfo.totalpage}">末页</a></li>
                                                    </c:if>
                                                </ul>
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
  <!-- 导入尾部 -->
<%@include file="footer.jsp" %>
</body>
</html>
