<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Pagina amministratore, viene visualizzato il carrello e la gestione prodotti -->
<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./admin");	
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
	<link rel="icon" href="./immagini/favicon.ico" />

	<title>Ammu★Nation Catalogo</title>
</head>

<body>
<div id="blocco">
<h1 id="ciro">Ammu★Nation</h1>
</div>

	<h2>Prodotti</h2>
	<a href="prodotti">Lista</a>
	<table border="1">
		<tr>
			<th>ID <a href="admin?sort=ID">Sort</a></th>
			<th>Nome <a href="admin?sort=NOME">Sort</a></th>
			<th>Categoria <a href="admin?sort=CATEGORIA">Sort</a></th>
			<th>Quantità</th>
			<th>Marca e modello</th>
			<th>Prezzo</th>
			<th>Opzioni</th>
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
			
			
			<td><a href="admin?action=delete&id=<%=bean.getID()%>">Delete</a><br>
				<a href="admin?action=read&id=<%=bean.getID()%>">Details</a>
				<a href="admin?action=addC&id=<%=bean.getID()%>">Add to cart</a></td>
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
	<h2>Inserimento</h2>
	
	<form action="admin" method="post">
		<input type="hidden" name="action" value="insert"> 
		
		<label for="name">Nome:</label>
		<input name="name" type="text" maxlength="20" required placeholder="Inserisci il nome"><br> 
		
		<label for="description">Categoria:</label><br>
		<textarea name="description" maxlength="100" rows="3" required placeholder="Inserisci Categoria"></textarea><br>
		
		<label for="quantity">Quantità:</label>
		<input name="quantity" type="number" min="0" value="0" required><br>

		<label for="mar_mod">Marca e modello:</label>
		<input name="mar_mod" type="text" maxlength="20" required placeholder="Inserisci la Marca e il Modello"><br> 

		<label for="price">Prezzo:</label>
		<input name="price" type="number" min="0" value="0" required><br>
		
		
		<input type="submit" value="Aggiungi"><input type="reset" value="Reset">

	</form>
		<% if(cart != null) { %>
		<h2>Cart</h2>
		<table border="1">
		<tr>
			<th>Name</th>
			<th>Action</th>
		</tr>
		<% List<ProductBean> prodcart = cart.getProducts(); 	
		   for(ProductBean beancart: prodcart) {
		%>
		<tr>
			<td><%=beancart.getNOME()%></td>
			<td><a href="prodotti?action=deleteC&id=<%=beancart.getID()%>">Delete from cart</a></td>
		</tr>
		<%} %>
	</table>		
	<% } %>	
</body>
</html>