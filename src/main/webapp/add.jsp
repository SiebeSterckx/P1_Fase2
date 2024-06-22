<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Foutje"/>
    </jsp:include>
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="addMaterial"/>
    </jsp:include>
    <main class="p-3 bg-light">
        <h2>Add an offer</h2>
        <c:if test="${not empty errors}">
            <div class="text-danger" id="error">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form class="widthform" method="post" action="Controller?command=Add" enctype='multipart/form-data' novalidate="novalidate">
            <div>
                <p class="form-check form-check-inline"><input class="form-check-input" type="radio" value="Vites" name="sortby" id="sortby1" checked><label class="form-check-label" for="sortby1">Vites</label></p>
                <p class="form-check form-check-inline"><input class="form-check-input" type="radio" value="Materialenbank" name="sortby" id="sortby2"><label class="form-check-label" for="sortby2">Materialenbank</label></p>
                <p class="form-check form-check-inline"><input class="form-check-input" type="radio" value="Scholen 20-30" name="sortby" id="sortby3"><label class="form-check-label" for="sortby3">Scholen 20-30</label></p>
            </div>
            <!-- novalidate in order to be able to run tests correctly -->
            <p class="form-group"><label for="img">Select Photo:</label><input class="form-control input-md" type="file" name="img" id="img" accept="image/*" required></p>

            <p class="form-group"><label for="LastDate">Last possible pickup time</label><input class="form-control input-md" type="date" id="LastDate" name="LastDate" value="${datePreviousValue}" required ></p>

            <p class="form-group"><label for="category">Category</label><input class="form-control input-md" type="text" id="category" name="category" value="${categoryPreviousValue}" required></p>

            <p class="form-group"><label for="location">Location</label><input class="form-control input-md" type="text" id="location" name="location" value="${locationPreviousValue}" required></p>

            <p><button class="btn btn-outline-dark" type="submit" id="addMaterial">Add offer</button></p>
        </form>
    </main>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>