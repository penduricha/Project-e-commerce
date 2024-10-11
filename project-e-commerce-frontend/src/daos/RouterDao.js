import Router from "@/routers/Router.js";


export default class RouterDao{
    constructor(){

    }

    saveRouterPathToSessionStorage(routerPath){
        if(routerPath !== undefined){
            sessionStorage.setItem('routerPath', routerPath);
        }
    }

    getRouterPathFromSessionStorage(){
        const routerPath = sessionStorage.getItem('routerPath');
        if(routerPath){
            let routerGet = new Router();
            routerGet.setRouterPath(routerPath);
            return routerGet._routerPath;
        }
        else {
            return null;
        }
    }

    //local storage

    saveEmailPhoneNumberLocalStorage(emailPhoneNumber){
        if(emailPhoneNumber !== undefined){
            localStorage.setItem('emailPhoneNumber', emailPhoneNumber);
        }
    }

    getEmailPhoneNumberFromLocalStorage(){
        const emailPhoneNumber = localStorage.getItem('emailPhoneNumber');
        if(emailPhoneNumber){
            let router = new Router();
            router.setEmailPhoneNumber(emailPhoneNumber)
            return router._emailPhoneNumber;
        }
        else {
            return null;
        }
    }
}