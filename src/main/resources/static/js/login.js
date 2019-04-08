$("button#login-button").click( function() {
    $.ajax({
        url: "/login",
        type: "post",
        data: $("form#login-form").serialize(),
        success : function (response) {
            localStorage.setItem('token', "Bearer " + response);
            location.reload();
        }
    })
});
