 function getUser(user) {
        var url = "/wall/"+user;
        $.ajax({
            url: url,
            type: 'GET',
            beforeSend: function (xhr) {   //Include the bearer token in header
                xhr.setRequestHeader("Authorization", localStorage.getItem('token'));
            },
            success: function (resp) {
                replaceWallView(resp);
            },
        });
    };

    function replaceWallView(resp) {
        var wallView = $(".right-column");
        wallView.fadeOut(200, function(){
            wallView
                .hide()
                .html(resp)
                .fadeIn(200, function() {
                    wallView.animate({
                        height: 0 + wallView.height() + "px"
                    });
                });
        });
    };