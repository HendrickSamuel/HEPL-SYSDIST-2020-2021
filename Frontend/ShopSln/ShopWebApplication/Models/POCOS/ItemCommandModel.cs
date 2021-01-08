using System.Text.Json.Serialization;

namespace ShopWebApplication.Models.POCOS
{
    public class ItemCommandModel
    {
        #region Public Properties
        [JsonPropertyName("id")]
        public string Id { get; set; }
        
        [JsonPropertyName("name")]
        public string Name { get; set; } //TODO: PAS PRESENT DANS LA REQUETE JSON
        
        [JsonPropertyName("type")]
        public string Type { get; set; } //TODO: A NULL DE LA REQUETE JSON
        
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
            get { return Price += Price * (Tva / 100); }
        }
        
        [JsonPropertyName("totalPrice")]
        public float TotalPrice { get; set; }
        
        [JsonPropertyName("totalTVAPrice")]
        public float TotalPriceTva { get; set; }
        #endregion

        #region Constructor
        public ItemCommandModel()
        {
            Price = 0;
            Quantity = 0;
            Tva = 0;
            Name = "";
            Type = "";
        }
        #endregion
    }
}