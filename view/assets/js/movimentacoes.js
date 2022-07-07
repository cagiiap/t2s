window.addEventListener("load", init);

async function init() {
    await load_movimentacao();

    await load_eventos();
}

const load_movimentacao = async () => {
    let url = "http://localhost:8080/movimentacao";

    let response = await fetch(url);
    let json = await response.json();

    let html = "";

    json.forEach(x => {
        html += `
            <tr>
                <td>${x.tipo_movimentacao}</td>
                <td>${x.inicio}</td>
                <td>${x.fim}</td>
                <td><button id_movimentacao=${x.id} id="excluir">EXCLUIR</button></td>
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
            await excluir(x.getAttribute("id_movimentacao"));
        });
    });
}

const salvar = async () => {
    let url = "http://localhost:8080/movimentacao"

    let json = {
        tipo_movimentacao: document.querySelector("#tipo").value,
        inicio: document.querySelector("#inicio").value,
        fim: document.querySelector("#fim").value,
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

const excluir = async (id_movimentacao) => {
    let url = `http://localhost:8080/movimentacao/${id_movimentacao}`;

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