<script>

    /* export let transactionId;
     export let completed;
     export let fromVaultId;
     export let toVaultId;
     export let itemId;*/

    export let transaction = {
        transactionId: 0,
        state: "",
        fromVaultId: 0,
        toVaultId: 0,
        itemId: 0,
        price: 0,
        type: ""
    }
    export let headerDisplayed = false;


</script>


<div id="transaction">
    <div class="column">
        {#if headerDisplayed === false}
            <p id="headerId">ID</p>
        {/if}
        <p id="dataId">{transaction.transactionId}</p>
    </div>
    <div class="column">
        {#if headerDisplayed === false}
            <p class="transactionHeader">State</p>
        {/if}
        <p class="transactionData">{transaction.state}</p>
    </div>
    <div class="column">
        {#if headerDisplayed === false}
            <p class="transactionHeader">FromVaultId</p>
        {/if}
        <p class="transactionData">{transaction.fromVaultId}</p>
    </div>
    <div class="column">
        {#if headerDisplayed === false}
            <p class="transactionHeader">ToVaultId</p>
        {/if}
        <p class="transactionData">{transaction.toVaultId}</p>
    </div>
    <div class="column">
        {#if headerDisplayed === false}
            <p class="transactionHeader">ItemId</p>
        {/if}
        <p class="transactionData">{transaction.itemId}</p>
    </div>
    <div class="column">
        {#if headerDisplayed === false}
            <p class="transactionHeader">Price</p>
        {/if}
        <p class="transactionData">{transaction.price}</p>
    </div>
    <div class="column">
        {#if headerDisplayed === false}
            <p class="transactionHeader">Type</p>
        {/if}
        <p class="transactionData">{transaction.type}</p>
    </div>

</div>
<p id="arrow">&#x2193</p>

<style>

    #transaction {
        display: flex;
        flex-direction: row;
        justify-content: center;

    }

    #arrow {
        display: flex;
        justify-content: center;
        color: #1a9efd;
        margin-top: 0;
        margin-bottom: 4px;
    }

    #headerId {

        margin: 0;
        color: #1a9efd;
        border-right: black solid 1px;
        border-top: black solid 1px;
        border-left: black solid 1px;
    }

    #dataId {
        width: 20px;
        height: 20px;
        margin: 0;
        border: black solid 1px;
        font-weight: bold;
        color: #525252;;

    }

    .column {

    }

    .transactionHeader {
        border-right: black solid 1px;
        border-top: black solid 1px;
        margin: 0;
        color: #1a9efd;

    }

    .transactionData {
        border-right: black solid 1px;
        border-top: black solid 1px;
        border-bottom: black solid 1px;
        width: 90px;
        height: 20px;
        margin: 0;
    }
</style>