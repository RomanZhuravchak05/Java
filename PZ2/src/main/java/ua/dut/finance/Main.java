package ua.dut.finance;

import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";


        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);


        double balance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(balance);


        String monthYear = "01-2024";
        int count = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, count);


        List<Transaction> top = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(top);


        Transaction max = TransactionAnalyzer.findMaxExpenseByPeriod(transactions, "01-01-2024", "31-12-2024");
        Transaction min = TransactionAnalyzer.findMinExpenseByPeriod(transactions, "01-01-2024", "31-12-2024");
        TransactionReportGenerator.printMaxAndMinExpenses(max, min);


        Map<String, Double> expenses = TransactionAnalyzer.calculateExpensesByCategory(transactions);
        TransactionReportGenerator.printCategoryReportWithStars(expenses);
    }
}


