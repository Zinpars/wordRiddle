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

    public String getRiddle() {
        String input = getRandomWord2();
        input = input.toUpperCase();
        StringBuilder result = new StringBuilder();
        ArrayList<Character> inputChars = new ArrayList<Character>();
        char[] inputArray = input.toCharArray();

        for (char c : inputArray) {
            inputChars.add(c);
        }
        while (!inputChars.isEmpty()) {
            int randomIndex = (int) (Math.random() * (inputChars.size()) - 1);
            result.append(inputChars.get(randomIndex));
            inputChars.remove(randomIndex);
        }

        return result.toString();
       // return input;
    }

    private String getRandomWord() {

        Path dir = Paths.get("/word-list.txt");
        try {
            list = Files.readAllLines(dir);
        } catch (Exception ex) {
            System.out.println("This won't happen");
            return "List is empty";
        }
        return list.get((int)(Math.random() * list.size()));
    }
    private String getRandomWord2() {
        try (InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("word-list.txt")) {

            if (is == null) {
                throw new RuntimeException("word-list.txt not found in resources");
            }

            List<String> list = new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .toList();

            return list.get((int) (Math.random() * list.size()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
