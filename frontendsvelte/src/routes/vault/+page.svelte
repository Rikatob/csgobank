<script>

    import {getVaults} from "../../services/ApiClient.js";
    import {onMount} from "svelte";
    import VaultCard from "../../components/VaultCard.svelte";
    import {accountStore} from "../../stores.js";
    import {vaultStore} from "../../stores.js";

    const storeAccount = accountStore();
    const storeVault = vaultStore();

    let vaults = [];


    onMount(async function () {
            vaults = await getVaults($storeAccount.account.id);
        }
    )

    function onComponentClick(event) {
        storeVault.set(event.detail);
        window.location.assign("/item/")
    }


</script>

<h1>CSGO BANK</h1>
<h2>Vaults</h2>

<div id="vaults">
    {#each vaults as vault}
        <VaultCard on:clicked={onComponentClick} {...vault}/>
    {/each}
</div>

<style>
    #vaults {
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