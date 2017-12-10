# Hidden_Founders_Coding_Challenge

The project is an implementation of the Hidden Founders 2017 Coding Challenge.

This app exposes around 340 shops via a RESTful API architecture. After initial subscription through the app, the user must login to access
the main page which will be displaying registered  shops excluding liked and disliked ones, in an order by geographical location.
 The user may also visualise his liked shops in a second page called preferred shops.
 The act of disliking a shop is only valid for two hours.
## Note:
So far, only the backend aspect of the app is implemented.
    
## Technologies



 * Spring Boot
 * Angular
 * RxJS
 * MongoDb
    
## Getting Started



 1. Install MongoDb on your machine.
 2. Clone project on your machine:
        
        https://github.com/youch12/Hidden_Founders_Coding_Challenge.git
 3. unzip the project and cd to Shops_Project_Coding_Challenge.
 3. Create a database called shop, create two collection within it: users and shops.
 4. Import the shops and users data by executing the following commands:
        
        use shop
        db.shops
        mongoimport --db shop --collection shops  --file shops.json
        db.users
        mongoimport --db shop --collection users  --file users.json
 5. Build your project with gradle.
 6. Run your project as a spring boot app.
 
 By default, there is only one user with the credentials:
    
        email: example@email.com
        password: password
 The apis are secured with JWT, so you must request a token by sending a post request to the api /login with a body of credentials.
 The next requests must contain the token as a Bearer header.
 The apis are accessible through: 
 
        http://localhost:8080
    
