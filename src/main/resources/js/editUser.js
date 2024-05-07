const editModal = document.getElementById('ModalEdit')
editModal.addEventListener('show.bs.modal', event => {

    const button = event.relatedTarget
    // console.log(button)

    const userId = button.getAttribute('data-bs-userId')
    const userEmail = button.getAttribute('data-bs-userEmail')
    const userName = button.getAttribute('data-bs-userName')
    const userLastName = button.getAttribute('data-bs-userLastName')
    const userAge = button.getAttribute('data-bs-userAge')

    const modalUserId = editModal.querySelector('#userId')
    const modalUserEmail = editModal.querySelector('#userEmail')
    const modalUserName = editModal.querySelector('#userName')
    const modalUserLastName = editModal.querySelector('#userLastName')
    const modalUserAge = editModal.querySelector('#userAge')


    modalUserId.value = userId
    modalUserEmail.value = userEmail
    modalUserName.value = userName
    modalUserLastName.value = userLastName
    modalUserAge.value = userAge


})

const formEdit = document.getElementById("formEdit");
formEdit.addEventListener('submit', e => {
    e.preventDefault();

    const formData = new FormData(formEdit);
    const object = {
        roles:[]
    };

    formData.forEach((value, key) => {
        console.log(key)
        if (key === "rolesId"){

            const roleId = value.split(" ")[0];
            const roleName = value.split(" ")[1];
            const role = {
                id : roleId,
                role : roleName
            };

            object.roles.push(role);
        } else {
            object[key] = value;
        }
    });
    fetch("/admin/gen", {
        method: "PUT",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(object)
    })
        .then(() => getUsers());
    $("#ModalEdit").modal("hide");
    formEdit.reset();
})