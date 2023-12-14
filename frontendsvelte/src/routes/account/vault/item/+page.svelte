<script>
    import TransactionCard from "../../../../components/TransactionCard.svelte";
    import {onMount} from "svelte";
    import {itemStore} from "../../../../stores.js";
    import {vaultStore} from "../../../../stores.js";
    import {getTransactions} from "../../../../services/ApiClient.js";
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
        let withdrewItem = await withdrawItem($storeVault.vault.id, $storeItem.item.id);

        alert(withdrewItem + "successfully withdrew");
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
{#each transactions as transaction, i}
    {#if i > 0}
        <TransactionCard transaction={transaction} headerDisplayed={true}/>
    {:else}
        <TransactionCard transaction={transaction} headerDisplayed={false}/>
    {/if}
{/each}

<div id="btn_div">
    <button id="withdraw_btn" on:click={withdrawItemBtnClicked}> Withdraw item</button>
    <button id="transfer_btn" on:click={transferItemBtnClicked}>Transfer item</button>
    <input bind:value={toVaultId} type="text" placeholder="Enter receivers vault id">

</div>

<style>

    button {
        margin: 5px;
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