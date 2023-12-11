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
