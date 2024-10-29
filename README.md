
# 🚀 JAVA - MSSQL | OOP-CRUD APP

---

> **Note:** Bu proje, Java ile SQLite/MSSQL veritabanı kullanarak crud işlemlerini gerçekleştiren oop uygulamasıdır.

## ⚙️ Kurulum:

### 📝 Geliştirme Ortamı

> **Note:** Bu projeyi çalıştırmak için aşağıdaki yazılımların yüklü olması gerekmektedir.
- 📦 IntelliJ IDEA - [Download](https://www.jetbrains.com/idea/download)
- 📦 JDK 21 - [Download](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- 📦 Git - [Download](https://git-scm.com/downloads)
- 📦 JDBC Driver - [Download](https://docs.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15)
- 📦 SQLite - Projeye dahil
- 📦 Docker Desktop (Opsiyonel)
- 📦 MSSQL 2022 Server (Opsiyonel)
- 📦 SQL Server Management Studio 20 (Opsiyonel)

> **Not:** SQL Server Bağlantısı:

```java
// SQL Server Connection
String url = "jdbc:sqlserver://localhost:1433;databaseName=db;encrypt=false;trustServerCertificate=true";
        String username = "sa";
        String password = "!#1Password";
```

```powershell
$ docker pull mcr.microsoft.com/mssql/server:2022-latest

$ docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=!#1Password" -p 1433:1433 --name mssql --hostname mssql -d mcr.microsoft.com/mssql/server:2022-latest
```

- SSMS - Login
- Server Type: Database Engine
- Server name: 127.0.0.1, 1433
- Authentication: SQL Server Authentication
- Login: sa
- Password: !#1Password
- Connection Security - Encryption: Optional

- 🌐 SQL Server [127.0.0.1:1433](http://127.0.0.1:1433) portunda çalışmaktadır.

> **Not:** SQLite Bağlantısı:

```java
// SQLite Connection
String url = "jdbc:sqlite:db.sqlite";
```

### 📦 Projeyi İndirme

```bash
$ git clone https://github.com/gurayalinn/arac.git
$ cd oop-crud-app
```

## 📃 LİSANS

<strong> &copy; 2024</strong> [APACHE-2.0](LICENSE)
