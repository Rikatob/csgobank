<script>

    import {getIncomingTradeOffers} from "$services/ApiClient.js";
    import {accountStore} from "../../../stores.js";
    import RespondToTradeOfferCard from "../../../components/RespondToTradeOfferCard.svelte";
    const storeAccount = accountStore();


</script>

<h1>CSGO BANK</h1>
<h2>{$storeAccount ? $storeAccount.account.firstName : ""}`s Incoming trade offers</h2>

<RespondToTradeOfferCard getOffers={getIncomingTradeOffers}/>

<style>


    h1, h2 {
        text-align: center;
    }
</style>