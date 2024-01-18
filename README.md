# final-project-
#  Cars online Shopping System

The Cars is a web application developed using HTML, CSS, JavaScript, Bootstrap, Java, Spring Boot, Spring Security, MySQL, and Maven. It enables users to manage their car collection, perform CRUD operations on cars, upload car images, and utilize a shopping cart feature. Users can also create accounts, log in, and log out.
## Home Page
![home page .png](src%2Fmain%2Fwebapp%2Fpub%2Fimages%2Fhome%20page%20.png)
## view cart page 
![img.png](img.png)
## Features

1. **User Authentication:**
    - Create an account and log in.
    - Log out when the session is complete.

2. **User Roles:**
    - Differentiate between regular users and admin users.

3. **Car Management:**
    - Create, read, update, and delete cars.
    - Upload images for each car.

4. **Shopping Cart:**
    - Save cars to the shopping cart.
    - Edit or delete cars from the cart.

5. **Admin Privileges:**
    - Admins have additional privileges for managing users and cars.

## Getting Started

Follow these steps to set up and run the project locally.

1. **Clone the Repository:**
   ```bash
   git clonehttps://github.com/samimesfun/final-project-
Build and Run with Maven:

bash
Copy code
mvn clean install
java -jar target/the-cars.jar
Configure Database:

Set up a MySQL database and update the application.properties file with your database credentials.
Configure Environment Variables:

Copy the .env.example file to .env and configure the required variables.
Access the Application:

Open your browser and go to http://localhost:8080.
Usage
User Registration:

Create a new user account.
User Authentication:

Log in with your credentials.
Car Management:

Add, edit, and delete cars.
Upload car images.
Shopping Cart:

Add cars to the shopping cart.
Edit or delete cars from the cart.
Admin Privileges:

Admins have additional options for managing users and cars.
