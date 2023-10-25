<script>
    import { onMount } from 'svelte';
    import ApiClient from "./services/ApiClient.js";

    let vault;
    let loading = true;

    onMount(async () => {
        try {
            const response = await fetch(ApiClient.SERVER_URL + ApiClient.GET_ITEMS);
            vault = await response.json();
            loading = false;
        } catch (error) {
            console.error(error);
        }
    });
</script>

{#if loading}
    <div>LOADING...</div>
{:else}
    <h1>CSGO VAULT</h1>
    <div class="inventory">
        {#each vault as i (i.id)}
            <div class="item-card" key={i.id}>
                <img src={i.image} alt={i.name} />
                <div class="item-info">
                    <label>ID</label>
                    <br />
                    {i.id}
                </div>
                <div class="item-info">
                    <label>NAME</label>
                    <br />
                    {i.name}
                </div>
            </div>
        {/each}
    </div>
{/if}

<style>
    /* Overall page styling */
    body {
        background-color: #171a21;
        font-family: Arial, sans-serif;
        color: #c1c6cf;
        margin: 0;
        padding: 0;
    }

    /* Header styling */
    h1 {
        text-align: center;
        padding: 20px;
        font-size: 24px;
        background-color: #1e2127;
        color: #61b1f7;
    }

    /* Inventory grid styling */
    div.inventory {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
        gap: 10px;
        padding: 20px;
    }

    /* Item card styling */
    div.item-card {
        background-color: #2c303b;
        border: 1px solid #3e424f;
        padding: 10px;
        border-radius: 5px;
    }

    div.item-card img {
        max-width: 100%;
        height: auto;
    }

    /* Label styling */
    label {
        color: #61b1f7;
        font-weight: bold;
    }

    /* Item information styling */
    div.item-info {
        margin-top: 10px;
    }
</style>
