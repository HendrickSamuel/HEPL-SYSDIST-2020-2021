using System;
using System.Collections.Generic;
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
        public IActionResult Catalog()
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
                    if (itemShopHelper.Items.Count > 0)
                    {
                        return View(itemShopHelper);
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
            return View(itemShopHelper);
        }
    }
}