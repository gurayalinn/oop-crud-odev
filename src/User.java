/*
    id INT PRIMARY KEY IDENTITY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(254) NOT NULL,
    password VARCHAR(128) NOT NULL,
    age INT NOT NULL,
*/

public class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private int age;


    // Constructor
    public User(String email, String password, String name, String surname, int age) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    // Getter ve Setter metodları

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public void setFullName(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    // toString method
    @Override
    public String toString() {
        return "[Ad: " + name + ", Soyad: " + surname + ", Yaş: " + age + ", Email: " + email + "]";
    }

}
