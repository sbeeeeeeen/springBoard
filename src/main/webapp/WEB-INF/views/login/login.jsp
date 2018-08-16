<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/bootstrap/css/signin.css" rel="stylesheet">
    <%@include file="/WEB-INF/views/common/jquery.jsp" %>
    <script>
    	function getCookie(name){
    		var cookies = document.cookie;
			var cookieArr = cookies.split("; ");
			var cookieResult = "";    
			
			for(var i in cookieArr){
				var cookie = cookieArr[i];
				var cookieNameValue = cookie.split("=");
				
				var cookieName = cookieNameValue[0];
				var cookieValue = cookieNameValue[1];
				
				if(name == cookieName){
					cookieResult = cookieValue;
					break;
				}
			}
			return cookieResult;
    	}
    	
    	function setCookie(cookieName, cookieValue, expires){
    		var dt = new Date();
			dt.setDate(dt.getDate() + parseInt(expires));
			document.cookie = cookieName + "=" + cookieValue 
				+ "; path=/; expries=" + dt.toGMTString();
    	}
    	
    	function deleteCookie(cookieName){
    		setCookie(cookieName, "", -1);
    	}
    	
    	//문서 로딩 완료 후 실행
    	$(function(){
	    	if(getCookie("rememberMe")=="y"){
	    		//userId input에 userId cookie값을 설정
	    		$("#std_id").val(getCookie("std_id"));
				$("#rememberMe").attr("checked",true);	
				$("#rememberMe").val(getCookie("rememberMe"));
	    	}
	    	
	    	//rememberMe 체크박스 클릭 이벤트
	    	$("#rememberMe").on("click",function(){
	    		if($(this).is(":checked")){
	    			//rememberMe cookie 설정
	    			setCookie("rememberMe","y",30); //30일동안 유효하게
	    			setCookie("std_id", $("#std_id").val(), 30);
	    		}else{
	    			//쿠키 제거
	    			deleteCookie("rememberMe");
	    		}
	    	});
    	});
    </script>
  </head>

  <body>
    <div class="container">
      <form class="form-signin" action="/login/stdLogin" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        
        <label for="std_id" class="sr-only">std_id</label>
        <input type="text" id="std_id" name="std_id" class="form-control" 
        placeholder="std_id" required autofocus value="${param.std_id}">
        
        <label for="password" class="sr-only">Password</label>
        <input type="password" id="pass" name="pass" class="form-control" placeholder="Password" required>
        
        <div class="checkbox">
          <label>
            <input type="checkbox" id="rememberMe" name="rememberMe" value=""> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->
  </body>
</html>
