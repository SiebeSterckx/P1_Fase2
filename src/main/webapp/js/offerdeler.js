

function saveID(element) {
    document.getElementById('materialId').value = element;
}

function showConfirm(element) {
    document.querySelector('#selectedOption').innerHTML = element.dataset.value;
    document.getElementById('choose').value = element.dataset.value;

}


function confirmOption() {
    console.log('Confirmed');
    let option = document.querySelector('#selectedOption').innerHTML;
    console.log(option);
    document.getElementById('sendSucces').innerHTML = `Succesvol doorgestuurd naar ${option}`;
}

