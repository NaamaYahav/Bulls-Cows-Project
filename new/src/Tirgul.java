import java.util.Scanner;

public class Tirgul {
    public static int[] newArr(int[] arr, int num, int in) //num=7, inx=3 [1,2,3,4,5]
    {
        int newArr[] = new int[arr.length + 1];
        for (int i = 0; i < in; i++) {
            newArr[i] = arr[i];
        }
        newArr[in] += num;
        for (int j = in; j < arr.length; j++) {
            newArr[j+1] = arr[j];
        }
        return newArr;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("enter the value of a random number:");
        int numb = scan.nextInt();
         System.out.println("enter the value of a random index:");
        int ind = scan.nextInt();
        int lidor[]={1,2,3,4,5,7};
        int[] namma =newArr(lidor,numb,ind);
        for (int x= 0;x<namma.length;x++)
        {
            System.out.println(namma[x]);
        }
            }
}
