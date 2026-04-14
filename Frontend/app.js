const varos = document.getElementById('varos');
const datum = document.getElementById('datum');
const button = document.getElementById('button');
const result = document.getElementById('result');

const baseUrl = 'https://localhost:8080/api/idojaras/';

//CORS policy miatt kellene de beraktuk a mode: "no-cors"-t 
/*const httpsHeaders = {
    'Access-Control-Allow-Origin': "*" //nagyon gatya productionben de jo lesz most
}

const myHeaders = new Headers(httpsHeaders);*/

async function getWeather() {

    if (!varos.value.trim() && !datum.value) {

    } else if (!datum.value) {
        let response = await fetch(baseUrl + '?varos=' + varos.value.trim(), {
            headers: {
                "Content-Type": "Application/json",
                "Access-Control-Allow-Origin": "*"
            }
        });
        console.log(response);
        if (response.ok) {
            let data = await response.json();
            console.log(data);

            result.innerHTML = JSON.stringify(data);
        }
    }

}