window.addEventListener('load', function(){
    fetch("/turnos")
    .then(function(response){
        return response.json()
    })
    .then(function(data){
        console.log(data)
        let tablaTurno = document.querySelector('#turnoTable tbody')
        tablaTurno.innerHTML = ""
        data.forEach(turno => {
            let get_More_Info_Btn_Turno = `<button
                                    id="btn_id_${turno.turnoId}"
                                    type="button" class="btn btn-info btn_id"
                                    style="cursor:pointer"
                                    onclick="popular_update_turno(${turno.turnoId})"> 
                                    ${turno.turnoId}
                                    </button>` 
            let delete_Btn = `<button
                            id="btn_delete_${turno.turnoId}"
                            type="button" class="btn btn-danger"
                            style="cursor:pointer"
                            onclick="delete_turno(${turno.turnoId})"> 
                            x
                            </button>` 
                
            let tr_id = `tr${turno.turnoId}`
            let turnoRow = `<tr id="${tr_id}">
                      <td> ${get_More_Info_Btn_Turno} </td>
                      <td class="td_id_paciente"> ${turno.paciente.id} </td>
                      <td class="td_id_odontologo"> ${turno.odontologo.id} </td>
                      <td class="td_fecha_hora"> ${turno.fechaHora}</td>
                      <td> ${delete_Btn}</td>
                      </tr>`          
                
            tablaTurno.innerHTML += turnoRow
        })
    })
    .catch(function(e){
        alert("ERROR: ", e)
        console.log("ERROR: ", e)
    }
    )
    
    let pathname = window.location.pathname
    if (pathname == "/turnos.html") {
        let nav = document.querySelector(".nav .nav-item a:last-of-type")
        nav.classList.add("active")
    }
})