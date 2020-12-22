using Microsoft.AspNetCore.Mvc;

namespace ShopWebApplication.Controllers
{
    public class Catalog : Controller
    {
        // GET
        public IActionResult Index()
        {
            return View();
        }
    }
}