<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.dao.Item"%>
<%@ page import="java.util.Iterator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<title>Supplement Factory</title>
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
	<!--[if lte IE 6]><link rel="stylesheet" href="css/ie6.css" type="text/css" media="all" /><![endif]-->
	
	
	<!-- JS -->
	<script src="js/jquery-1.4.1.min.js" type="text/javascript"></script>	
	<script src="js/jquery.jcarousel.pack.js" type="text/javascript"></script>	
	<script src="js/jquery-func.js" type="text/javascript"></script>	
	<!-- End JS -->
	
</head>
<body>
	
<!-- Shell -->	
<div class="shell">
	
	<!-- Header -->	
	<div id="header">
		<h1 id="logo"><a href="#">Supplement Factory</a></h1>	
		
		<!-- Cart -->
		<div id="cart">
			<a href="#" class="cart-link">Your Shopping Cart</a>
			<div class="cl">&nbsp;</div>
			<span>Items in Cart: <strong>0</strong></span>
			&nbsp;&nbsp;
			<span>Total: <strong>$0.0</strong></span>
			&nbsp;&nbsp;<br>
			<span>Wallet Balance: <strong>$10.0</strong></span>
		</div>
		<!-- End Cart -->
		
		<!-- Navigation -->
		<div id="navigation">
			<ul>
			    <li><a href="/eCommerce" class="active">Home</a></li>
			    <li><a href="#">Support</a></li>
			    <li><a href="#">My Account</a></li>
				<li><a href="#">Sign Up!</a></li>
			    <li><a href="#">The Store</a></li>
			    <li><a href="#">Contact us</a></li>
			</ul>
		</div>
		<!-- End Navigation -->
	</div>
	<!-- End Header -->
	
	<!-- Main -->
	<div id="main">
		<div class="cl">&nbsp;</div>
		
		<!-- Content -->
		<div id="content">
							<div id="slider" class="box">
				<div id="slider-holder">
					<ul>			
										
					    <li><a href="#"><img src="https://res.cloudinary.com/sagarbigdataproject/image/upload/v1543262317/General/a1.gif" alt="" /></a></li>
						<li><a href="#"><img src="https://res.cloudinary.com/sagarbigdataproject/image/upload/v1543262317/General/a5.jpg" alt="" /></a></li>
						<li><a href="#"><img src="https://res.cloudinary.com/sagarbigdataproject/image/upload/v1543262318/General/main_banner.jpg" alt="" /></a></li>							
						<li><a href="#"><img src="https://res.cloudinary.com/sagarbigdataproject/image/upload/v1543262317/General/a4.gif" alt="" /></a></li>
						
					</ul>
				</div>
				<div id="slider-nav">
					<a href="#" class="active">1</a>
					<a href="#">2</a>
					<a href="#">3</a>
					<a href="#">4</a>
				</div>
			</div>
			
		<div class="cl">&nbsp;</div>
		<% List<Item> pList = (List<Item>)request.getAttribute("products2");%>
			<div >	
            <% if( pList != null)
                { 
            	for(int i =0; i< pList.size();i++)
          	  
                	{  %> 
                		<h2>             	                    
                        <a href="#"><%out.print(pList.get(i).item_name); %></a>
                        </h2> 
                         
                        <br><%String url="https://res.cloudinary.com/sagarbigdataproject/image/upload/v1543247740/"+ pList.get(i).item_id+".jpg";%> 
                        <p align="center"><img id="resultimg" src="<%=url%>"alt=""/>&nbsp;<p>
						
                        <br><strong class="price"> $<%out.print(pList.get(i).item_price); %></strong>
                        <h4><br>
                        <p>Product ID: <%out.print(pList.get(i).item_id); %></p>
                        <p>Brand: <%out.print(pList.get(i).item_brand); %></p>
                        <p>Shipping:<%out.print(pList.get(i).item_shipping); %></p></h4>
                        <p><%out.print(pList.get(i).item_description); %></p>
						<p align="center"><input type="submit" class="search-submit" value="Add to cart" /></p>
                        
                        <br>
                     
               	<%	}
            }
            else if (pList==null)
            {%>
            	<h2> No Records Found...</h2>
          <%  }
           %>
           </div>
           
           </div>
		<!-- End Content -->
		
		<!-- Sidebar -->
				<div id="sidebar">
		
		<div class="ncolBox" style="height:107px">
				<a href="#" class="imgBox" style="height:105px;"><img style="height:105px;width:170px;" ></a>
		</div>

			
			<!-- Search -->
			<div class="box search">
				<h2>Search by <span></span></h2>
				<div class="box-content">
					<form action="Search" method="post">
						
						<label>Keyword:</label>
						<input type="text" class="field" name="keyword" id="desc" placeholder="Enter Keyword Here ..."/>
						
						<label>Item ID:</label>
						<input type="text" class="field" name="itemid" id="pid" placeholder="Enter Item ID Here ..."/>
					
						<input type="submit" class="search-submit" value="Search" />
						<input type="submit" class="search-submit" value="Reset" />
						
						
					</form>
				</div>
			</div>
			<div id="hotItemsBox" class="ncolBox">
					  <div class="title red">Hot New Products</div>
					  <div class="clearCnt red">
						<ul>
						  <li><span>New </span><br/><a href="#">BeetFit Beet Root Powder by Fitness Labs</a></li>
						  <li><span>New </span><br/><a href="#">Keto C8 MCT Oil by Sports Research</a></li>
						  <li><span>New </span><br/><a href="#">Essential  AMIN.O. Energy +   Electrolytes RTD 12 Fl Oz by Optimum Nutrition</a></li>
						  <li><span>New </span><br/><a href="#">3 Egg Whites Protein Bar by NuGo Nutrition</a></li>
						  <li><span>New </span><br/><a href="#">Bang RTD 16 Fl Oz by VPX</a></li>
						  <li><span>New </span><br/><a href="#">PeptiFit Collagen Peptides Type 1 &amp; 3 by Fitness Labs</a></li>
						</ul>
						<a href="#">View all new products</a>
					  </div>
					</div>
			<!-- End Search -->
			<div class="box search">
				<h2>Send us a query! <span></span></h2>
				<div class="box-content">
					<form action="Search" method="post">
						
						<label>Name:</label>
						<input type="text" class="field" name="name" id="desc" placeholder="Enter name Here ..."/>
						
						<label>Contact #:</label>
						<input type="text" class="field" name="Contact" id="pid" placeholder="Enter contact Here ..."/>
						
						<label>Email Id:</label>
						<input type="text" class="field" name="email" id="pid" placeholder="Enter email ID Here ..."/>
						
						<label>Query:</label>
						<input type="text" class="field" name="query" id="pid" placeholder="Enter Query Here ..."/>
					
						<input type="submit" class="search-submit" value="Send" />
						<input type="submit" class="search-submit" value="Reset" />
						
						
					</form>
				</div>
			</div>
			
			<div class="box categories">
				<h2>Section: <span></span></h2>
				<div class="box-content">
					<ul>
					    <li><a href="#">Men Supplements</a></li>
					    <li><a href="#">Women Supplements</a></li>
					    <li><a href="#">Unisex Supplements</a></li>

					    
					</ul>
				</div>
				
				
			</div>
			<!-- Categories -->
			<div class="box categories">
				<h2>Categories: <span></span></h2>
				<div class="box-content">
					<ul>
					    <li><a href="#">Active Whey</a></li>
					    <li><a href="#">Mass Gainers</a></li>
					    <li><a href="#">Testo Boosters</a></li>
					    <li><a href="#">Meto Products</a></li>
					    <li><a href="#">Fat Burners</a></li>
					    
					</ul>
				</div>
				
				
			</div>
			
			<div class="box categories">
				<h2>Price Range: <span></span></h2>
				<div class="box-content">
					<ul>
					    <li><a href="#">0.00-100 USD</a></li>
					    <li><a href="#">100-250 USD</a></li>
					    <li><a href="#">250-500 USD</a></li>
					    <li><a href="#">500-1000 USD</a></li>
					    <li><a href="#">1000- Max</a></li>
					    
					</ul>
			</div>
				
				
			</div>
			
			<div class="box categories">
				<h2>Discount Category: <span></span></h2>
				<div class="box-content">
					<ul>
					    <li><a href="#">10% Off</a></li>
					    <li><a href="#">20% Off</a></li>
					    <li><a href="#">30% Off</a></li>
					    <li><a href="#">50% Off</a></li>
					    <li><a href="#">70% and above</a></li>
					    
					</ul>
			</div>											
			</div>
			
			<!-- End Categories -->
			
		</div>
		<!-- End Sidebar -->
		
		<div class="cl">&nbsp;</div>
	</div>
	<!-- End Main -->
	

	
	<!-- Footer -->
	<div id="footer">
		<p class="left">
			<a href="#">Careers</a>
			<span>|</span>
			<a href="#">Donate</a>
			<span>|</span>
			<a href="#">Help</a>
			<span>|</span>
			<a href="#">The Store</a>
			<span>|</span>
			<a href="#">Locations</a>
		</p>
		<p class="right">
			&copy; 2018 Supplement Factory 
		</p>
	</div>
	<!-- End Footer -->
	
</div>	
<!-- End Shell -->
	
	
</body>
</html>