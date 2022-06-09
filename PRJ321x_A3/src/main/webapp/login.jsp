 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login 1</title>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        .error {
            font-weight: bold;
            color: red;
        }

        .true {
            font-weight: normal;
            color: green;
        }
        input {
            width: 100%;
        }
        #btn{width: 150px;}
        #btn-div{text-align: center;}
    </style>

</head>




<body>
    <div class="container-fluid mt-3">
        <div class="container-fluid">
            <div class="row">
    
                <!-- col left-->
                <div class="col-12 col-sm-12 col-md-6 col-lg-6 p-3" style="background-color: azure;"> <!--bg-primary text-white-->

    
                    <div class="container mt-3">
                        <h2 style="text-align: center;"><b>Sign in</b></h2>
                        <p></p>
                    
                        
                        <form action="<%= response.encodeUrl(request.getContextPath() + "/Login?action=dologin&page=1") %>" class="was-validated" method="post">
                        <%-- <form action="/PRJ321x_A3/Login" class="was-validated" method="post"> without encodeUrl --%>                                              	
                        	
                            <input type="hidden" name="action" value="dologin">
                            <div class="mb-3 mt-3">
                                <label for="uname" class="form-label">Username:</label><br>
                                
                                
                                
                                
                                <!-- value="%= //session.getAttribute("usr") %" -->
                                <input type="text" id="uname" placeholder="Enter username" name="usr" value="<%  
        								//out = response.getWriter();(just servlet need this)
            							Cookie[] cookies = request.getCookies();
            							
            							if (cookies == null) {
            								out.println("Enter your email as username.");
            							} else {
            								for(Cookie e:cookies) {									
            									if (e.getName().equals("email")) {
            										out.println(e.getValue());
            									}            									
            								}
            							}
                                %>">
                    				<!-- uname -->
                    			
                    			
                    			
                    			
                    				
                                <p id="name-feedback">Please fill out this field.</p>
                            </div>
                    
                    
                            <div class="mb-3">
                                <label for="pwd" class="form-label">Password:</label><br>
                                <input type="password" id="pwd" placeholder="Enter password" name="pwd">
                    				<!-- pswd -->
                                <p id="pass-feedback">Please fill out this field.</p>
                                <h5 style="text-align:center"><%= session.getAttribute("error") %></h5>
                            </div>
                    
                            <div id="btn-div">
                                <button id="btn" type="submit" class="btn btn-primary"><b>Login</b></button>
                            </div>
                    
                            <div class="form-check mb-3" style="padding-top:22px;">
                                <input class="form-check-input" type="checkbox" id="myCheck" name="remember" value="rememberme">
                                <label class="form-check-label" for="myCheck">Remember me</label>
                    
                            </div>
                            <div style="background-color: rgb(238, 237, 236);padding-top:5px;position: relative;height: 40px;">
                    
                                <div style="width: 50%;margin: 0px;padding: 0px;position: absolute;">
                                    <button type="button" style="background-color: brown;">Cancel</button>
                                </div>
                    
                                <div style="text-align: right;width:50%;margin: 0px;padding: 0px;position: absolute;right: 0px;">
                                    <b><span>Forgot </span><a href="#">password?</a></b>
                                </div>
                    
                            </div>
                    
                        </form>
                    </div>
    
    
                </div>



                <!-- col right-->
                <div class="col-12 col-sm-12 col-md-6 col-lg-6  bg-primary  text-white  p-3">
                <!--style="margin: auto;" => div thu hẹp bằng text area + giữa của col (top=bottom) -->
                    <div style="text-align: center;padding: 29% 0;"><!--border-style: solid;border-width: 2px;-->
                        <h1><b>Welcome back!</b></h1>
                        <h4>To keep connected with us<br/>please login with your personal info</h4>
                    </div>
                </div>
                    
            </div>
        </div>
    </div>



        






    <script>
        $(document).ready(function () {
            var form = $("form");
            var name = $("#uname");
            var pass = $("#pwd");
            var nfb = $("#name-feedback");
            var pfb = $("#pass-feedback");



            name.keyup(validateName);
            pass.keyup(validatePass);


            // form submit
            form.submit(function () {
                if (validateName() && validatePass()) {
                    return true;
                } else {
                    return false;
                }
            });


            // validateName
            function validateName() {
                if (name.val().length < 1) {
                    nfb.removeClass("true");
                    nfb.addClass("error");
                    nfb.text("Please fill out this field.");
                    return false;
                } else {
                    nfb.addClass("true");
                    nfb.text("Your username is valid.");
                    return true;
                }
            }
            // validateEmail at 6 min 15 sec FROM VIDEO, var regexp = ; if regexp.test-email- 
            // (Phần 4 - jQuery nâng cao  Bài 15 - Chuẩn hóa dữ liệu Form bằng jQuery  Video - Chuẩn hóa dữ liệu Form - f2) 



            //validatePassword
            function validatePass() {
                if (pass.val().length < 3) {
                    pfb.removeClass("true");
                    pfb.addClass("error");
                    pfb.text("Please fill out this field.");
                    return false;
                } else {
                    pfb.addClass("true");
                    pfb.text("Your password is valid.");
                    return true;
                }
            }
            // cofirm pass, 11min 2 sec FROM VIDEO, if pass1.val-- !== pass2.val-- 
            // (Phần 4 - jQuery nâng cao  Bài 15 - Chuẩn hóa dữ liệu Form bằng jQuery  Video - Chuẩn hóa dữ liệu Form - f2) 

        });

    </script>

</body>

</html>