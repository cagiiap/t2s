window.addEventListener("load", init);

async function init() {
    await load_conteiner();

    await load_eventos();
}

const load_conteiner = async () => {
    let url = "http://localhost:8080/conteiner";

    let response = await fetch(url);
    let json = await response.json();

    let html = "";

    json.forEach(x => {
        html += `
            <tr>
                <td>${x.numero_conteiner}</td>
                <td>${x.tipo}</td>
                <td>${x.status}</td>
                <td>${x.categoria}</td>
                <td><button id_conteiner=${x.id} id="excluir">EXCLUIR</button></td>
            </tr>
        `;
    });

    document.querySelector("table>tbody").innerHTML = html;
}

const load_eventos = async () => {
    document.getElementById("submit").addEventListener("click", async () => {
        await salvar();
    });

    document.querySelectorAll("#excluir").forEach(x => {
        x.addEventListener("click", async () => {
            await excluir(x.getAttribute("id_conteiner"));
        });
    });
}

const salvar = async () => {
    let url = "http://localhost:8080/conteiner"

    let json = {
        id_cliente: document.querySelector("#cliente").value,
        numero_conteiner: document.querySelector("#numero_conteiner").value,
        tipo: document.querySelector("#tipo").value,
        status: document.querySelector("#status").value,
        categoria: document.querySelector("#categoria").value
    }

    let settings = {
        "method": "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(json)
    }

    let response = await fetch(url, settings);

    if (response.status == 201) {
        window.location.reload();
    }
}

const excluir = async (id_conteiner) => {
    let url = `http://localhost:8080/conteiner/${id_conteiner}`;

    let settings = {
        "method": "DELETE",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }

    let response = await fetch(url, settings);
    
    if(response.status == 200){
        window.location.reload();
    }
}