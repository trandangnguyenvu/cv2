<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>pickaboo</title>
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Dongle&family=Roboto:wght@100&display=swap">
  
    <link rel="stylesheet" type="text/css" href="css.css" />




    <style>
        /*div, ul {
            border-style: solid; border-width: 1px;                        
        }

        div {
            border-color: aquamarine;
        }

        ul {
            border-color: brown;
        }
        */

        .nav-link {
            font-weight: bold;
            color: rgb(203, 214, 224);
        }

        #a {
            position: relative;
        }
        #b {
            position: absolute;
            top: 8px;
            right: 16px;
        }  


    </style>

</head>






<body>
   



    






    


   
<!--BODY -->
<div class=""><!--container-fluid  mt-3-->
    <div class=""><!--container-fluid-->
        <div class="row">




            <!-- LEFT-->
            <div class="col-12 col-sm-12 col-md-2 col-lg-2 p-3 " style="background-color: rgba(4, 5, 26, 0.768);"><!--bg-dark-->
                <h6 style="color: bisque;text-align: center;">Welcome <%               	
     
                	String s = (String)session.getAttribute("usr");
				
					int idx = s.indexOf("@"); 
					String result = s.substring(0,idx);
				
					out.println(result);
                %></h6>				



				
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Staff Manager</a>
                    </li>

                </ul>
                
                <!-- <p style="color: white;">
                %= 
                session.getAttribute("rem")
                %
                </p>-->


            </div>



            


            <!--RIGHT-->
            <div class="col-12 col-sm-12 col-md-10 col-lg-10 p-3 bg-dark" >
                      

                <div id="a" >
                    <img src="${pageContext.request.contextPath}/image/background.jpg" style="width: 100%;" >

                    <div style="text-align: right" id="b">
                        
                        <a class="nav-link" href="/PRJ321x_A3/Logout?action=logout">Logout</a>
                    </div>
                </div>
 

                <div class="container mt-3"><!--container mt-3-->
                    
                    
                    <table class="table table-dark table-hover" style="width:55%; ">
                        <thead>
                            <tr>
                                <th colspan="4" style="background-color: blueviolet;">Members of team</th>
                            </tr>
                                
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Student ID</th>
                                <th>Class</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>Member 1</td>
                                <td>Member Code 1</td>
                                <td>Class 1</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>Member 2</td>
                                <td>Member Code 2</td>
                                <td >Class 2</td>
                            </tr>

                        </tbody>
                    </table>
                </div>

            </div>


            
           
        </div>
    </div>
    <!--=> BODY -->





</body>




</html>