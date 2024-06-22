<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Overview Providers" />
    </jsp:include>
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="verdelers" />
    </jsp:include>
    <main class="p-3 bg-light">
        <h2>Overview Providers</h2>
        <c:choose>
            <c:when test="${empty users}">
                <p>There are no Providers</p>
            </c:when>
            <c:otherwise>
                <table class="table table-hover table-responsive">
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    <tbody>
                    <c:forEach var="m" items="${users}">
                       <tr>
                           <td>${m.getCompanyName()}</td>
                           <td>${m.email}</td>
                           <td><a href="Controller?command=EditUser&id=${m.userId}">Edit</a></td>
                           <td><a href="Controller?command=DeleteUserConfirm&id=${m.userId}">Delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </main>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>