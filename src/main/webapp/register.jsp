<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Register"/>
    </jsp:include>
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="Register"/>
    </jsp:include>
    <main class="p-3 bg-light">
        <c:if test="${not empty message}">
            <p class="text-danger">${message}</p>
        </c:if>
        <c:if test="${not empty errors}">
            <div id="error" class="alert alert-danger">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <h2>Register</h2>
        <form class="widthform" method="post" action="Controller?command=RegisterUser" >
            <p class="form-group">
                <label for="companyName2">Company name: </label>
                <input class="form-control input-md" type="text" name="companyname" id="companyname2" value="<c:out value='${companyNamePreviousValueRegister}'/>" required >
            </p>
            <p class="form-group">
                <label for="emailregister2">Email: </label>
                <input class="form-control input-md" type="email" name="email" id="emailregister2" value="<c:out value='${emailPreviousValueRegister}'/>" required >
            </p>
            <p class="form-group">
                <label for="passwordregister2">Password: </label>
                <input class="form-control input-md" type="password" name="password" id="passwordregister2" required value="">
            </p>
            <p><button class="btn btn-outline-dark" type="submit" id="Register2">Register</button></p>
        </form>
    </main>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>