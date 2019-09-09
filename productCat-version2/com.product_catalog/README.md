# product_catalog
this application manages a collection of products in a shop

#Point to note
    The application is of two versions.
       1.   Version is on the master branch and allows any user to modify any product
       2.   Version two is on the branch secondpointofview and allows a logged in user to modify or delete only a product which they had added
#Getting it running
    After having cloned the repository:
1. First import the file named "product_catalog_ysql_script.sql" from the "sql_script" directory of this repository. It is a mysql database script, which contains the database used within the project. It can be imported into a mysql server, but for best of output, it can be imported using xampp. (for the original database resides in xampp)
2. Next import the project, into a java supported IDE. (most preferrebly eclipse IDE).
3. Running the project as it is, starts the server at "localhost:8080". After successfully running the application, go to a browser of your choice and type in the "local:8080" in a new tab. then you have the application running.

    NB: this is a maven project, making use of certain dependencies. so when running the project in your IDE for the first time, ensure that you have internet connection (so as to download then maven dependencies as need may be).
    
#Getting around the application
   1. With the application running in your browser at "localhost:8080", you see a catalog of products currently present in the shop, and details about those products(with their respective categories inclusive).
   2. The system is designed to handle visiting users and authorised users(users with accounts). A user without an account can only view things, but cannot modify products or categories.
   3. To login, fill the form at the base of the page appropriately. For a test, a currently registered user is "ndzib" with password "bruno". A user could create a new account by clicking on the link above the login form and in turn filling in the requested data.
        NB. when signing up, the user name (second field) is optional, but if left out, the user's real name will be assumed as the username as well.
        
   4.  Once logged in, a user can have access to explicit view of the shop's products, and for each product, clicking on the "modify" button below it, permits the logged in user to change any parameter of the product as they so desire, and either save the changes to the product by clicking on the "add" button, delete the product by clicking on the "delete" button or save the edited product as a completely new product by clicking on the "add as new" button (these operations go same for modifying a category).
   5.  While still in the explicit view, a user can directly delete a product from the shop by clicking the delete button.
   6.  A user can also swap between viewing products or all categories by clicking on the appropriate button on the top right of each page. (i.e Product catalog to view all products or "Product Categories" to view all available product categories).
   7. While viewing all categories, clicking on the Explore button displays all the products for that category. And specifically for logged in users, they could delete complete categories or modify existing ones, by clicking on the delete and modify buttons respectively.
   8.  After performing whatever operations, a logged in user can logout by simply clicking on the "log out" button at the top right side of the application's interface.
    
    
 
 
 
 @nDZIB
