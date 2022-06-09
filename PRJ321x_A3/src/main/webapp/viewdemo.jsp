<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.util.*,model.*,dao.*"%>
	<%  
	String spageid=request.getParameter("page");  
	int pageid=Integer.parseInt(spageid);  
	int offset = 0;
	int total=4;
	

	if(pageid==1){}  
	else{
			// mySQL	
	    //pageid=pageid-1;  
	    //pageid=pageid*total+1; 
	    
			// sqlServer	
		offset = (pageid - 1)*total;
	}
	
	

	
	List<Product> list=ListProductDAO.getRecords(offset,total);  
	  
	out.print("<h1>Page No: "+spageid+"</h1>");  
	out.print("<table border='1' cellpadding='4' width='60%'>");  
	out.print("<tr><th>Id</th><th>Name</th><th>des</th><th>price</th><th>src</th><th>type</th><th>brand</th></tr>");  
	for(Product e:list){  
	    out.print("<tr><td>"+e.getType()+"</td><td>"+e.getName()+"</td><td>"+e.getPrice()+"</td></tr>");  
	}  
	out.print("</table>");  
	%>
	
	<a href="viewdemo.jsp?page=1">1</a>
	<a href="viewdemo.jsp?page=2">2</a>
	<a href="viewdemo.jsp?page=3">3</a>
</body>
</html>