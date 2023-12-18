import {writable} from "svelte/store";


export function accountStore(accountId) {
    const {subscribe, set, update} = writable(accountId);

    const isBrowser = typeof window !== 'undefined'

    if (isBrowser && localStorage.account) {
        set(JSON.parse(localStorage.account));
    }

    return {
        subscribe,
        set: accountId => {
            if (isBrowser) {
                localStorage.account = JSON.stringify(accountId);
            }
            set(accountId);
        }
    }
}

export function vaultStore(vaultId) {
    const {subscribe, set, update} = writable(vaultId);

    const isBrowser = typeof window !== 'undefined'

    if (isBrowser && localStorage.vault) {
        set(JSON.parse(localStorage.vault));
    }

    return {
        subscribe,
        set: vaultId => {
            if (isBrowser) {
                localStorage.vault = JSON.stringify(vaultId);
            }
            set(vaultId);
        }
    }
}

export function itemStore(item) {
    const {subscribe, set} = writable(item);

    const isBrowser = typeof window !== 'undefined'

    if (isBrowser && localStorage.item) {
        set(JSON.parse(localStorage.item));
    }

    return {
        subscribe,
        set: item => {
            if (isBrowser) {
                localStorage.item = JSON.stringify(item);
            }
            set(item);
        }
    }
}
