$(document).ready(function ()
{
    /** 全局变量 **/
    var MyApp = {};
    MyApp.HEAD_PIC_NUM = 4;

    var userNameInput = $("input[name=userName]");

    var headPicImg = $("img.headpic");

    /** 输入完用户名之后，获取用户头像，并替换 **/
    userNameInput.focusout(function ()
    {
        $.ajax({
            url: "getUserHeadPic.html",
            type: "post",
            dataType: "text",
            data: {
                userName: userNameInput.val()
            },
            success: function (headpic)
            {
                replaceHeadPic(headpic);
            },
            error: function (data, type, err)
            {
                console.log(data);
                console.log(type);
                console.log(err);
            }
        });
    });

    /** 点击替换头像事件 **/
    headPicImg.on("click", function ()
    {
        var headpicInput = $("input[name=headpic]");
        if (headpicInput.val() !== undefined) {
            var newHeadPic = (parseInt(headpicInput.val())) % MyApp.HEAD_PIC_NUM + 1;
            headpicInput.val(newHeadPic);
            replaceHeadPic(newHeadPic);
        }
    });

    function replaceHeadPic(newHeadPic)
    {
        var src = headPicImg.attr("src");
        var reg = new RegExp("/\\d\\.png", "g");
        src = src.replace(reg, "/" + newHeadPic + ".png");
        headPicImg.attr("src", src);
    }
});