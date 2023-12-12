<script>
    import {createEventDispatcher} from 'svelte';

    const dispatch = createEventDispatcher();
    export let id = "0";

    export let email = "Default email";

    export let firstName = "first name";

    export let lastName = "Last name";

    export let userName = "User name";

    let account = {
        id: id,
        email: email,
        firstName: firstName,
        lastName: lastName,
        userName: userName
    }

    function handleClick() {
        dispatch("clicked", {account});
    }

</script>


<div on:click={handleClick} id="account-card">
    <h1>{id} | {userName}</h1>
    <table id="account-info">
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

    }

    h1:hover {
        text-wrap: initial;
    }

    table {
        border: solid black 1px;
        width: auto;
    }
</style>