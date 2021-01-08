namespace ShopWebApplication.Helpers
{
    public class ReponseHelper
    {
        public bool Ok { get; set; }
        public string Message { get; set; }

        public ReponseHelper()
        {
        }

        public ReponseHelper(bool ok, string message)
        {
            Ok = ok;
            Message = message;
        }

        public override string ToString()
        {
            return $"{nameof(Ok)}: {Ok}, {nameof(Message)}: {Message}";
        }
    }
}