<%--
  Created by IntelliJ IDEA.
  User: jvers
  Date: 22/12/2022
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Edit"/>
    </jsp:include>
</head>
<body>
<div class="container">
  <jsp:include page="header.jsp">
    <jsp:param name="actual" value="edit"/>
  </jsp:include>
  <main class="p-3 bg-light">
    <c:if test="${not empty errors}">
      <div class="text-danger" id="error">
        <ul>
          <c:forEach items="${errors}" var="error">
            <li>${error}</li>
          </c:forEach>
        </ul>
      </div>
    </c:if>
    <form class="widthform" method="post" action="Controller?command=EditUserExecute&id=${param.get("id")}" >
      <p class="form-group">
      <div>
        <p class="form-check form-check-inline"><input class="form-check-input" type="radio" value="Verdeler" name="sortby" id="sortby1" ${rolePreviousValue eq 'verdeler' ? 'Checked' : ''}><label class="form-check-label" for="sortby1">Verdeler</label></p>
        <p class="form-check form-check-inline"><input class="form-check-input" type="radio" value="Aanbieder" name="sortby" id="sortby2" ${rolePreviousValue eq 'aanbieder' ? 'Checked' : ''}><label class="form-check-label" for="sortby2">Aanbieder</label></p>
      </div>
      </p>
      <p class="form-group">
        <label for="email">New email: </label>
        <input class="form-control input-md" type="email" name="email" id="email" value="<c:out value='${emailPreviousValue}'/>" required >
      </p>
      <p class="form-group">
        <label for="name">New company name: </label>
        <input class="form-control input-md" type="text" name="name" id="name" required value="<c:out value='${namePreviousValue}'/>">
      </p>
      <p><button class="btn btn-outline-dark" type="submit" id="Register">Edit</button></p>
    </form>
  </main>
  <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
