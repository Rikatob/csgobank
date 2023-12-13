<script>

</script>
<h1>CSGO BANK</h1>
<h2>Create new account</h2>

<div>
    <form method="POST" action="?/create">
        <!--<label for="account_id"> <strong> Account Id </strong> </label>
        <input id="account_id" name="account_id" type="text"  />-->

        <label for="first_name"> <strong> First Name </strong> </label>
        <input id="first_name" name="first_name" type="text"/>

        <label for="last_name"> <strong> Last Name </strong> </label>
        <input id="last_name" name="last_name" type="text"/>

        <label for="email"> <strong> Email </strong> </label>
        <input id="email" name="email" type="email"/>

        <label for="user_name"> <strong> User Name </strong> </label>
        <input id="user_name" name="user_name" type="text"/>

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
