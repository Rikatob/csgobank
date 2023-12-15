<script>

    import {getVaults} from "../../services/ApiClient.js";
    import {onMount} from "svelte";
    import VaultCard from "../../components/VaultCard.svelte";
    import {accountStore} from "../../stores.js";
    import {vaultStore} from "../../stores.js";
    import {deleteAccount} from "../../services/ApiClient.js";
    import {createNewVault} from "../../services/ApiClient.js";
    const storeAccount = accountStore();
    const storeVault = vaultStore();

    let vaults = [];


    onMount(async function () {
            vaults = await getVaults($storeAccount.account.id);
        }
    )

    function onComponentClick(event) {
        storeVault.set(event.detail);
        window.location.assign("/account/vault/");
    }

    async function createVaultBtnClicked() {
        let newVault = await createNewVault($storeAccount.account.id);
        vaults = [...vaults, newVault];
        alert(`New vault with id [${newVault.id}] created`);
    }

    async function incomingBtnClicked() {
        window.location.assign("/tradeOffer/incoming");
    }

    async function outgoingBtnClicked() {
        window.location.assign("/tradeOffer/outgoing");
    }


</script>

<nav><a href="/editAccount/">Edit account</a></nav>
<h1>ACCOUNT</h1>
<h2>{$storeAccount ? $storeAccount.account.firstName : ""}'s Vaults</h2>
<div id="vaults">
    {#each vaults as vault}
        <VaultCard on:clicked={onComponentClick} {...vault}/>
    {/each}

</div>

<div id="btn_div">
    <button on:click={createVaultBtnClicked}>
        Create new vault
    </button>
    <button on:click={incomingBtnClicked}>
        Incoming trade offers
    </button>
    <button on:click={outgoingBtnClicked}>
        Outgoing trade offers
    </button>
</div>


<style>
    #vaults {
        padding: 5px;
        display: grid;
        grid-auto-columns: minmax(50rem, auto);
        grid-template-columns: repeat(auto-fill, minmax(15rem, auto));
        grid-gap: 10px;
    }

    h1, h2 {
        text-align: center;
    }

    #btn_div {
        display: flex;
        justify-content: center;
    }

    button {
        margin-left: 10px;
        border-radius: 5px;
        border: none;
        height: 20px;
        width: 140px;
    }
</style>