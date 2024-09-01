package model;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Part;

import resources.MyConstants;

public class Products {
	String productID, productName, description, brand, category, imageUrlFromPart, imageUrl;
	BigDecimal price, discount, rating;
	int stock, quantity;
	Boolean does_exists;

	public Products() {
	}

	public Products(String productID, String productName, String description, BigDecimal price, BigDecimal discount,
			int stock, String brand, String category, BigDecimal rating, Part part, Boolean does_exists,
			String imageUrl, int quantity) {
		this.productID = productID;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.brand = brand;
		this.category = category;
		this.rating = rating;
		this.imageUrlFromPart = getImageUrl(part, productName);
		this.does_exists = does_exists;
		this.imageUrl = imageUrl;
		this.quantity = quantity;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}

	public void setImageUrlFromPart(String imageUrlFromPart) {
		this.imageUrlFromPart = imageUrlFromPart;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Boolean getDoes_exists() {
		return does_exists;
	}

	public void setDoes_exists(Boolean does_exists) {
		this.does_exists = does_exists;
	}

	private String getImageUrl(Part part, String productName) {
		 if (part == null) {
		        return "download.png";
		    } else {
		    	String savePath = MyConstants.PRODUCT_IMAGE_DIR_SAVE_PATH;
				File fileSaveDir = new File(savePath);
				String imageUrlFromPart = null;
				if (!fileSaveDir.exists()) {
					fileSaveDir.mkdir();
				}
				String contentDisp = part.getHeader("content-disposition");
				String[] items = contentDisp.split(";");
				for (String s : items) {
					if (s.trim().startsWith("filename")) {
						imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
					}
				}
				if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
					imageUrlFromPart = "download.png";
				} else {
					String extension = imageUrlFromPart.substring(imageUrlFromPart.lastIndexOf("."));
					String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
					imageUrlFromPart = productName + "_" + timestamp + extension;
				}
				return imageUrlFromPart;
		    }
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
