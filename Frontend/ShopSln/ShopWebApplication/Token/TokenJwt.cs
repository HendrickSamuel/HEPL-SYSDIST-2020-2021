using System.Text.Json.Serialization;

namespace ShopWebApplication.Token
{
    public class TokenJwt
    {
        [JsonPropertyName("jwt")]
        public string Jwt { get; set; }
        
        public TokenJwt()
        {
            Jwt = "";
        }
    }
}