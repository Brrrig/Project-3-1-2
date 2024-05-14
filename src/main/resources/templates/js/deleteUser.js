const DeleteModal = document.getElementById('ModalDelete')
DeleteModal.addEventListener('show.bs.modal', event => {

    const button = event.relatedTarget

    console.log(button)
    const userId = button.getAttribute('data-bs-userId')
    const userEmail = button.getAttribute('data-bs-userEmail')
    const userName = button.getAttribute('data-bs-userName')
    const userLastName = button.getAttribute('data-bs-userLastName')
    const userAge = button.getAttribute('data-bs-userAge')
    console.log(button)

    const modalUserId = DeleteModal.querySelector('#userIdDelete')
    const modalUserEmail = DeleteModal.querySelector('#userEmailDelete')
    const modalUserName = DeleteModal.querySelector('#userNameDelete')
    const modalUserLastName = DeleteModal.querySelector('#userLastNameDelete')
    const modalUserAge = DeleteModal.querySelector('#userAgeDelete')


    modalUserId.value = userId
    modalUserEmail.value = userEmail
    modalUserName.value = userName
    modalUserLastName.value = userLastName
    modalUserAge.value = userAge

})

const formDelete = document.getElementById('formDelete')
formDelete.addEventListener('submit', e =>{
    e.preventDefault();
    const formData = new FormData(formDelete);
    fetch("/rest/admin/gen"+formData.get("id"), {
        method: "DELETE"
    })
        .then(() => getUsers());
    $("#ModalDelete").modal("hide");
    formDelete.reset();
})