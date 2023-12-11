<script>
    import FireSerpent from "../lib/images/FireSerpent.png"
    import DragonLore from "../lib/images/DragonLore.png"
    import Howl from "../lib/images/Howl.png"
    import { createEventDispatcher } from 'svelte';


    const dispatch = createEventDispatcher();
    export let id = "0";
    export let floatValue = "0"
    export let price = "0"
    export let type = "Default weapon";
    export let name = "Default skin";
    export let wearCategory = "Default wear";
    export let image;
    function handleClick() {
        dispatch("clicked",id);
    }
</script>

<div on:click={handleClick} id="item-card">
    <img src="{image}" alt="{type} {name}">
    <h1>{type} | {name} ({wearCategory})</h1>

    <table id="item-info">
        <tr>
            <th>ID:</th>
            <td>{id}</td>
        </tr>
        <tr>
            <th>FLOAT:</th>
            <td>{floatValue}</td>
        </tr>
        <tr>
            <th>PRICE:</th>
            <td>${price}</td>
        </tr>
    </table>

</div>

<style>
    #item-card {
        cursor: pointer;
        border: black solid 2px;
        padding: 10px;
        height: auto;
        width: auto;
        overflow: hidden;
        background-color: #2c303b;
        border-radius: 10px;
    }
    img {
        width: 100%;
        height: auto;
    }
    th {
        text-align: start;
    }
    h1 {
        font-size: 1.3em;
        text-wrap: nowrap;

    }
    h1:hover {
        text-wrap: initial;
    }
    table {
        border: solid black 1px;
        width: 100%;
    }
</style>