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
                        <h2 style="text-align: center;"><b>Register</b></h2>
                        <p></p>
                    
                        
                        <form action="<%= response.encodeUrl(request.getContextPath() + "/Register?action=doregister") %>" class="was-validated" method="post">
                                                                      	
                        	
                            <input type="hidden" name="action" value="doregister">
                            <div class="mb-3 mt-3">
                                <label for="uname" class="form-label">User email:</label><br>
                                
                                
                                
                                
                                <!-- value="%= //session.getAttribute("usr") %" -->
                                <input type="text" id="uname" placeholder="Enter username" name="usr" value="<%=  
                                	session.getAttribute("usr") 
                                %>">
                    				<!-- uname -->
                    			
                    			
                    			
                    			
                    				
                                <p id="name-feedback">Please fill out this field.</p>
                            </div>
                    
                    
                            <div class="mb-3">
                                <label for="pwd" class="form-label">Password:</label><br>
                                <input type="password" id="pwd" placeholder="Enter password" name="pwd">
                    				<!-- pswd -->
                                <p id="pass-feedback">Please fill out this field.</p>
                                <%-- <h5 style="text-align:center"><%= session.getAttribute("error") %></h5> --%>
                            </div>
                            
                            
                            <div class="mb-3">
                                <label for="rpwd" class="form-label">Repeat password:</label><br>
                                <input type="password" id="rpwd" placeholder="Enter password again" name="rpwd">
                    				
                                <p id="pass-feedback">Please fill out this field.</p>
                                <%-- <h5 style="text-align:center"><%= session.getAttribute("error") %></h5> THIS --%>
                            </div>

							<div class="mb-3"> <%-- number --%>
								<label for="rl" class="form-label">Role:</label><br>
								<input type="number" id="rl"
									placeholder="Enter your role" name="rl">
								
								<p id="pass-feedback">Please fill out this field.</p>
								<p><b>1. Customer</b></p>
								<p><b>2. Admin</b></p>
								<%-- <h5 style="text-align:center"><%= session.getAttribute("error") %></h5> THIS --%>
							</div>

							<div class="mb-3">
								<label for="nm" class="form-label">User name:</label><br>
								<input type="text" id="nm"
									placeholder="Enter your name" name="nm">
								
								<p id="pass-feedback">Please fill out this field.</p>
								<%-- <h5 style="text-align:center"><%= session.getAttribute("error") %></h5> THIS --%>
							</div>

							<div class="mb-3">
								<label for="adrs" class="form-label">User address:</label><br>
								<input type="text" id="adrs"
									placeholder="Enter your address" name="adrs">
								
								
								<%-- <h5 style="text-align:center"><%= session.getAttribute("error") %></h5> THIS --%>
							</div>

							<div class="mb-3">
								<label for="phn" class="form-label">User phone:</label><br>
								<input type="number" id="phn" placeholder="Enter your phone"
									name="phn">


								<h5 style="text-align:center"><%= session.getAttribute("error") %></h5>
							</div>

							<div id="btn-div">
                                <button id="btn" type="submit" class="btn btn-primary"><b>Register</b></button>
                            </div>
                    


                    
                        </form>
                    </div>
    
    
                </div>



                <!-- col right-->
                <div class="col-12 col-sm-12 col-md-6 col-lg-6  bg-primary  text-white  p-3">
                <!--style="margin: auto;" => div thu hẹp bằng text area + giữa của col (top=bottom) -->
                    <div style="text-align: center;padding: 29% 0;"><!--border-style: solid;border-width: 2px;-->
                        <h1><b>Welcome!</b></h1>
                        
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