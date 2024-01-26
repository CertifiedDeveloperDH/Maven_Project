function delete_paciente(paciente_id) {
    let paciente = document.querySelector(`#pacienteTable tbody #tr${paciente_id}`)
            let confirmacion = confirm("¿Desea eliminar el paciente con id: "+ paciente_id + "?")
            if (confirmacion){

                const url =`/pacientes/${paciente_id}`
                const settings = {
                    method: 'DELETE',
                    headers:{
                        "Content-type": "application/json"
                    }
                }
                fetch(url, settings)
                .then(response => {
                    paciente.remove()
                })
                .catch(function(e){
                    alert("ERROR: ", e)
                    console.log("ERROR: ", e)
                })
            }
}