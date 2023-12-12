<script>
    import TransactionCard from "../../components/TransactionCard.svelte";
    import {onMount} from "svelte";
    import {itemStore} from "../../stores.js";
    import {getTransactions} from "../../services/ApiClient.js";

    const storeItem = itemStore();

    let transactions = [
        {
            transactionId: 1,
            completed: 1,
            fromVaultId: 3,
            toVaultId: 1,
            itemId: 7
        },
        {
            transactionId: 2,
            completed: 1,
            fromVaultId: 3,
            toVaultId: 2,
            itemId: 10
        }
    ];

    onMount(async function () {
        /*transactions = await getTransactions($storeItem.item.id);*/
    })
</script>
<h1>CSGO BANK</h1>
<h2>Transactions</h2>
{#each transactions as transaction, i}
    {#if i > 0}
        <TransactionCard transaction={transaction} headerDisplayed={true}/>
    {:else}
        <TransactionCard transaction={transaction} headerDisplayed={false}/>
    {/if}
{/each}

<style>
    h1, h2 {
        text-align: center;
    }
</style>