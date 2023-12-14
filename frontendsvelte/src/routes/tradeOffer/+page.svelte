<script>

    import {vaultStore} from "../../stores.js";
    import {accountStore} from "../../stores.js";
    import {sendTradeOffer} from "$services/ApiClient.js";
    import {getVaults} from "$services/ApiClient.js";

    const storeVault = vaultStore();
    const storeAccount = accountStore();

    let chosenItem = 0;
    let chosenVault = 0;
    let chosenAction = "NOT SET";
    let chosenPrice = 0;

    let items = [1, 2, 3, 4];
    let vaults = [1, 2, 3, 4];
    let actions = ["SELL", "BUY"];


    let isItemDropDownOpen = false;
    let isVaultDropDownOpen = false;
    let isActionDropDownOpen = false;

    function handleItemDropdown() {
        isItemDropDownOpen = !isItemDropDownOpen;
    }

    function handleVaultDropdown() {
        isVaultDropDownOpen = !isVaultDropDownOpen;
    }

    function handleActionDropdown() {
        isActionDropDownOpen = !isActionDropDownOpen;
    }

    async function sendTradeOfferBtnClicked() {
        let error = false;

        let tradeOffer =
            {
                "itemId": {chosenItem},
                "fromVaultId": $storeVault.vault.id,
                "toVaultId": {chosenVault},
                "type": {chosenAction},
                "price": {chosenPrice}
            }

        await sendTradeOffer(tradeOffer).catch(
            () => {
                alert("Failed to send trade offer");
                error = true;
            }
        );
        if (!error) {
            alert("Trade offer successfully sent");
        }

    }

    async function chosenActionBtnClicked(action) {

        if (action === "BUY") {
            vaults = [1, 2, 3, 4];

        } else if (action === "SELL") {
            let response = await getVaults($storeAccount.account.id);

            vaults = response.map(vault => vault.id);
        }

        chosenAction = action;
    }

</script>

<h1>TRADE OFFER</h1>

<div id="choices_div">
    <h2>Choices</h2>
    <table>
        <tr>
            <th>Item:</th>
            <td>{chosenItem}</td>
        </tr>
        <tr>
            <th>Vault:</th>
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
        <button class="dropdown_btn" on:click={handleActionDropdown}>Action</button>
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
        <button class="dropdown_btn" on:click={handleVaultDropdown}>
            Vault
        </button>
        <div class="dropdown_content" style:visibility={isVaultDropDownOpen ? 'visible' : 'hidden'}>
            <ul>
                {#each vaults as vault}
                    <li>
                        <button on:click={()=> chosenVault = vault}>{vault}</button>
                    </li>
                {/each}
            </ul>
        </div>
    </div>

    <div id="item_div">
        <button class="dropdown_btn" on:click={handleItemDropdown}>Item</button>
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
