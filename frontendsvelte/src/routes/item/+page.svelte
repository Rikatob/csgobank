<script>
    import ItemCard from "../../components/ItemCard.svelte";
    import {getItems} from "../../services/ApiClient.js";
    import {onMount} from "svelte";
    import {vaultStore} from "../../stores.js";
    import {itemStore} from "../../stores.js";

    const storeVault = vaultStore();
    const storeItem = itemStore();
    let items = [];

    onMount(async function () {
        items = await getItems($storeVault.vault.id);


        items.forEach(item => {
            item.image = "src/lib/images/" + item.name + ".png"
        })
    })

    function onComponentClick(event) {
        storeItem.set(event.detail);
        window.location.assign("/transactionHistory/")
        console.log('ACCOUNT ID:', event.detail)
    }

</script>
<h1>CSGO BANK</h1>
<h2>Items</h2>
<div id="items">
    {#each items as item}
        <ItemCard on:clicked={onComponentClick} {...item}/>
    {/each}
</div>

<style>
    #items {
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