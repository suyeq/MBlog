<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="blog-${param.blogid}" class="panel panel-default">
    <div class="panel-body">
        <div class="media">
            <div class="media-left">
                <a>
                    <img src="${pageContext.request.contextPath}/resource/pics/${param.headpic}.png"
                         style="width:100px;height:100px" alt="">
                </a>
            </div>
            <div class="media-body">
                <div>
                    <a href="<c:url value="/user.html?userId=${param.userid}"/>" style="font-size: 25px">
                        ${param.username}
                    </a>
                </div>
                <div class="grey-font">
                    ${param.publishtime}
                    <c:if test="${param.isself}">
                        <a id="delete-blog" class="cursor-pointer grey-font" blogid="${param.blogid}">delete</a>
                    </c:if>
                </div>
                <div>
                    ${param.content}
                </div>
            </div>
        </div>
    </div>
</div>

