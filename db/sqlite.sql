-- SQLITE3

CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    surname TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    age INTEGER,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (name, surname, email, password, age)
    VALUES
        ('ADMIN', 'ACCOUNT', 'admin@app.local', '123456', 20),
        ('GURAY', 'ALIN', '222203578@ogr.uludag.edu.tr', '123456', 26);