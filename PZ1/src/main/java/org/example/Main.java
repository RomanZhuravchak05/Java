package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");


        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics));
        products.add(new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та автономністю", smartphones));
        products.add(new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories));


        Cart cart = new Cart();


        List<Order> orderHistory = new ArrayList<>();

        while (true) {
            System.out.println("\n=== МЕНЮ ІНТЕРНЕТ-МАГАЗИНУ ===");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Видалити товар з кошика");
            System.out.println("5 - Оформити замовлення");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товарів");
            System.out.println("0 - Вийти");
            System.out.print("Ваш вибір: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    products.forEach(System.out::println);
                    break;

                case 2:
                    System.out.print("Введіть ID товару: ");
                    int addId = scanner.nextInt();
                    products.stream()
                            .filter(p -> p.getId() == addId)
                            .findFirst()
                            .ifPresentOrElse(cart::addProduct,
                                    () -> System.out.println("Товар з таким ID не знайдено."));
                    break;

                case 3:
                    System.out.println(cart);
                    break;

                case 4:
                    System.out.print("Введіть ID товару для видалення: ");
                    int removeId = scanner.nextInt();
                    Product toRemove = cart.getProducts().stream()
                            .filter(p -> p.getId() == removeId)
                            .findFirst()
                            .orElse(null);
                    if (toRemove != null) {
                        cart.removeProduct(toRemove);
                        System.out.println("Товар видалено з кошика.");
                    } else {
                        System.out.println("Товар з таким ID у кошику не знайдено.");
                    }
                    break;

                case 5:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед замовленням.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;

                case 6:
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        System.out.println("=== Історія замовлень ===");
                        orderHistory.forEach(System.out::println);
                    }
                    break;

                case 7:
                    System.out.println("Пошук за (1 - назва, 2 - категорія): ");
                    int searchOption = scanner.nextInt();
                    scanner.nextLine(); // спожити Enter

                    if (searchOption == 1) {
                        System.out.print("Введіть назву товару: ");
                        String name = scanner.nextLine().toLowerCase();
                        products.stream()
                                .filter(p -> p.getName().toLowerCase().contains(name))
                                .forEach(System.out::println);
                    } else if (searchOption == 2) {
                        System.out.print("Введіть назву категорії: ");
                        String categoryName = scanner.nextLine().toLowerCase();
                        products.stream()
                                .filter(p -> p.getCategory().getName().toLowerCase().contains(categoryName))
                                .forEach(System.out::println);
                    } else {
                        System.out.println("Невірний варіант пошуку.");
                    }
                    break;

                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;

                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
            }
        }
    }
}








