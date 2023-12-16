class ApiClient {

    static SERVER_URL = 'http://localhost:8000';
    static GET_ALL_ACCOUNTS = '/account/all'
    static GET_ALL_VAULTS = '/vault/all';
    static GET_ALL_AVAILABLE_ITEMS = '/vaultItem/available'
    static UPDATE_ACCOUNT = '/account/update'
    static CREATE_ACCOUNT = '/account/new'
    static SEND_TRADE_OFFER = '/vault/offer'
}


/////////////////////////////////////////  ACCOUNT  /////////////////////////////////////////

// Get all accounts.
export async function getAccounts() {
    const response = await fetch(ApiClient.SERVER_URL + ApiClient.GET_ALL_ACCOUNTS);
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
            method: 'DELETE',
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
    const response = await fetch(ApiClient.SERVER_URL + vaultUrl, {
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

// Get all vaults.
export async function getAllVaults() {

    const response = await fetch(ApiClient.SERVER_URL + ApiClient.GET_ALL_VAULTS, {
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
    const res = await fetch(ApiClient.SERVER_URL + withdrawUrl, {
        method: 'POST'
    });

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

    let response = await fetch(ApiClient.SERVER_URL + ApiClient.GET_ALL_AVAILABLE_ITEMS);
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error("Request failed");
    }
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

// Post a trade-offer to send.
export async function sendTradeOffer(tradeOffer) {
    const response = await fetch(ApiClient.SERVER_URL + ApiClient.SEND_TRADE_OFFER,
        {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(tradeOffer)
        });

    if (response.ok) {
        return await response.json();
    } else {
        throw new Error("Request failed");
    }
}

// Get incoming trade-offers in an account.
export async function getIncomingTradeOffers(accountId) {
    let incomingUrl = `/transaction/incoming/${accountId}`;
    const response = await fetch(ApiClient.SERVER_URL + incomingUrl);

    if (response.ok) {
        return await response.json();
    } else {
        throw new Error("Request failed");
    }
}

// Get outgoing trade-offers in an account.
export async function getOutgoingTradeOffers(accountId) {
    let incomingUrl = `/transaction/outgoing/${accountId}`;
    const response = await fetch(ApiClient.SERVER_URL + incomingUrl);

    if (response.ok) {
        return await response.json();
    } else {
        throw new Error("Request failed");
    }
}

// Accept trade-offer with tradeOffer id (transactionId).
export async function acceptTradeOffer(tradeOfferId) {
    let acceptUrl = `/transaction/accept/${tradeOfferId}`;
    const response = await fetch(ApiClient.SERVER_URL + acceptUrl, {
        method: 'POST'
    });

    if (response.ok) {
        return await response.json()
    } else {
        throw new Error("Request failed");
    }
}

// Decline trade-offer with tradeOffer id (transactionId).
export async function declineTradeOffer(tradeOfferId) {
    let declineUrl = `/transaction/decline/${tradeOfferId}`;
    const response = await fetch(ApiClient.SERVER_URL + declineUrl, {
        method: 'POST'
    });

    if (response.ok) {
        return await response.json()
    } else {
        throw new Error("Request failed");
    }
}

/////////////////////////////////////////  CREDITS  /////////////////////////////////////////

export async function depositCredits(accountId, credits) {
    let depositUrl = `/account/credit/${accountId}/deposit`;

    let response = await fetch(ApiClient.SERVER_URL + depositUrl, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(credits)
    })

    if (response.ok) {
        return await response.json()
    } else {
        throw new Error("Request failed");
    }


}

export async function withdrawCredits(accountId, credits) {
    let withdrawUrl = `/account/credit/${accountId}/withdraw`;

    let response = await fetch(ApiClient.SERVER_URL + withdrawUrl, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(credits)
    })

    if (response.ok) {
        return await response.json()
    } else {
        throw new Error("Request failed");
    }
}