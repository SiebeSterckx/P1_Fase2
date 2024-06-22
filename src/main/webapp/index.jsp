<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Home"/>
    </jsp:include>
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="Home"/>
    </jsp:include>
    <main class="p-3 bg-light">
        <c:if test="${not empty successRegister}">
            <div class="alert alert-success" role="alert">
                <strong>Success!</strong> ${successRegister}
            </div>
        </c:if>
        <c:if test="${not empty message}">
            <p class="text-danger">${message}</p>
        </c:if>
        <c:if test="${not empty user}">
            <c:if test="${not empty errors}">
                <div id="error" class="alert alert-danger">
                    <ul>
                        <c:forEach var="error" items="${errors}">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
        </c:if>
        <c:choose>
            <c:when test="${empty user}">
                <c:if test="${not empty errors}">
                    <div id="error" class="alert alert-danger">
                        <ul>
                            <c:forEach var="error" items="${errors}">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

                <h2>Login</h2>
                <form class="widthform" method="post" action="Controller?command=Login" >
                    <p class="form-group">
                        <label for="email">Email: </label><input class="form-control input-md" type="email" name="email" id="email" value="<c:out value='${emailPreviousValue}'/>" required >
                    </p>
                    <p class="form-group">
                        <label for="password">Password: </label><input class="form-control input-md" type="password" name="password" id="password" required value="">
                    </p>
                    <p><button class="btn btn-outline-dark" type="submit" id="Login">Log In</button></p>
                </form>


            </c:when>
            <c:otherwise>
                <p id="welcome_msg">Welcome ${user.email}</p>
            </c:otherwise>
        </c:choose>
    </main>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>