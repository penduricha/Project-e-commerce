export default class User{
    _email;
    _phoneNumber;
    _password;
    _firstName;
    _lastName;
    _middleName;
    _address;

    constructor(email, phoneNumber, password, firstName, lastName, middleName, address) {
        this._email = email;
        this._phoneNumber = phoneNumber;
        this._password = password;
        this._firstName = firstName;
        this._lastName = lastName;
        this._middleName = middleName;
        this._address = address;
    }
}