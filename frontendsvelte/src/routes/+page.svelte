<script>
    import AccountCard from "../components/AccountCard.svelte";
    import {getAccounts} from "../services/ApiClient.js";
    import {onMount} from "svelte";
    import {accountStore} from "../stores.js";

    let accounts = [];
    const store = accountStore();

    onMount(async function () {
        accounts = await getAccounts();
    })

    function onComponentClick(event) {
        store.set(event.detail);
        window.location.assign("/vault/");
    }
</script>


<h1>CSGO BANK</h1>
<h2>Accounts</h2>
<div id="accounts" >

    {#each accounts as account}
        <AccountCard on:clicked={onComponentClick} {...account}/>
    {/each}
</div>

<style>
    #accounts {
        padding: 5px;
        display: grid;
        grid-auto-columns: minmax(50rem, auto);
        grid-template-columns: repeat(auto-fill, minmax(15rem, auto));
        grid-gap:10px;
    }


    h1, h2 {
        text-align: center;
    }
</style>