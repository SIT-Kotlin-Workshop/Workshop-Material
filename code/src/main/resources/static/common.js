const parseJson = async response => {
    try{
        const text = await response.text();
        const json = JSON.parse(text);
        return json;
    } catch(err) {
        return [];
    }
}

function sendGET(url) {

    return fetch(url,
        {headers: {'Accept': 'application/json'}}
    );
}

function sendPOST(url, data) {

    return fetch(url, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    });
}

function sendDELETE(url) {

    return fetch(url, {
        method: "DELETE"
    });
}
