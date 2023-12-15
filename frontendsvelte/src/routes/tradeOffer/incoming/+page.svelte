<script>

    import {onMount} from "svelte";
    import {getIncomingTradeOffers} from "$services/ApiClient.js";
    import {acceptTradeOffer} from "$services/ApiClient.js";
    import {declineTradeOffer} from "$services/ApiClient.js";
    import {accountStore} from "../../../stores.js";
    import TransactionCard from "../../../components/TransactionCard.svelte";

    const storeAccount = accountStore();

    let tradeOffers = [];

    onMount(async function () {

        let error = false;
        tradeOffers = await getIncomingTradeOffers($storeAccount.account.id)
            .catch(() => {
                console.log("Failed to get incoming offers");
                error = true;
            });

        if (!error) {
            console.log(tradeOffers.length);
        }
    })

    async function acceptBtnClicked(offerId) {

        let error = false;
        await acceptTradeOffer(offerId)
            .catch(() => {
                alert("Could not accept offer");
                error = true;
            })

        if (!error) {
            alert("Trade offer successfully accepted");
            window.location.reload();
        }
    }

    async function declineBtnClicked(offerId) {

        let error = false;
        await declineTradeOffer(offerId)
            .catch(() => {
                alert("Could not decline offer");
                error = true;
            })

        if (!error) {
            alert("Trade offer declined");
            window.location.reload();
        }
    }
</script>

<h1>CSGO BANK</h1>
<h2>{$storeAccount ? $storeAccount.account.firstName : ""}`s Incoming trade offers</h2>

{#if tradeOffers.length === 0}
    <div id="empty_offers_div">No incoming trade offers!</div>
{:else}
    {#each tradeOffers as transaction, i}

        {#if i > 0}
            <TransactionCard transaction={transaction} headerDisplayed={true}/>
            <div class="btn_div">
                <button on:click={()=>{acceptBtnClicked(transaction.transactionId)}}>Accept trade offer</button>
                <button on:click={()=>{declineBtnClicked(transaction.transactionId)}}>Decline trade offer</button>
            </div>
        {:else}
            <TransactionCard transaction={transaction} headerDisplayed={false}/>
            <div class="btn_div">
                <button on:click={()=>{acceptBtnClicked(transaction.transactionId)}}>Accept trade offer</button>
                <button on:click={()=>{declineBtnClicked(transaction.transactionId)}}>Decline trade offer</button>
            </div>
        {/if}
    {/each}
{/if}

<style>

    #empty_offers_div {
        margin-top: 200px;
        text-align: center;
    }

    .btn_div {
        text-align: center;
    }

    h1, h2 {
        text-align: center;
    }
</style>