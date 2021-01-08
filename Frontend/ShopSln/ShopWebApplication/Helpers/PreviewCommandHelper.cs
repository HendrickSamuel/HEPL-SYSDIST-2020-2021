using ShopWebApplication.Models.POCOS;

namespace ShopWebApplication.Helpers
{
    public class PreviewCommandHelper
    {
        public UserModel User { get; set; }
        public CommandModel CurrentCommand { get; set; }

        public PreviewCommandHelper()
        {
        }

        public override string ToString()
        {
            return $"{nameof(User)}: {User}, {nameof(CurrentCommand)}: {CurrentCommand}";
        }
    }
}