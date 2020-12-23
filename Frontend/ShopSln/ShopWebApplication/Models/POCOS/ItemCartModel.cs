using System.Text.Json.Serialization;

namespace ShopWebApplication.Models.POCOS
{
    public class ItemCartModel
    {
        #region Public Properties
        [JsonPropertyName("id")]
        public string Id { get; set; }
        
        [JsonPropertyName("name")]
        public string Name { get; set; }
        
        [JsonPropertyName("type")]
        public string Type { get; set; }
        
        [JsonPropertyName("totalQuantity")]
        public int Stock { get; set; }     
        
        [JsonPropertyName("wantedQuantity")]
        public int Quantity { get; set; }
        
        [JsonPropertyName("price")]
        public float Price { get; set; }
        
        [JsonPropertyName("tva")]
        public float Tva { get; set; }
        
        public float PriceTva
        {
            get { return Price += Price * Tva; }
        }
        
        [JsonPropertyName("totalPrice")]
        public float TotalPrice { get; set; }
        
        [JsonPropertyName("totalTVAPrice")]
        public float TotalPriceTva { get; set; }
        #endregion

        #region Constructor
        public ItemCartModel()
        {
            Price = 0;
            Quantity = 0;
            Tva = 0;
        }
        #endregion
        
    }
}