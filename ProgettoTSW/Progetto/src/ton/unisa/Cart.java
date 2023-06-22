package ton.unisa;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<ProductBean> products;
	
	public Cart() {
		products = new ArrayList<ProductBean>();
	}
	/*Funzione per aggiungere un prodotto al carrello*/
	public void addProduct(ProductBean prodotti) {
		products.add(prodotti);
	}
	/*Funzione per rimuovere un prodotto dal carrello*/
	public void deleteProduct(ProductBean prodotti) {
		for(ProductBean prod : products) {
			if(prod.getID() == prodotti.getID()) {
				products.remove(prod);
				break;
			}
		}
 	}
	/*Getter per i prodotti*/
	public List<ProductBean> getProducts() {
		return  products;
	}
}
