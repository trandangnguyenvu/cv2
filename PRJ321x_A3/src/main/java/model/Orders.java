package model;

import java.sql.Date;
import java.util.List;

public class Orders {
	private int orderID;
	private float price;
	private int status;
	private Date orderDate;
	private String address;
	private String phonenumber;
	private List<ProductOrders> lp;
	private String userMail;
	private Date receivedDate;
	private String discount;
	
	
	
	public Orders(String address, String userMail) {
		super();
		this.address = address;
		this.userMail = userMail;
	}


	public Orders(int orderID, float price, int status, Date orderDate, String address, String phonenumber,
			List<ProductOrders> lp, String userMail, Date receivedDate, String discount) {
		super();
		this.orderID = orderID;
		this.price = price;
		this.status = status;
		this.orderDate = orderDate;
		this.address = address;
		this.phonenumber = phonenumber;
		this.lp = lp;
		this.userMail = userMail;
		this.receivedDate = receivedDate;
		this.discount = discount;
	}

	
	public Orders() {}
	

	public int getOrderID() {
		return orderID;
	}


	public float getPrice() {
		return price;
	}


	public int getStatus() {
		return status;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public String getAddress() {
		return address;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public List<ProductOrders> getLp() {
		return lp;
	}


	public String getUserMail() {
		return userMail;
	}


	public Date getReceivedDate() {
		return receivedDate;
	}


	public String getDiscount() {
		return discount;
	}


	


	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public void setLp(List<ProductOrders> lp) {
		this.lp = lp;
	}


	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}


	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}


	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	
	
	
	
}
