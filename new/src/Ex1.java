import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Introduction to Computer Science, Ariel University, Ex1 (manual Example + a Template for your solution)
 * See: https://docs.google.com/document/d/1C1BZmi_Qv6oRrL4T5oN9N2bBMFOHPzSI/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true
 * <p>
 * Ex1 Bulls & Cows - Automatic solution.
 * **** Add a general readme text here ****
 * Add your explanation here:
 * This game is playing automatically the game- bulls and cows.
 * the algorithm is playing in an effective way, with the fewer guesses as possible.
 * **** General Solution (algorithm) ****
 * Add your explanation here:
 * First, the function creates a Boolean array of size 10^(the number of digits between 2-6),
 * and turns each cell in the array to a true value.
 * Second, the function sends the game a random guess from the array that has a true value.
 * The game gives us back for each random guess answers about the number of bulls and the number of cows the guess has with the secret code
 * We call those answers = results.
 * Then comparing the guess to each index in the array that has a true value,
 * and getting answers for each index about the number of bulls and the number of cows with the guess.
 * We call those answers= final results.
 * The program compares the result to the final result.
 * If the final results for the random index are different from the results then the value of this index turns into false.
 * Run all functions again, send again a random guess whose value is correct
 * Until you get to the secret code.
 * **** Results ****
 * Make sure to state the average required guesses
 * for 2,3,4,5,6 - digit code:
 * <p>
 * Average required guesses 2: 5.73
 * Average required guesses 3: 5.49
 * Average required guesses 4: 6.2
 * Average required guesses 5: 6.72
 * Average required guesses 6: 6.84
 * The average for all the averages(2-6) is: 6.196
 */
public class Ex1 {
    public static final String Title = "Ex1 demo: manual Bulls & Cows game";
    private static Random random;

    public static void main(String[] args) {
        BP_Server game = new BP_Server();   // Starting the "game-server"
        long myID = 213174790;             // Your ID should be written here
        int numOfDigits =6;                // Number of digits [2,6]
        game.startGame(myID, numOfDigits);  // Starting a game
        System.out.println(Title + " with code of " + numOfDigits + " digits");
        // manualEx1Game(game);
        autoEx1Game(game); // you should implement this function )and any additional required functions).
    }

    public static void manualEx1Game(BP_Server game) {
        Scanner sc = new Scanner(System.in);
        int ind = 1;      // Index of the guess
        int numOfDigits = game.getNumOfDigits();
        double max = Math.pow(10, numOfDigits);
        while (game.isRunning()) {           // While the game is running (the code has not been found yet
            System.out.println(ind + ") enter a guess: ");
            int g = sc.nextInt();
            if (g >= 0 && g < max) {
                int[] guess = toArray(g, numOfDigits); // int to digit array
                int[] res = game.play(guess); // Playing a round and getting the B,C
                if (game.isRunning()) {     // While the game is running
                    System.out.println(ind + ") B: " + res[0] + ",  C: " + res[1]); // Prints the Bulls [0], and the Cows [1]
                    ind += 1;               // Increasing the index
                }
            } else {
                System.out.println("ERR: wrong input, try again");
            }
        }
        System.out.println(game.getStatus());
    }


    /**
     * Simple parsing function that gets an int and returns an array of digits
     *
     * @param a    - a natural number (as a guess)
     * @param size - number of digits (to handle the 00 case).
     * @return an array of digits
     */
    private static int[] toArray(int a, int size) {
        int[] c = new int[size];
        for (int j = (c.length-1); j >=0; j--) {
            c[j] = a % 10;
            a = a / 10;
        }
        return c;
    }
////////////////////////////////////////////////////

    /**
     * This function solves the Bulls & Cows game automatically.
     * You should implement
     * **** Add a specific explanation for each function ****
     */

