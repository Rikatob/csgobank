<script>
    import FireSerpent from "../assets/fireserpent.png"
    import DragonLore from "../assets/dragonLore.png"
    import Howl from "../assets/howl.png"


    export let id = "0";

    export let floatValue = "0"

    export let price = "0"
    export let type = "Default weapon";
    export let name = "Default skin";
    export let wearCategory = "Default wear";
    export let image;

</script>

<div id="item-card">
    <img src="{image}" alt="{type} {name}">
    <h1>{type} | {name} ({wearCategory})</h1>

    <table id="item-info">
        <tr>
            <th>ID:</th>
            <td>{id}</td>
        </tr>
        <tr>
            <th>FLOAT:</th>
            <td>0.{floatValue}</td>
        </tr>
        <tr>
            <th>PRICE:</th>
            <td>${price}</td>
        </tr>
    </table>

</div>

<style>
    #item-card {
        border: black solid 2px;
        display: inline-block;
        padding: 10px;
        height: 100%;
        width: 100%;
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