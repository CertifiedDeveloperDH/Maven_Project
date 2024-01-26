function popular_update_paciente(id){
    const paciente_table = document.getElementById("modify_paciente_table")
    paciente_table.setAttribute("style", "display: block; background-color:#e6fffa; padding:10px; border-radius:3px")
    
    const id_update = document.querySelector("#modify_paciente #id")
    const nombre_update = document.querySelector("#modify_paciente #nombre")
    const apellido_update = document.querySelector("#modify_paciente #apellido")
    const dni_update = document.querySelector("#modify_paciente #dni")
    const fecha_ingreso_update = document.querySelector("#modify_paciente #fecha_ingreso")
    const id_update_direccion = document.querySelector("#modify_paciente #id_domicilio")
    const calle_update = document.querySelector("#modify_paciente #calle")
    const numero_update = document.querySelector("#modify_paciente #numero")
    const localidad_update = document.querySelector("#modify_paciente #localidad")
    const provincia_update = document.querySelector("#modify_paciente #provincia")

    fetch(`/pacientes/${id}`)
    .then(function(response){
        return response.json()
    })
    .then(function(data){
        console.log(data)
        id_update.value = data.id
        nombre_update.value = data.nombre
        apellido_update.value = data.apellido
        dni_update.value = data.dni
        fecha_ingreso_update.value = data.fecha_ingreso
        id_update_direccion.value = data.domicilio.id
        calle_update.value = data.domicilio.calle
        numero_update.value = data.domicilio.numero
        localidad_update.value = data.domicilio.localidad
        provincia_update.value = data.domicilio.provincia
    })
    .catch(error => {
        console.log("Error: " + error)
    })
}