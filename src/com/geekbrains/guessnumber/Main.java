package com.geekbrains.guessnumber;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Выберите игру 0 - Угадай число 1 - Угадай слово");
        int answer = getAnswer(0, 1);
        if (answer == 0) {
            guessNumber();
        } else {
            guessWord();
        }
        scanner.close();
    }

    /**
     * Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается 3 попытки
     * угадать это число. При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число,
     * чем загаданное, или меньше.
     * После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
     */
    private static void guessNumber() {
        int attempts = 3;
        int min = 0;
        int max = 9;
        do {
            int number = getRandom(min, max);
            System.out.printf("Угадайте число от %d - %d%n", min, max);
            boolean win = false;
            for (int i = 1; i <= attempts; i++) {
                System.out.println("Попытка #" + i);
                int inputNumber = getAnswer(min, max);
                if (inputNumber == number) {
                    win = true;
                    break;
                } else if (inputNumber > number) {
                    System.out.println("Больше загаданного числа");
                } else {
                    System.out.println("Меньше загаданного числа");
                }
            }
            String result = win ? "выиграли" : "проиграли";
            System.out.println("Вы " + result);
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
        } while (getAnswer(0, 1) == 1);
    }

    /**
     * При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным
     * словом и сообщает, правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы,
     * которые стоят на своих местах.
     */
    private static void guessWord() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        int patternLength = 15;
        String word = words[getRandom(0, words.length - 1)];
        boolean valid;
        do {
            System.out.println("Введите слово");
            String answer = scanner.nextLine();
            valid = word.equals(answer);
            if (!valid) {
                StringBuilder result = new StringBuilder("Неверно ");
                for (int i = 0; i < patternLength; i++) {
                    result.append(i < word.length() && i < answer.length() && word.charAt(i) == answer.charAt(i) ? word.charAt(i) : '#');
                }
                System.out.println(result);
            }
        } while (!valid);
        System.out.println("Верно");
    }

    /**
     * Возвращает случайное число из диапазона
     *
     * @param min <code>int</code> min value
     * @param max <code>int</code> max value
     * @return <code>int</code>
     */
    private static int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /**
     * Ввод в консоле числа с проверкой вхождения в диапазон
     *
     * @param min <code>int</code> min value
     * @param max <code>int</code> max value
     * @return <code>int</code>
     */
    private static int getAnswer(int min, int max) {
        int num = 0;
        boolean valid = false;
        do {
            try {
                num = Integer.parseInt(scanner.nextLine());
                if (num < min || num > max) {
                    throw new IllegalArgumentException();
                }
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода.");
            }
        } while (!valid);
        return num;
    }

}