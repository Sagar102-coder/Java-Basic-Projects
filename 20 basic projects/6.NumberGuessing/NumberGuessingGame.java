import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int secretNumber = random.nextInt(100) + 1;
        int guess;
        int attempts = 0;

        System.out.println("=================================");
        System.out.println("      NUMBER GUESSING GAME");
        System.out.println("=================================");
        System.out.println("Guess a number between 1 and 100");

        while (true) {

            System.out.print("\nEnter your guess: ");
            guess = sc.nextInt();

            attempts++;

            if (guess == secretNumber) {

                System.out.println("\nCongratulations!");
                System.out.println("You guessed the correct number.");
                System.out.println("Secret Number : " + secretNumber);
                System.out.println("Attempts Taken: " + attempts);
                break;

            } else if (guess > secretNumber) {

                System.out.println("Too High! Try Again.");

            } else {

                System.out.println("Too Low! Try Again.");
            }
        }

        sc.close();
    }
}
