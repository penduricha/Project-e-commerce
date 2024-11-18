import WareHouseService from "@/services/WareHouseService.js";


export default class CartDtoDao {
    constructor() {

    }

    async getItemCart_FromAPI_ProductId_Size_Color(productId, size, color){
        let cartDto = {};
        if(color){
            color = color.slice(1);
            //Cat #
        }

        if(!color && size){
            try{
                const response = await WareHouseService.fetchItemCart_By_ProductId_Size(productId, size);
                cartDto = response.data;
                return cartDto;
            }catch(error){
                console.error(error);
                alert(error);
                return {};
            }
        }

        if(color && !size){
            try{
                const response = await WareHouseService.fetchItemCart_By_ProductId_Color(productId, color);
                cartDto = response.data;
                return cartDto;
            }catch(error){
                console.error(error);
                alert(error);
                return {};
            }
        }

        if(color && size){
            try{
                const response = await WareHouseService.fetchItemCart_By_ProductId_Size_Color(productId, size, color);
                cartDto = response.data;
                return cartDto;
            }catch(error){
                console.error(error);
                alert(error);
                return {};
            }
        }
    }
}