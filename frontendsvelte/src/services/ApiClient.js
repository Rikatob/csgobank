class ApiClient {

    static SERVER_URL = 'http://localhost:8000';
    static GET_VAULT = '/vault';
    static GET_ITEMS = '/vault/1/items';
    static GET_ACCOUNTS = '/account/all'
    static GET_VAULTS = '/vault/all';
    static UPDATE_ACCOUNT = '/account/update'
    static CREATE_ACCOUNT = '/account/new'
    static SEND_TRADE_OFFER = '/vault/offer'
}


/////////////////////////////////////////  ACCOUNT  /////////////////////////////////////////

// Get all accounts.
export async function getAccounts() {
    const response = await fetch(ApiClient.SERVER_URL + ApiClient.GET_ACCOUNTS);
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error("Request failed")
    }
}

// Update an existing account.
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

// Delete an existing account.
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

// Create a new account.
export async function createAccount(account) {
    const response = await fetch(ApiClient.SERVER_URL + ApiClient.CREATE_ACCOUNT,
        {
            method: 'POST',
            headers: {
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

// Get all vaults in a account.
export async function getVaults(accountId) {

    const vaultUrl = `/vaultAccount/${accountId}/vaults`
    const response = await fetch(ApiClient.SERVER_URL + vaultUrl);
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error("Request failed")
    }
}

// Create a new vault in a account.
export async function createNewVault(accountId) {

    const newVaultUrl = `/vault/new/${accountId}`
    const response = await fetch(ApiClient.SERVER_URL + newVaultUrl, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        }
    });
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error("Request failed")
    }
}

// Delete vault i an account.

export async function deleteVault(vaultId) {

    const newVaultUrl = `/vault/delete/${vaultId}`
    const response = await fetch(ApiClient.SERVER_URL + newVaultUrl, {
        method: 'DELETE'
    });
    if (response.ok) {
        return response;
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

export async function withdrawItem(vaultId, itemId) {

    let withdrawUrl = `/vault/${vaultId}/item/withdraw/${itemId}`
    const res = await fetch(ApiClient.SERVER_URL + withdrawUrl);

    if (res.ok) {
        return await res.json();
    } else {
        throw new Error("Request failed");
    }
}

export async function depositItem(vaultId, itemId) {

    let depositUrl = `/vault/${vaultId}/item/deposit/${itemId}`
    const res = await fetch(ApiClient.SERVER_URL + depositUrl,
        {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            }
        });

    if (res.ok) {
        return await res.json();
    } else {
        throw new Error("Request failed");
    }
}

export async function transferItem(transaction) {

    let transferUrl = `/vault/transfer`
    const res = await fetch(ApiClient.SERVER_URL + transferUrl,
        {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(transaction)
        });

    if (res.ok) {
        return await res.json();
    } else {
        throw new Error("Request failed");
    }
}

export async function getAllAvailableItems() {
    return [{
        "id": 14,
        "type": "AK-47",
        "name": "FireSerpent",
        "floatValue": "0.06008401885629",
        "price": 4032272,
        "wearCategory": "FACTORY_NEW",
        "image": "../src/lib/images/FireSerpent.png"
    }, {
        "id": 1,
        "type": "AK-47",
        "name": "FireSerpent",
        "floatValue": "0.06008401885629",
        "price": 4032272,
        "wearCategory": "FACTORY_NEW",
        "image": "../src/lib/images/FireSerpent.png"
    }, {
        "id": 1,
        "type": "AK-47",
        "name": "FireSerpent",
        "floatValue": "0.06008401885629",
        "price": 4032272,
        "wearCategory": "FACTORY_NEW",
        "image": "../src/lib/images/FireSerpent.png"
    }, {
        "id": 1,
        "type": "AK-47",
        "name": "FireSerpent",
        "floatValue": "0.06008401885629",
        "price": 4032272,
        "wearCategory": "FACTORY_NEW",
        "image": "../src/lib/images/FireSerpent.png"
    }, {
        "id": 1,
        "type": "AK-47",
        "name": "FireSerpent",
        "floatValue": "0.06008401885629",
        "price": 4032272,
        "wearCategory": "FACTORY_NEW",
        "image": "../src/lib/images/FireSerpent.png"
    }, {
        "id": 1,
        "type": "AK-47",
        "name": "FireSerpent",
        "floatValue": "0.06008401885629",
        "price": 4032272,
        "wearCategory": "FACTORY_NEW",
        "image": "../src/lib/images/FireSerpent.png"
    }, {
        "id": 1,
        "type": "AK-47",
        "name": "FireSerpent",
        "floatValue": "0.06008401885629",
        "price": 4032272,
        "wearCategory": "FACTORY_NEW",
        "image": "../src/lib/images/FireSerpent.png"
    }]
}

/////////////////////////////////////////  TRANSACTION-HISTORY  /////////////////////////////////////////
export async function getTransactions(itemId) {

    let itemUrl = `/history/item/${itemId}`
    const res = await fetch(ApiClient.SERVER_URL + itemUrl);

    if (res.ok) {
        return await res.json();
    } else {
        throw new Error("Request failed");
    }
}

export default ApiClient;

/////////////////////////////////////////  TRADE-OFFER  /////////////////////////////////////////

export async function sendTradeOffer(tradeOffer) {
    const response = await fetch(ApiClient.SERVER_URL + ApiClient.SEND_TRADE_OFFER);

    if (response.ok) {
        return await response.json();
    } else {
        throw new Error("Request failed");
    }
}