<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Home</title>
    <jsp:include page="common/head.jsp"/>
</head>
<body>
<jsp:include page="common/navbar.jsp"/>
<link href="<c:url value="/resource/css/index.css"/>" rel="stylesheet">
<div class="container ">
    <div class="row">
        <div class="col-lg-2 col-md-2 col-xs-0"></div>
        <div class="col-lg-8 col-md-8 col-xs-12">

            <%-- 发布微博 --%>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form class="form-inline" action="<c:url value="publishBlog.html"/>" method="post">
                        <textarea name="blogContent" class="form-control rounded-0" rows="5" style="width: 100%"
                                  placeholder="Write something here..."></textarea>
                        <br><br>
                        <%--<input type="text" name="blogContent" class="form-control">--%>
                        <button type="submit" class="btn btn-default btn-sm" style="float: right;">Submit</button>
                    </form>
                </div>
            </div>

            <%--微博列表--%>
            <c:forEach var="blog" items="${allBlog}">
                <jsp:include page="common/blog.jsp">
                    <jsp:param name="username" value="${blog.username}"/>
                    <jsp:param name="blogid" value="${blog.blogid}"/>
                    <jsp:param name="userid" value="${blog.userid}"/>
                    <jsp:param name="headpic" value="${blog.headpic}"/>
                    <jsp:param name="content" value="${blog.content}"/>
                    <jsp:param name="publishtime" value="${blog.publishtime}"/>
                    <jsp:param name="isself" value="${blog.userid==user.userid}"/>
                </jsp:include>
            </c:forEach>

        </div>
        <div class="col-lg-2 col-md-2 col-xs-0"></div>
    </div>
</div>
<jsp:include page="common/foot.jsp"/>
</body>
</html>

