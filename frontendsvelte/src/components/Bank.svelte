<script>
    import ApiClient from "../services/ApiClient.js";
    import ItemCard from "./ItemCard.svelte";
    import {getItems} from "../services/ApiClient.js";
    import {onMount} from "svelte";
    import {getAccounts} from "../services/ApiClient.js";
    import {getVaults} from "../services/ApiClient.js";
    import FireSerpent from "../lib/images/FireSerpent.png"
    import DragonLore from "../lib/images/DragonLore.png"
    import Howl from "../lib/images/Howl.png"
    import AccountCard from "./AccountCard.svelte";
    import VaultCard from "./VaultCard.svelte";

    let items = [];
    let accounts = [];
    let vaults = [];

    onMount(async function () {

        vaults = await getVaults();

        accounts = await getAccounts();

        items = await getItems();

        items.forEach(item => {
            if (item.name === "FireSerpent") {
                item.image = FireSerpent;
            } else if (item.name === "Howl") {
                item.image = Howl;
            } else if (item.name === "DragonLore") {
                item.image = DragonLore;
            }
        })
    })


    function onComponentClick(event) {

        console.log('ACCOUNT ID:', event.detail)
    }

</script>


<h1>CSGO BANK</h1>
<h2>Accounts</h2>
<div id="accounts" >

    {#each accounts as account}
        <AccountCard on:clicked={onComponentClick} {...account}/>
    {/each}
</div>

<!--<div id="items">
    {#each items as item}
        <ItemCard on:clicked={onComponentClick} {...item}/>
    {/each}
</div>-->

<!--
<div id="vaults">
    {#each vaults as vault}
        <VaultCard on:clicked={onComponentClick} {...vault}/>
    {/each}
</div>
-->

<style>
    #accounts {
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