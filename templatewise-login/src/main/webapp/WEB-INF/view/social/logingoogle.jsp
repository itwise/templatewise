<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>google login page</title>

    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
    <script src="https://apis.google.com/js/api:client.js"></script>
    <script>
        var googleUser = {};
        var startApp = function() {
            gapi.load('auth2', function(){
                // Retrieve the singleton for the GoogleAuth library and set up the client.
                auth2 = gapi.auth2.init({
                    client_id: '507352471351-59orjjv4abv0vbedf2jupu8pr6509npd.apps.googleusercontent.com',
                    cookiepolicy: 'single_host_origin',
                    // Request scopes in addition to 'profile' and 'email'
                    //scope: 'additional_scope'
                });
                attachSignin(document.getElementById('customBtn'));
            });
        };

        function attachSignin(element) {
            console.log(element.id);
            auth2.attachClickHandler(element, {},
                    function(googleUser) {
                        document.getElementById('name').innerText = "Signed in: " +
                                googleUser.getBasicProfile().getName();
                    }, function(error) {
                        alert(JSON.stringify(error, undefined, 2));
                    });
        }
    </script>
    <style type="text/css">
        #customBtn {
            display: inline-block;
            background: #4285f4;
            color: white;
            width: 190px;
            border-radius: 5px;
            white-space: nowrap;
        }
        #customBtn:hover {
            cursor: pointer;
        }
        span.label {
            font-weight: bold;
        }
        span.icon {
            background: url('/identity/sign-in/g-normal.png') transparent 5px 50% no-repeat;
            display: inline-block;
            vertical-align: middle;
            width: 42px;
            height: 42px;
            border-right: #2265d4 1px solid;
        }
        span.buttonText {
            display: inline-block;
            vertical-align: middle;
            padding-left: 42px;
            padding-right: 42px;
            font-size: 14px;
            font-weight: bold;
            /* Use the Roboto font that is loaded in the <head> */
            font-family: 'Roboto', sans-serif;
        }
    </style>
</head>
<body>
<div id="gSignInWrapper">
    <span class="label">Sign in with:</span>
    <div id="customBtn" class="customGPlusSignIn">
        <span class="icon"></span>
        <span class="buttonText">Google</span>
    </div>
</div>
<div id="name"></div>
<script>startApp();</script>




<script type="text/javascript">
    (function() {
        var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
        po.src = 'https://apis.google.com/js/client:plusone.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
    })();

    function signinCallback(authResult) {
        if (authResult['access_token']) {
            // 승인 성공
            // 사용자가 승인되었으므로 로그인 버튼 숨김. 예:
            document.getElementById('signinButton').setAttribute('style', 'display: none');
        } else if (authResult['error']) {
            // 오류가 발생했습니다.
            // 가능한 오류 코드:
            //   "access_denied" - 사용자가 앱에 대한 액세스 거부
            //   "immediate_failed" - 사용자가 자동으로 로그인할 수 없음
            // console.log('오류 발생: ' + authResult['error']);
        }
    }
</script>

<span id="signinButton">
  <span
          class="g-signin"
          data-callback="signinCallback"
          data-clientid="507352471351-59orjjv4abv0vbedf2jupu8pr6509npd.apps.googleusercontent.com"
          data-cookiepolicy="single_host_origin"
          data-requestvisibleactions="http://schemas.google.com/AddActivity"
          data-scope="https://www.googleapis.com/auth/plus.login">
  </span>
</span>


<script type="text/javascript">
    function disconnectUser(access_token) {
        var revokeUrl = 'https://accounts.google.com/o/oauth2/revoke?token=' +
                access_token;

        // 비동기 GET 요청을 수행합니다.
        $.ajax({
            type: 'GET',
            url: revokeUrl,
            async: false,
            contentType: "application/json",
            dataType: 'jsonp',
            success: function(nullResponse) {
                // 사용자가 연결 해제되었으므로 작업을 수행합니다.
                // 응답은 항상 정의되지 않음입니다.
            },
            error: function(e) {
                // 오류 처리
                // console.log(e);
                // 실패한 경우 사용자가 수동으로 연결 해제하게 할 수 있습니다.
                // https://plus.google.com/apps
            }
        });
    }
    // 버튼 클릭으로 연결 해제를 실행할 수 있습니다.
    $('#revokeButton').click(disconnectUser);
</script>
</body>
</html>