    /**
     * First, the function creates a new array.
     * Second define the size of the array as 10^(number of digits).
     * Then turns the array into a boolean array that all it is values are True.
     * **/
    public static void autoEx1Game(BP_Server game) {
        int numOfDig = game.getNumOfDigits();
        int size = (int) Math.pow(10, numOfDig);
        int sum = 0;
        boolean[] arr = new boolean[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = true;
        }
        /** Then this function gets a guess from the function-"getGuess" and turns it into an array by the function "toArray"
         * The function sends the guess to the game and gets results for the bulls and cows that the guess has compared to the secret code.
         * Then turns the results of the bulls and the cows into an array, where the bulls are in the first index and the cows in the second index.
         * The function printing the guess and the bulls and cows.
         * at last call the "compareBC" function(full explanation is above the function).
         * **/
        while (game.isRunning()) {
            Ex1Testing.count ++;
            int guess = getGuess(arr, numOfDig);
            int[] gu = toArray(guess, numOfDig);
            int[] result = game.play(gu);
            System.out.println(guess);
            System.out.println("B:" + result[0] + "   C:" + result[1]);

            sum++;
            int B = result[0];
            int C = result[1];
            compareBC(guess, arr, numOfDig, result);
            System.out.println();

        }
    }


    /**
     * This function is takes a random number(from the array).
     * If the value inside 'randomNumber' index in the array is true: take it as a guess.
     * If there are indexes with false, skip the false indexes and keep looking for the true one.
     * And keep until you find the right code.
     * In case the number is bigger than the array length, go back to the first index[0] and keep looking for the code.
     **/

    public static int getGuess(boolean[] arr, int numOfDigits) {
        Random random = new Random();
        int answer;
        int randomNumber = random.nextInt(0, (arr.length - 1));
        boolean a = true;
        while (a == true) {
            if (arr[randomNumber]) {
                a = false;
            } else {
                randomNumber = randomNumber + 1;
                if (randomNumber >= arr.length)
                    randomNumber = 0;
            }
        }
        answer = randomNumber;
        return answer;
    }

    /**
     * This function validates the bulls&cows between our last guess and all the other options in the optional array(only the valid one).
     * The function compares between the guess and all the valid options that last in the array, this compare gives B&C value that called "FinalResult".
      **/

    public static int[] checkBullsAndCows(int[] guess, int[] answer, int numOfDigits) {
        int B = 0;

        boolean[] tempGuess = new boolean[numOfDigits];
        boolean[] tempAnswer = new boolean[numOfDigits];
        Arrays.fill(tempAnswer, true);
        Arrays.fill(tempGuess, true);

        for (int i = 0; i < numOfDigits; i++) {
            if (guess[i] == answer[i]) {
                B++;
                tempAnswer[i] = false;
                tempGuess[i] = false;
            }
        }

        int C = 0;
        for (int i = 0; i < numOfDigits; i++) {
            for (int j = 0; j < numOfDigits; j++) {
                if ((guess[i] == answer[j]) && tempAnswer[i] && tempGuess[j]) {
                    C++;
                    tempAnswer[i] = false;
                    tempGuess[j] = false;
                    break;

                }
            }
        }
        int[] finalRes = new int[2];
        finalRes[0] = B;
        finalRes[1] = C;
        return finalRes;
    }
    /** In this function in every compare we also compare the "FinalResult" to the result for the original guess.
     * if the result and the FinalResult not equal then it's no longer a valid option and the index value turns into false.**/

    public static void compareBC(int guess, boolean[] arr, int numOfDig, int[] result) {
        int[] guessArr = toArray(guess, numOfDig);
        int[] finalRes;
        for (int i = 0; i < arr.length; i++) {
            int answer = i;

            int[] answerArr = toArray(answer, numOfDig);
            finalRes = checkBullsAndCows(answerArr, guessArr, numOfDig);

            if (finalRes[0] != result[0] || finalRes[1] != result[1]) {
                arr[answer] = false;
            }
        }
    }
}