<script>

    import {vaultStore} from "../../stores.js";
    import {accountStore} from "../../stores.js";
    import {sendTradeOffer} from "$services/ApiClient.js";
    import {getVaults} from "$services/ApiClient.js";
    import {getAllVaults} from "$services/ApiClient.js";
    import {getItems} from "$services/ApiClient.js";
    import {onMount} from "svelte";

    const storeVault = vaultStore();
    const storeAccount = accountStore();

    let chosenItem = 0;
    let chosenVault = 0;
    let chosenAction = "NOT SET";
    let chosenPrice = 0;

    let myVaults = [];
    let allVaults = [];

    let items = [];
    let vaults = [];
    let actions = ["SELL", "BUY"];

    let buySellBtnName = "Vault";

    let isItemDropDownOpen = false;
    let isVaultDropDownOpen = false;
    let isActionDropDownOpen = false;


    onMount(async () => {
        let response = await getVaults($storeAccount.account.id).catch(() => {
            console.log("Failed to get vaults on id");
        });
        myVaults = response.map(vault => vault.id);


        response = await getAllVaults().catch(() => {
            console.log("Failed to get all vaults");
        });
        allVaults = response
            .filter(vault => vault.vaultAccount.id !== $storeVault.vault.vaultAccount.id)
            .map(vault => vault.id);

    })

    function handleDropDown(type) {
        switch (type) {
            case 'item':
                isItemDropDownOpen = !isItemDropDownOpen
                break;
            case 'fromVault':
                isVaultDropDownOpen = !isVaultDropDownOpen;
                break;
            case 'action':
                isActionDropDownOpen = !isActionDropDownOpen;
                break;
        }
    }

    async function sendTradeOfferBtnClicked() {
        let error = false;

        let tradeOffer =
            {
                "itemId": chosenItem,
                "fromVaultId": $storeVault.vault.id,
                "toVaultId": chosenVault,
                "type": chosenAction,
                "price": chosenPrice
            }

        if (chosenAction === "BUY") {
            tradeOffer.fromVaultId = chosenVault;
            tradeOffer.toVaultId = $storeVault.vault.id;
        }

        if (chosenPrice > $storeAccount.account.credit) {
            alert("You dont have enough credit to create that trade");
        } else {
            await sendTradeOffer(tradeOffer).catch(
                () => {
                    alert("Failed to create trade offer");
                    error = true;
                }
            );
            if (!error) {
                alert("Trade offer successfully sent");
                window.location.assign("/account/vault");
            }
        }
    }

    function chosenActionBtnClicked(action) {
        clearChosenOptions();
        items = [];
        if (action === "BUY") {
            buySellBtnName = "From Vault";
            vaults = allVaults;

        } else if (action === "SELL") {
            buySellBtnName = "To Vault";
            vaults = allVaults;
        }

        chosenAction = action;
    }

    function clearChosenOptions() {
        chosenVault = 0;
        chosenItem = 0;
        chosenAction = "NOT SET";
        chosenPrice = 0;
    }

    async function chosenVaultBtnClicked(vaultId) {

        if (chosenAction === "BUY") {
            let response = await getItems(vaultId);
            items = response.map(item => item.id);
        } else {
            let response = await getItems($storeVault.vault.id);
            items = response.map(item => item.id);
        }


        chosenVault = vaultId;
    }


</script>

<h1>TRADE OFFER</h1>

<div id="choices_div">
    <h2>Choices</h2>
    <table>
        <tr>
            <th>Item ID:</th>
            <td>{chosenItem}</td>
        </tr>
        <tr>
            <th>From Vault ID:</th>
            <td>{chosenVault}</td>
        </tr>
        <tr>
            <th>Action:</th>
            <td>{chosenAction}</td>
        </tr>
        <tr>
            <th>Price:</th>
            <td>{chosenPrice}</td>
        </tr>
    </table>
</div>
<div id="dropdown_menu_div">

    <div id="action_div">
        <button class="dropdown_btn" on:click={()=>handleDropDown('action')}>Action</button>
        <div class="dropdown_content" style:visibility={isActionDropDownOpen ? 'visible' : 'hidden'}>
            <ul>
                {#each actions as action}
                    <li>
                        <button on:click={()=>chosenActionBtnClicked(action)}>{action}</button>
                    </li>
                {/each}
            </ul>
        </div>
    </div>

    <div id="vault_div">
        <button class="dropdown_btn" on:click={()=>handleDropDown('fromVault')}>
            {buySellBtnName}
        </button>
        <div class="dropdown_content" style:visibility={isVaultDropDownOpen ? 'visible' : 'hidden'}>
            <ul>
                {#each vaults as vault}
                    <li>
                        <button on:click={()=>chosenVaultBtnClicked(vault)}>{vault}</button>
                    </li>
                {/each}
            </ul>
        </div>
    </div>

    <div id="item_div">
        <button class="dropdown_btn" on:click={()=>handleDropDown('item')}>Item</button>
        <div class="dropdown_content" style:visibility={isItemDropDownOpen ? 'visible' : 'hidden'}>

            <ul>
                {#each items as item}
                    <li>
                        <button on:click={()=> chosenItem = item}>{item}</button>
                    </li>
                {/each}
            </ul>
        </div>
    </div>


    <div id="price_input_div">
        <label for="price_input">Price:</label>
        <input name="price_input" type="text" bind:value={chosenPrice}>
    </div>
</div>

<div id="send_trade_btn_div">
    <button on:click={sendTradeOfferBtnClicked} id="send_trade_btn">
        Send trade
    </button>
</div>
<style>

    #price_input_div {
        margin-top: 15px;
    }

    #send_trade_btn_div {
        display: flex;
        justify-content: center;
    }

    #send_trade_btn {
        border-radius: 7px;
        padding: 0;
        width: 120px;
        height: 40px;
        border: none;
    }

    #dropdown_menu_div {
        display: flex;
        justify-content: center;
        align-items: flex-start;
        height: 30vh;
    }

    .dropdown_btn {
        border-radius: 5px;
        margin-top: 10px;
        margin-left: 10px;
        margin-right: 10px;
        padding: 0;
        width: 80px;
        height: 30px;
        border: none;
        cursor: pointer;

    }

    .dropdown_content {
        /*position: absolute;*/
        align-content: center;
        background-color: #191d25;
        /*min-width: 100px;*/
        height: auto;
        box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
        /*z-index: 1;*/
    }

    .dropdown_content li {
        text-align: center;
        padding-bottom: 5px;
        padding-top: 5px;
        text-decoration: none;
        display: block;

    }

    ul {
        padding: 0;

    }

    #choices_div {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        border: black solid 1px;
    }

    h1, h2 {
        text-align: center;
    }
</style>
