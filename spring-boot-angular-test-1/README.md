# MySQL local installation

Download MySQL for Windows 64 bit from:
https://dev.mysql.com/downloads/mysql/

After installation follow these steps:
1) Create a data folder within the folder in which MySQL has been installed
2) Run this command from command prompt : mysqld --initialize
3) Note the temporary password from .err file
4) Login using : mysql -h localhost -u root -p<your temporary password>
5) Change password using : ALTER USER 'root'@'localhost' IDENTIFIED BY 'changeit';
6) Subsequently use this to login : mysql -h localhost -u root -pchangeit
7) Create schema : create schema todos;

Creating MySQL DB on Azure:
1) Create a MySQL DB on Azure portal
2) Use a separate resource group called Lenz-Microflows-DB-RG
3) Name it lenz-microflows-db
4) Create user adminuser and password as admin123$
5) Go to DB. In Connection security, enable Azure services to connect to DB
6) Go to DB. In Connection security, disable SSL (for connecting with mysql on Azure CLI)
7) Go to App. In Configuration, add App setting for RDS_USERNAME, RDS_HOST and RDS_PASSWORD
8) Open Azure CLI. Login to the DB using mysql command.
9) Create schema : create schema todos;
10) Restart app using az webapp restart --name <app name> --resource-group <resource group name>
 