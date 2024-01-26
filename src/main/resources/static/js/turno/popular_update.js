function popular_update_turno(id){
    const turno_table = document.getElementById("modify_turno_table")
    if (turno_table.style.display === "none"){
        turno_table.style.display = "block"
    }
    
    const id_update = document.querySelector("#modify_turno #id")
    const id_paciente_update = document.querySelector("#modify_turno #id_paciente")
    const id_odontologo_update = document.querySelector("#modify_turno #id_odontologo")
    const fecha_hora_update = document.querySelector("#modify_turno #fecha_hora")

    fetch(`/turnos/${id}`)
    .then(function(response){
        return response.json()
    })
    .then(function(data){
        console.log(data)
        id_update.value = data.turnoId
        id_paciente_update.value = data.paciente.id
        id_odontologo_update.value = data.odontologo.id
        fecha_hora_update.value = data.fechaHora
        
    })
    .catch(error => {
        console.log("Error: " + error)
    })
}