using System.Text.Json.Serialization;

namespace ShopWebApplication.Models.POCOS
{
    public class ItemShopModel
    {
        #region Public Properties
        [JsonPropertyName("id")]
        public string Id { get; set; }
        
        [JsonPropertyName("name")]
        public string Name { get; set; }
        
        [JsonPropertyName("type")]
        public string Type { get; set; }
        
        [JsonPropertyName("stock")]
        public int Stock { get; set; }
        
        [JsonPropertyName("price")]
        public float Price { get; set; }
        
        [JsonPropertyName("description")]
        public string Description { set; get; }
        
        #endregion
        
        #region Constructor
        public ItemShopModel() {}
        #endregion

        public override string ToString()
        {
            return $"{nameof(Id)}: {Id}, {nameof(Name)}: {Name}, {nameof(Type)}: {Type}, {nameof(Stock)}: {Stock}, {nameof(Price)}: {Price}, {nameof(Description)}: {Description}";
        }
    }
}