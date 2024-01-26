function delete_odontologo(odontologo_id) {
    let odontologo = document.querySelector(`#odontologoTable tbody #tr${odontologo_id}`)
            let confirmacion = confirm("¿Desea eliminar el odontólogo con id: "+ odontologo_id + "?")
            if (confirmacion){

                const url =`/odontologos/${odontologo_id}`
                const settings = {
                    method: 'DELETE',
                    headers:{
                        "Content-type": "application/json"
                    }
                }
                fetch(url, settings)
                .then(response => {
                    odontologo.remove()
                })
                .catch(function(e){
                    alert("ERROR: ", e)
                    console.log("ERROR: ", e)
                })
            }
}