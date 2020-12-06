package ru.vsu.cs.skofenko;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {
    private static final Map<String, Integer> map = createMonthsMap();

    private static Map<String, Integer> createMonthsMap() {
        Map<String, Integer> temp = new HashMap<>();
        temp.put("января", 1);
        temp.put("февраля", 2);
        temp.put("марта", 3);
        temp.put("апреля", 4);
        temp.put("мая", 5);
        temp.put("июня", 6);
        temp.put("июля", 7);
        temp.put("августа", 8);
        temp.put("сентября", 9);
        temp.put("октября", 10);
        temp.put("ноября", 11);
        temp.put("декабря", 12);
        return temp;
    }
    /*
    наверное, на таком маленьком количестве элементов было бы рациональней, что-то типо
    этого, но раз уж в задании просят использовать коллекции...

    private static int monthNameToInt(String group) {
        return switch (group) {
            case "января" -> 1;
            case "февраля" -> 2;
            case "марта" -> 3;
            case "апреля" -> 4;
            case "мая" -> 5;
            case "июня" -> 6;
            case "июля" -> 7;
            case "августа" -> 8;
            case "сентября" -> 9;
            case "октября" -> 10;
            case "ноября" -> 11;
            case "декабря" -> 12;
            default -> 0;
        };
    }
    */

    public static List<String> findDates(List<String> list) {
        List<String> datesList = new ArrayList<>();
        List<Pattern> patterns = new ArrayList<>();
        patterns.add(Pattern.compile("(\\d{1,2})\\.(\\d{2})\\.(\\d{4})"));
        patterns.add(Pattern.compile("(\\d{1,2})\\s([а-я|А-Я]+)\\s(\\d{4})"));
        patterns.add(Pattern.compile("(\\d{1,2})\\s([а-я|А-Я]+)(?!\\s\\d{4})"));
        for (String line : list) {
            for (Pattern pattern : patterns) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    int index1 = matcher.start();
                    int index2 = matcher.end();
                    if (isDateOk(Integer.parseInt(matcher.group(1)), matcher.group(2),
                            (matcher.groupCount() > 2) ? Integer.parseInt(matcher.group(3)) : 0)) {
                        String str = line.substring(index1, index2);
                        datesList.add(str);
                    }
                }
            }
        }
        return datesList;
    }

    private static boolean isDateOk(int day, String month, int year) {
        int m;
        try {
            m = Integer.parseInt(month);
        } catch (NumberFormatException e) {
            try {
                m = map.get(month.toLowerCase());
            } catch (NullPointerException exception) {
                return false;
            }
        }
        return isDateOk(day, m, year);
    }

    private static boolean isDateOk(int day, int month, int year) {
        if (month > 12 || month == 0 || day == 0) {
            return false;
        }
        if (year % 4 == 0 && month == 2 && day > 29) { //когда год не указан, будем считать его високосным
            return false;
        }
        if (year % 4 != 0 && month == 2 && day > 28) {
            return false;
        }
        if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day > 31) {
            return false;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            return false;
        }
        return true;
    }
}
