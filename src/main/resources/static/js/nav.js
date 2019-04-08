$(document).ready(function() {
    // REFRESHES navbar
    var logInButton = $('nav ul li#login');
    var signUpButton = $('nav ul li#register');
    var allUsersButton = $('nav ul li#users');
    $.ajax({
        url: "/2Z5SIQQUHCWO2NJU1KX5",
        type: 'GET',
        beforeSend: function (xhr) {   //Include the bearer token in header
            xhr.setRequestHeader("Authorization", localStorage.getItem('token'));
        },
        success: function (data) {
            if (data) {
                logInButton.detach();
                signUpButton.attr('id', 'signout');
                signUpButton.html('Sign Out');
            } else {
                allUsersButton.detach();
                $('nav-list').append(logInButton);
                signUpButton.attr('id', 'register');
                signUpButton.html('Sign Up');
            }
        },
        error: function(){
            localStorage.removeItem('token');
            allUsersButton.detach();
            $('nav-list').append(logInButton);
            signUpButton.attr('id', 'register');
            signUpButton.html('Sign Up');
            alert("You've been logged out!")
        }
    });

    $("nav ul li").click(function(){

        var navList = $('ul#nav-list')
        var active = $("nav ul li.active");

        $("nav ul li").not(this).removeClass("active");
        $(this).addClass("active");
        active = $("nav ul li.active");
        getResult(active);

        var activeIndex = $('.active').index('nav ul li:visible');
        var offsetPerButton = navList.data('button-width');

        $("#nav-slide").stop().animate({left: activeIndex * offsetPerButton + 'px'}, 500, "easeInOutExpo");
    })
});

function replaceMainContent(response) {
    var mainContent = $('main.container');
    mainContent.fadeOut(200, function(){
        mainContent
            .hide()
            .html(response)
            .fadeIn(200, function() {
                mainContent.animate({
                    height: 0 + mainContent.height() + "px"
                });
            });
    })
}

function getResult(active) {
    var selectedId = $(active).attr('id');
    var url = "";
    switch (selectedId) {
        case "home":
            url = "/home";
            break;
        case "users":
            url = "/users";
            break;
        case "register":
            url = "/signup";
            break;
        case "signout":
            localStorage.removeItem('token');
            location.reload();
            return;
        case "login":
            url = "/login"
            break;
        default:
            url = "/home"
            break;
    }
    $.ajax({
        url: url,
        type: 'GET',
        beforeSend: function (xhr) {   //Include the bearer token in header
            xhr.setRequestHeader("Authorization", localStorage.getItem('token'));
        },
        success: function (resp) {
            replaceMainContent(resp);
        },
    });
}
