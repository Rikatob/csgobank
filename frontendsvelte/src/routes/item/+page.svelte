<script>
    import ItemCard from "../../components/ItemCard.svelte";
    import {getItems} from "../../services/ApiClient.js";
    import {onMount} from "svelte";

    let items = [];

    onMount(async function () {
        items = await getItems("1");


        items.forEach(item => {
            item.image = "src/lib/images/" + item.name + ".png"
        })
    })

    function onComponentClick(event) {

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
        grid-template-columns: repeat(auto-fill, minmax(15rem, 50fr));
        grid-gap: 30px;
    }


    h1, h2 {
        text-align: center;
    }
</style>