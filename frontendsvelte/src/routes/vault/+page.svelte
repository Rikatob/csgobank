<script>

    import {getVaults} from "../../services/ApiClient.js";
    import {onMount} from "svelte";
    import VaultCard from "../../components/VaultCard.svelte";
    import {accountStore} from "../../stores.js";
    import {vaultStore} from "../../stores.js";
    import {deleteAccount} from "../../services/ApiClient.js";

    const storeAccount = accountStore();
    const storeVault = vaultStore();

    let vaults = [];


    onMount(async function () {
            vaults = await getVaults($storeAccount.account.id);
        }
    )

    function onComponentClick(event) {
        storeVault.set(event.detail);
        window.location.assign("/item/")
    }

    async function onDeleteBtnClick() {
        let alertMessage = "Are you sure you want to delete this account?\nPress OK to confirm."

        if (confirm(alertMessage) === true) {
            await deleteAccount($storeAccount.account.id);
            alert("Account with id " + $storeAccount.account.id + " is Deleted");
            window.location.assign("/");
        }
    }

</script>

<nav><a href="/editAccount/">Edit account</a></nav>
<h1>CSGO BANK</h1>
<h2>{$storeAccount ? $storeAccount.account.firstName : ""}'s Vaults</h2>
<div id="vaults">
    {#each vaults as vault}
        <VaultCard on:clicked={onComponentClick} {...vault}/>
    {/each}

</div>

<div id="delete-btn">
    <button on:click={onDeleteBtnClick}>Delete account</button>
</div>

<style>
    #vaults {
        padding: 5px;
        display: grid;
        grid-auto-columns: minmax(50rem, auto);
        grid-template-columns: repeat(auto-fill, minmax(15rem, auto));
        grid-gap: 10px;
    }

    #delete-btn {
        text-align: center;
    }

    h1, h2 {
        text-align: center;
    }
</style>