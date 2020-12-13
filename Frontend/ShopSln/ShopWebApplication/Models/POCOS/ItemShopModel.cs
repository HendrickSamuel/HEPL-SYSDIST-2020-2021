namespace ShopWebApplication.Models.POCOS
{
    public class ItemShopModel
    {
        #region Public Properties
        public int Id { get; set; }
        public string Name { get; set; }
        public ItemType ItemType { get; set; }
        public int Stock { get; set; }
        public float Price { get; set; }
        public string Description { set; get; }
        #endregion
    }

    public enum ItemType
    {
        Bouffe,
        Livre,
        Essentiel,
        Bricolage,
        Autre
    }
    
    
}