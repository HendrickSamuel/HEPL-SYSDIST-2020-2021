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

namespace ShopWebApplication.Controllers
{
    public class BillController : Controller
    {
        #region Members
        private readonly HttpClient _client;
        private readonly UserManager<IdentityUser> _userManager;
        private readonly IHttpContextAccessor _context;
        #endregion

        #region Constructor
        public BillController(IHttpContextAccessor context, HttpClient client, UserManager<IdentityUser> userManager)
        {
            _context = context;
            _userManager = userManager;
            _client = client;
        }
        #endregion

        #region Action

        [Authorize(Roles = "Customer")]
        public IActionResult Preview()
        {
            return View("Preview", GetUser());
        }
        #endregion


        #region Method
        private User GetUser()
        {
            var user = new User();
            var currentUser = GetUserIdentity();
            try
            {
                using (var request = new HttpRequestMessage(HttpMethod.Get, $"http://localhost:8080/cart/get/{currentUser.Name.ToLower()}"))
                {
                    _client.DefaultRequestHeaders.Accept.Clear();
                    request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                    var reponse = _client.SendAsync(request).Result;
                    if (reponse.IsSuccessStatusCode)
                    {
                        var jsonString = reponse.Content.ReadAsStringAsync().Result;
                        user = JsonSerializer.Deserialize<User>(jsonString);
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine($"{e.Source}: {e.Message}");
                return new User();
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

        #endregion
    }
}
