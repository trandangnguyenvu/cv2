
<%-- SHOWING ALL PRODUCT --%>

<div class="container-fluid mt-3">
	<div class="container-fluid">
		<div class="row"> <%-- row --%>



			<%@ page import="java.util.*,model.*,dao.*"%>
			<%  
			String spageid=request.getParameter("page");  
			int pageid=Integer.parseInt(spageid);  
			int offset = 0;
			int total=6;
			
		
			if(pageid==1){}  
			else{
					// mySQL	
			    //pageid=pageid-1;  
			    //pageid=pageid*total+1; 
			    
					// sqlServer	
				offset = (pageid - 1)*total;
			}
			
			
		
			
			List<Product> list=ListProductDAO.getRecords(offset,total);  
			  
			out.print("<h5>Page No: "+spageid+"</h5>");  
			//TABLE out.print("<table border='1' cellpadding='4' width='60%'>");			
			//TABLE-TH : out.print("<tr><th>Id</th><th>Name</th><th>des</th><th>price</th><th>src</th><th>type</th><th>brand</th></tr>");  
			
			for(Product e:list){  
				//class='product-image'-- style='display:block;width:auto;height:auto;margin-left: auto;margin-right: auto;' -- style='padding-left:50px'
			    out.print("<div class='col-12 col-sm-12 col-md-4 col-lg-4 p-3'><a href='Controller?action=image&image=" + e.getId() +"'><img  style='display:block;width:100%;height:auto;margin-left: auto;margin-right: auto;' src='" + e.getSrc() + "'/></a><div>" + "<p>" + e.getType() +"</p>"+  "<p>" + e.getName()+ "</p>" + "<p>$ " + e.getPrice()+"</p></div></div>");  
			}  
			//TABLE out.print("</table>");  
			%>
			
			<div style="text-align: center;">
				<a href="home.jsp?page=1">1</a><span> | </span>
				<a href="home.jsp?page=2">2</a><span> | </span>
				<a href="home.jsp?page=3">3</a>		
			</div>

			
		</div> <%-- row --%>
	</div>
</div>

