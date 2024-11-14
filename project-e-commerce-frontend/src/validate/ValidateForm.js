import UserDao from "@/daos/UserDao.js";
import User from "@/models/User.js";

export default class ValidateForm {
    constructor() {

    }


    isNumeric (str){
        return /^\d+$/.test(str);
    }

    async getUserByEmailOrPhoneNumber(emailPhoneNumber){
        let user = await UserDao.getUserByEmailOrPhoneNumber(emailPhoneNumber);
        if(user!==null){
            return new User(user.email, user.phoneNumber, user.password, user.firstName, user.lastName, user.middleName, user.address);
        }else{
            return null;
        }
    }

    comparePassword(password1, password2){
        return password1 === password2;
    }

    async sha512(password) {
        let buf = await crypto.subtle.digest("SHA-512", new TextEncoder("utf-8").encode(password));
        return Array.prototype.map.call(new Uint8Array(buf), x => (('00' + x.toString(16)).slice(-2))).join('');
    }

    isValidVietnameseFirstName(name) {
        // Remove accents from Vietnamese characters
        const removeAscent = (str) => {
            if (str === null || str === undefined) return str;
            return str.normalize('NFD').replace(/[\u0300-\u036f]/g, '');
        };

        // Regex pattern for Vietnamese names without spaces
        const regex = /^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$/;

        // Check if name is valid and doesn't contain spaces
        const processedName = removeAscent(name);
        return regex.test(processedName) && !/\s/.test(processedName);
    }

    isValidVietnameseName(name) {
        // Remove accents from Vietnamese characters
        const removeAscent = (str) => {
            if (str === null || str === undefined) return str;
            return str.normalize('NFD').replace(/[\u0300-\u036f]/g, '');
        };
        // Regex pattern for Vietnamese names
        const regex = /^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸửữựỳỵỷỹ\s]+$/;
        return regex.test(removeAscent(name));
    }

    isFullOfSpaces(s) {
        for (let char of s) {
            if (char !== ' ') {
                return false;
            }
        }
        return true;
    }

    isValidEmail(email){
        return /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email.trim());
    }

    isValidPassword(password){
        return /^(?=.*[0-9])(?=.*[^a-zA-Z0-9])(?!.*\s).{6,20}$/.test(password);
    }
}