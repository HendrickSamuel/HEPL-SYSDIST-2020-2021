using System.Collections.Generic;
using System.Text.Json.Serialization;
using ShopWebApplication.Models.POCOS;

namespace ShopWebApplication.Helpers
{
    public class ItemShopHelper
    {
        public ItemShopHelper()
        {
            
        }
        [JsonPropertyName("items")]
        public List<ItemShopModel> Items { get; set; }
    }
}