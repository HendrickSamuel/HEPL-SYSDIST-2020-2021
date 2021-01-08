using System;
using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace ShopWebApplication.Models.POCOS
{
    public class UserModel
    {
        #region Public Properties
        [JsonPropertyName("id")]
        public int Id { get; set; }
        
        [JsonPropertyName("nom")]
        public string Name { get; set; }
        
        [JsonPropertyName("porteFeuille")]
        public float Wallet { get; set; } 
        
        [JsonPropertyName("cart")]
        public CartModel MyCartModel { get; set; }
        
        [JsonPropertyName("status")]
        public string Status { get; set; }
        
        #endregion
        
        #region Constructor

        public UserModel()
        {
            // Name = Environment.MachineName;
            // Status = "visiteur";
        }
        #endregion

        public override string ToString()
        {
            return $"{nameof(Id)}: {Id}, {nameof(Name)}: {Name}, {nameof(Wallet)}: {Wallet}, {nameof(MyCartModel)}: {MyCartModel}, {nameof(Status)}: {Status}";
        }
    }
}