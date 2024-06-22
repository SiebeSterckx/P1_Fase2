<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="verdelers" />
    </jsp:include>
    <script src="js/offerdeler.js"></script>
</head>x
<body>
<div class="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="verdelers" />
    </jsp:include>
    <main class="p-3 bg-light">
        <h2>Offer Overview For Distributor ${user.email}</h2>
        <c:if test="${success != null}">
            <div class="alert alert-success" role="alert">
                ${success}
            </div>
        </c:if>
        <c:choose>
            <c:when test="${empty materials}">
                <p>There are no materials for this verdeler</p>
            </c:when>
            <c:otherwise>
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
                                    <br><b>Location:</b> <a href="https://maps.google.com/?q=${m.getAddress()}" target="_blank">${m.getAddress()}</a></p>
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
                                <c:if test="${m.getStatusAsString() == 'Pending'}">
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#optionsModal" onclick="saveID(${m.getId()})">Doorsturen</button>
                                    <a class="btn btn-success" href="Controller?command=AcceptMaterialConfirm&id=${m.getId()}">Accept</a>
                                    <a class="btn btn-danger" href="Controller?command=RejectMaterialConfirm&id=${m.getId()}">Reject</a>
                                </c:if>
                            </div>
                        </div>

                    </c:forEach>
                </div>




                <!-- Modal container element -->
                <div class="modal" id="optionsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="false" data-id="">
                    <!-- Modal content element -->
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <!-- Modal header -->
                            <div class="modal-header">
                                <h5 class="modal-title">Select an option</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <!-- Modal body -->
                            <div class="modal-body">
                                <!-- List of options -->
                                <c:if test="${user.getCompanyName() != 'VITES'}">
                                    <button class="mt-2 btn btn-primary option-btn" onclick="showConfirm(this)" data-value="vites" data-dismiss="modal" data-toggle="modal" data-target="#confirmModal">Vites</button>
                                </c:if>
                                <c:if test="${user.getCompanyName() != 'MATERIALENBANK'}">
                                    <button class="mt-2 btn btn-primary option-btn" onclick="showConfirm(this)" data-value="materialenbank" data-dismiss="modal" data-toggle="modal" data-target="#confirmModal">Materialenbank</button>
                                </c:if>
                                <c:if test="${user.getCompanyName() != 'SCHOLEN'}">
                                    <button class="mt-2 btn btn-primary option-btn" onclick="showConfirm(this)" data-value="scholen 20-30" data-dismiss="modal" data-toggle="modal" data-target="#confirmModal">Scholen 20-30</button>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Confirmation modal -->
                <div class="modal" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="false">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Confirm your selection</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>

                            <div class="modal-body">
                                Are you sure you want to select <span id="selectedOption"></span>?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                <form action="Controller?command=sendOtherDistributor" method="post" novalidate>
                                    <input type="hidden" name="choose" id="choose" value="">
                                    <input type="hidden" id="materialId" name="materialId" data-id="">
                                    <button type="submit" class="btn btn-primary">Confirm</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                </tbody>
            </table>
            </c:otherwise>
        </c:choose>

</main>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
