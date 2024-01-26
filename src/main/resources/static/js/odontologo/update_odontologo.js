window.addEventListener('load', function(){
    const form = document.getElementById("modify_odontologo")
    const id = document.querySelector("#modify_odontologo #id")
    const nombre = document.querySelector("#modify_odontologo #nombre")
    const apellido = document.querySelector("#modify_odontologo #apellido")
    const matricula = document.querySelector("#modify_odontologo #matricula")

    const url ='/odontologos'
    form.addEventListener('submit', function(event){
        event.preventDefault()
        const payload = {
            id : id.value,
            nombre: nombre.value,
            apellido: apellido.value,
            numeroMatricula: matricula.value
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
                alert("Odontologo modificado con éxito")
            } else if (response.status === 404){
            alert("Odontologo no existente, intente nuevamente")
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
    if (pathname == "/odontologos.html") {
        let nav = document.querySelector(".nav .nav-item a:last-of-type")
        nav.classList.add("active")
    }
})
    