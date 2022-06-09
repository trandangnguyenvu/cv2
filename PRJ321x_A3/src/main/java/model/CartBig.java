package model;

import java.util.ArrayList;
import java.util.List;

public class CartBig {

	private List<Cart> items;
	
	
	
	public CartBig() {
		items = new ArrayList<Cart>();
	}
	
	
	public void add(Cart c) {
		items.add(c);	
	}
	
	public void clear() {
		items.clear();
		
	}
	
	//return list of products in cart
	public List<Cart> getItems () {
		return items;
	}
	
	public double getAmount() {
		double total=0;
		for (Cart c:items) {
			total = c.getAmount();
		}
		return total;
	}
	
}
