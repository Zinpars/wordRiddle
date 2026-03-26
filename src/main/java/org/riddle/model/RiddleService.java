package org.riddle.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RiddleService {

    private static final RiddleService instance = new RiddleService();
    private static List<String> list;

    public static RiddleService getInstance() {
        return instance;
    }

    public String getRiddle(String input) {
        StringBuilder result = new StringBuilder();
        ArrayList<Character> inputChars = new ArrayList<Character>();
        char[] inputArray = input.toCharArray();

        for (char c : inputArray) {
            inputChars.add(c);
        }
        char first = inputArray[0];
        char last = inputArray[inputArray.length - 1];

        while (true) {
            int randomIndex = (int) (Math.random() * (inputChars.size()) - 1);
            if (inputChars.get(randomIndex) != first) {
                result.append(inputChars.get(randomIndex));
                inputChars.remove(randomIndex);
                break;
            }

        }

        while (!inputChars.isEmpty()) {
            int randomIndex = (int) (Math.random() * (inputChars.size()));
            result.append(inputChars.get(randomIndex));
            inputChars.remove(randomIndex);
        }

        if (result.charAt(result.length() - 1) == last) {
            char tmp = result.charAt(result.length() - 1);
            result.setCharAt(result.length() - 1, result.charAt(result.length() - 2));
            result.setCharAt(result.length() - 2, tmp);
        }

        return result.toString();
       // return input;
    }

    public String getRandomWord() {

        Path dir = Paths.get("/word-list.txt");
        try {
            list = Files.readAllLines(dir);
        } catch (Exception ex) {
            System.out.println("This won't happen");
            return "List is empty";
        }
        return list.get((int)(Math.random() * list.size()));
    }
    public String getRandomWord2() {
        try (InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("word-list.txt")) {

            if (is == null) {
                throw new RuntimeException("word-list.txt not found in resources");
            }

            List<String> list = new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .toList();

            return list.get((int) (Math.random() * list.size())).toUpperCase();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
