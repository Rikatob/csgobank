<script>

    import {createEventDispatcher} from 'svelte';

    const dispatch = createEventDispatcher()

    export let id = "default id";
    export let vaultAccount = {id: 0};

    export let totalValue = "0";


    let vault = {
        id: id,
        vaultAccount: vaultAccount,
        totalValue: totalValue
    }

    function handleClick() {
        dispatch("clicked", {vault});
    }

</script>

<div on:click={handleClick} id="vault-card">
    <h1> Vault id:{id}</h1>
    <table id="vault-info">
        <tr>
            <th>OWNER ID:</th>
            <td>{vaultAccount.id}</td>
        </tr>
        <tr>
            <th>TOTAL VALUE:</th>
            <td>{totalValue}</td>
        </tr>
    </table>
</div>

<style>
    #vault-card {
        cursor: pointer;
        border: black solid 2px;
        padding: 10px;
        height: auto;
        width: auto;
        overflow: hidden;
        background-color: #2c303b;
        border-radius: 10px;
    }

    img {
        width: 100%;
        height: auto;
    }

    th {
        text-align: start;
    }

    h1 {
        font-size: 1.3em;
        text-wrap: nowrap;

    }

    h1:hover {
        text-wrap: initial;
    }

    table {
        border: solid black 1px;
        width: 100%;
    }
</style>