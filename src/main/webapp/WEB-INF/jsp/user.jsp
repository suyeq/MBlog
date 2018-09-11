<%--<jsp:useBean id="user" scope="request" type="com.smart.pojo.User"/>--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>User Center</title>
    <jsp:include page="common/head.jsp"/>
</head>
<body>

<jsp:include page="common/navbar.jsp"/>

<link href="<c:url value="/resource/css/index.css"/>" rel="stylesheet">

<div class="container ">
    <div class="row">
        <div class="col-lg-2 col-md-2 col-xs-0"></div>
        <div class="col-lg-8 col-md-8 col-xs-12">

            <div class="panel panel-default">
                <div class="panel-body" align="center">

                    <img src="${pageContext.request.contextPath}/resource/pics/${theUser.headpic}.png"
                         style="width: 150px;height:150px" alt="">
                    <br>
                     <p style="font-size: 18px"> ${theUser.username} </p>
                    <c:if test="${user != theUser}">
                        <a href="follow.html?userId=${theUser.userid}">Follow</a>
                    </c:if>

                    <br>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-4 col-xs-4" align="center">
                            <a href="user.html?userId=${theUser.userid}&page=0">Blog</a>
                        </div>
                        <div class="col-md-4 col-xs-4" align="center">
                            <a href="user.html?userId=${theUser.userid}&page=1">Followers</a>
                        </div>
                        <div class="col-md-4 col-xs-4" align="center">
                            <a href="user.html?userId=${theUser.userid}&page=2">Followings</a>
                        </div>
                    </div>
                </div>
            </div>

            <div>
                <c:if test="${page==0}">
                    <c:forEach var="blog" items="${userBlog}">
                        <jsp:include page="common/blog.jsp">
                            <jsp:param name="username" value="${blog.username}"/>
                            <jsp:param name="userid" value="${blog.userid}"/>
                            <jsp:param name="headpic" value="${blog.headpic}"/>
                            <jsp:param name="content" value="${blog.content}"/>
                            <jsp:param name="publishtime" value="${blog.publishtime}"/>
                            <jsp:param name="isself" value="${blog.userid==user.userid}"/>
                        </jsp:include>
                    </c:forEach>
                </c:if>

                <c:if test="${page==1 || page == 2}">
                    <c:forEach var="f" items="${follows}">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div align="center">
                                    <img src="${pageContext.request.contextPath}/resource/pics/${f.headpic}.png"
                                         style="width: 80px;height:80px" alt=""><br>
                                    <a href="user.html?userId=${f.userid}" style="font-size: 18px">${f.username}</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>

            </div>


        </div>
        <div class="col-lg-2 col-md-2 col-xs-0"></div>
    </div>
</div>

<jsp:include page="common/foot.jsp"/>

</body>
</html>

