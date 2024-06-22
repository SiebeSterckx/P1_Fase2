<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Chat</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
  <link rel="stylesheet" type="text/css" href="style/style.css"/>
  <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
  <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
  <script type="text/javascript" src="javascript/chatrefresh.js" defer ></script>
</head>
<body>
<div class="container">
  <jsp:include page="header.jsp">
    <jsp:param name="actual" value="myOffers"></jsp:param>
  </jsp:include>
  <main class="p-3 bg-light" style="min-height: 100%">

    <h3 class="text-center">Chat with ${chatWith.name} (${chatWith.role.stringValue})</h3>

      <div class="card text-center ">
        <div class="card-body">
          <h5 class="card-title">${material.type}</h5>
          <h6 class="card-subtitle mb-2 text-muted">${material.status}</h6>
          <p class="card-text">${material.address} ${material.lastPickupDate}</p>
        </div>
      </div>

      <div id="messages" >
        <p>Loading messages..</p>
      </div>

    <div>
      <div>

      </div>
    </div>
      <form method="post" action="Controller?command=SendMessage" enctype='multipart/form-data'>
        <div class="input-group mb-3">
          <input type="text" class="form-control" placeholder="message..." name="newMessage" aria-describedby="button-addon2" required>
          <input type="hidden" name="materialId" value="${materialId}">
          <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Send</button>
          </div>
        </div>
      </form>

  </main>
  <jsp:include page="footer.jsp"/>
</div>
</body>
</html>