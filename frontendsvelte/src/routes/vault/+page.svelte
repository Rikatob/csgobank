<script>

    import {getVaults} from "../../services/ApiClient.js";
    import {onMount} from "svelte";
    import VaultCard from "../../components/VaultCard.svelte";

    let vaults = [];


    onMount(async function () {
            vaults = await getVaults();
        }
    )

    function onComponentClick(event) {

        console.log('ACCOUNT ID:', event.detail)
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
        grid-template-columns: repeat(auto-fill, minmax(15rem, 50fr));
        grid-gap: 30px;
    }


    h1, h2 {
        text-align: center;
    }
</style>