import java.util.Random;
import java.util.Scanner;

public class Task_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;
        int round = 1;

        System.out.println("🎯 Welcome to the Number Guessing Game!");

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;  // Random number between 1 and 100
            int attemptsLeft = 5;
            boolean hasGuessedCorrectly = false;

            System.out.println("\n🎮 Round " + round);
            System.out.println("Guess the number between 1 and 100");
            System.out.println("You have " + attemptsLeft + " attempts!");

            while (attemptsLeft > 0) {
                System.out.print("🔢 Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsLeft--;

                if (userGuess == numberToGuess) {
                    System.out.println("🎉 Correct! You guessed the number.");
                    hasGuessedCorrectly = true;
                    totalScore += 10;  // Example scoring: 10 points per correct guess
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("📉 Too low!");
                } else {
                    System.out.println("📈 Too high!");
                }

                if (attemptsLeft > 0) {
                    System.out.println("Attempts remaining: " + attemptsLeft);
                } else {
                    System.out.println("❌ No attempts left! The correct number was: " + numberToGuess);
                }
            }

            // Ask if the user wants to play another round
            System.out.print("🔁 Do you want to play another round? (yes/no): ");
            String response = scanner.next().toLowerCase();

            if (!response.equals("yes")) {
                playAgain = false;
                System.out.println("🧾 Final Score: " + totalScore + " points");
                System.out.println("Thanks for playing! 🎮");
            }

            round++;
        }

        scanner.close();
    }
}
