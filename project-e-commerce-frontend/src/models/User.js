export default class User{
    constructor(email, phoneNumber, password, firstName, lastName, middleName, address) {
        this._email = email;
        this._phoneNumber = phoneNumber;
        this._password = password;
        this._firstName = firstName;
        this._lastName = lastName;
        this._middleName = middleName;
        this._address = address;
    }

    get email() {
        return this._email;
    }

    set email(value) {
        this._email = value;
    }

    get phoneNumber() {
        return this._phoneNumber;
    }

    set phoneNumber(value) {
        this._phoneNumber = value;
    }

    get password() {
        return this._password;
    }

    set password(value) {
        this._password = value;
    }

    get firstName() {
        return this._firstName;
    }

    set firstName(value) {
        this._firstName = value;
    }

    get lastName() {
        return this._lastName;
    }

    set lastName(value) {
        this._lastName = value;
    }

    get middleName() {
        return this._middleName;
    }

    set middleName(value) {
        this._middleName = value;
    }

    get address() {
        return this._address;
    }

    set address(value) {
        this._address = value;
    }

    toString() {
        return `Email: ${this._email}\n` +
            `Phone Number: ${this._phoneNumber}\n` +
            `Password: ${this._password}\n` +
            `First Name: ${this._firstName}\n` +
            `Last Name: ${this._lastName}\n` +
            `Middle Name: ${this._middleName}\n` +
            `Address: ${this._address}`;
    }
}