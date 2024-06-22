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
    <jsp:param name="actual" value="OverviewAwaitingUsers" />
  </jsp:include>
  <main class="p-3 bg-light">
    <h2>Overview Awaiting Providers</h2>
    <c:if test="${success != null}">
      <div class="alert alert-success" role="alert">
          ${success}
      </div>
    </c:if>
    <c:choose>
      <c:when test="${empty awaitingUsers}">
        <p>There are no Awaiting Providers</p>
      </c:when>
      <c:otherwise>
        <table class="table table-hover table-responsive">
          <tr>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Approve</th>
            <th scope="col">Reject</th>
          </tr>
          <tbody>
          <c:forEach var="m" items="${awaitingUsers}">
            <tr>
              <td>${m.getCompanyName()}</td>
              <td>${m.email}</td>
              <td><a href="Controller?command=AwaitingUserApprove&id=${m.userId}">Approve</a></td>
              <td><a href="Controller?command=AwaitingUserReject&id=${m.userId}">Reject</a></td>
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