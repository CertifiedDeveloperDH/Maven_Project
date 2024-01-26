window.addEventListener('load', function(){
    const form = document.getElementById("modify_turno")
    const id = document.querySelector("#modify_turno #id")
    const id_paciente = document.querySelector("#modify_turno #id_paciente")
    const id_odontologo = document.querySelector("#modify_turno #id_odontologo")
    const fecha_hora = document.querySelector("#modify_turno #fecha_hora")


    const url ='/turnos'
    form.addEventListener('submit', function(event){
        event.preventDefault()
        const payload = {
            turnoId : id.value,
            paciente: {
                id: id_paciente.value
            },
            odontologo: {
                id: id_odontologo.value
            },
            fechaHora: fecha_hora.value
        }
        console.log(payload)
        const settings = {
            method: 'PUT',
            headers:{
                'Content-type': 'application/json'
            },
            body: JSON.stringify(payload)
        }
        fetch(url, settings)
        .then(response => {
            if (response.status === 200){
                alert("Turno modificado con éxito")
            } else if (response.status === 404){
                let notFoundAlert = '<div class="alert alert-success alert-dismissible">' +
                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        '<strong></strong> turno no existente, intente nuevamente</div>'
            document.querySelector("#response").innerHTML = notFoundAlert
            document.querySelector("#response").setAttribute("style", "display:block; margin-top:10px")
            }else if (response.status === 400){
            alert("Paciente u odotólogo no existentes")
            }
            console.log(response)
            console.log(response.status)
            location.reload()
        })
        .catch(function(e){
            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        '<strong> Error intente nuevamente</strong> </div>'
            document.querySelector("#response").innerHTML = errorAlert
            document.querySelector("#response").setAttribute("style", "display:block; margin-top:10px")
            console.log("ERROR: ", e)
        })
    })
    
    let pathname = window.location.pathname
    if (pathname == "/turnos.html") {
        let nav = document.querySelector(".nav .nav-item a:last-of-type")
        nav.classList.add("active")
    }
})
    