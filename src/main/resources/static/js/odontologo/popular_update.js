function popular_update(id){
    const odontologo_table = document.getElementById("modify_odontologo_table")
    const id_update = document.querySelector("#modify_odontologo #id")
    const nombre_update = document.querySelector("#modify_odontologo #nombre")
    const apellido_update = document.querySelector("#modify_odontologo #apellido")
    const matricula_update = document.querySelector("#modify_odontologo #matricula")

    fetch(`/odontologos/${id}`)
    .then(function(response){
        return response.json()
    })
    .then(function(data){
        id_update.value = data.id
        nombre_update.value = data.nombre
        apellido_update.value = data.apellido
        matricula_update.value = data.numeroMatricula
        if (odontologo_table.style.display === "none"){
            odontologo_table.style.display = "block"
        }
    })
    .catch(error => {
        console.log("Error: " + error)
    })
}