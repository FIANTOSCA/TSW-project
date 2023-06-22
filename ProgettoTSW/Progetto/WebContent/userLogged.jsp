<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
	response.sendRedirect("./prodotti");	
	return;
		}
	ProductBean prodotti= (ProductBean) request.getAttribute("prodotti");
	
	Cart cart = (Cart) request.getAttribute("cart");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,ton.unisa.ProductBean,ton.unisa.Cart"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="armi,fucili,pistole,coltelli,proiettili">
<meta name="description" content="Vendita di armi LEGALE *occhiolino* ">
<meta name="author" content="Iantosca Francesco e Baldo Alessandro">
<meta charset="UTF-8">
<meta http-equiv="refresh" content="3600">
<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link href="style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="ciao.css" type="text/css">
	<link href="val.css" rel="stylesheet" type="text/css">
	<link rel="icon" href="./immagini/favicon.ico" />
	
	<title>Ammu★Nation Database</title>
</head>

<body>
<div id="blocco">
<h1 id="ciro">Ammu★Nation</h1>
</div>

	<h2>Products</h2>
	
	<a href="prodotti">List</a>
	
	<table border="1">
		<tr>
			<td> <a href="prodotti?sort=ID">Sort</a> </td>
			<td>Nome <a href="prodotti?sort=NOME">Sort</a></td>
			<td>Categoria <a href="prodotti?sort=CATEGORIA">Sort</a></td>
			<td>Quantità</td>
			<td>Marca e modello</td>
			<td>Prezzo</td>
			<td>Opzioni</td>
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
		%>
		<tr>
			<td><%=bean.getID()%></td>
			<td><%=bean.getNOME()%></td>
			<td><%=bean.getCATEGORIA()%></td>
			<td><%=bean.getQTA_DISP()%></td>
			<td><%=bean.getMARCA_MOD()%></td>
			<td><%=bean.getPREZZO()%></td>
			<td><a href="prodotti?action=read&id=<%=bean.getID()%>">Details</a>
			<a href="prodotti?action=addC&id=<%=bean.getID()%>">Add to cart</a></td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">No products available</td>
		</tr>
		<%
			}
		%>
	</table> 
	
	<%
		if (prodotti != null) {
	%>
	<h2>Details</h2>
	<table border="1">
		
		<tr>
			<th>ID</th>
			<th>NOME</th>
			<th>CATEGORIA</th>
			<th>QUANTITA'</th>
			<th>MARCA/MODELLO</th>
			<th>PREZZO</th>
		</tr>
		<tr>
			<td><%=prodotti.getID()%></td>
			<td><%=prodotti.getNOME()%></td>
			<td><%=prodotti.getCATEGORIA()%></td>
			<td><%=prodotti.getQTA_DISP()%></td>
			<td><%=prodotti.getMARCA_MOD()%></td>
			<td><%=prodotti.getPREZZO()%></td>
			
		</tr>
	</table>
	<%
		}
	%>

	<% if(cart != null) { %>
		<h2>Carrello</h2>
		<table border="1">
		<tr>
			<th>Nome</th>
			<th>Marca e Modello</th>
			<th>Prezzo</th>
			<th>Azione</th>
		</tr>
		<% List<ProductBean> prodcart = cart.getProducts(); 	
		   for(ProductBean beancart: prodcart) {
		%>
		<tr>
			<td><%=beancart.getNOME()%></td>
			<td><%=beancart.getMARCA_MOD()%></td>
			<td><%=beancart.getPREZZO()%></td>
			<td><a href="prodotti?action=deleteC&id=<%=beancart.getID()%>">Elimina dal carrello</a></td>
		</tr>
		<%} %>
	</table>		
	<% } %>	
	<a href="home.html" id="abc"><button id="ab" class="btn" type="button">Per tornare alla Home</button></a>
</body>
</html>