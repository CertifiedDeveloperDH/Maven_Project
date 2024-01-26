window.addEventListener('load', function(){
    document.querySelector("#response").setAttribute("style", "display:none; margin-top:10px")

    const form = document.getElementById("add_new_odontologo")
    const nombre = document.querySelector("#add_new_odontologo #nombre")
    const apellido = document.querySelector("#add_new_odontologo #apellido")
    const matricula = document.querySelector("#add_new_odontologo #matricula")


    const url ='/odontologos'
    form.addEventListener('submit', function(event){
        event.preventDefault()
        const payload = {
            nombre: nombre.value,
            apellido: apellido.value,
            numeroMatricula: matricula.value
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
                        '<strong></strong> odontologo guardado</div>'
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
    if (pathname == "/odontologos.html") {
        let nav = document.querySelector(".nav .nav-item a:last-of-type")
        nav.classList.add("active")
    }
})