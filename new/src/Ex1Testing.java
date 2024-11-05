import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
public class Ex1Testing {


    @Test
    public void testBullsAndCows() {
        int[] random = {1, 2, 3, 4};
        int[] guess = {2, 2, 0, 1};
        int[] flag = Ex1.checkBullsAndCows(guess, random, 4);
        int[] testRes = {1, 1};
        assertTrue(testRes[0] == flag[0] && testRes[1] == flag[1]);
    }

   @Test
   public void testGetGuess() {
        boolean[] array = {true, false, true, false, true};
        int guess = Ex1.getGuess(array, 5);
        assertTrue(guess>=0&&guess<array.length);
        assertTrue(array[guess]);
    }
    @Test
    public void testCheckCompare(){
        boolean[] array= {true,true,true,true,true,true,true,true,true,true};
        int guess= 3;
        int numOfDigits= 1;
        int[] result= {1,0};
        Ex1.compareBC(guess,array,numOfDigits,result);

        for (int i = 0; i < array.length; i++) {
            if(i==guess){
                assertTrue(array[i]);
            }
        else{
            assertFalse(array[i]);
            }
        }
    }


    public static int count=0;
    @Test
    public void testAutoEx1() {
        double[] average = {0, 0, 0, 0, 0};
        double allTheAverage=0;
        for (int i = 2; i < 7; i++) {
            for (int j = 0; j < 100; j++) {
                BP_Server game = new BP_Server();
                game.startGame(213174790, i);
                Ex1.autoEx1Game(game);
            }

            average[i - 2] = count / 100.0;
            count = 0;

            System.out.println("The average for-" + i + "is:" + average[i - 2]);

            allTheAverage = average[0]+average[1]+average[2]+average[3]+average[4];
            System.out.println("The average for 2-6 digits is:"+allTheAverage/5);
            //assertTrue(allTheAverage<7);
        }
    }
    }