using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using ShopWebApplication.Models.Authentification;

namespace ShopWebApplication.Controllers
{
    public class AuthenticationController : Controller
    {
        [AllowAnonymous]
        public ActionResult Login()
        {
            return View("Login", new LoginModel());
        }
    }
}