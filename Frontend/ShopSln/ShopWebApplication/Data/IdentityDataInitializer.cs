using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ShopWebApplication.Data
{
    public static class IdentityDataInitializer
    {
        public static void SeedData(UserManager<IdentityUser> userManager,
            RoleManager<IdentityRole> roleManager)
        {
            SeedRoles(roleManager);
            SeedUsers(userManager);
        }

        public static void SeedUsers(UserManager<IdentityUser> userManager)
        {
            var machineName = Environment.MachineName.ToLower();
            var username = $"visitor_{machineName}";
            if (userManager.FindByNameAsync(username).Result == null)
            {
                var user = new IdentityUser { UserName = username };
                var createUserResult = userManager.CreateAsync(user, "visitor").Result;
                if (createUserResult.Succeeded)
                {
                    userManager.AddToRoleAsync(user, "Visitor").Wait();
                }
            }
        }

        public static void SeedRoles(RoleManager<IdentityRole> roleManager)
        {
            if (!roleManager.RoleExistsAsync("Customer").Result)
            {
                var role = new IdentityRole { Name = "Customer" };
                var createRoleResult = roleManager.CreateAsync(role).Result;
            }

            if (!roleManager.RoleExistsAsync("Visitor").Result)
            {
                var role = new IdentityRole { Name = "Visitor" };
                var createRoleResult = roleManager.CreateAsync(role).Result;
            }
        }
    }
}
