<script>
    import EditAccountCard from "../../components/EditAccountCard.svelte";
    import {onMount} from "svelte";

    let account = {};
    let userName;
    let firstName;
    let lastName;
    let email;

    onMount(async function () {

        account = JSON.parse(localStorage.getItem("account")).account;
        console.log(account.id);

    })

    function handleUpdateClick(){
        alert("JAJAJ")
        console.log(userName);
    }


</script>

<h1>CSGO BANK</h1>
<h2>Edit account</h2>
<div id="edit-account">
    <div id="account-preview">

        <EditAccountCard {...account}/>
    </div>
    <div id="update-data">

        <table>
            <tr>
                <th>User name:</th>
                <td><input bind:value={userName} type="text"></td>
            </tr>
            <tr>
                <th>First name:</th>
                <td><input bind:value={firstName} type="text"></td>
            </tr>
            <tr>
                <th>Last name:</th>
                <td><input bind:value={lastName} type="text"></td>
            </tr>
            <tr>
                <th>Email:</th>
                <td><input bind:value={email} type="text"></td>
            </tr>
            <tr>
                <td>
                    <button id="update-btn" on:click={handleUpdateClick}> Update</button>
                </td>
            </tr>
        </table>
    </div>

</div>


<style>
    #edit-account {
        display: flex;
        height: 60vh;
        width: 100vw;
        flex-direction: row-reverse;
        justify-content: center;
        align-items: center;
    }

    #update-data{
        align-items: center;
    }
    /*#update-btn{
        align-self: center;
    }*/

    h1, h2 {
        text-align: center;
    }

</style>
