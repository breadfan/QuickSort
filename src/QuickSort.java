import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class QuickSort {

    public static final int SEED = 1;
    public static final int SIZE = 10000;

    public static void fillingArrList(ArrayList<Integer> arrList){
        //int [] arr = new int [SIZE];
        Random rnd = new Random(SEED);
        for(int i = 0; i < SIZE; i++) {
            arrList.add(rnd.nextInt());
        }
    }

    public static void main(String[] args) {

        ArrayList<Integer> arr = new ArrayList<>();

        System.out.println("Enter number of nums");

        /*try {
            number = System.in.read();
        } catch (IOException e) {
            System.out.println("Not right: " + e);
            System.exit(-1);
        }*/


        //number = number - 48;       //System.in.read reads char elements
        //System.out.println(number);

        fillingArrList(arr);

        /*for (int i = 0; i < number; ++i) {    //reading an arr;
            try {
                arr.add((System.in.read()) - 48);
            } catch (IOException e) {
                System.out.println("Not right:" + e);
                System.exit(-1);
            }
        }*/

        long startTime = System.nanoTime();
        QuickSortWithTwoParts(arr, 0, SIZE - 1);
        System.out.println("Time for sorting an array with algo 1: " + (System.nanoTime() - startTime));

        arr.clear();
        fillingArrList(arr);

        startTime = System.nanoTime();
        QuickSortWithThreeParts(arr, 0, SIZE - 1);
        System.out.println("Time for sorting an array with algo 2: " + (System.nanoTime() - startTime));
    }

    private static void QuickSortWithTwoParts(ArrayList<Integer> arr, int l, int r) {
        while (l < r) {
            int partEl = PartitionTwoParts(arr, l, r);
            QuickSortWithTwoParts(arr, l, partEl - 1);
            l = partEl + 1;
        }
    }

    static void swap(ArrayList<Integer> arr, int indOfFir, int indOfSec) {
        int temp = arr.get(indOfFir);
        arr.set(indOfFir, arr.get(indOfSec));
        arr.set(indOfSec, temp);
    }

    static int PartitionTwoParts(ArrayList<Integer> arr, int l, int r) {
        Integer v = arr.get((l + r) / 2);
        int i = l, j = r;
        while (i <= j) {
            while (arr.get(j--) > v) ;

            while (arr.get(i--) < v) ;
            if (i >= j)
                break;
            swap(arr, i, j);
        }
        return j;
    }

    private static void QuickSortWithThreeParts(ArrayList<Integer> arr, int l, int r) {
        int partEl = arr.get(l);
            int i = l;
            int j = r - 1;
            int p = l - 1;
            int q = r;

            //some pic for visualization:
            //              ------------| --------------| ------------| -----------| ---------| |V el|
            //              ===========V  <<<<<<<<<<<<<V  ????????????  >>>>>>>>>>V  ========V   V
            //              ------------| --------------| ------------| -----------| ---------| |V el|
            //   indices:   L           p               i             j            q          r

            while (l <= r) {
                Integer v = arr.get(r);
                while (arr.get(i) < v)
                    i++;
                while (arr.get(j) > v)
                    --j;
                if (l >= r)
                    break;
                swap(arr, i, j);

                if (arr.get(i) == v) {
                    ++p;
                    swap(arr, p, i);
                }
                ++i;
                if (arr.get(j) == v) {
                    --q;
                    swap(arr, q, j);
                }
                --j;

            }
            swap(arr, i, r);
            j = i - 1;      //now j and i changed their roles
            //we have one void element, because j = i - 2; NOW J < I
            i++;
            //now we need to exchange blocks "=" wia "<", and "=" wia ">" respectively
            for (int k = l; k <= p; k++, j--)
                swap(arr, k, j);

            for (int k = r - 1; k >= q; k--, i++)
                swap(arr, k, i);


            QuickSortWithThreeParts(arr, l, j);
            l = i;//QuickSortWithThreeParts(arr, i, r); cutting the tail recursion
        }

}
