#pragma checksum "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Home\Index.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "4aeec27240c6102c66a9d229b7aaf143aeb1889c"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Home_Index), @"mvc.1.0.view", @"/Views/Home/Index.cshtml")]
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
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"4aeec27240c6102c66a9d229b7aaf143aeb1889c", @"/Views/Home/Index.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"ed0efb42ceab8be106663ef969b79685907bd9d4", @"/Views/_ViewImports.cshtml")]
    public class Views_Home_Index : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<dynamic>
    {
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
#nullable restore
#line 1 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Home\Index.cshtml"
  
    ViewData["Title"] = "Home";
    Layout = ViewData["Layout"] as String;

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n<div class=\"text-center\">\r\n    <h1 class=\"display-4\">");
#nullable restore
#line 7 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Home\Index.cshtml"
                     Write(ViewBag.Welcome);

#line default
#line hidden
#nullable disable
            WriteLiteral("</h1>\r\n    <p>");
#nullable restore
#line 8 "C:\Bene\Ecole\B3\SysDistribue\Labo\HEPL-SYSDIST-2020-2021\Frontend\ShopSln\ShopWebApplication\Views\Home\Index.cshtml"
  Write(ViewBag.Message);

#line default
#line hidden
#nullable disable
            WriteLiteral("</p>\r\n</div>");
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
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<dynamic> Html { get; private set; }
    }
}
#pragma warning restore 1591
