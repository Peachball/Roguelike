package multiplayer;

import java.util.ArrayList;

public class RSA {

    public static final int DEFAULT_SIZE = 100;

    public ArrayList<Integer> primes;
    public int prime;
    public int e;
    public int d;
    public int p;
    public int q;

    public RSA(int size) {
        primes = new ArrayList<Integer>();
        obtainPrimes(size);
    }

    public int getPrime(int min) {
        int counter = 0;
        while(true){
            if(counter>=primes.size()){
                addPrimes(counter);
            }
            if(primes.get(counter)>min){
                return primes.get(counter);
            }
            counter++;
        }
    }

    private void obtainPrimes(int x) {
        primes.ensureCapacity(x);
        primes.add(2);
        int y = 3;
        for (int counter = 0; counter < x; counter++) {
            if (isPrime(y)) {
                primes.add(y);
            } else {
                y++;
            }
        }
    }

    public void addPrimes(int x) {
        int counter2 = 0;
        for (int counter = primes.get(primes.size() - 1)+1; counter2 < x; counter++) {
            if(isPrime(counter)){
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
