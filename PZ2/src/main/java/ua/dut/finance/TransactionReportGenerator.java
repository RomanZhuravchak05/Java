package ua.dut.finance;

import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double balance) {
        System.out.println("Загальний баланс: " + balance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> top) {
        System.out.println("10 найбільших витрат:");
        for (Transaction t : top) {
            System.out.println(t.getDescription() + " -> " + t.getAmount());
        }
    }

    public static void printMaxAndMinExpenses(Transaction max, Transaction min) {
        System.out.println("\nВитрати за період:");
        if (max != null)
            System.out.println("Найбільша витрата: " + max.getDescription() + " (" + max.getAmount() + ")");
        if (min != null)
            System.out.println("Найменша витрата: " + min.getDescription() + " (" + min.getAmount() + ")");
    }

    public static void printCategoryReportWithStars(Map<String, Double> expenses) {
        System.out.println("\nЗвіт по категоріях (1 * = 1000 грн):");
        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            int stars = (int) (entry.getValue() / 1000);
            String line = "*".repeat(Math.max(1, stars));
            System.out.println(entry.getKey() + ": " + line + " (" + entry.getValue() + " грн)");
        }
    }
}
