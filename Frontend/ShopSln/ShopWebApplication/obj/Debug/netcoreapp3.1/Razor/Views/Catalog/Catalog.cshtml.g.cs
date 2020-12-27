#pragma checksum "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "d87b99574c2a407f94b88e79d0e0284b7fa05f88"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Catalog_Catalog), @"mvc.1.0.view", @"/Views/Catalog/Catalog.cshtml")]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#nullable restore
#line 1 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\_ViewImports.cshtml"
using ShopWebApplication;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\_ViewImports.cshtml"
using ShopWebApplication.Models;

#line default
#line hidden
#nullable disable
#nullable restore
#line 1 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
using System.Linq;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"d87b99574c2a407f94b88e79d0e0284b7fa05f88", @"/Views/Catalog/Catalog.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"ed0efb42ceab8be106663ef969b79685907bd9d4", @"/Views/_ViewImports.cshtml")]
    public class Views_Catalog_Catalog : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<ShopWebApplication.Helpers.DataCatalogHelper>
    {
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
#nullable restore
#line 3 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
  
    ViewData["Title"] = "Catalog";
    Layout = ViewData["Layout"] as String;
    // AjaxOptions ajaxOptions = new AjaxOptions()
    // {
    //     HttpMethod = "POST",
    //     InsertionMode = InsertionMode.Replace,
    //     UpdateTargetId = "current-cart-item"
    // };

#line default
#line hidden
#nullable disable
            WriteLiteral("<section class=\" mt-5 \">\r\n    <div class=\"mt-4\">\r\n");
            WriteLiteral("        ");
#nullable restore
#line 16 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
   Write(await Html.PartialAsync("~/Views/Shared/_CartCatalog.cshtml", Model.MyCart));

#line default
#line hidden
#nullable disable
            WriteLiteral(@" <!-- OR : <partial name=""~/Views/Shared/_CartCatalog.cshtml"" />-->
        <div class=""row"">
            <div class=""col-12"">
                <h1 class=""catalog-title text-center"">Nos produits</h1>
            </div>
        </div>
            <div class=""row justify-content-center"">
");
#nullable restore
#line 23 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                 foreach (var item in @Model.IShopHelper.Items)
                {

#line default
#line hidden
#nullable disable
            WriteLiteral("                    <div class=\"col-12 col-lg-6 col-xl-4\">\r\n");
#nullable restore
#line 26 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                         using (Html.BeginForm("OnSelectedItem", "Catalog", FormMethod.Post))
                        {
                            bool exist = Model.MyCart.Items.Exists(itemCart => itemCart.Id == item.Id);
                            bool stockSuffisant = true;
                            bool stockNull = item.Stock == 0;
                            if (stockNull)
                            {
                                stockSuffisant = false;
                                
                            }
                            if (exist)
                            {
                                var itemCartModel = Model.MyCart.Items.Where(itemCart => itemCart.Id == item.Id).FirstOrDefault();
                                stockSuffisant = itemCartModel != null && itemCartModel.Quantity < itemCartModel.Stock;
                            }
                            

#line default
#line hidden
#nullable disable
#nullable restore
#line 41 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                       Write(Html.AntiForgeryToken());

#line default
#line hidden
#nullable disable
#nullable restore
#line 41 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                                                    ;

#line default
#line hidden
#nullable disable
            WriteLiteral("                            <div");
            BeginWriteAttribute("id", " id=\"", 1971, "\"", 1976, 0);
            EndWriteAttribute();
            WriteLiteral(@" class=""card-item"">
                                <!-- Cercle avec le stock -->
                                <div class=""card-item-stock-container"">
                                    <div class=""card-item-stock-bg"">
                                        <div class=""card-item-stock"">
                                            <p><span>");
#nullable restore
#line 47 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                                                Write(item.Stock);

#line default
#line hidden
#nullable disable
            WriteLiteral(@"</span></p>
                                        </div>
                                    </div>
                                </div>
                                <div class=""card-item-header"">
                                    <div class=""row"">
                                        <!-- Image -->
                                        <div class=""col-12"">
                                            <img class=""card-item-img""");
            BeginWriteAttribute("src", " src=\"", 2791, "\"", 2816, 3);
            WriteAttributeValue("", 2797, "/img/", 2797, 5, true);
#nullable restore
#line 55 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
WriteAttributeValue("", 2802, item.Id, 2802, 10, false);

#line default
#line hidden
#nullable disable
            WriteAttributeValue("", 2812, ".jpg", 2812, 4, true);
            EndWriteAttribute();
            WriteLiteral(@" alt=""card-img"" onerror=""this.onerror=null;this.src='/img/not-found.jpg'""/>
                                        </div>
                                    </div>
                                </div>
                                <div class=""card-item-body"">
                                <div class=""row"">
                                    <div class=""col-12"">
                                        <p class=""card-item-type"">");
#nullable restore
#line 62 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                                                              Write(item.Type);

#line default
#line hidden
#nullable disable
            WriteLiteral(@"</p>
                                    </div>
                                </div>
                                <div class=""row"">
                                    <div class=""col-12"">
                                        <h3 class=""card-item-name"">");
#nullable restore
#line 67 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                                                               Write(item.Name);

#line default
#line hidden
#nullable disable
            WriteLiteral(@"</h3>
                                    </div>
                                </div>
                                <div class=""row"">
                                    <div class=""col-12"">
                                        <p class=""card-item-desc"">");
#nullable restore
#line 72 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                                                              Write(item.Description);

#line default
#line hidden
#nullable disable
            WriteLiteral(@"</p>
                                    </div>
                                </div>
                                <div class=""row"">
                                    <div class=""col-12"">
                                        <p class=""card-item-price""><span>");
#nullable restore
#line 77 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                                                                     Write(item.Price);

#line default
#line hidden
#nullable disable
            WriteLiteral(@"</span> €</p>
                                    </div>
                                </div>
                                <div class=""row"">
                                    <div class=""col-12"">
                                        <div class=""col-12 mb-3 text-center"">
");
#nullable restore
#line 83 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                                             if (stockSuffisant)
                                            {

#line default
#line hidden
#nullable disable
            WriteLiteral("                                                <input placeholder=\"Quantité\" class=\"form-control quantity-input\" required type=\"number\" min=\"1\"");
            BeginWriteAttribute("max", " max=\"", 4667, "\"", 4684, 1);
#nullable restore
#line 85 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
WriteAttributeValue("", 4673, item.Stock, 4673, 11, false);

#line default
#line hidden
#nullable disable
            EndWriteAttribute();
            WriteLiteral(" name=\"quantity\"/>\r\n                                                <input type=\"hidden\" name=\"id-item\"");
            BeginWriteAttribute("value", " value=\"", 4788, "\"", 4804, 1);
#nullable restore
#line 86 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
WriteAttributeValue("", 4796, item.Id, 4796, 8, false);

#line default
#line hidden
#nullable disable
            EndWriteAttribute();
            WriteLiteral(">\r\n");
#nullable restore
#line 87 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                                            }
                                            else if(stockNull)
                                            {

#line default
#line hidden
#nullable disable
            WriteLiteral("                                                <input placeholder=\"Quantité\" class=\"form-control quantity-input\" readonly type=\"number\" min=\"1\"");
            BeginWriteAttribute("max", " max=\"", 5110, "\"", 5127, 1);
#nullable restore
#line 90 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
WriteAttributeValue("", 5116, item.Stock, 5116, 11, false);

#line default
#line hidden
#nullable disable
            EndWriteAttribute();
            WriteLiteral(" name=\"quantity\"/>\r\n                                                <input type=\"hidden\" name=\"id-item\"");
            BeginWriteAttribute("value", " value=\"", 5231, "\"", 5247, 1);
#nullable restore
#line 91 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
WriteAttributeValue("", 5239, item.Id, 5239, 8, false);

#line default
#line hidden
#nullable disable
            EndWriteAttribute();
            WriteLiteral(">\r\n");
#nullable restore
#line 92 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                                            }
                                            else
                                            {

#line default
#line hidden
#nullable disable
            WriteLiteral("                                                <input placeholder=\"Quantité\" class=\"form-control quantity-input\" readonly type=\"number\" min=\"1\"");
            BeginWriteAttribute("max", " max=\"", 5539, "\"", 5556, 1);
#nullable restore
#line 95 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
WriteAttributeValue("", 5545, item.Stock, 5545, 11, false);

#line default
#line hidden
#nullable disable
            EndWriteAttribute();
            WriteLiteral(" name=\"quantity\"/>\r\n                                                <input type=\"hidden\" name=\"id-item\"");
            BeginWriteAttribute("value", " value=\"", 5660, "\"", 5676, 1);
#nullable restore
#line 96 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
WriteAttributeValue("", 5668, item.Id, 5668, 8, false);

#line default
#line hidden
#nullable disable
            EndWriteAttribute();
            WriteLiteral(">\r\n");
#nullable restore
#line 97 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                                            }

#line default
#line hidden
#nullable disable
            WriteLiteral(@"                                        </div>
                                    </div>
                                </div>
                            </div>
                                <div class=""card-item-footer"">
                                    <div class=""row"">
                                        <!-- Bouton Add -->
                                        <div class=""col-12"">
");
#nullable restore
#line 106 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                                             if (exist)
                                            {
                                                if (stockSuffisant)
                                                {

#line default
#line hidden
#nullable disable
            WriteLiteral("                                                    <button type=\"submit\"");
            BeginWriteAttribute("onclick", " onclick=\"", 6434, "\"", 6444, 0);
            EndWriteAttribute();
            WriteLiteral(" class=\"card-item-btn-add\">Add</button>\r\n");
#nullable restore
#line 111 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                                                }
                                                else
                                                {

#line default
#line hidden
#nullable disable
            WriteLiteral("                                                    <button type=\"submit\" disabled");
            BeginWriteAttribute("onclick", " onclick=\"", 6724, "\"", 6734, 0);
            EndWriteAttribute();
            WriteLiteral(" class=\"card-item-btn-add-disabled\">Add</button>\r\n");
#nullable restore
#line 115 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                                                }
                                            }
                                            else
                                            {
                                                if(stockNull)
                                                {

#line default
#line hidden
#nullable disable
            WriteLiteral("                                                    <button type=\"submit\" disabled");
            BeginWriteAttribute("onclick", " onclick=\"", 7176, "\"", 7186, 0);
            EndWriteAttribute();
            WriteLiteral(" class=\"card-item-btn-add-disabled\">Add</button>\r\n");
#nullable restore
#line 122 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                                                }
                                                else
                                                {

#line default
#line hidden
#nullable disable
            WriteLiteral("                                                    <button type=\"submit\"");
            BeginWriteAttribute("onclick", "  onclick=\"", 7466, "\"", 7477, 0);
            EndWriteAttribute();
            WriteLiteral(" class=\"card-item-btn-add\">Add</button>\r\n");
#nullable restore
#line 126 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"

                                                }
                                                // formaction="/Catalog/OnSelectedItem/@(item.Id)"
                                            }

#line default
#line hidden
#nullable disable
            WriteLiteral("                                        </div>\r\n                                </div>\r\n                            </div>\r\n                            </div>\r\n");
#nullable restore
#line 134 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                        }

#line default
#line hidden
#nullable disable
            WriteLiteral("                    </div>\r\n");
#nullable restore
#line 136 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
                }

#line default
#line hidden
#nullable disable
            WriteLiteral("            </div>\r\n    </div>\r\n</section>");
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<ShopWebApplication.Helpers.DataCatalogHelper> Html { get; private set; }
    }
}
#pragma warning restore 1591
