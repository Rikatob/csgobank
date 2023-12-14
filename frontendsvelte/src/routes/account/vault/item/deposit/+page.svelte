<script>

    import {onMount} from "svelte";
    import ItemCard from "../../../../../components/ItemCard.svelte";
    import {accountStore} from "../../../../../stores.js";
    import {vaultStore} from "../../../../../stores.js";
    import {deleteAccount, getAllAvailableItems} from "../../../../../services/ApiClient.js";
    import {depositItem} from "../../../../../services/ApiClient.js";


    const storeVault = vaultStore();
    const storeAccount = accountStore();
    let items = [];

    onMount(async function () {
        items = await getAllAvailableItems();

        items.forEach(item => {
            item.image = "../../../../src/lib/images/" + item.name + ".png"
        })
    })


    async function onComponentClick(event) {

        let alertMessage = "Are you sure you want to buy this item?\nPress OK to confirm."
        let error = false;

        if (confirm(alertMessage) === true) {
            await depositItem($storeVault.vault.id, event.detail.item.id)
                .catch(() => {
                    alert("Failed to deposit item");
                    error = true;
                });
            if (!error) {
                alert(`Item with id [${event.detail.item.id}] successfully deposit`);
                /*window.location.reload();*/
            }
        }


    }

</script>
<h1>Marketplace</h1>
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
        grid-gap: 10px;
    }

    h1, h2 {
        text-align: center;
    }
</style>