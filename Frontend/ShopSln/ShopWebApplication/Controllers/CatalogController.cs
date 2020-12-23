using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text.Json;
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
        private readonly ILogger<CatalogController> _logger;
        #endregion

        #region Constructors
        public CatalogController(ILogger<CatalogController> logger)
        {
            _logger = logger;
            _client = new HttpClient();
        }
        #endregion
       
        // GET
        [HttpGet]
        public IActionResult MyCatalog()
        {
            ItemShopHelper itemShopHelper = GetItems();
            Cart myCart = GetCart("samuel");
            
            return View("Catalog", new DataCatalogHelper(){IShopHelper = itemShopHelper, MyCart = myCart});
        }
        [HttpPost("[controller]/OnSelectedItem/{idItem}")]
        public IActionResult OnSelectedItem([FromRoute] string idItem)
        {
            Console.WriteLine(idItem);
            AddItemToCart(idItem, "samuel", 1);
            return View("Catalog", new DataCatalogHelper(){IShopHelper = GetItems(), MyCart = GetCart("samuel")});
        }

        private Cart GetCart(String username)
        {
            Cart myCart = new Cart();
            try
            {
                _client.DefaultRequestHeaders.Accept.Clear();
                _client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                var reponse = _client.GetAsync($"http://localhost:8080/cart/get/{username}").Result;
                if (reponse.IsSuccessStatusCode)
                {
                    var jsonString = reponse.Content.ReadAsStringAsync().Result;
                    myCart = JsonSerializer.Deserialize<User>(jsonString).MyCart;
                }
            }
            catch(Exception e)
            {
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
                return new ItemShopHelper();
            }
            return itemShopHelper;
        }

        private bool AddItemToCart(string idItem, string user, int quantity)
        {
            _client.DefaultRequestHeaders.Accept.Clear();
            _client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

            var reponse = _client.GetAsync($"http://localhost:8080/cart/add/{user}/{idItem}/{quantity}").Result;
            
            if (reponse.IsSuccessStatusCode)
            {
                return true;
            }
            return false;
        }
        
    }
}