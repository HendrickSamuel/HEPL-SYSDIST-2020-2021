using System.Text.Json.Serialization;

namespace ShopWebApplication.Helpers
{
    public class ReponseMessage
    {
        public bool Success { get; set; }
        [JsonPropertyName("message")]
        public string Message { get; set; }
        
        public ReponseMessage(){}

        public override string ToString()
        {
            return $"{nameof(Success)}: {Success}, {nameof(Message)}: {Message}";
        }
    }
}