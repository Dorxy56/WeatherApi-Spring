const varos = document.getElementById('varos');
const datum = document.getElementById('datum');
const button = document.getElementById('button');
const result = document.getElementById('result');

const baseUrl = 'http://localhost:8080/api/idojaras/';

//CORS policy miatt kellene de beraktuk a mode: "no-cors"-t 
/*const httpsHeaders = {
    'Access-Control-Allow-Origin': "*" //nagyon gatya productionben de jo lesz most
}

const myHeaders = new Headers(httpsHeaders);*/

async function getWeather() {

    if (!varos.value.trim() && !datum.value || !varos.value.trim() && datum.value) {
        let response = await fetch(baseUrl, {
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*"
            }
        });
        console.log(response);

        let data = await response.json();
        console.log(data);

        result.innerHTML = JSON.stringify(data);
    } else if (!datum.value) {
        let response = await fetch(baseUrl + '?varos=' + varos.value.trim(), {
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*"
            }
        });
        console.log(response);

        let data = await response.json();
        console.log(data);

        result.innerHTML = JSON.stringify(data);
    } else {
        let response = await fetch(baseUrl + '?varos=' + varos.value.trim() + '?datum=' + datum.value, {
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*"
            }
        });
        console.log(response);

        let data = await response.json();
        console.log(data);

        result.innerHTML = JSON.stringify(data);

        if (Array.isArray(data)) {
            result.innerHTML = JSON.stringify(data[0]);
        } else {
            result.innerHTML = JSON.stringify(data);
        }
    }

}