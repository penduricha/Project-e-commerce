// Saving data to local storage
import Router from "./Router.js";

// const router = new Router('example@example.com', '/home');
//
// // Loading data from local storage
// const loadedRouter = Router.loadFromLocalStorage();
// // console.log(loadedRouter);
//
// // Clearing data from local storage
// loadedRouter.clearLocalStorage();
//
// const loadedRouter2 = Router.loadFromLocalStorage();
// console.log(loadedRouter2);

// Saving data to local storage
// const router = new Router('example@example.com', '/home');
//
// // Loading data from local storage
// const loadedRouter = Router.loadFromLocalStorage();
// //console.log(loadedRouter);
//
// // Clearing data from local storage
// loadedRouter.clearLocalStorage();
//
// console.log(loadedRouter);

//const router = new Router('example@example.com', '/home');

// Loading data from local storage
// const loadedRouter = Router.loadFromLocalStorage();
//
// loadedRouter.clearLocalStorage();
// if (loadedRouter) {
//     console.log(loadedRouter);
//     // Logs the instance with email and router path
// }

// let router = new Router('abcd@123','/home-page')
//
// localStorage.setItem('router', JSON.stringify(router));
localStorage.removeItem('router');

const storedData = localStorage.getItem('router');

// if (storedData) {
//              const { emailPhoneNumber, routerPath } = JSON.parse(storedData);
//              return new Router(emailPhoneNumber, routerPath);
// }

console.log(storedData);

function getRouter(){
    if(storedData){
        const { _emailPhoneNumber, _routerPath } = JSON.parse(storedData);
        return new Router(_emailPhoneNumber, _routerPath);
    }
    else {
        return null;
    }
}

console.log(getRouter());