package ua.dut.finance;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {

    public static List<Transaction> readTransactions(String filePath) {
        List<Transaction> list = new ArrayList<>();

        try {
            URL url = new URL(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                Transaction t = new Transaction(parts[0], Double.parseDouble(parts[1]), parts[2]);
                list.add(t);
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }

        return list;
    }
}

