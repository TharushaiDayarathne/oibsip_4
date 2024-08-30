import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private String profile;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.profile = "Default Profile";
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void updateProfile(String profile) {
        this.profile = profile;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public String getProfile() {
        return profile;
    }
}

class Exam {
    private Map<Integer, String> questions;
    private Map<Integer, String> correctAnswers;
    private Map<Integer, String> userAnswers;
    private int timer;

    public Exam() {
        questions = new HashMap<>();
        correctAnswers = new HashMap<>();
        userAnswers = new HashMap<>();
        timer = 60; // Timer set to 60 seconds for example

        // Sample questions
        questions.put(1, "What is the capital of France? A) Paris B) London C) Berlin D) Madrid");
        correctAnswers.put(1, "A");

        questions.put(2, "What is 2 + 2? A) 3 B) 4 C) 5 D) 6");
        correctAnswers.put(2, "B");

        // Add more questions as needed
    }

    public void startExam() {
        Scanner scanner = new Scanner(System.in);

        for (Map.Entry<Integer, String> entry : questions.entrySet()) {
            System.out.println("Q" + entry.getKey() + ": " + entry.getValue());
            System.out.print("Your answer: ");
            String answer = scanner.nextLine();
            userAnswers.put(entry.getKey(), answer);

            // Simulate timer (not a real-time timer)
            timer--;
            if (timer == 0) {
                System.out.println("Time's up!");
                autoSubmit();
                return;
            }
        }

        autoSubmit();
    }

    public void autoSubmit() {
        System.out.println("Submitting your answers...");
        // Process answers
        int score = 0;
        for (Map.Entry<Integer, String> entry : userAnswers.entrySet()) {
            if (correctAnswers.get(entry.getKey()).equalsIgnoreCase(entry.getValue())) {
                score++;
            }
        }
        System.out.println("You scored: " + score + "/" + questions.size());
    }
}

public class OnlineExaminationSystem {
    private static Map<String, User> users = new HashMap<>();
    private static User currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeUsers();

        while (true) {
            System.out.println("Welcome to the Online Examination System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeUsers() {
        users.put("student1", new User("student1", "password1"));
        users.put("student2", new User("student2", "password2"));
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            currentUser = user;
            System.out.println("Login successful! Welcome, " + currentUser.getUsername());
            afterLogin(scanner);
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void afterLogin(Scanner scanner) {
        while (true) {
            System.out.println("1. Update Profile");
            System.out.println("2. Change Password");
            System.out.println("3. Start Exam");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    updateProfile(scanner);
                    break;
                case 2:
                    changePassword(scanner);
                    break;
                case 3:
                    Exam exam = new Exam();
                    exam.startExam();
                    break;
                case 4:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void updateProfile(Scanner scanner) {
        System.out.print("Enter new profile information: ");
        String profile = scanner.nextLine();
        currentUser.updateProfile(profile);
        System.out.println("Profile updated successfully!");
    }

    private static void changePassword(Scanner scanner) {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        currentUser.updatePassword(newPassword);
        System.out.println("Password changed successfully!");
    }
}
