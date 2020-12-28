using ShopWebApplication.Helpers;

namespace ShopWebApplication.Services
{
    public class TokenService : ITokenService
    {
        private readonly string _baseUri;
        private static UserToken _currentUserToken;
        public TokenService(string baseUri, UserToken userToken)
        {
            _baseUri = baseUri;
            _currentUserToken = userToken;
        }

        private UserToken GetUserToken()
        {
            return _currentUserToken;
        }

        public string GetToken(UserToken userToken)
        {
            throw new System.NotImplementedException();
        }

        bool ITokenService.InitToken()
        {
            throw new System.NotImplementedException();
        }

        public void SaveToken()
        {
            throw new System.NotImplementedException();
        }
    }
}