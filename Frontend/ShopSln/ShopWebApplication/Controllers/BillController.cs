using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text.Json;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using ShopWebApplication.Helpers;
using ShopWebApplication.Models.POCOS;
using ShopWebApplication.Token;

namespace ShopWebApplication.Controllers
{
    public class BillController : Controller
    {
        #region Members
        private readonly HttpClient _client = new HttpClient();
        
        private readonly UserManager<IdentityUser> _userManager;
        private readonly IHttpContextAccessor _context;
        private static CommandModel _currentCommand;
        #endregion

        #region Constructor
        public BillController(IHttpContextAccessor context, UserManager<IdentityUser> userManager)
        {
            _context = context;
            _userManager = userManager;
        }
        #endregion

        #region Action

        [Authorize(Roles = "Customer")]
        public IActionResult PreviewCommand()
        {
            throw new NotImplementedException();
        }
        
        [Authorize(Roles = "Customer")]
        public IActionResult Preview()
        {
            return View("Preview", GetUser());
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "Customer")]
        public async Task<IActionResult> Payment()
        {
            throw new NotImplementedException();
        }        
        
        #endregion


        #region Method
        private UserModel GetUser()
        {
            var user = new UserModel();
            var currentUser = GetUserIdentity();
            try
            {
                using (var request = new HttpRequestMessage(HttpMethod.Get, $"http://localhost:8080/cart/get/{currentUser.Name.ToLower()}"))
                {
                    _client.DefaultRequestHeaders.Accept.Clear();
                    request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                    request.Headers.Authorization = new AuthenticationHeaderValue("Bearer", TokenManager.GetToken());

                    var reponse = _client.SendAsync(request).Result;
                    if (reponse.IsSuccessStatusCode)
                    {
                        var jsonString = reponse.Content.ReadAsStringAsync().Result;
                        user = JsonSerializer.Deserialize<UserModel>(jsonString);
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine($"{e.Source}: {e.Message}");
                return new UserModel();
            }
            return user;
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

        private ReponseHelper CreateCommmad()
        {
            CurrentUser currentUser = GetUserIdentity();
            using (var request = new HttpRequestMessage(HttpMethod.Get, $"http://localhost:8080//command/preview/{currentUser.Name}"))
            {
                _client.DefaultRequestHeaders.Accept.Clear();
                request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                request.Headers.Authorization = new AuthenticationHeaderValue("Bearer", TokenManager.GetToken());

                var reponse = _client.SendAsync(request).Result;
                if (reponse.IsSuccessStatusCode)
                {
                    var jsonString = reponse.Content.ReadAsStringAsync().Result;
                    _currentCommand = JsonSerializer.Deserialize<CommandModel>(jsonString);
                    return new ReponseHelper(true, "Commande cree !"); 
                }
            }
            return new ReponseHelper(false, "Commande pas cree !");
        }

        
        private ReponseHelper ConfirmPayment(UserModel connectedUserModel, String deliveryMode)
        {
            if (connectedUserModel.Wallet >= connectedUserModel.MyCartModel.TotalTva)
            {
                // using (var client = new HttpClient())
                using (var request = new HttpRequestMessage(HttpMethod.Get,
                    $"http://localhost:8080/command/checkout/{_currentCommand.Id}?methode={deliveryMode.ToLower()}"))
                {
                    _client.DefaultRequestHeaders.Accept.Clear();
                    request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                    request.Headers.Authorization = new AuthenticationHeaderValue("Bearer", TokenManager.GetToken());
                    var reponse = _client.SendAsync(request).Result;
                    if (reponse.IsSuccessStatusCode)
                    {
                        return new ReponseHelper(true, "Payement effectué !");
                    }
                    return new ReponseHelper(false, "Erreur serveur lors de l'execution du paiement!");
                }
            }
            return new ReponseHelper(false, "Pas assez d'argent de le portefeuille!");
        }
        #endregion
    }
}
