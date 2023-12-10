class ApiClient {

    static SERVER_URL = 'http://localhost:8000';
    static GET_VAULT = '/vault';
    static GET_ITEMS = '/vault/1/items';
    static GET_ACCOUNTS = '/account/all'
    static GET_VAULTS = '/vault/all';


}


export async function getItems(vaultId) {

    let itemUrl = `/vault/${vaultId}/items`
    const res = await fetch(ApiClient.SERVER_URL + itemUrl);

    if (res.ok) {
        return await res.json();
    } else {
        throw new Error("Request failed");
    }
}

export async function getAccounts() {
    const response = await fetch(ApiClient.SERVER_URL + ApiClient.GET_ACCOUNTS);
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error("Request failed")
    }
}

export async function getVaults() {
    const response = await fetch(ApiClient.SERVER_URL + ApiClient.GET_VAULTS);
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error("Request failed")
    }
}

export default ApiClient;