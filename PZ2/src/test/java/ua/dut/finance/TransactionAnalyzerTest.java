package ua.dut.finance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class TransactionAnalyzerTest {

    @Test
    void testCalculateTotalBalance() {
        // Створюємо три транзакції (2 доходи, 1 витрата)
        Transaction t1 = new Transaction("01-01-2024", 100.0, "Дохід");
        Transaction t2 = new Transaction("02-01-2024", -50.0, "Витрата");
        Transaction t3 = new Transaction("03-01-2024", 150.0, "Дохід");

        List<Transaction> list = Arrays.asList(t1, t2, t3);

        double result = TransactionAnalyzer.calculateTotalBalance(list);

        Assertions.assertEquals(200.0, result);
    }

    @Test
    void testCountTransactionsByMonth() {
        // Створюємо кілька транзакцій у різні місяці
        Transaction t1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction t2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction t3 = new Transaction("05-03-2023", 100.0, "Дохід");

        List<Transaction> list = Arrays.asList(t1, t2, t3);

        int feb = TransactionAnalyzer.countTransactionsByMonth(list, "02-2023");

        int mar = TransactionAnalyzer.countTransactionsByMonth(list, "03-2023");

        Assertions.assertEquals(2, feb);
        Assertions.assertEquals(1, mar);
    }
}
