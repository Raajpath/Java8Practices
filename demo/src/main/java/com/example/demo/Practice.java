package com.example.demo;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Practice {
    public static void main(String[] args) {

       // Valid parenthesis check

       String str = "{[()]}}";

       HashMap<Character, Character> hashMap = new HashMap<>();
       hashMap.put('{','}');
       hashMap.put('[',']');
       hashMap.put('(',')');

       Stack<Character> stack = new Stack<>();
       boolean valid = true;


        for(char ch : str.toCharArray()){
           if(hashMap.containsKey(ch)){
               stack.push(ch);
           }
           else{
               if(stack.isEmpty()){
                   valid=false;
                   break;
               }
               Character pop = stack.pop();
               if(hashMap.get(pop)!=ch){
                   valid=false;
                   break;
               }
           }

        }

        if(!stack.isEmpty()){
            valid=false;
        }

        System.out.println("Valid Combination :: "+valid);

         // keep all 0's at end
        // Input  : [1,0,2,0,3,0,4]
        int  arr[] = {1,0,2,0,3,0,4};

        List<Integer> list = Stream.concat(
                Arrays.stream(arr).filter(n -> n != 0).boxed(),
                Arrays.stream(arr).filter(n -> n == 0).boxed()
        ).toList();

        System.out.println(list);


        // keep 0, 1, 2 Input  : [2,0,1,2,1,0]
        int  arr1[] = {2,0,1,2,1,0};
        List<Integer> list1 = Stream.concat(
                                    Stream.concat(
                                        Arrays.stream(arr1).filter(n -> n == 0).boxed(),
                                        Arrays.stream(arr1).filter(n -> n == 1).boxed()),
                            Arrays.stream(arr1).filter(n -> n == 2).boxed()
                            ).toList();
        System.out.println(list1);

    }
}
