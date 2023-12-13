/** @type {import('./$types').Actions} */
import {createAccount} from "../../services/ApiClient.js";

let account = {
    /*id: 0,*/
    email: "",
    firstName: "",
    lastName: "",
    userName: "",
    credit: 0
}
export const actions = {
    create: async ({request}) => {
        const data = await request.formData();
        /*account.id = data.get('account_id');*/
        account.email = data.get('email');
        account.firstName = data.get('first_name');
        account.lastName = data.get('last_name');
        account.userName = data.get('user_name');
        await createAccount(account);

        console.log(account);

        return {success: true};
    },

};