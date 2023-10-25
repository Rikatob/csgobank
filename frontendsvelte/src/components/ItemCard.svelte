<script>
    import Image from "../assets/fireserpent.png"


    export let weapon = "Default weapon"
    export let skin = "Default skin"
    export let wear = "Default wear"

</script>

<div id="item-card">
    <img src="{Image}" alt="AK-47 fireserpent">
    <h1>{weapon} | {skin} ({wear})</h1>

    <table id="item-info">
        <tr>
            <th>ID:</th>
            <td>2</td>
        </tr>
        <tr>
            <th>FLOAT:</th>
            <td>0.253</td>
        </tr>
        <tr>
            <th>PRICE:</th>
            <td>15000</td>
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
</style>