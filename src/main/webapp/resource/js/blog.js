$(document).ready(function ()
{
    var deleteBlog = $("#delete-blog");

    /** 点击删除，使用 ajax 传输 blogid，并将这个微博隐藏 **/
    deleteBlog.on("click", function ()
    {
        var blogid = deleteBlog.attr("blogid");
        var blogDiv = $("#blog-" + blogid.toString());
        $.ajax({
            url: "deleteBlog.html",
            type: "post",
            dataType: "text",
            data: {
                blogId: blogid
            },
            success: function ()
            {
                blogDiv.hide(200);
            }
        });
    })
});