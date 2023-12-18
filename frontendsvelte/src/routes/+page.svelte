<script>



    function loginBtnClicked(){
        window.location.assign("/login");
    }

    function createBtnClicked(){
        window.location.assign("/createAccount");
    }

</script>


<h1>CSGO BANK</h1>
<h2>Home page</h2>


<div id="btn_div">
    <button class="btn" on:click={loginBtnClicked}>Login</button>
    <button class="btn" on:click={createBtnClicked}>Create account</button>
</div>

<style>

    .btn{
        margin: 5px;
    }
    #btn_div{
        display: flex;
        justify-content: center;
        align-items: center;
        height: 30vh;
    }
    h1, h2 {
        text-align: center;
    }
</style>