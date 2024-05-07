async function getUsers() {
    const response = await fetch("http://localhost:8080/admin/gen");

    if (response.ok) {
        let json = await response.json().then(data => replaceTable(data));
    }

    function replaceTable(data) {

        const placement = document.getElementById("usersTablePlacement")
        placement.innerHTML = "";
        data.forEach(({id, email, name, lastName, age, roles}) => {
            let userRoles = "";
            roles.forEach((role) => {
                userRoles = userRoles + role.role.replace("ROLE_", "") + " ";
            })
            const element = document.createElement("tr");

            element.innerHTML = `
            <th scope="row">${id}</th>
            <td>${email}</td>
            <td>${name}</td>
            <td>${lastName}</td>
            <td>${age}</td>
            <td>${roles}</td>
            <td>
                <button type="button" class="btn btn-info text-white"
                    data-bs-userId=${id}
                    data-bs-userEmail=${email}
                    data-bs-userName=${name}
                    data-bs-userLastName=${lastName}
                    data-bs-userAge=${age}
                    data-bs-toggle="modal"
                    data-bs-target="#ModalEdit">Edit</button>
            </td>
            <td>
                <button type="button" class="btn btn-danger"
                    data-bs-userId=${id}
                    data-bs-userEmail=${email}
                    data-bs-userName=${name}
                    data-bs-userLastName=${lastName}
                    data-bs-userAge=${age}
                    data-bs-toggle="modal"
                    data-bs-target="#ModalDelete">Delete</button>
            </td>
            `
            placement.append(element);
        })

    }

}