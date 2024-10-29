/* https://learn.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver16#download */
/* docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=!#1Password" -p 1433:1433 --name mssql --hostname mssql -d mcr.microsoft.com/mssql/server:2022-latest */

CREATE DATABASE db;
GO

USE db;
GO

CREATE TABLE users (
    id INT PRIMARY KEY IDENTITY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(254) NOT NULL,
    password VARCHAR(128) NOT NULL,
    age INT,
    createdAt DATETIME DEFAULT GETDATE(),
    updatedAt DATETIME DEFAULT GETDATE()
);
GO

INSERT INTO users (name, surname, email, password, age)
    VALUES
        ('ADMIN', 'ACCOUNT', 'admin@app.local', '123456', '20'),
        ('GURAY', 'ALIN', '222203578@ogr.uludag.edu.tr' , '123456', '26');

GO