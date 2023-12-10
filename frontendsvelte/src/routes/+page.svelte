<script>
    import AccountCard from "../components/AccountCard.svelte";
    import {getAccounts} from "../services/ApiClient.js";
    import {onMount} from "svelte";


    let accounts = [];

    onMount(async function () {
        accounts = await getAccounts();
    })

    function onComponentClick(event) {

        console.log('ACCOUNT ID:', event.detail)
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
        grid-template-columns: repeat(auto-fill, minmax(15rem, 50fr));
        grid-gap: 30px;
    }


    h1, h2 {
        text-align: center;
    }
</style>