<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register.css" />
</head>
<body>

	<div class="wrapper">
		<div class="add_product_form">
			<div class="title">Add Product</div>

			<form action="${pageContext.request.contextPath}/addProducts"
				method="post" enctype="multipart/form-data">
				<div class="form_wrap">
					<div class="input_wrap">
						<label for="productName">Product Name</label> <input type="text"
							id="productName" name="productName">
					</div>
					<div class="input_wrap">
						<label for="description">Description</label>
						<textarea id="description" name="description"></textarea>
					</div>
					<div class="input_wrap">
						<label for="price">Price</label> <input type="text" id="price"
							name="price">
					</div>
					<div class="input_wrap">
						<label for="discount">Discount</label> <input type="text"
							id="discount" name="discount" value="0">
					</div>
					<div class="input_wrap">
						<label for="stock">Stock</label> <input type="text" id="stock"
							name="stock">
					</div>
					<div class="input_wrap">
						<label for="brand">Brand</label> <input type="text" id="brand"
							name="brand">
					</div>
					<div class="input_wrap">
						<label for="category">Category</label> <select id="category"
							name="category">
							<option value="Tops">Tops</option>
							<option value="Bottoms">Bottoms</option>
							<option value="Dresses">Dresses</option>
							<option value="Outerwear">Outerwear</option>
							<option value="Activewear">Activewear</option>
							<option value="Swimwear">Swimwear</option>
							<option value="Undergarments">Undergarments</option>
							<option value="Accessories">Accessories</option>
						</select>

					</div>
					<div class="input_wrap">
						<label for="rating">Rating</label> <input type="text" id="rating"
							name="rating">
					</div>
					<div class="input_wrap">
						<label for="productImage">Image</label> <input type="file" id="productImage"
							name="productImage">
					</div>
					<div class="input_wrap">
						<input type="submit" value="Add Product" class="submit_btn">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
