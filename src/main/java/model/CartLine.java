package model;

public class CartLine extends Products {
	String cartLineID, productID, userID, cartID;
	int Quantity;

	public CartLine() {
	}

	public CartLine(String cartLineID, 	String userID, String productID, String cartID,  int Quantity) {
		this.cartLineID = cartLineID;
		this.userID = userID;
		this.productID = productID;
		this.cartID = cartID;
		this.Quantity = Quantity;
	}

	public String getCartLineID() {
		return cartLineID;
	}

	public void setCartLineID(String cartLineID) {
		this.cartLineID = cartLineID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getCartID() {
		return cartID;
	}

	public void setCartID(String cartID) {
		this.cartID = cartID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}	
	
}
