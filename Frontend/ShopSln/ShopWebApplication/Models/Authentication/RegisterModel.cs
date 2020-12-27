using System.ComponentModel.DataAnnotations;

namespace ShopWebApplication.Models.Authentication
{
    public class RegisterModel
    {
        [Required]
        [Display(Name = "Nom d'utilisateur")]
        public string UserName { get; set; }

        [Required]
        [Display(Name = "Mot de passe")]
        public string Password { get; set; }

        [Required]
        [Display(Name = "Confirmer votre mot de passe")]
        public string ConfirmPassword { get; set; }
        
        public RegisterModel()
        {
            UserName = "";
            Password = "";
            ConfirmPassword = "";
        }
    }
}