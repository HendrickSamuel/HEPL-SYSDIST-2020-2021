﻿using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text.Json;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using ShopWebApplication.Helpers;
using ShopWebApplication.Models.POCOS;

namespace ShopWebApplication.Controllers
{
    public class CatalogController : Controller
    {
        #region Private Variables
        private readonly HttpClient _client;
        //private readonly ILogger<CatalogController> _logger;
        private readonly UserManager<IdentityUser> _userManager;
        private readonly IHttpContextAccessor _context;
        #endregion

        #region Constructors
        //public CatalogController(ILogger<CatalogController> logger)
        //{
        //    _logger = logger;
        //    _client = new HttpClient();
        //}
        public CatalogController(IHttpContextAccessor context, HttpClient client, UserManager<IdentityUser> userManager)
        {
            _context = context;
            _userManager = userManager;
            _client = client;
        }
        #endregion


        #region Actions
        // GET
        [HttpGet]
        public IActionResult MyCatalog()
        {
            var itemShopHelper = GetItems();
            var currentUser = GetUserIdentity();
            var myCart = GetCart(currentUser.Name);
            if (currentUser.Role == "Visitor")
                ViewData["Layout"] = "_Layout";
            else
                ViewData["Layout"] = "_LayoutLogged";
            return View("Catalog", new DataCatalogHelper(){IShopHelper = itemShopHelper, MyCart = myCart});
        }
        // /{idItem}")]
        [HttpPost("[controller]/OnSelectedItem")]
        public IActionResult OnSelectedItem(/*[FromRoute] string idItem*/) // TODO : Utiliser un model comme pour le login (Model.State)
        {
            //Console.WriteLine(Request.Form);
            var currentUser = GetUserIdentity();
            int quantity = -1;
            string idItem = "";
            //string user = "samuel"; //TODO :  GetUser
            foreach (var key in Request.Form.Keys)
            {
                if (String.Compare(key.ToLower(), "quantity", StringComparison.Ordinal) == 0)
                    quantity = Int32.Parse(Request.Form[key]);
                else if (String.Compare(key.ToLower(), "id-item", StringComparison.Ordinal) == 0)
                    idItem = Request.Form[key];
            }

            if (quantity >= 1 && idItem != String.Empty)
            {
                AddItemToCart(idItem, currentUser.Name, quantity);
            }
            if (currentUser.Role == "Visitor")
                ViewData["Layout"] = "_Layout";
            else
                ViewData["Layout"] = "_LayoutLogged";
            return View("Catalog", new DataCatalogHelper(){IShopHelper = GetItems(), MyCart = GetCart(currentUser.Name) });
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
        private Cart GetCart(String username)
        {
            Cart myCart = new Cart();
            try
            {
                _client.DefaultRequestHeaders.Accept.Clear();
                using (var request = new HttpRequestMessage(HttpMethod.Get, $"http://localhost:8080/cart/get/{username.ToLower()}"))
                {
                    request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                    var reponse = _client.SendAsync(request).Result;
                    if (reponse.IsSuccessStatusCode)
                    {
                        var jsonString = reponse.Content.ReadAsStringAsync().Result;
                        myCart = JsonSerializer.Deserialize<User>(jsonString).MyCart;
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine($"{e.Source}: {e.Message}");
                return new Cart();
            }
            return myCart;
        }
        private ItemShopHelper GetItems()
        {
            ItemShopHelper itemShopHelper = new ItemShopHelper();
            try
            {
                _client.DefaultRequestHeaders.Accept.Clear();
                _client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                var reponse = _client.GetAsync("http://localhost:8080/items/").Result;

                if (reponse.IsSuccessStatusCode)
                {
                    var jsonString = reponse.Content.ReadAsStringAsync().Result;
                    //Console.WriteLine(jsonString);
                    itemShopHelper = JsonSerializer.Deserialize<ItemShopHelper>(jsonString);
                }
            }
            catch(Exception e)
            {
                Console.WriteLine($"{e.Source}: {e.Message}");

                return new ItemShopHelper();
            }
            return itemShopHelper;
        }

        private bool AddItemToCart(string idItem, string user, int quantity)
        {
            _client.DefaultRequestHeaders.Accept.Clear();
            _client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

            var reponse = _client.GetAsync($"http://localhost:8080/cart/add/{user.ToLower()}/{idItem}/{quantity}").Result;
            
            if (reponse.IsSuccessStatusCode)
            {
                return true;
            }
            return false;
        }
        #endregion
        
    }
}