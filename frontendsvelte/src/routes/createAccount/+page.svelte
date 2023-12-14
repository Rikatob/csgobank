<script>
    import {createAccount} from "../../services/ApiClient.js";


    async function submitBtnClicked(event) {
        const formData = new FormData(event.target);
        const data = {};
        for (let field of formData) {
            const [key, value] = field;
            data[key] = value;
            if (value === "" || value === null) {
                alert("Fill in all fields");
                return;
            }
        }
        await createAccount(data);
        alert("Account created");
        window.location.assign("/");

    }


</script>
<h1>CSGO BANK</h1>
<h2>Create new account</h2>

<div>
    <form on:submit|preventDefault={submitBtnClicked}>

        <label for="firstName"> <strong> First Name </strong> </label>
        <input id="first_name" name="firstName" type="text"/>

        <label for="lastName"> <strong> Last Name </strong> </label>
        <input id="last_name" name="lastName" type="text"/>

        <label for="email"> <strong> Email </strong> </label>
        <input id="email" name="email" type="email"/>

        <label for="userName"> <strong> User Name </strong> </label>
        <input id="user_name" name="userName" type="text"/>

        <br/>
        <button type="submit">Create account</button>
    </form>
</div>

<style>
    div {
        display: flex;
        justify-content: center;
        align-items: center;
        text-align: center;
        padding: 1em;
        margin: 0 auto;
    }

    form {
        display: flex;
        flex-direction: column;
        max-width: 240px;
    }

    h1, h2 {
        text-align: center;
    }
</style>
