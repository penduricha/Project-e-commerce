import UserService from "@/services/UserService.js";
import User from "@/models/User.js";

export default class UserDAO{

    constructor(){}

    static async getUserByEmailOrPhoneNumber(emailOrPhoneNumber){
        let user=null;
        await UserService.getUserByEmailOrPhoneNumber(emailOrPhoneNumber).then(response => {
            user = response.data;
        }).catch(error => {
            console.error(error);
        });
        return user;
    }

    static async createAccount(user){

        const newUser = {
            "email": user._email,
            "phoneNumber": user._phoneNumber,
            "password": user._password,
            "firstName": user._firstName,
            "lastName": user._lastName,
            "middleName": user._middleName,
            "address": user._address
        }

        try {
            //thực hiện POST
            await UserService.createAccount(newUser);
            return 1;
        } catch (error) {
            console.error('Error creating :', error);
            return 0;
        }
    }
}