<script>
    import {getOutgoingTradeOffers} from "$services/ApiClient.js";
    import {accountStore} from "../../../stores.js";
    import RespondToTradeOfferCard from "../../../components/RespondToTradeOfferCard.svelte";

    const storeAccount = accountStore();


</script>

<h1>CSGO BANK</h1>
<h2>{$storeAccount ? $storeAccount.account.firstName : ""}`s Outgoing trade offers</h2>

<RespondToTradeOfferCard getOffers={getOutgoingTradeOffers} acceptable = {false}/>

<style>


    h1, h2 {
        text-align: center;
    }
</style>