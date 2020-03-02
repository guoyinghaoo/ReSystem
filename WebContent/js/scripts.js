
$(document).ready(function() { //  加载JS后，会自动执行

    /*
        Background slideshow  实现图片轮播
    */
    $.backstretch([
      "img/backgrounds/1.jpg"
    , "img/backgrounds/2.jpg"
    , "img/backgrounds/3.jpg"
    ], {duration: 3000, fade: 750});
    /*
        Tooltips
    */
    $('.links a.home').tooltip();  // 实现鼠标移上去显示标签
    $('.links a.blog').tooltip();  // 实现鼠标移上去显示标签

});
    /*
        Form validation
    */
    $('.register form').submit(function(){
        $(this).find("label[for='username']").html('用户名');
        $(this).find("label[for='email']").html('邮箱');
        $(this).find("label[for='password']").html('密码');
        $(this).find("label[for='sex']").html('性别');
        $(this).find("label[for='address']").html('地址');
        var username = $(this).find('input#username').val();
        var email = $(this).find('input#email').val();
        var password = $(this).find('input#password').val();
        var sex = $(this).find('input#sex').val();
        var address = $(this).find('input#address').val();
        if(username == '') {
            $(this).find("label[for='username']").append("<span style='display:none' class='red'> - Please enter your user name.</span>");
            $(this).find("label[for='username'] span").fadeIn('medium');
            return false;
        }
        if(email == '') {
            $(this).find("label[for='email']").append("<span style='display:none' class='red'> - Please enter a valid email.</span>");
            $(this).find("label[for='email'] span").fadeIn('medium');
            return false;
        }
        if(password == '') {
            $(this).find("label[for='password']").append("<span style='display:none' class='red'> - Please enter a valid password.</span>");
            $(this).find("label[for='password'] span").fadeIn('medium');
            return false;
        }
        if(sex == '') {
            $(this).find("label[for='sex']").append("<span style='display:none' class='red'> - Please enter your sex.</span>");
            $(this).find("label[for='sex'] span").fadeIn('medium');
            return false;
        }
        if(address == '') {
            $(this).find("label[for='address']").append("<span style='display:none' class='red'> - Please enter a valid address.</span>");
            $(this).find("label[for='address'] span").fadeIn('medium');
            return false;
        }
    });

    // Login Register页面
    $(".login form  button[type='button']").click(function(){
        $('.login').fadeOut('medium',function loginShow() {
            $('.register').fadeIn('medium');
        });
    });



