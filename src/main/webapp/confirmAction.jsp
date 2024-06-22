<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
  <jsp:param name="title" value="Confirm"/>
</jsp:include>
<body>
<div class="container">
  <jsp:include page="header.jsp">
    <jsp:param name="title" value="Confirm"/>
  </jsp:include>
  <main class="p-3 bg-light">
    <c:if test="${message != null}">
      <div class="text-danger" id="error">
        <p>${message}</p>
      </div>
    </c:if>
    <c:choose>
      <c:when test="${variable eq 'removeMaterial'}">
        <h1>Are you sure you want to remove this offer?</h1>
        <form class="widthform" action="Controller?command=RemoveMaterial&id=${id}" method="post">
          <p><button class="btn btn-outline-warning" type="submit">Yes</button></p>
        </form>
        <c:choose>
          <c:when test="${user.getRole() eq 'MODERATOR'}">
            <form class="widthform" action="Controller?command=ModOverviewMatPage" method="post">
              <p><button class="btn btn-outline-warning" type="submit">No</button></p>
            </form>
            </c:when>
            <c:otherwise>
            <form class="widthform" action="Controller?command=MaterialsOverview" method="post">
              <p><button class="btn btn-outline-dark" type="submit">No</button></p>
            </form>
          </c:otherwise>
        </c:choose>
      </c:when>
      <c:when test="${variable eq 'acceptMaterial'}">
        <h1>Are you sure you want to accept this offer?</h1>
        <form class="widthform" action="Controller?command=AcceptMaterial&id=${id}" method="post">
          <p><button class="btn btn-outline-warning" type="submit">Yes</button></p>
        </form>
        <form class="widthform" action="Controller?command=OverviewVerdeler" method="post">
          <p><button class="btn btn-outline-dark" type="submit">No</button></p>
        </form>
      </c:when>
      <c:when test="${variable eq 'rejectMaterial'}">
        <h1>Why do you want to reject this offer?</h1>
        <form class="widthform" action="Controller?command=RejectMaterial&id=${id}" method="post">
          <div class="form-check">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" value="Too low value" checked>
            <label class="form-check-label" for="flexRadioDefault1">Too low value</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" value="Too small quantity">
            <label class="form-check-label" for="flexRadioDefault2">Too small quantity</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault3" value="Full of stock">
            <label class="form-check-label" for="flexRadioDefault3">Full of stock</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault" value="Own text">
            <input class="form-control-md" type="text" for="flexRadioDefault" name="radioTextField" placeholder="What is your other reason?"/>
          </div>
          <p><button class="btn btn-outline-warning" type="submit">Reject</button></p>
        </form>
        <form class="widthform" action="Controller?command=OverviewVerdeler" method="post">
          <p><button class="btn btn-outline-dark" type="submit">Cancel</button></p>
        </form>
      </c:when>
      <c:when test="${variable eq 'removeUser'}">
        <h1>Are you sure you want to remove this User?</h1>
        <form class="widthform" action="Controller?command=DeleteUser&id=${id}" method="post">
          <p><button class="btn btn-outline-warning" type="submit">Yes</button></p>
        </form>
        <form class="widthform" action="Controller?command=Home" method="post">
          <p><button class="btn btn-outline-dark" type="submit">No</button></p>
        </form>
      </c:when>
    </c:choose>
  </main>
</div>
</body>
</html>
