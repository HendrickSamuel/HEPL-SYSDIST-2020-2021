using System.Collections.Generic;
using System.Text.Json.Serialization;
using ShopWebApplication.Models.POCOS;

namespace ShopWebApplication.Helpers
{
    public class ItemShopHelper
    {
        public ItemShopHelper()
        {
            Items = new List<ItemShopModel>();
        }
        [JsonPropertyName("items")]
        public List<ItemShopModel> Items { get; set; }
    }
}