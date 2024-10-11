

export default class Router {
    _emailPhoneNumber
    _routerPath

    constructor(emailPhoneNumber, routerPath) {
        this._emailPhoneNumber = emailPhoneNumber;
        this._routerPath = routerPath;
    }

    setRouterPath(routerPath){
        this._routerPath = routerPath;
    }


    setEmailPhoneNumber(emailPhoneNumber) {
        this._emailPhoneNumber = emailPhoneNumber;
    }
}