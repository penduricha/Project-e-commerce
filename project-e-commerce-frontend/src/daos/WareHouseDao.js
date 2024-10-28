import WareHouseService from "@/services/WareHouseService.js";

export default class WareHouseDao{
    constructor() {

    }

    async getListWareHouseBy_ProductId(productId){
        let wareHouses = [];
        try{
            const response = await WareHouseService.fetchWareHouses_By_ProductId(productId);
            wareHouses = response.data;
            return wareHouses;
        }catch(error){
            console.error(error);
            alert(error);
            return null;
        }
    }


}