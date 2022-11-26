import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EulerProject {

    public static void main(String[] args) {
        Euler1();
        Euler2();
        Euler3();
        Euler4();
    }

    public static void Euler1(){
        int ans = IntStream.range(1,1000).filter(num -> num%3 == 0 || num%5 ==0).sum();
        System.out.println(ans);
    }

    public static void Euler2(){
         int ans = Stream.iterate(new int[]{0,1}, nextFib -> new int[]{nextFib[1],nextFib[0] + nextFib[1]})
                .limit(35)
                .map(nextFib -> nextFib[0])
                .mapToInt(Integer::intValue)
                .filter(num -> num %2 == 0)
                .sum();
        System.out.println(ans);
    }

    public static void Euler3(){
        Double num = 600851475143d;
        int numLimit = (int) Math.sqrt(num);
        Optional<Integer> ans = IntStream.rangeClosed(2,numLimit)
                .boxed()
                    .sorted(Collections.reverseOrder())
                        .filter(e -> num%e ==0)
                            .filter(EulerProject::isPrime).findFirst();
        if (ans.isPresent()){
            System.out.println(ans.get());
        }
    }


    public static void Euler4(){
        int ans = 0;
        for (int i = 999; i >= 100; i--){
            for (int j = 999; j >= 100; j--){
                int mult = i*j;
                if (checkPalindrome(mult) && mult > ans)
                    ans = mult;
            }
        }
        System.out.println(ans);
    }

    public static boolean checkPalindrome(int num){
        StringBuilder stringBuilder = new StringBuilder();
        String reversedNumber = stringBuilder.append(num).reverse().toString();
        return reversedNumber.equals(String.valueOf(num));

    }

    public static boolean isPrime(int num){
        for (int i =2; i<(int) Math.sqrt(num); i++){
            if (num%i==0)
                return false;
        }
        return true;
    }

    public static boolean isPrimeSmallNumbers(int num){
        for (int i =2; i<num; i++){
            if (num%i==0)
                return false;
        }
        return true;
    }

    public static List<Integer> getPrimes(int num){
        return IntStream.range(1,num+1)
                .boxed()
                .filter(e -> isPrimeSmallNumbers(e) && num%e ==0)
                .collect(Collectors.toList());
    }

    public static List<Integer> getPrimesInRange(int range){
        return IntStream.rangeClosed(1,range).filter(e -> isPrimeSmallNumbers(e)).boxed().collect(Collectors.toList());
    }


}
