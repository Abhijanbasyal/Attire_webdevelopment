package model;

public class Cart {
	String cartID, userID;
	Boolean is_CheckedOut;

	public Cart() {
	}

	public Cart(String cartID, 	String userID, Boolean is_CheckedOut) {
		this.cartID = cartID;
		this.userID = userID;
		this.is_CheckedOut = is_CheckedOut;
	}

	public String getCartID() {
		return cartID;
	}

	public void setCartID(String cartID) {
		this.cartID = cartID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Boolean getIs_CheckedOut() {
		return is_CheckedOut;
	}

	public void setIs_CheckedOut(Boolean is_CheckedOut) {
		this.is_CheckedOut = is_CheckedOut;
	}
	
	
	
	
}
