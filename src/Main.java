import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        Scanner scanner = new Scanner(System.in);
        User loggedInUser = null;

        try {
            database.connect();
                while (true) {
                    if (loggedInUser == null) {
                    System.out.println("\n--- Hoş geldiniz ---");
                    System.out.println("1. Kayıt Ol");
                    System.out.println("2. Giriş Yap");
                    System.out.println("0. Çıkış");
                    System.out.print("Seçiminiz: \n");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice == 1) {
                        System.out.print("Ad: ");
                        String name = scanner.nextLine();

                        if (!isValidName(name)) {
                            System.out.println("Geçersiz ad.");
                            continue;
                        }
                        System.out.print("Soyad: ");
                        String surname = scanner.nextLine();

                        if (!isValidName(surname)) {
                            System.out.println("Geçersiz soyad.");
                            continue;
                        }
                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        if (!isValidEmail(email)) {
                            System.out.println("Geçersiz email.");
                            continue;
                        }
                        System.out.print("Şifre: ");
                        String password = scanner.nextLine();

                        System.out.print("Yaş: ");
                        int age = scanner.nextInt();

                        if (!isValidAge(age)) {
                            System.out.println("Geçersiz yaş.");
                            continue;
                        }

                        User newUser  = new User(email, password, name, surname, age);
                        database.registerUser(newUser);

                    } else if (choice == 2) {
                        // admin@app.local - 123456
                        System.out.println("Lütfen giriş yapın.");
                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        if (!isValidEmail(email)) {
                            System.out.println("Geçersiz email.");
                            continue;
                        }

                        System.out.print("Şifre: ");
                        String password = scanner.nextLine();

                        loggedInUser = database.LoginUser(email, password);

                        if (loggedInUser != null) {
                            System.out.println("Giriş başarılı!");
                        } else {
                            System.out.println("Giriş başarısız. Lütfen bilgilerinizi kontrol edin.");
                        }

                    } else if (choice == 0) {
                        System.out.println("Çıkış yapılıyor...");
                        break;
                    } else {
                        System.out.println("Geçersiz seçim.");
                    }

                    // Kullanıcı giriş yaptıktan sonraki menü
                } else {
                    System.out.println("\n-- Kullanıcı İşlemleri -- ");
                    System.out.println("Hoş geldiniz, " + loggedInUser.getFullName() + "!");
                    System.out.println("1. Kayıt ekle");
                    System.out.println("2. Kayıtları listele");
                    System.out.println("3. Yaşa göre sırala");
                    System.out.println("4. Kayıt sil");
                    System.out.println("5. Şifre değiştir");
                    System.out.println("6. Profilim");
                    System.out.println("0. Çıkış");
                    System.out.print("Seçiminiz: \n");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice == 1) {
                        System.out.print("Ad: ");
                        String name = scanner.nextLine();
                        System.out.print("Soyad: ");
                        String surname = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Şifre: ");
                        String password = scanner.nextLine();
                        System.out.print("Yaş: ");
                        int age = scanner.nextInt();
                        User user = new User(email, password, name, surname, age);
                        database.registerUser(user);
                    } else if (choice == 2) {
                        database.usersList();
                    } else if (choice == 3) {
                        database.ageOrder();

                    } else if (choice == 4) {
                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        if (!isValidEmail(email)) {
                            System.out.println("Geçersiz email.");
                            continue;
                        }
                        System.out.print("Şifre: ");
                        String password = scanner.nextLine();

                        if(loggedInUser.getEmail().equals(email) && loggedInUser.getPassword().equals(password)) {
                        database.deleteUserByEmailAndPassword(email, password);
                        loggedInUser = null;

                        } else {
                            System.out.println("Kayıt silinemedi. Email veya şifre hatalı.");

                        }
                    } else if (choice == 5) {
                        System.out.println("Mevcut şifre: ");
                        String oldPassword = scanner.nextLine();
                        if (loggedInUser.getPassword().equals(oldPassword)) {
                            System.out.println("Şifre doğrulandı.");
                        } else {
                            System.out.println("Şifre doğrulanamadı.");
                            continue;
                        }
                        System.out.print("Yeni şifre: ");
                        String newPassword = scanner.nextLine();
                        loggedInUser.setPassword(newPassword);
                        database.updatePassword(loggedInUser.getEmail(), newPassword);

                    } else if (choice == 6) {
                        System.out.println("Profil bilgileriniz: ");
                        System.out.println(loggedInUser);

                    } else if (choice == 0) {
                        System.out.println("Çıkış yapılıyor...");
                        break;
                    } else {
                        System.out.println("Geçersiz seçim.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                database.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                scanner.close();
            }
        }
    }

    // Validasyon methodları

    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return isNotEmpty(email) && email.contains("@") && email.contains(".");
    }

    public static boolean isValidName(String name) {
        return isNotEmpty(name) && name.length() >= 2;
    }

    public static boolean isValidAge(int age) {
        return age >= 1 && age <= 100;
    }

}
