using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text.Json;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using ShopWebApplication.Helpers;
using ShopWebApplication.Token;

namespace ShopWebApplication.Controllers
{
    public class CommandController : Controller
    {
        // GET
        #region Members
        private readonly HttpClient _client = new HttpClient();
        private readonly UserManager<IdentityUser> _userManager;
        private readonly IHttpContextAccessor _context;
        #endregion

        #region Constructor
        public CommandController(IHttpContextAccessor context, UserManager<IdentityUser> userManager)
        {
            _context = context;
            _userManager = userManager;
        }
        #endregion

        #region Action
        [HttpGet]
        [Authorize(Roles = "Customer")]
        public IActionResult Commands()
        {
            return View("Commands", GetCommands());
        }
        #endregion

        #region Method
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
        private UserCommandHelper GetCommands(){
        
            UserCommandHelper userCommandHelper = new UserCommandHelper(){Username = GetUserIdentity().Name};
            try
            {
                using (var request = new HttpRequestMessage(HttpMethod.Get,
                    $"http://{TokenManager.GetHost()}:{TokenManager.GetPort()}/command/list/{userCommandHelper.Username.ToLower()}"))
                {
                    _client.DefaultRequestHeaders.Accept.Clear();
                    request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                    request.Headers.Authorization = new AuthenticationHeaderValue("Bearer", TokenManager.GetToken());

                    var reponse = _client.SendAsync(request).Result;
                    if (reponse.IsSuccessStatusCode)
                    {
                        var jsonString = reponse.Content.ReadAsStringAsync().Result;
                        userCommandHelper = JsonSerializer.Deserialize<UserCommandHelper>(jsonString);
                    }
                }
            }
            catch(Exception e)
            {
                return new UserCommandHelper();
            }
            return userCommandHelper;
        }
        #endregion
    }
}