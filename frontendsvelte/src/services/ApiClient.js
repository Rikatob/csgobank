class ApiClient {

    static SERVER_URL = 'http://localhost:8000';
    static GET_VAULT = '/vault';
    static GET_ITEMS = '/vault/items';


    static getItems() {
        return fetch(ApiClient.SERVER_URL + ApiClient.GET_ITEMS)
    }
}

export async function getItems() {
    const res = await fetch(ApiClient.SERVER_URL + ApiClient.GET_ITEMS);

    if (res.ok) {
        return await res.json();
    } else {
        throw new Error("Request failed");
    }
}

export default ApiClient;