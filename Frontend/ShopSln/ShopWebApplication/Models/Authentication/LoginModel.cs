﻿using System.ComponentModel.DataAnnotations;

namespace ShopWebApplication.Models.Authentication
{
    public class LoginModel
    {
        [Required]
        [Display(Name = "Nom d'utilisateur")]
        public string UserName { get; set; }
        [Required]
        [Display(Name = "Mot de passe")]
        public string Password { get; set; }
        
        public LoginModel()
        {
            UserName = "";
            Password = "";
        }
    }
}