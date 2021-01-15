using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;
using ShopWebApplication.Models.POCOS;

namespace ShopWebApplication.Helpers
{
    public class UserCommandHelper
    {
        public string Username { get; set; }
        
        [JsonPropertyName("commands")]
        public List<CommandModel> Commands { get; set; }

        
        public UserCommandHelper()
        {
            Username = "";
            Commands = new List<CommandModel>();
        }
        
        public UserCommandHelper(String username, List<CommandModel> commands)
        {
            Username = username;
            Commands = commands;
        }
    }
}