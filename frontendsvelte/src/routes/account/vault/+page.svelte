<script>
    import ItemCard from "../../../components/ItemCard.svelte";
    import {getItems} from "../../../services/ApiClient.js";
    import {onMount} from "svelte";
    import {vaultStore} from "../../../stores.js";
    import {itemStore} from "../../../stores.js";
    import {deleteVault} from "../../../services/ApiClient.js";
    import {accountStore} from "../../../stores.js";

    const storeAccount = accountStore();
    const storeVault = vaultStore();
    const storeItem = itemStore();
    let items = [];

    onMount(async function () {
        items = await getItems($storeVault.vault.id);


        items.forEach(item => {
            item.image = "../src/lib/images/" + item.name + ".png"
        })
        console.log(items);
    })

    function onComponentClick(event) {
        storeItem.set(event.detail);
        window.location.assign("/account/vault/item")
        console.log('ACCOUNT ID:', event.detail)
    }

    async function deleteVaultBtnClicked() {
        if (items.length !== 0) {
            alert(`Not allowed to delete vault containing items`);
        } else {
            await deleteVault($storeVault.vault.id);
            alert(`Vault with id [${$storeVault.vault.id}] deleted`);
            window.location.assign("/account/");
        }

    }

    function depositItemBtnClicked() {
        window.location.assign("/account/vault/item/deposit");
    }

</script>
<nav><a href="/editAccount/">Edit account</a></nav>
<h1>VAULT</h1>
<h2>{$storeAccount ? $storeAccount.account.firstName : ""}'s Items</h2>
<div id="items">
    {#each items as item}
        <ItemCard on:clicked={onComponentClick} {...item}/>
    {/each}
</div>

<div id="btn_div">
    <button on:click={deleteVaultBtnClicked} id="delete_vault_btn">
        Delete vault
    </button>
    <button on:click={depositItemBtnClicked} id="deposit_item_btn">
        Deposit item
    </button>
</div>

<style>
    #items {
        padding: 5px;
        display: grid;
        grid-auto-columns: minmax(50rem, auto);
        grid-template-columns: repeat(auto-fill, minmax(15rem, auto));
        grid-gap: 10px;
    }


    button {
        margin: 5px;
    }

    #btn_div {
        display: flex;
        justify-content: center;
    }

    h1, h2 {
        text-align: center;
    }

</style>