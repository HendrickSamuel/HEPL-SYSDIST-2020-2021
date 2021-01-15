using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using ShopWebApplication.Helpers;
using ShopWebApplication.Models.Authentication;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Security.Claims;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using ShopWebApplication.Token;

namespace ShopWebApplication.Controllers
{
    public class AuthenticationController : Controller
    {
        #region Members
        private readonly UserManager<IdentityUser> _userManager;
        private readonly SignInManager<IdentityUser> _signInManager;
        private readonly RoleManager<IdentityRole> _roleManager;
        private readonly IHttpContextAccessor _context;

        #endregion

        #region Constructor
        public AuthenticationController(UserManager<IdentityUser> userManager,
            SignInManager<IdentityUser> signInManager,
            RoleManager<IdentityRole> roleManager, IHttpContextAccessor context)
        {
            _userManager = userManager;
            _signInManager = signInManager;
            _roleManager = roleManager;
            _context = context;
        }
        #endregion

        #region Method

        #endregion

        #region Actions
        public IActionResult Index()
        {
            return RedirectToActionPreserveMethod("Index", "Home");
        }

        #region Login
        [Authorize(Roles = "Visitor")]
        public IActionResult Login()
        {
            return View("Login", new LoginModel());
        }


        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Login(LoginModel obj)
        {
            if(ModelState.IsValid)
            {
                var user = await _userManager.FindByNameAsync(obj.UserName);
                if (user != null)
                {
                    //await _signInManager.SignOutAsync();
                    var signInResult = await _signInManager.PasswordSignInAsync(obj.UserName, obj.Password, false, false);
                    if (signInResult.Succeeded)
                    {
                        SwapCart(obj.UserName);
                        return RedirectToAction("MyCatalog", "Catalog");
                    }
                    ViewBag.Error = "Mauvais mot de passe !";
                }
                else
                    ViewBag.Error = "L'utilisateur n'existe pas !";
            }
            obj.Password = "";
            return View("Login", obj);
        }
        #endregion

        #region Logout
        [Authorize(Roles = "Customer")]
        public async Task<IActionResult> Logout()
        {
            // Logout functionality
            await _signInManager.SignOutAsync();

            return RedirectToAction("Index", "Home");

        }
        #endregion

        #region Register
        [Authorize(Roles = "Visitor")]
        public IActionResult Register()
        {
            return View("Register", new RegisterModel());
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Register(RegisterModel obj)
        {
         //   var reponseHelper = new ReponseHelper();
            if (ModelState.IsValid)
            {
                if(String.Compare(obj.Password.ToLower(), obj.ConfirmPassword.ToLower(), StringComparison.Ordinal) == 0)
                {
                    var user = new IdentityUser { UserName = obj.UserName };
                    var createUserResult = await _userManager.CreateAsync(user, obj.Password);
                    if(createUserResult.Succeeded)
                    {
                        var roleExist = await _roleManager.RoleExistsAsync("Customer");
                        if (!roleExist)
                        {
                            var role = new IdentityRole { Name = "Customer" };
                            var createRoleResult = await _roleManager.CreateAsync(role);
                            if (!createRoleResult.Succeeded)
                            {
                                ViewBag.Error =  "Error while creating role!";
                                obj.Password = "";
                                obj.ConfirmPassword = "";
                                return View("Register", obj);
                            }

                        }
                        _userManager.AddToRoleAsync(user, "Customer").Wait();
                      //  reponseHelper = new ReponseHelper(){Ok = true, Message ="L'utilisateur a été enregistré, veuillez vous connecter"};
                        ViewBag.Success = "L'utilisateur a été enregistré, veuillez vous connecter";
                       // TempData["Reponse"] = reponseHelper;
                        SwapCart(obj.UserName);
                        return RedirectToAction("Login", "Authentication");
                    }

                }
                // Password != ConfirmPassword
                obj.Password = "";
                obj.ConfirmPassword = "";
            //    reponseHelper = new ReponseHelper(){Ok = false, Message ="Le mot de passe encodé ne correspond pas au mot de passe confirmé"};

                ViewBag.Error = "Le mot de passe encodé ne correspond pas au mot de passe confirmé";
                return View("Register", obj);
            }
            ViewBag.Error = "Un des champs est invalide";
            obj.Password = "";
            obj.ConfirmPassword = "";
            return View("Register", obj);
        }
        #endregion

        #endregion

        #region Method

        private bool SwapCart(string connectedUser)
        {
            var currentUser = GetUserIdentity();
            using (var client = new HttpClient())
            using (var request = new HttpRequestMessage(HttpMethod.Get, $"http://{TokenManager.GetHost()}:{TokenManager.GetPort()}/cart/swap/{currentUser.Name.ToLower()}/{connectedUser.ToLower()}"))
            {
                client.DefaultRequestHeaders.Accept.Clear();
                request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                request.Headers.Authorization = new AuthenticationHeaderValue("Bearer", TokenManager.GetToken());

                var reponse = client.SendAsync(request).Result;
                if (reponse.IsSuccessStatusCode)
                {
                    return true; 
                }
            }
            return false;
        }
        private CurrentUser GetUserIdentity()
        {
            var currentUser = new CurrentUser();
            var user = _userManager.GetUserAsync(_context.HttpContext.User).Result;
            if (user != null)
            {
                currentUser.Name = user.UserName;
                if (_userManager.IsInRoleAsync(user, "Customer").Result)
                {
                    currentUser.Role = "Customer";
                }
                else if (_userManager.IsInRoleAsync(user, "Visitor").Result)
                {
                    currentUser.Role = "Visitor";
                }
            }
            return currentUser;
        }
        #endregion
    }
}