const clearElement = (modalContent) => {
    const element = document.getElementById(modalContent)
    element.innerHTML = ""
}

const fetchNotifications = async () => {
    const response = await fetch('Controller?command=FetchNotifications');
    const notifications = await response.json();
    //log all notifications
    console.log("Notifications");
    for (let i = 0; i < notifications.length; i++) {
        console.log(notifications[i]);
    }
    return notifications;
}

// on click of button with modalContent "notificationsButton" fetch notifications
document.getElementById("notificationButton").addEventListener("click", function() {
    fetchNotifications().then(r => {

        //print r
        console.log(r);
        // show modal without backdrop
        $('#notificationModal').modal({
            backdrop: false
        });


        const modalContent = "modal-content";
        clearElement(modalContent);

        document.getElementById(modalContent).className = " w-25 col-3 float-right float-sm-right float-md-right float-lg-right float-xl-right";

        if (r.length > 10) {
            document.getElementById(modalContent).className += " overflow-auto h-75";
        }

        //close modal when clicked on message
        document.getElementById(modalContent).addEventListener("click", function() {
            $('#notificationModal').modal('hide');
        });


        // text that says click to close
        let closeText = document.createElement('p');
        closeText.className = "text-center";
        closeText.textContent = "Click to close";
        document.getElementById(modalContent).appendChild(closeText);


        // make notifications container
        const notificationsContainer = document.createElement('div');
        notificationsContainer.className = "container";
        notificationsContainer.id = "notificationsContainer";

        r.forEach((notification) => {

            let card = document.createElement('div');
            card.className = "card w-100 mb-1 bg-dark text-white text-right float-sm-right";
            let cardBody = document.createElement('div');
            cardBody.className = "card-body";
            let cardText = document.createElement('p');
            cardText.className = "card-text";
            cardText.textContent = notification.message;
            cardBody.appendChild(cardText);
            card.appendChild(cardBody);
            notificationsContainer.appendChild(card);
        })
        document.getElementById(modalContent).appendChild(notificationsContainer);

    });
});