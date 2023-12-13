<script>
    import {accountStore} from "../stores.js";
    import {deleteAccount} from "../services/ApiClient.js";

    let storeAccount = accountStore();

    export let id = "0";

    export let email = "Default email";

    export let firstName = "first name";

    export let lastName = "Last name";

    export let userName = "User name";


    async function onDeleteBtnClick() {
        let alertMessage = "Are you sure you want to delete this account?\nPress OK to confirm."

        if (confirm(alertMessage) === true) {
            await deleteAccount($storeAccount.account.id);
            alert("Account with id " + $storeAccount.account.id + " is Deleted");
            window.location.assign("/");
        }
    }

</script>


<div id="account-card">
    <h1>Preview</h1>
    <table id="account-info">
        <tr>
            <th>ID:</th>
            <td>{id}</td>
        </tr>
        <tr>
            <th>USERNAME:</th>
            <td>{userName}</td>
        </tr>
        <tr>
            <th>FIRST NAME:</th>
            <td>{firstName}</td>
        </tr>
        <tr>
            <th>LAST NAME:</th>
            <td>{lastName}</td>
        </tr>
        <tr>
            <th>EMAIL:</th>
            <td>{email}</td>
        </tr>
    </table>
</div>

<div id="delete-btn">
    <button on:click={onDeleteBtnClick}>Delete account</button>
</div>

<style>
    #account-card {
        cursor: pointer;
        border: black solid 2px;
        padding: 10px;
        height: auto;
        width: auto;
        overflow: hidden;
        background-color: #2c303b;
        border-radius: 10px;
    }


    th {
        text-align: start;
        text-wrap: nowrap;
    }

    h1 {
        font-size: 1.3em;
        text-wrap: nowrap;
        text-align: center;

    }

    h1:hover {
        text-wrap: initial;
    }

    table {
        border: solid black 1px;
        width: auto;
    }

    #delete-btn {
        text-align: center;
    }
</style>