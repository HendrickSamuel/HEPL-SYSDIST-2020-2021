using System;
using ShopWebApplication.Models.POCOS;

namespace ShopWebApplication.Helpers
{
    public class DataCatalogHelper
    {
        // public Tuple<ItemShopHelper, Cart> Data { get; set; }
        public CartModel MyCartModel { get; set; }
        public ItemShopHelper IShopHelper { get; set; }
        public DataCatalogHelper()
        {
            MyCartModel = null;
            IShopHelper = null;
        }
    }
}