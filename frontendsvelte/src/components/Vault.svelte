<script>
    import ApiClient from "../services/ApiClient.js";
    import ItemCard from "./ItemCard.svelte";
    import {getItems} from "../services/ApiClient.js";
    import {onMount} from "svelte";
    import FireSerpent from "../assets/fireserpent.png"
    import DragonLore from "../assets/dragonLore.png"
    import Howl from "../assets/howl.png"


    let items = [];


    onMount(async function () {

            items = await getItems();

            items.forEach(item => {
                if(item.name === "FireSerpent"){
                    item.image = FireSerpent;
                } else if(item.name === "Howl"){
                    item.image = Howl;
                } else if(item.name === "DragonLoore"){
                    item.image = DragonLore;
                }
            })
        })


</script>


<h1>CSGO VAULT</h1>

<div id="vault">
    {#each items as item}
        <ItemCard {...item}/>
    {/each}
</div>


<style>
    #vault {
        padding: 5px;
        display: grid;
        grid-auto-columns: minmax(50rem, auto);
        grid-template-columns: repeat(auto-fill, minmax(15rem, 50fr));
        grid-gap: 30px;
    }

    h1 {
        text-align: center;
    }
</style>