
# "MyFace" Social Network Exercise


## Install software

You'll need MySQL server and MySQL workbench.  
You probably already have this installed.

If you don't, you can download MySQL from [here](https://dev.mysql.com/downloads/installer/).
When running through the installer you can keep all the default options and choose a sensible root password.


## Setup

* Open MySQL workbench
* Choose the local MySQL instance, and log in with your root credentials.
* Create a database called `myface` and run the `bootstrapDatabase.sql` script
  to create the tables and insert some test data.
* Create a user account called `myfaceuser` with the password `myfacepassword` that can access the database.
  Once you create the user, go to the "Schema Privileges" tab and click "Add Entry" (middle-right of screen).


## Running the application

To run the application in IntelliJ:
* Open `WebApplication.java`
* Click the green `>` in the margin next to `public static void main` (on line ~8)
* Once the application has started go to [http://localhost:8080]() for the homepage.