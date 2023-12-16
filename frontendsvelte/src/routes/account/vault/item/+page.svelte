<script>
    import TransactionCard from "../../../../components/TransactionCard.svelte";
    import {onMount} from "svelte";
    import {itemStore} from "../../../../stores.js";
    import {vaultStore} from "../../../../stores.js";
    import {deleteAccount, getTransactions} from "../../../../services/ApiClient.js";
    import {withdrawItem} from "../../../../services/ApiClient.js";
    import {transferItem} from "../../../../services/ApiClient.js";

    const storeItem = itemStore();
    const storeVault = vaultStore();


    let transactions = [];
    let vaults = [];
    let toVaultId = "";

    onMount(async function () {
        transactions = await getTransactions($storeItem.item.id);
    })

    async function withdrawItemBtnClicked() {

        let alertMessage = "Are you sure you want to withdraw this account?\nPress OK to confirm."

        if (confirm(alertMessage) === true) {
            let withdrewItem = await withdrawItem($storeVault.vault.id, $storeItem.item.id);
            alert("Item with id [" + withdrewItem.id + "] successfully withdrew");
        }

    }

    async function transferItemBtnClicked() {
        let error = false;
        const transaction = {
            fromVaultId: $storeVault.vault.id,
            toVaultId: toVaultId,
            itemId: $storeItem.item.id
        }
        await transferItem(transaction)
            .catch(() => {
                alert("Failed to do transaction");
                error = true
            })


        if (!error) {
            alert("Item was successfully transferred");
        }

    }
</script>
<h1>CSGO BANK</h1>
<h2>Item info</h2>
<br>
<br>
<h3>Transaction History</h3>

{#if transactions.length === 0}
    <div id="empty_history_div">Item has never been traded...</div>
{:else}
    {#each transactions as transaction, i}
        {#if i > 0}
            <TransactionCard transaction={transaction} headerDisplayed={true}/>
        {:else}
            <TransactionCard transaction={transaction} headerDisplayed={false}/>
        {/if}
    {/each}
{/if}
<div id="btn_div">
    <button id="withdraw_btn" on:click={withdrawItemBtnClicked}> Withdraw item</button>
    <button id="transfer_btn" on:click={transferItemBtnClicked}>Transfer item</button>
    <input bind:value={toVaultId} type="text" placeholder="Enter receivers vault id">

</div>

<style>

    button {
        margin: 5px;
    }

    #empty_history_div {
        margin-top: 100px;
        margin-bottom: 100px;
        text-align: center;
    }
    input {
        margin: 5px;
    }

    #btn_div {
        display: flex;
        justify-content: center;
    }

    h1, h2, h3 {
        text-align: center;
    }
</style>