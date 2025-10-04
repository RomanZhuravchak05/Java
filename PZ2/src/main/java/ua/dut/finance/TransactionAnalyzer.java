package ua.dut.finance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public abstract class TransactionAnalyzer {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static double calculateTotalBalance(List<Transaction> list) {
        double sum = 0;
        for (Transaction t : list) {
            sum += t.getAmount();
        }
        return sum;
    }

    public static int countTransactionsByMonth(List<Transaction> list, String monthYear) {
        int count = 0;
        for (Transaction t : list) {
            LocalDate date = LocalDate.parse(t.getDate(), FORMAT);
            String current = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (current.equals(monthYear)) count++;
        }
        return count;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> list) {
        return list.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }


    public static Transaction findMaxExpenseByPeriod(List<Transaction> list, String from, String to) {
        LocalDate start = LocalDate.parse(from, FORMAT);
        LocalDate end = LocalDate.parse(to, FORMAT);

        return list.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), FORMAT);
                    return !date.isBefore(start) && !date.isAfter(end);
                })
                .min(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }

    public static Transaction findMinExpenseByPeriod(List<Transaction> list, String from, String to) {
        LocalDate start = LocalDate.parse(from, FORMAT);
        LocalDate end = LocalDate.parse(to, FORMAT);

        return list.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), FORMAT);
                    return !date.isBefore(start) && !date.isAfter(end);
                })
                .max(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }

    public static Map<String, Double> calculateExpensesByCategory(List<Transaction> list) {
        Map<String, Double> result = new HashMap<>();

        for (Transaction t : list) {
            if (t.getAmount() < 0) {
                result.put(t.getDescription(),
                        result.getOrDefault(t.getDescription(), 0.0) + Math.abs(t.getAmount()));
            }
        }
        return result;
    }
}

