using ShopWebApplication.Helpers;

namespace ShopWebApplication.Services
{
    public interface ITokenService
    {
        public string GetToken(UserToken userToken);
        protected bool InitToken();
        public void SaveToken();
    }
}