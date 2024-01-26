window.addEventListener('load', function(){
    document.querySelector("#response").setAttribute("style", "display:none; margin-top:10px")

    const form = document.getElementById("add_new_paciente")
    const nombre = document.querySelector("#add_new_paciente #nombre")
    const apellido = document.querySelector("#add_new_paciente #apellido")
    const dni = document.querySelector("#add_new_paciente #dni")
    const fecha_ingreso = document.querySelector("#add_new_paciente #fecha_ingreso")
    const calle = document.querySelector("#add_new_paciente #calle")
    const numero = document.querySelector("#add_new_paciente #numero")
    const localidad = document.querySelector("#add_new_paciente #localidad")
    const provincia = document.querySelector("#add_new_paciente #provincia")


    const url ='/pacientes'
    form.addEventListener('submit', function(event){
        event.preventDefault()
        const payload = {
            nombre: nombre.value,
            apellido: apellido.value,
            dni: dni.value,
            fecha_ingreso: fecha_ingreso.value,
            domicilio:{
                calle: calle.value,
                numero: numero.value,
                localidad: localidad.value,
                provincia: provincia.value
            }
        }
        console.log(payload)
        const settings = {
            method: 'POST',
            headers:{
                'Content-type': 'application/json'
            },
            body: JSON.stringify(payload)
        }
        fetch(url, settings)
        .then(response => {
            if (response.status === 200){
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        '<strong></strong> paciente guardado</div>'
            document.querySelector("#response").innerHTML = successAlert
            document.querySelector("#response").setAttribute("style", "display:block; margin-top:10px")
            }
            console.log(response)
            console.log(response.status)
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