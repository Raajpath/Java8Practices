package com.example.demo;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Practice {
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(

                new Employee(1, "Raja", "IT", 85000),
                new Employee(2, "Amit", "IT", 95000),
                new Employee(3, "Sneha", "HR", 70000),
                new Employee(4, "Priya", "HR", 92000),
                new Employee(5, "Rahul", "Finance", 88000),
                new Employee(6, "Karan", "Finance", 99000),
                new Employee(7, "Neha", "Admin", 60000),
                new Employee(8, "Vikram", "Admin", 75000)

        );

        //1. Highest salaried employee from each department
        Map<String,Employee> deptwiseHighestSalariedEmployee =
                employees.stream().
                collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),Optional::get)));

        System.out.println("Highest salaried employee from each department");
        System.out.println("===============================================");
        System.out.println(deptwiseHighestSalariedEmployee);
        System.out.println("===============================================");


        //2. first non repeating word
        List<String> words =
                Arrays.asList("java", "spring", "boot", "microservices", "java", "boot");

        String firstNonRepeatingWord = words.stream().
                collect(
                        Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().
                filter(e -> e.getValue() == 1).map(e -> e.getKey()).findFirst().get();

        System.out.println("First non repeating word");
        System.out.println("===============================================");
        System.out.println(firstNonRepeatingWord);
        System.out.println("===============================================");

        //3. Find duplicate elements in a list using Streams
        List<Integer> list = Arrays.asList(1,2,3,4,2,5,1,6);
        // Method - I
        List<Integer> duplicateElementList  = list.stream().
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(e -> e.getKey()).toList();

        System.out.println("Duplicate Elements are::");
        System.out.println("===============================================");
        System.out.println(duplicateElementList);
        System.out.println("===============================================");


        // Find duplicate elements in a list using Streams
        // Method - II
        Set<Integer> set = new LinkedHashSet<>();
        List<Integer> duplicateElementUsingSet = list.stream().
                filter(n -> !set.add(n)).toList();
        System.out.println("Duplicate Elements are::");
        System.out.println("===============================================");
        System.out.println(duplicateElementUsingSet);
        System.out.println("===============================================");

        // Find the second highest salary from Employee list using Java 8 Streams.
        Employee employeeWithSecondHighSal = employees.stream().
                sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(1).findFirst().get();
        System.out.println("Employee with second highest salary::");
        System.out.println("===============================================");
        System.out.println(employeeWithSecondHighSal);
        System.out.println("===============================================");

        //4. Find frequency of each word in a sentence using Streams.
        String sentence = "java is easy and java is powerful";
        Map<String, Long> freqOfEachWord = Arrays.stream(sentence.split(" "))
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()));

        System.out.println("Frequency of each word::");
        System.out.println("===============================================");
        System.out.println(freqOfEachWord);
        System.out.println("===============================================");



        List<String> wordss = Arrays.asList(
                "java",
                "springboot",
                "microservices",
                "docker",
                "kafka",
                "kubernetes"
        );

        //5.  Find the longest string from a list using Streams.
        String longestWord = words.stream().sorted(Comparator.comparingInt(String::length).reversed()).findFirst().get();
        System.out.println("Longest word is::");
        System.out.println("===============================================");
        System.out.println(longestWord);
        System.out.println("===============================================");


        int[] arr = {1, 0, 2, 0, 4, 0, 5};
        // Keep all 0's at end
        List<Integer> listOfZerosAtEnd = IntStream.concat(
                Arrays.stream(arr).filter(i -> i != 0),
                Arrays.stream(arr).filter(i -> i == 0)
        ).boxed().toList();

        System.out.println("List of Zeros at end::");
        System.out.println("===============================================");
        System.out.println(listOfZerosAtEnd);
        System.out.println("===============================================");



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
        int  arr2[] = {1,0,2,0,3,0,4};

        List<Integer> list2 = Stream.concat(
                Arrays.stream(arr2).filter(n -> n != 0).boxed(),
                Arrays.stream(arr2).filter(n -> n == 0).boxed()
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


       // Input  : "abcabcbb"
        //  Output : 3

        int max_length = Integer.MIN_VALUE;
        String str1 = "abcabcbb";
        int count =0;
        int startIndex=0 , endIndex=0;
        for(int i=0;i<str1.length()-1;i++){
            if(str1.charAt(i)!=str1.charAt(i+1)){
                count++;
            }
            else{
                count =0;
            }
            if(count>max_length){
                max_length=count;
            }
        }
        System.out.println("The longest substring :"+count);

        // Merge two unsorted array into single sorted array
        int[] arr3 = {5, 2, 9, 1};
        int[] arr4 = {8, 3, 7, 4};

        List<Integer> list3 = IntStream.concat(
                Arrays.stream(arr3),
                Arrays.stream(arr4)
        ).sorted().boxed().toList();

        System.out.println("Single sorted Arrary::");
        System.out.println(list3);

        // check two strings are anagram or not
        String str3 = "listen";
        String str4 = "silent";

        String str33 = Arrays.stream(str3.split("")).sorted().collect(Collectors.joining());
        String str44 = Arrays.stream(str4.split("")).sorted().collect(Collectors.joining());
        if(str33.equals(str44)){
            System.out.println("Given string is anagram");
        }else{
            System.out.println("Given string is not anagram");
        }


        // Common elements between two lists
        List<Integer> list5 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list6 = Arrays.asList(3, 4, 5, 6, 7);

        List<Integer> list7 = list5.stream().filter(list6::contains).toList();
        System.out.println("Common elements are :: "+list7);

        // Reverse each word of string
        String word = "Hello world";
        String revWord = Arrays.stream(word.split(" ")).map(s -> new StringBuffer(s).reverse()).collect(Collectors.joining(" "));
        System.out.println("Reverse Word: "+revWord);

        // Reverse an integer array
        int[] arr7 = {1, 2, 3, 4, 5};
        List<Integer> revArray = IntStream.rangeClosed(1, arr7.length).map(i -> arr7[arr7.length - i]).boxed().toList();
        System.out.println(revArray.toString());
    }
}
