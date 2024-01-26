function delete_turno(turno_id) {
    let turno = document.querySelector(`#turnoTable tbody #tr${turno_id}`)
    console.log(turno)
            let confirmacion = confirm("¿Desea eliminar el turno con id: "+ turno_id + "?")
            if (confirmacion){

                const url =`/turnos/${turno_id}`
                const settings = {
                    method: 'DELETE',
                    headers:{
                        "Content-type": "application/json"
                    }
                }
                fetch(url, settings)
                .then(response => {
                    console.log(response)
                    turno.remove()
                })
                .catch(function(e){
                    alert("ERROR: ", e)
                    console.log("ERROR: ", e)
                })
            }
}