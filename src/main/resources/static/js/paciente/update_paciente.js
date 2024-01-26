window.addEventListener('load', function(){
    const form = document.getElementById("modify_paciente")
    const id = document.querySelector("#modify_paciente #id")
    const nombre = document.querySelector("#modify_paciente #nombre")
    const apellido = document.querySelector("#modify_paciente #apellido")
    const dni = document.querySelector("#modify_paciente #dni")
    const fecha_ingreso = document.querySelector("#modify_paciente #fecha_ingreso")
    const id_direccion = document.querySelector("#modify_paciente #id_domicilio")
    const calle = document.querySelector("#modify_paciente #calle")
    const numero = document.querySelector("#modify_paciente #numero")
    const localidad = document.querySelector("#modify_paciente #localidad")
    const provincia = document.querySelector("#modify_paciente #provincia")

    const url ='/pacientes'
    form.addEventListener('submit', function(event){
        event.preventDefault()
        const payload = {
            id : id.value,
            nombre: nombre.value,
            apellido: apellido.value,
            dni: dni.value,
            fecha_ingreso: fecha_ingreso.value,
            domicilio:{
                id: id_direccion.value,
                calle: calle.value,
                numero: numero.value,
                localidad: localidad.value,
                provincia: provincia.value
            }
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
                alert("Paciente modificado con éxito")
            } else if (response.status === 404){
            alert("Paciente no existente, intente nuevamente")
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
    if (pathname == "/pacientes.html") {
        let nav = document.querySelector(".nav .nav-item a:last-of-type")
        nav.classList.add("active")
    }
})
    