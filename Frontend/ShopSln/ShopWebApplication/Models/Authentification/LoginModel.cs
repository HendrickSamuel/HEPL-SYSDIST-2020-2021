using System.ComponentModel.DataAnnotations;

namespace ShopWebApplication.Models.Authentification
{
    public class LoginModel
    {
        [Required]
        [Display(Name = "Nom d'utilisateur")]
        public string Login { get; set; }
        [Required]
        [Display(Name = "Mot de passe")]
        public string Password { get; set; }
    }
}