/*google 认证*/
var googleUser = {};
var startApp = function() {
    gapi.load('auth2', function(){
        // Retrieve the singleton for the GoogleAuth library and set up the client.
        auth2 = gapi.auth2.init({
            client_id: '662426624378-p313vqiou5mhj80coqfkdacalmg5ddfk.apps.googleusercontent.com',
            cookiepolicy: 'single_host_origin',
            // Request scopes in addition to 'profile' and 'email'
            //scope: 'additional_scope'
        });
        attachSignin(document.getElementById('googleLogin'));
    });
};
function attachSignin(element) {
    console.log(element.id);
    auth2.attachClickHandler(element, {},
        function(googleUser) {
            var profile = googleUser.getBasicProfile();
            var userOb = {};
            userOb.userSid = profile.getId();
            userOb.loginName =profile.getEmail();
            userOb.userName = profile.getName();
            userOb.userImage = profile.getImageUrl();
            userOb.userEmail = profile.getEmail();
            userOb.userSource = "1";//google
            userOb.userType = "1";//需求客户
            userLogin.userOauth(userOb);
        }, function(error) {
            alert("login failed !");
        });
}

/*facebook 认证*/
// This is called with the results from from FB.getLoginStatus().
function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
        // Logged into your app and Facebook.
        //testAPI();
    } else {
        console.log("登录失败 ！")
           }
}
// This function is called when someone finishes with the Login
// Button.  See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });
}

window.fbAsyncInit = function() {
    FB.init({
        appId      : '394004374503210',
        cookie     : true,  // enable cookies to allow the server to access
                            // the session
        xfbml      : true,  // parse social plugins on this page
        version    : 'v3.2' // The Graph API version to use for the call
    });
    // Now that we've initialized the JavaScript SDK, we call
    // FB.getLoginStatus().  This function gets the state of the
    // person visiting this page and can return one of three states to
    // the callback you provide.  They can be:
    //
    // 1. Logged into your app ('connected')
    // 2. Logged into Facebook, but not your app ('not_authorized')
    // 3. Not logged into Facebook and can't tell if they are logged into
    //    your app or not.
    //
    // These three cases are handled in the callback function.

    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });

};
// Load the SDK asynchronously
(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
$(function(){
    $("#facebookLogin").click(function(){
        alert()
        FB.login(function(response) {
            if (response.status === 'connected') {
                FB.api('/me', function(response) {
                    console.log('Successful login for: ' + response.name);
                });
            } else {
                console.log('该用户没有登录');
            }
        }, {scope: 'public_profile,email'});
    });
});