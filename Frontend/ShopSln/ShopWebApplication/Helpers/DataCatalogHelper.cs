using System;
using ShopWebApplication.Models.POCOS;

namespace ShopWebApplication.Helpers
{
    public class DataCatalogHelper
    {
        // public Tuple<ItemShopHelper, Cart> Data { get; set; }
        public Cart MyCart { get; set; }
        public ItemShopHelper IShopHelper { get; set; }
        public DataCatalogHelper()
        {
            MyCart = null;
            IShopHelper = null;
        }
    }
}