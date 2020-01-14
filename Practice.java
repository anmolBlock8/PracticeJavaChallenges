/*
        You are given a permutation A={a1,a2,a3,...aN}
         of N
         integers from 0
         to N−1
        .
        You are also given Q
         queries of the following two forms:
        1  X Y
        : Swap (aX,aY)
        2  L R K
        : Print MexK
         (aL,…,aR)
        Here, MexK
        (aL,…,aR)
         = Kth
         smallest non-negative integer that is not available in the subarray (aL,…,aR)
        .
        You can answer the next query only when you answer the previous one. You must answer these queries online.
        Input format
        First line: A single integer N
         denoting the number of elements in the permutation
        Second line: N
         space-separated integers a1,a2,a3,...aN
        Next Q
         lines: Each describing a query
        As you are required to respond to the queries online, the queries are encoded.
        A query of the first type is provided in the following format:
        1  X′ Y′
        .
        A query of the second type is provided in the following format:
        2  L′ R′ K′
        .
        You can decode the queries as follows:
        X=X′⊕LastAns,Y=Y′⊕LastAns,L=L′⊕LastAns,R=R′⊕LastAns,K=K′⊕LastAns
        Here, LastAns
         is the last answer to the query of the second type. Initially, LastAns=0
        .
        Output format
        For each query, print the answer in a single line
        Constraints
        1≤N≤105
        1≤Q≤2∗105
        1≤X,Y≤N
        1≤L≤R≤N
        1≤K≤105
        0≤ai<N
        SAMPLE INPUT

        4
        0 3 1 2
        4
        2 2 3 1
        1 1 2
        2 2 3 1
        2 3 6 0
        SAMPLE OUTPUT

        0
        2
        5
        Explanation
        Initially, A = {0,3,1,2}
         and LastAns = 0
        Query of 2nd type L=2,R=3,K=1
         : LastAns = 0
        Query of 1st type X=1,Y=2
        . Permutation A changed to : {3,0,1,2}
        Query of 2nd type L=2,R=3,K=1
         : LastAns = 2
        Query of 2nd type L=1,R=4,K=2
         : LastAns = 5

*/


package practice;
import java.io.*;
import java.util.*;

public class Practice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int lastans = 0;
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int q = sc.nextInt();
        int flag = 0, x = 0, y = 0, l = 0, r = 0, k = 0;
        for(int i = 0; i < q; i++) {
            flag = 0;
            int init = sc.nextInt();
            if(init == 1) {
                flag = 1;
                x = sc.nextInt();
                y = sc.nextInt();
                x=x-1;
                y=y-1;
                x = x ^ lastans;
                y = y ^ lastans;
                int temp = a[x];
                a[x] = a[y];
                a[y] = temp;
            }
            else if(init == 2) {
                l = sc.nextInt();
                r = sc.nextInt();
                k = sc.nextInt();
                
                if(l  <1 || r > 4 ) {
                    r = r ^ lastans;
                    l = l ^ lastans;
                    k = k ^ lastans;
                    
                    lastans = l ^ r;
                    System.out.println(lastans);
                    continue;
                }
                int c = n - ( r - l + 1);
                int[] b = new int[c];
                int q1 = 0;
                for(int p = l-2; p >= 0; p--) {
                    if(q1 <= c){
                        b[q1] = a[p];
                    } 
//                    System.out.print(b[q1]);
                    q1 = q1+1;
                }
                for(int p = r; p < n; p++) {
                    if(q1 <= c)
                    {
                    b[q1] = a[p];
                    }
//                   System.out.print(b[q1]);
                    
                    q1 = q1 + 1;
                }
                Arrays.sort(b);
                lastans = b[k-1];
                System.out.println(lastans);
            }
        } 
    }
}
