window.addEventListener('load', function(){
    fetch("/pacientes")
    .then(function(response){
        return response.json()
    })
    .then(function(data){
        console.log(data)
        let tablaPaciente = document.querySelector('#pacienteTable tbody')
        tablaPaciente.innerHTML = ""
        data.forEach(paciente => {
            let get_More_Info_Btn_Paciente = `<button
                                    id="btn_id_${paciente.id}"
                                    type="button" class="btn btn-info btn_id"
                                    style="cursor:pointer"
                                    onclick="popular_update_paciente(${paciente.id})"> 
                                    ${paciente.id}
                                    </button>` 
            let delete_Btn = `<button
                            id="btn_delete_${paciente.id}"
                            type="button" class="btn btn-danger"
                            style="cursor:pointer"
                            onclick="delete_paciente(${paciente.id})"> 
                            x
                            </button>` 
                
            let tr_id = `tr${paciente.id}`
            let pacienteRow = `<tr id="${tr_id}">
                      <td> ${get_More_Info_Btn_Paciente} </td>
                      <td class="td_first_name"> ${paciente.nombre.toUpperCase()} </td>
                      <td class="td_last_name"> ${paciente.apellido} </td>
                      <td class="td_dni"> ${paciente.dni} </td>
                      <td class="td_fecha_ingreso"> ${paciente.fecha_ingreso} </td>
                      <td class= "td_id_domicilio"> ${paciente.domicilio.id}</td>
                      <td> ${delete_Btn}</td>
                      </tr>`          
                
            tablaPaciente.innerHTML += pacienteRow
        })
    })
    .catch(function(e){
        alert("ERROR: ", e)
        console.log("ERROR: ", e)
    }
    )
    
    let pathname = window.location.pathname
    if (pathname == "/pacientes.html") {
        let nav = document.querySelector(".nav .nav-item a:last-of-type")
        nav.classList.add("active")
    }
})