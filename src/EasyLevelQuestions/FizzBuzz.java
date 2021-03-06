package EasyLevelQuestions;
import java.util.*;

// LC Question 412
public class FizzBuzz {
    public static void main(String[] args){
        int input = 15;
        int input2 = 20;
        System.out.println(fizzBuzz(input));
    }

    // Runtime O(n), Space O(n)
    public static List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for(int i=1; i<=n; i++){
            if(i % 15 == 0) result.add("FizzBuzz");
            else if(i % 5 == 0) result.add("Buzz");
            else if(i % 3 == 0) result.add("Fizz");
            else result.add(String.valueOf(i));
        }
        return result;
    }
}
