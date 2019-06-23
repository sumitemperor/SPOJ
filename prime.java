import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

public class prime {

    static void findPrime(int lim, Vector<Integer> pri) {
        Boolean mark[] = new Boolean[lim+1];

        for(int i = 0; i < mark.length; i++) {
            mark[i] = true;
        }

        for(int i = 2; i*i < lim; i++) {
            if(mark[i] == true) {
                for(int j = 2*i; j < lim; j +=i) {
                   mark[j] = false ;
                }
            }
        }

        for(int i = 2; i < lim; i++) {
            if(mark[i] == true) {
                pri.add(i);
            }
        }
    }

    static void sieve(int low, int high) {
        int limit = (int) floor(sqrt(high) + 1);
        Vector<Integer> prime = new Vector<>();

        findPrime(limit,prime);

        Boolean mark[] = new Boolean[high - low + 1];

        for(int i = 0; i < mark.length; i++) {
            mark[i] = true;
        }
        high +=1;
        
        for(int i = 0; i < prime.size(); i++) {
            int lowdiv = (int) floor(low/prime.get(i)) * prime.get(i);
            
            if(lowdiv < low) {
                lowdiv += prime.get(i);
            }

            if(lowdiv == prime.get(i)) {
                lowdiv += prime.get(i);
            }

            for(int j = lowdiv; j < high; j += prime.get(i)) {
                    mark[j - low] = false;
            }
        }

        for(int i = low; i < high; i++) {
            if(mark[i-low] == true && i > 1) {
                System.out.println(i);
            }
        }

        System.out.println();
    }

    public static void main(String[] args)throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(in.readLine());
        Integer t[][] = new Integer[count][2];

        for(int i = 0;i < count; i++)
        {
            StringTokenizer tk = new StringTokenizer(in.readLine());
            t[i][0] = Integer.parseInt(tk.nextToken());
            t[i][1] = Integer.parseInt(tk.nextToken());
        }

        for(int i = 0; i < count; i++) {
            sieve(t[i][0],t[i][1]);
        }

    }
}
