$(document).ready(function ()
{
    /** 确保页脚始终在页面最底部 **/
    var footer = $('.bs-docs-footer');
    var footer_height = footer.offset().top;
    var windows_height = window.screen.height;
    if (footer_height < windows_height - 100) {
        footer.css("margin-top", windows_height - footer_height - 100);
    }
});

