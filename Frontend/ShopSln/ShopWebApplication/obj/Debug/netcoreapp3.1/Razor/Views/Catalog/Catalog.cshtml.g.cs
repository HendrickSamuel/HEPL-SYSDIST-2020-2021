#pragma checksum "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "8977175173c4f691a3cc123826026442f7809d01"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Catalog_Catalog), @"mvc.1.0.view", @"/Views/Catalog/Catalog.cshtml")]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
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
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"8977175173c4f691a3cc123826026442f7809d01", @"/Views/Catalog/Catalog.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"ed0efb42ceab8be106663ef969b79685907bd9d4", @"/Views/_ViewImports.cshtml")]
    public class Views_Catalog_Catalog : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<ShopWebApplication.Models.POCOS.ItemShopModel>
    {
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            WriteLiteral("\r\n");
#nullable restore
#line 3 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Catalog\Catalog.cshtml"
  
    ViewData["Title"] = "Catalog";
    Layout = "_Layout";

#line default
#line hidden
#nullable disable
            WriteLiteral(@"<section class=""pb-5 mt-5 "">
    <div class=""container-fluid mt-4"">
        <div class=""row"">
            <div class=""col-12"">
                <h1 class=""catalog-title text-center"">Nos produits</h1>
            </div>
        </div>
        <div class=""row"">
            <div class=""col-12 col-md-6 mx-auto col-lg-7 mt-3"">
                <div id=""slider-catalog-items"">
                    <div class=""swiper-container"">
                        <div class=""swiper-wrapper"">
        
                            <div class=""swiper-slide"">
                                <div");
            BeginWriteAttribute("id", " id=\"", 714, "\"", 719, 0);
            EndWriteAttribute();
            WriteLiteral(@" class=""card-item"">
                                    <!-- Cercle avec le stock -->
                                    <div class=""card-item-stock-container"">
                                        <div class=""card-item-stock-bg"">
                                            <div class=""card-item-stock"">
                                                <p><span>10/</span>10</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class=""card-item-header"">
                                        <div class=""row"">
                                            <!-- Image -->
                                            <div class=""col-12"">
                                                <img class=""card-item-img"" src=""img/boxes/pizza.jpg"" alt=""card-img"" />
                                            </div>
                                        </div>
                  ");
            WriteLiteral(@"                  </div>
                                    <div class=""card-item-body"">
                                        <div class=""row"">
                                            <!--Categorie +  Nom + Decription + Prix -->
                                            <div class=""col-12"">
                                                <p class=""card-item-type"">Bouffe</p>
                                            </div>
                                        </div>
                                        <div class=""row"">
                                            <div class=""col-12"">
                                                <h3 class=""card-item-name"">Bonbons</h3>
                                            </div>
                                        </div>
                                        <div class=""row"">    
                                            <div class=""col-12"">
                                                <p class=""card-item-desc"">Lorem ipsum do");
            WriteLiteral(@"lor sit amet, consectetur adipiscing elit. Phasellus vitae bibendum lectus. Duis tempor eget eros euismod placerat.</p>
                                            </div>
                                        </div>
                                        <div class=""row"">
                                            <div class=""col-12"">
                                                <p class=""card-item-price""><span>4,65</span> €</p>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class=""card-item-footer"">
                                        <div class=""row"">
                                            <!-- Bouton Add -->
                                            <div class=""col-12"">
                                                <button class=""card-item-btn-add"">Add</button>
                                          ");
            WriteLiteral(@"  </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class=""swiper-slide"">
                                <div");
            BeginWriteAttribute("id", " id=\"", 4062, "\"", 4067, 0);
            EndWriteAttribute();
            WriteLiteral(@" class=""card-item"">
                                    <!-- Cercle avec le stock -->
                                    <div class=""card-item-stock-container"">
                                        <div class=""card-item-stock-bg"">
                                            <div class=""card-item-stock"">
                                                <p><span>10/</span>10</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class=""card-item-header"">
                                        <div class=""row"">
                                            <!-- Image -->
                                            <div class=""col-12"">
                                                <img class=""card-item-img"" src=""img/boxes/pizza.jpg"" alt=""card-img"" />
                                            </div>
                                        </div>
                  ");
            WriteLiteral(@"                  </div>
                                    <div class=""card-item-body"">
                                        <div class=""row"">
                                            <!--Categorie +  Nom + Decription + Prix -->
                                            <div class=""col-12"">
                                                <p class=""card-item-type"">Bouffe</p>
                                            </div>
                                        </div>
                                        <div class=""row"">
                                            <div class=""col-12"">
                                                <h3 class=""card-item-name"">Bonbons</h3>
                                            </div>
                                        </div>
                                        <div class=""row"">    
                                            <div class=""col-12"">
                                                <p class=""card-item-desc"">Lorem ipsum do");
            WriteLiteral(@"lor sit amet, consectetur adipiscing elit. Phasellus vitae bibendum lectus. Duis tempor eget eros euismod placerat.</p>
                                            </div>
                                        </div>
                                        <div class=""row"">
                                            <div class=""col-12"">
                                                <p class=""card-item-price""><span>4,65</span> €</p>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class=""card-item-footer"">
                                        <div class=""row"">
                                            <!-- Bouton Add -->
                                            <div class=""col-12"">
                                                <button class=""card-item-btn-add"">Add</button>
                                          ");
            WriteLiteral(@"  </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class=""swiper-slide"">
                                <div");
            BeginWriteAttribute("id", " id=\"", 7410, "\"", 7415, 0);
            EndWriteAttribute();
            WriteLiteral(@" class=""card-item"">
                                    <!-- Cercle avec le stock -->
                                    <div class=""card-item-stock-container"">
                                        <div class=""card-item-stock-bg"">
                                            <div class=""card-item-stock"">
                                                <p><span>10/</span>10</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class=""card-item-header"">
                                        <div class=""row"">
                                            <!-- Image -->
                                            <div class=""col-12"">
                                                <img class=""card-item-img"" src=""img/boxes/pizza.jpg"" alt=""card-img"" />
                                            </div>
                                        </div>
                  ");
            WriteLiteral(@"                  </div>
                                    <div class=""card-item-body"">
                                        <div class=""row"">
                                            <!--Categorie +  Nom + Decription + Prix -->
                                            <div class=""col-12"">
                                                <p class=""card-item-type"">Bouffe</p>
                                            </div>
                                        </div>
                                        <div class=""row"">
                                            <div class=""col-12"">
                                                <h3 class=""card-item-name"">Bonbons</h3>
                                            </div>
                                        </div>
                                        <div class=""row"">    
                                            <div class=""col-12"">
                                                <p class=""card-item-desc"">Lorem ipsum do");
            WriteLiteral(@"lor sit amet, consectetur adipiscing elit. Phasellus vitae bibendum lectus. Duis tempor eget eros euismod placerat.</p>
                                            </div>
                                        </div>
                                        <div class=""row"">
                                            <div class=""col-12"">
                                                <p class=""card-item-price""><span>4,65</span> €</p>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class=""card-item-footer"">
                                        <div class=""row"">
                                            <!-- Bouton Add -->
                                            <div class=""col-12"">
                                                <button class=""card-item-btn-add"">Add</button>
                                          ");
            WriteLiteral(@"  </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class=""swiper-slide"">
                                <div");
            BeginWriteAttribute("id", " id=\"", 10758, "\"", 10763, 0);
            EndWriteAttribute();
            WriteLiteral(@" class=""card-item"">
                                    <!-- Cercle avec le stock -->
                                    <div class=""card-item-stock-container"">
                                        <div class=""card-item-stock-bg"">
                                            <div class=""card-item-stock"">
                                                <p><span>10/</span>10</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class=""card-item-header"">
                                        <div class=""row"">
                                            <!-- Image -->
                                            <div class=""col-12"">
                                                <img class=""card-item-img"" src=""img/boxes/pizza.jpg"" alt=""card-img"" />
                                            </div>
                                        </div>
                  ");
            WriteLiteral(@"                  </div>
                                    <div class=""card-item-body"">
                                        <div class=""row"">
                                            <!--Categorie +  Nom + Decription + Prix -->
                                            <div class=""col-12"">
                                                <p class=""card-item-type"">Bouffe</p>
                                            </div>
                                        </div>
                                        <div class=""row"">
                                            <div class=""col-12"">
                                                <h3 class=""card-item-name"">Bonbons</h3>
                                            </div>
                                        </div>
                                        <div class=""row"">    
                                            <div class=""col-12"">
                                                <p class=""card-item-desc"">Lorem ipsum do");
            WriteLiteral(@"lor sit amet, consectetur adipiscing elit. Phasellus vitae bibendum lectus. Duis tempor eget eros euismod placerat.</p>
                                            </div>
                                        </div>
                                        <div class=""row"">
                                            <div class=""col-12"">
                                                <p class=""card-item-price""><span>4,65</span> €</p>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class=""card-item-footer"">
                                        <div class=""row"">
                                            <!-- Bouton Add -->
                                            <div class=""col-12"">
                                                <button class=""card-item-btn-add"">Add</button>
                                          ");
            WriteLiteral(@"  </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- <div class=""swiper-pagination""></div> -->
                        <div class=""swiper-button-prev""></div>
                        <div class=""swiper-button-next""></div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>
");
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
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<ShopWebApplication.Models.POCOS.ItemShopModel> Html { get; private set; }
    }
}
#pragma warning restore 1591