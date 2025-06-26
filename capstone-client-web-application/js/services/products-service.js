let productService;

class ProductService {

    photos = [];


    filter = {
        cat: undefined,
        minPrice: undefined,
        maxPrice: undefined,
        color: undefined,
        queryString: () => {
            let qs = "";
            if(this.filter.cat){ qs = `cat=${this.filter.cat}`; }
            if(this.filter.minPrice)
            {
                const minP = `minPrice=${this.filter.minPrice}`;
                if(qs.length>0) {   qs += `&${minP}`; }
                else { qs = minP; }
            }
            if(this.filter.maxPrice)
            {
                const maxP = `maxPrice=${this.filter.maxPrice}`;
                if(qs.length>0) {   qs += `&${maxP}`; }
                else { qs = maxP; }
            }
            if(this.filter.color)
            {
                const col = `color=${this.filter.color}`;
                if(qs.length>0) {   qs += `&${col}`; }
                else { qs = col; }
            }

            return qs.length > 0 ? `?${qs}` : "";
        }
    }

    constructor() {

        //load list of photos into memory
        axios.get("/images/products/photos.json")
            .then(response => {
                this.photos = response.data;
            });
    }

    hasPhoto(photo){
        return this.photos.filter(p => p == photo).length > 0;
    }

    addCategoryFilter(cat)
    {
        if(cat == 0) this.clearCategoryFilter();
        else this.filter.cat = cat;
    }
    addMinPriceFilter(price)
    {
        if(price == 0 || price == "") this.clearMinPriceFilter();
        else this.filter.minPrice = price;
    }
    addMaxPriceFilter(price)
    {
        if(price == 0 || price == "") this.clearMaxPriceFilter();
        else this.filter.maxPrice = price;
    }
    addColorFilter(color)
    {
        if(color == "") this.clearColorFilter();
        else this.filter.color = color;
    }

    clearCategoryFilter()
    {
        this.filter.cat = undefined;
    }
    clearMinPriceFilter()
    {
        this.filter.minPrice = undefined;
    }
    clearMaxPriceFilter()
    {
        this.filter.maxPrice = undefined;
    }
    clearColorFilter()
    {
        this.filter.color = undefined;
    }

    search()
    {
        const url = `${config.baseUrl}/products${this.filter.queryString()}`;

        axios.get(url)
             .then(response => {
                 let data = {};
                 data.products = response.data;

                 data.products.forEach(product => {
                     if (!this.hasPhoto(product.imageUrl)) {
                         if (product.name && product.name.includes("Smart Home Hub")) { product.imageUrl = "smart-home.png"; }
                         if (product.name && product.name.includes("Portable Charger")) { product.imageUrl = "portable-charger.png"; }
                         if (product.name && product.name.includes("Men's Swim Trunks")) { product.imageUrl = "mens-swim-trunks.png"; }
                         if (product.name && product.name.includes("Men's Casual Shirt")) { product.imageUrl = "mens-casual-shirt.png"; }
                         if (product.name && product.name.includes("Women's Jumpsuit")) { product.imageUrl = "womens-jumpsuit.png"; }
                         if (product.name && product.name.includes("Women's Swimwear")) { product.imageUrl = "womens-swimwear.png"; }
                         if (product.name && product.name.includes("Air Fryer")) { product.imageUrl = "air-fryer.png"; }
                         if (product.name && product.name.includes("Cookies")) { product.imageUrl = "cookies.png"; }
                         if (product.name && product.name.includes("Grandma Cookies")) { product.imageUrl = "grandma-cookies.png"; }
                     }
                 });

                 templateBuilder.build('product', data, 'content', this.enableButtons);

             })
            .catch(error => {

                const data = {
                    error: "Searching products failed."
                };

                templateBuilder.append("error", data, "errors")
            });
    }

    enableButtons()
    {
        const buttons = [...document.querySelectorAll(".add-button")];

        if(userService.isLoggedIn())
        {
            buttons.forEach(button => {
                button.classList.remove("invisible")
            });
        }
        else
        {
            buttons.forEach(button => {
                button.classList.add("invisible")
            });
        }
    }

}





document.addEventListener('DOMContentLoaded', () => {
    productService = new ProductService();

});
