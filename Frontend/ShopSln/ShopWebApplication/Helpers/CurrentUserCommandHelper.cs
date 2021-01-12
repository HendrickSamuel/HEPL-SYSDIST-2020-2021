using System;
using ShopWebApplication.Models.POCOS;

namespace ShopWebApplication.Helpers
{
    public class CurrentUserCommandHelper
    {
        public bool Ok { get; set; }
        public string Message { get; set; }
        public UserModel User { get; set; }
        public CommandModel CurrentCommand { get; set; }
        public CurrentUserCommandHelper()
        {
            
        }
        

        public CurrentUserCommandHelper(UserModel user, CommandModel currentCommand)
        {
            User = user;
            CurrentCommand = currentCommand;
        }

        public CurrentUserCommandHelper(bool ok, string message, UserModel user, CommandModel currentCommand)
        {
            Ok = ok;
            Message = message;
            User = user;
            CurrentCommand = currentCommand;
        }
        
    }
}