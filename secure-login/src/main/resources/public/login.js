function login() {
    let inputusername = document.getElementById("username");
    let inputpassword = document.getElementById("password");
    let endPoint = "/login";
    let llamado = llamarAPI(endPoint,{
        username: inputusername.value,
        password: inputpassword.value

    });
    llamado.then((resultado)=>{
        let divRespuesta = document.getElementById("respuesta");
        const aux = Boolean(resultado);

        divRespuesta.innerHTML= aux?"Bienvenido!! 👌":"Usuario o contrasena incorrectos 🤔";

    });
}

async function llamarAPI(endPoint,cuerpo){
    return await fetch(window.location.origin+endPoint,{
        headers:{
            "Content-Type": "application/json"
        },
        method: "POST",
        body: cuerpo
    })
    .then((response)=>{
        if(!response.ok){
            throw new Error();
        }
        return response.text();
    })

    .catch((error)=> alert(error));


}