using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text.Json;
using System.Text.Json.Serialization;
using ShopWebApplication.Token;

namespace ShopWebApplication.Helpers
{
    public class CurrentCommand
    {
        private static CommandId _commandId;
        private static string _userId = "";
        private static HttpClient _client;
        
        public static string GetCommandId()
        {
            if(_commandId.Id == -1)
                return "";
            return _commandId.Id.ToString();
        }
        public static string GetUserId()
        {
            return _userId;
        }
        public static void SetCommandId(int id)
        {
            _commandId.Id = id;
        }
        public static void SetUserId(string id)
        {
            _userId = id;
        }

        public static void InitCurrentCommand(string userId)
        {
            if(_client == null)
                _client = new HttpClient();
            using (var request = new HttpRequestMessage(HttpMethod.Post, $"http://localhost:8080/command/current/{userId}"))
            {
                request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                request.Headers.Authorization = new AuthenticationHeaderValue("Bearer", TokenManager.GetToken());

                var reponse = _client.SendAsync(request).Result;
                if (reponse.IsSuccessStatusCode)
                {
                    var jsonString = reponse.Content.ReadAsStringAsync().Result;
                    _commandId = JsonSerializer.Deserialize<CommandId>(jsonString);
                }
            }
            _client.Dispose();
        }
    }

    public class CommandId
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }
        
        public CommandId()
        {
            Id = -1;
        }
    }
}