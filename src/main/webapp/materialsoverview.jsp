<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="My offers"/>
</jsp:include>
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="myOffers"></jsp:param>
    </jsp:include>
    <main class="p-3 bg-light">
        <c:if test="${not empty materialString}">
            <p>${materialString}</p>
        </c:if>

            <div class="card-deck">
            <c:forEach var="m" items="${materials}">
                <div class="card" style="">
                    <img class="card-img-top img-fluid" style="object-fit: cover;
                                                                object-position: center center;
                                                                width: 100%;
                                                                height: 250px;" src="
                                                    <c:choose>
                                                            <c:when test="${m.getImg() == '1'}">
                                                                <c:url value="https://b2892900.smushcdn.com/2892900/wp-content/uploads/2022/10/LOGO-1-AC-UM-1-scaled-e1666049615106.jpg"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:url value="img/${m.getImg()}"/>
                                                    </c:otherwise>
                                                    </c:choose>
                                                   " alt="Material">
                    <div class="card-body">
                        <h5 class="card-title">${m.type}</h5>
                        <p class="card-text"><b>Avalable Until:</b> ${m.lastPickupDateAsString}
                            <br><b>Location:</b> <a href="https://maps.google.com/?q=${m.getAddress()}" target="_blank">${m.getAddress()}</a>
                            <br><b>Verdeler:</b>
                            <c:forEach var="d" items="${distributors}">
                                <c:if test="${d.userId == m.distributorId}">
                                    ${d.name}
                                </c:if>
                            </c:forEach>
<%--                            <c:if test="${m.distributorId == 4}">--%>
<%--                            <td>Vites</td>--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${m.distributorId == 5}">--%>
<%--                                <td>Matrialen Bank</td>--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${m.distributorId == 6}">--%>
<%--                                <td>Scholen</td>--%>
<%--                            </c:if>--%>
                        </p>
                    </div>
                    <div class="list-group lis-group-flush">

                        <div class="list-group-item">
                            <p class="card-text"><b>Status:</b> ${m.statusAsString}</p>
                            <c:if test="${m.statusAsString == 'Rejected'}">
                                <p class="card-text"><b>Reason:</b> ${m.messageReject}</p>
                            </c:if>
                        </div>



                    </div>



                    <div class="card-footer d-flex justify-content-between">
                        <a href="Controller?command=OpenChat&id=${m.getId()}" class="btn btn-primary">Chat</a>
                        <a href="Controller?command=RemoveMaterialConfirm&id=${m.getId()}"><ion-icon class="bin" name="trash-bin-outline" size="large"></ion-icon></a>
                    </div>
                </div>

            </c:forEach>
            </div>
    </main>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
