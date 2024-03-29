﻿using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using Newtonsoft.Json;
using JsonSerializer = System.Text.Json.JsonSerializer;

namespace ShopWebApplication.Token
{
    public static class TokenManager
    {
        private static HttpClient _client;
        private static string _token = "";
        private static string _host = "78.129.34.151";
        private static int _port = 8080;


        public static string GetHost()
        {
            return _host;
        }
        public static int GetPort()
        {
            return _port;
        }
        
        public static string GetToken()
        {
            if(_token == String.Empty)
                InitToken();
            return _token;
        }
        public static void InitToken()
        {
            if(_client == null)
                _client = new HttpClient();
            using (var request = new HttpRequestMessage(HttpMethod.Post, $"http://{_host}:{_port}/auth"))
            {
                request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                var body = new Dictionary<string, string>
                {
                    { "username", "benedict" }, 
                    { "password", "benedict" }
                };
                request.Content = new StringContent(JsonConvert.SerializeObject(body), Encoding.UTF8, "application/json");
                var response = _client.SendAsync(request).Result;
                if (response.IsSuccessStatusCode)
                {
                    var jsonString = response.Content.ReadAsStringAsync().Result;
                    var tokenJwt = JsonSerializer.Deserialize<TokenJwt>(jsonString);
                    if (tokenJwt != null)
                        _token = tokenJwt.Jwt;
                }
            }
        }
    }
}