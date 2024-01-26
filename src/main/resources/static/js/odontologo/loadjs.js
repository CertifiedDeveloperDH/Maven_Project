function loadjs() {
    let script_filename = '/js/delete_odontologo.js'
    let script = ""
    script = document.createElement('script');
    script.setAttribute('type', 'text/javascript');
    script.setAttribute('src', script_filename);
    //script.setAttribute('id', 'script_id');
 
    //let script_id = document.getElementById('script_id');
    //if(script_id){
    //    document.getElementsByTagName('head')[0].removeChild(script_id);
    //}
    document.getElementsByTagName('head')[0].appendChild(script);
    let eliminar = document.querySelector("#eliminar")
    eliminar.disabled = "true"
    eliminar.onclick = ""
    console.log(eliminar)
    alert("Seleccione un id para eliminar odontólogo")
}