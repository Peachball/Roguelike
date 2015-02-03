package multiplayer;

import java.util.ArrayList;
import java.util.Scanner;

public class RSA {

    public static final int DEFAULT_SIZE = 100;

    public ArrayList<Integer> primes;
    public int prime1;
    public int prime2;
    public int e;
    public int d;
    public int p;
    public int q;

    public void test() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("THis is the terminal for screwing around\n"
                    + "with the RSA encryption thing\n"
                    + "1: generate d and e values\n"
                    + "2: encode a number, and get the result\n"
                    + "3: decode a number, and get the result\n");
            in.nextInt();
            switch(in.nextInt()){
                case 1:
                case 2:
                case 3:
            }
        }
    }

    public RSA(int size) {
        primes = new ArrayList<Integer>();
        obtainPrimes(size);
    }

    public int getPrime(int min) {
        int counter = 0;
        while (true) {
            if (counter >= primes.size()) {
                addPrimes(counter);
            }
            if (primes.get(counter) > min) {
                return primes.get(counter);
            }
            counter++;
        }
    }

    private void obtainPrimes(int x) {
        primes.ensureCapacity(x);
        primes.add(2);
        int y = 3;
        for (int counter = 0; counter < x; y++) {
            if (isPrime(y)) {
                primes.add(y);
                counter++;
            }
        }
    }

    public void addPrimes(int x) {
        int counter2 = 0;
        for (int counter = primes.get(primes.size() - 1) + 1; counter2 < x; counter++) {
            if (isPrime(counter)) {
                counter2++;
                primes.add(counter);
            }
        }
    }

    private boolean isPrime(int x) {
        for (int counter = 0; counter < primes.size(); counter++) {
            if (x % primes.get(counter) == 0) {
                return false;
            }
        }
        return true;
    }

}
