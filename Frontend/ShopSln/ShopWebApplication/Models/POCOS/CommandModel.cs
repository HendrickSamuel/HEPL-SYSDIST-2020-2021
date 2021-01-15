using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace ShopWebApplication.Models.POCOS
{
    public class CommandModel
    {
        [JsonPropertyName("commande")]
        public int Id { get; set; }

        [JsonPropertyName("client")]
        public string Client { get; set; }
        
        [JsonPropertyName("status")]
        public string Status { get; set; }
        
        [JsonPropertyName("mode")]
        public string Mode { get; set; }

        [JsonPropertyName("items")]
        public List<ItemCommandModel> Items { get; set; }
        
        [JsonPropertyName("amount")]
        public float Amount { get; set; }
        
        public int TotalElement
        {
            get
            {
                int total = 0;
                if (Items != null)
                {
                    foreach (var itemCommand in Items)
                        total += itemCommand.Quantity;
                }
                return total;
            }
            
        }

        public CommandModel()
        {
            Mode = "";
            Items = new List<ItemCommandModel>();
        }
    }
}