using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace ShopWebApplication.Models.POCOS
{
    public class CartModel
    {
        #region Public Properties
        [JsonPropertyName("totalTVA")]
        public float TotalTva { get; set; }
        
        [JsonPropertyName("total")]
        public float Total { get; set; }
        
        [JsonPropertyName("items")]
        public List<ItemCartModel> Items { get; set; }

        public int TotalElement
        {
            get
            {
                int total = 0;
                if (Items != null)
                {
                    foreach (var itemCart in Items)
                        total += itemCart.Quantity;
                }
                return total;
            }
            
        }
        #endregion
        
        #region Constructor
        public CartModel()
        {
            Total = 0f;
            TotalTva = 0f;
            Items = new List<ItemCartModel>();
        }
        #endregion

        public override string ToString()
        {
            return $"{nameof(TotalTva)}: {TotalTva}, {nameof(Total)}: {Total}, {nameof(Items)}: {Items}, {nameof(TotalElement)}: {TotalElement}";
        }
    }
}