namespace ShopWebApplication.Helpers
{
    public class CurrentUser
    {
        public string Name { get; set; }
        public string Role { get; set; }

        public CurrentUser()
        {
            Name = ""; Role = "";
        }

    }
}