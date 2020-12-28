namespace ShopWebApplication.Helpers
{
    public class UserToken
    {
        public string Name { get; set; }
        public string Password { get; set; }
        // public string Token { get; set; }


        public UserToken()
        {
            Name = ""; Password = "";
        }
    }
}