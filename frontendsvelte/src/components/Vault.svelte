<script>
    import ApiClient from "../services/ApiClient.js";
    import ItemCard from "./ItemCard.svelte";


    let items = [
        {
            weapon: "AK-47",
            skin: "Fire Serpent",
            wear: "Minimal Wear"
        },
        {
            weapon: "NOVA",
            skin: "Black laminate",
            wear: "Battle Scarred"
        },
        {
            weapon: "NOVA",
            skin: "Black laminate",
            wear: "Battle Scarred"
        },
        {
            weapon: "NOVA",
            skin: "Black laminate",
            wear: "Battle Scarred"
        },
        {
            weapon: "NOVA",
            skin: "Black laminate",
            wear: "Battle Scarred"
        },
        {
            weapon: "NOVA",
            skin: "Black laminate",
            wear: "Battle Scarred"
        }
    ]
</script>

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
        grid-template-columns: repeat(auto-fill, minmax(15rem,50fr));
        grid-gap: 30px;
    }
</style>