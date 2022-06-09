package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<Product> items;
	
	
	
	public Cart() {
		items = new ArrayList<Product>();
	}
	
	
	//add a new product to cart
	//'number' is the quantity of a product
	public void add(Product ci) {
		for (Product x:items) {
			if (ci.getId() == x.getId()) {
				x.setNumber(x.getNumber() + 1);
				return;
			}
		}
		items.add(ci);
		//ci.setNumber(1);
	}
	
	
	//remove a produc from a cart
	public void remove(int id) {
		for (Product x:items) {
			if (x.getId() == id) {
				items.remove(x);
				return;
			}
		}
	}
	
	
	public void clear() {		
		items.clear();		
	}
	
	
	// return total amount of cart
	public double getAmount() {
		double s = 0;
		for (Product x:items) {
			s += x.getPrice() * x.getNumber();
		}
		return Math.round(s * 100) / 100;
		// MATH.ROUND()
	}
	
	
	// return number of products in cart
	public int getNumber() {
		int s = 0;
		for (Product x:items) {
			s += x.getNumber();
		}
		return s;
	}
	
	//return list of products in cart
	public List<Product> getItems () {
		return items;
	}
	
	
}
