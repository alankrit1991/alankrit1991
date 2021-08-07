
I have used Spring boot and mvc pattern. Also assuming REST framework between frontend and backend. Below things have been done for each requirement :-

* User should be able to see products in different way. (Ex: The service to be designed and developed needs to return back products!) :-

- The UI will show different categories as headers. On clicking the category, call will be made to getTopProductsByCategory in controller and products from that category will be populated.
- By default the screen will show 10 products from each category with highest rating. Rating should be from 1 to 5 and should be validated on frontend. A separate table is made to store all the reviews of all products.
- The UI can also have a search box where user can type name of the product. Call will be made to "getAllProductsbyName" method in controller.

* Seller should be able to post products :- 

- The request to save product will go to "saveProduct" method in controller. The product should be sent as json in request body and quantity of product as request params. Once seller saves it, he will have to make a new call to save more products.

* User should be able to add products to cart:-

- A separate table is made for the cart where cart information of all buyers will be stored. While placing order, all the products for particular user will be taken from this table.

* User should be able to place an order by making payment:-

- For placing the order, an external payment service is assumed which can check whether user is able to make the payment. We will also check that product is available while placing the order, if not then exception will be thrown. The available product quantity will be reduced by 1 from Product table and final cart value will be calculated. The final values will be stored in "Order" object which will be used by Payment service for final payment.

**

- Assuming PRODUCT, PRODUCT_REVIEW, CART, USER tables
- Both Buyers and Sellers will use USER table
