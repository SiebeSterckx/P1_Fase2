
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="bg-dark text-white navbar-dark">
    <nav class="row navbar-expand-md ml-2">
        <h1 class="col">Material Bank</h1>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon mx-4"></span>
        </button>
        <div class="col collapse navbar-collapse pt-3 mx-2 justify-content-end" id="navbarSupportedContent">
            <c:choose>
                <c:when test="${user == null}">
                    <ul class="navbar-nav">
                        <li class="nav-item" ${param.actual eq 'Home' ? 'id= "actual"' : ""}><a class="nav-link" href="Controller?command=Home">Home</a></li>
                        <li class="nav-item" >
                            <form method="post" action="Controller?command=Register" novalidate>
                                <p><button class="btn btn-outline-warning" type="submit">Register</button></p>
                            </form>
                        </li>
                    </ul>

                </c:when>
                <c:otherwise>
                    <c:if test="${user.role == 'AANBIEDER' }">
                        <ul class="navbar-nav">
                            <li class="nav-item" ${param.actual eq 'Home' ? 'id= "actual"' : ""}><a class="nav-link" href="Controller?command=Home">Home</a></li>
                            <li class="nav-item" ${param.actual eq 'addMaterial' ? 'id= "actual"' : ""}><a class="nav-link" href="Controller?command=AddForm">Add Offer</a></li>
                            <li class="nav-item" ${param.actual eq 'myOffers' ? 'id= "actual"' : ""}><a class="nav-link" href="Controller?command=MaterialsOverview">My Offers</a></li>
                            <c:if test="${not empty user}">
                                <li class="nav-item" >
                                    <form method="post" action="Controller?command=Logout" novalidate>
                                        <p><button class="btn btn-outline-warning" type="submit" id="LoginAanbieder">Log out</button></p>
                                    </form>
                                </li>
                            </c:if>
                            <li><button onclick="fetchNotifications()" id="notificationButton" class="btn btn-outline-warning">Notifications</button></li>
                        </ul>
                    </c:if>
                    <c:if test="${user.role == 'VERDELER' }">
                        <ul class="navbar-nav">
                            <li class="nav-item" ${param.actual eq 'Home' ? 'id= "actual"' : ""}><a class="nav-link" href="Controller?command=Home">Home</a></li>
                            <li class="nav-item" ${param.actual eq 'verdelers' ? 'id= "actual"' : ""}><a class="nav-link" href="Controller?command=OverviewVerdeler">Overview Distributor</a></li>
                            <c:if test="${not empty user}">
                                <li class="nav-item" >
                                    <form method="post" action="Controller?command=Logout" novalidate>
                                        <p><button class="btn btn-outline-warning" type="submit" id="LoginVerdeler">Log out</button></p>
                                    </form>
                                </li>
                            </c:if>
                            <li><button onclick="fetchNotifications()" id="notificationButton" class="btn btn-outline-warning">Notifications</button></li>

                        </ul>
                    </c:if>
                    <c:if test="${user.role == 'MODERATOR' }">
                        <ul class="navbar-nav">
                            <li class="nav-item" ${param.actual eq 'Home' ? 'id= "actual"' : ""}><a class="nav-link" href="Controller?command=Home">Home</a></li>
                            <li class="nav-item" ${param.actual eq 'ModOverview' ? 'id= "actual"' : ""}><a class="nav-link" href="Controller?command=ModOverviewMatPage">Offers Overview</a></li>
                            <li class="nav-item" ${param.actual eq 'OverviewDistributors' ? 'id= "actual"' : ""}><a class="nav-link" href="Controller?command=overviewDistributors">Overview Distributors</a></li>
                            <li class="nav-item" ${param.actual eq 'OverviewProviders' ? 'id= "actual"' : ""}><a class="nav-link" href="Controller?command=overviewProviders">Overview Providers</a></li>
                            <li class="nav-item" ${param.actual eq 'OverviewAwaitingUsers' ? 'id= "actual"' : ""}><a class="nav-link" href="Controller?command=AwaitingUsers">Overview Awaiting </a></li>
                            <c:if test="${not empty user}">
                                <li class="nav-item" >
                                    <form method="post" action="Controller?command=Logout" novalidate>
                                        <p><button class="btn btn-outline-warning" type="submit" id="LoginModerator">Log out</button></p>
                                    </form>
                                </li>
                            </c:if>
                            <li><button onclick="fetchNotifications()" id="notificationButton" class="btn btn-outline-warning">Notifications</button></li>
                        </ul>
                    </c:if>



                    <!-- The Modal -->
                    <div id="notificationModal" class="modal">

                        <!-- Modal content -->
                        <div id="modal-content">

                        </div>


                    </div>


                </c:otherwise>
            </c:choose>

        </div>
    </nav>
</header>