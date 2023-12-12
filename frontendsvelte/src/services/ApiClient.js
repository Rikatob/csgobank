class ApiClient {

    static SERVER_URL = 'http://localhost:8000';
    static GET_VAULT = '/vault';
    static GET_ITEMS = '/vault/1/items';
    static GET_ACCOUNTS = '/account/all'
    static GET_VAULTS = '/vault/all';
    static UPDATE_ACCOUNT = '/account/update'
    static CREATE_ACCOUNT = '/account/new'

}


/////////////////////////////////////////  ACCOUNT  /////////////////////////////////////////
export async function getAccounts() {
    const response = await fetch(ApiClient.SERVER_URL + ApiClient.GET_ACCOUNTS);
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error("Request failed")
    }
}

export async function updateAccount(account) {
    const response = await fetch(ApiClient.SERVER_URL + ApiClient.UPDATE_ACCOUNT,
        {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(account)
        }
    )
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error("Request failed")
    }

}

// TODO SHOULD NOT BE A POST ??
export async function deleteAccount(accountId) {
    let deleteUrl = `/account/delete/${accountId}`
    const response = await fetch(ApiClient.SERVER_URL + deleteUrl,
        {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            }
        }
    )
    if (response.ok) {
        return response;
    } else {
        throw new Error("Request failed")
    }

}


export async function createAccount(account) {
    const response = await fetch(ApiClient.SERVER_URL + ApiClient.CREATE_ACCOUNT,
        {
            method: 'POST',
            headers:{
                'Content-type': 'application/json'
            },
            body: JSON.stringify(account)
        });

    if (response.ok) {
        return response.json();
    } else {
        throw new Error("Request failed")
    }
}


/////////////////////////////////////////  VAULT  /////////////////////////////////////////
export async function getVaults(accountId) {

    const vaultUrl = `/vaultAccount/${accountId}/vaults`
    const response = await fetch(ApiClient.SERVER_URL + vaultUrl);
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error("Request failed")
    }
}


/////////////////////////////////////////  ITEM  /////////////////////////////////////////
export async function getItems(vaultId) {

    let itemUrl = `/vault/${vaultId}/items`
    const res = await fetch(ApiClient.SERVER_URL + itemUrl);

    if (res.ok) {
        return await res.json();
    } else {
        throw new Error("Request failed");
    }
}


/////////////////////////////////////////  TRANSACTION-HISTORY  /////////////////////////////////////////
export async function getTransactions(itemId) {

    let itemUrl = `/transactionHistory/${itemId}`
    const res = await fetch(ApiClient.SERVER_URL + itemUrl);

    if (res.ok) {
        return await res.json();
    } else {
        throw new Error("Request failed");
    }
}

export default ApiClient;