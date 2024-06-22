const clearElement = (id) => {
    const element = document.getElementById(id)
    element.innerHTML = ""
}

const fetchUserIdAndMaterialId = async () => {
    const response = await fetch('Controller?command=FetchUserIdAndMaterialId');
    const userIdAndMaterialId = await response.json();
    return userIdAndMaterialId;
}

const fetchMessages = async (userIdAndMaterialId) => {
    const response = await fetch('Controller?command=FetchChat');
    const messages = await response.json();
    return messages;
}

const renderMessages = (messages, userId, materialId) => {
    const id = "messages";
    clearElement(id);
    messages.forEach((message) => {
        let card = document.createElement('div');
        card.className = "card w-75 mb-1";
        if (userId == message.fromId) {
            card.className += " float-sm-right bg-dark text-white text-right";
        }
        let cardBody = document.createElement('div');
        cardBody.className = "card-body";
        if (userId == message.fromId) {
            cardBody.className += " bg-dark text-white text-right";
        }
        let cardText = document.createElement('p');
        cardText.className = "card-text";
        cardText.textContent = `${message.message}`;
        cardBody.appendChild(cardText);
        card.appendChild(cardBody);
        document.getElementById(id).appendChild(card);
    }
    )
}

const fetchAndRenderMessages = async () => {
    const messages = await fetchMessages();
    const userIdandMaterialId = await fetchUserIdAndMaterialId();
    let userId = userIdandMaterialId.userId;
    let materialId = userIdandMaterialId.materialId;
    renderMessages(messages, userId, materialId);
}

fetchAndRenderMessages().then(r => { });

const intervalID = setInterval(function() {
    fetchAndRenderMessages().then(r => { });
}, 500);