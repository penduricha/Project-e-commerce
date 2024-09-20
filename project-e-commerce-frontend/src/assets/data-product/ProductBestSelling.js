const product = [
    {
        id: 1,
        name:'The north coat',
        priceOld: 360,
        priceNew: 260,
        image: new URL('@/assets/image-product/coat.png',import.meta.url).href,
    },
    {
        id: 2,
        name:'Gucci duffle bag',
        priceOld: 1160,
        priceNew: 960,
        image: new URL('@/assets/image-product/gucci.png',import.meta.url).href,
    },
    {
        id: 3,
        name:'RGB liquid CPU Cooler',
        priceOld: 170,
        priceNew: 160,
        image: new URL('@/assets/image-product/rgb-liquid.png',import.meta.url).href,
    },
    {
        id: 4,
        name:'Small BookSelf',
        priceOld: null,
        priceNew: 360,
        image: new URL('@/assets/image-product/book-self.png',import.meta.url).href,
    }
];

export default product;