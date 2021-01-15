using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using ShopWebApplication.Models;

namespace ShopWebApplication.Controllers
{
    public class HomeController : Controller
    {
        #region Members
        //private readonly ILogger<HomeController> _logger;
        private readonly UserManager<IdentityUser> _userManager;
        private readonly SignInManager<IdentityUser> _signInManager;
        #endregion

        #region Constructor

        //public HomeController(ILogger<HomeController> logger)
        //{
        //    _logger = logger;
        //}
        public HomeController(UserManager<IdentityUser> userManager, SignInManager<IdentityUser> signInManager)
        {
            _userManager = userManager;
            _signInManager = signInManager;
        }

        #endregion

        #region Method
        private string DefaultUserConnection()
        {
            var machineName = Environment.MachineName.ToLower();
            var username = $"visitor_{machineName}";
            var defaultuser =  _userManager.FindByNameAsync(username).Result;
            if (defaultuser != null)
            {
                var signInResult =  _signInManager.PasswordSignInAsync(defaultuser, "visitor", false, false).Result;
                if (signInResult.Succeeded)
                {
                    return username;
                }
            }
            return String.Empty;
        }
        #endregion

        #region Actions
        
        public IActionResult Index()
        {
            var user = _userManager.GetUserAsync(HttpContext.User).Result;
            if(user != null)
            {
                ViewBag.Welcome = $"Welcome {user.UserName}!";

                if (_userManager.IsInRoleAsync(user, "Customer").Result)
                {
                    ViewData["Layout"] = "_LayoutLogged";
                    ViewBag.Message = "You are a Customer.";
                }
                else if(_userManager.IsInRoleAsync(user, "Visitor").Result)
                {
                    ViewData["Layout"] = "_Layout";
                    ViewBag.Message = "You are a Visitor.";
                }
            }
            else
            {
                var username = DefaultUserConnection();
                ViewData["Layout"] = "_Layout";
                user = _userManager.GetUserAsync(HttpContext.User).Result;
                ViewBag.Welcome = $"Welcome {username}!";
                ViewBag.Message = "You are a Visitor.";
            }

            return View();
        }
        
        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel {RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier});
        }
        #endregion
    }
}