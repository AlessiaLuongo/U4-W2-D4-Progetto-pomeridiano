package alessia;

import alessia.entities.Customer;
import alessia.entities.Order;
import alessia.entities.Product;
import com.github.javafaker.Faker;
import jdk.jfr.Category;

import java.awt.color.ProfileDataException;
import java.rmi.MarshalledObject;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {


        //CREAZIONE PRODOTTI

        Product product1 = new Product("CD ANNIHILATOR", "Music", 25.50);
        Product product2 = new Product("CD METALLICA", "Music", 18.50);
        Product product3 = new Product("CD INSANITY ALERT", "Music", 25.5);
        Product product4 = new Product("Chiodo", "Clothes", 80.00);
        Product product5 = new Product("Jeans", "Clothes", 15.00);
        Product product6 = new Product("Dress", "Clothes", 18.50);
        Product product7 = new Product("Pasta", "Food", 2.80);
        Product product8 = new Product("Olive Oil", "Food", 12.80);
        Product product9 = new Product("Valpolicella Superiore", "Wine", 19.80);
        Product product10 = new Product("Bon Rye", "Wine", 13.50);


        //CREAZIONE WAREHOUSE

        List<Product> wareHouse = new ArrayList<>();
        wareHouse.add(product1);
        wareHouse.add(product2);
        wareHouse.add(product3);
        wareHouse.add(product4);
        wareHouse.add(product5);
        wareHouse.add(product6);
        wareHouse.add(product7);
        wareHouse.add(product8);
        wareHouse.add(product9);
        wareHouse.add(product10);


        // CREAZIONE CLIENTI

        Supplier<Customer> customerSupplier = () -> {
            Faker faker = new Faker(Locale.ITALY);
            return new Customer(faker.rickAndMorty().character(), faker.name());
        };
        List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            customerList.add(customerSupplier.get());
        }
        customerList.forEach(System.out::println);


        // CREAZIONE ORDINI - DATE

        LocalDate firstOrderDate = LocalDate.now();
        LocalDate secondOrderDate = LocalDate.now().minusMonths(2);
        LocalDate thirdOrderDate = LocalDate.now().minusMonths(3);
        LocalDate firstDeliveryDate = LocalDate.now().plusDays(5);
        LocalDate secondDeliveryDate = LocalDate.now().plusMonths(4);
        LocalDate thirdDeliveryDate = LocalDate.now().plusMonths(5);


        Order order1 = new Order("Success", firstOrderDate, firstDeliveryDate, customerList.get(1));
        Order order2 = new Order("Success", secondOrderDate, secondDeliveryDate, customerList.get(2));
        Order order3 = new Order("Success", thirdOrderDate, thirdDeliveryDate, customerList.get(3));
        Order order4 = new Order("Success", firstOrderDate, firstDeliveryDate, customerList.get(4));
        Order order5 = new Order("Success", firstOrderDate, firstDeliveryDate, customerList.get(1));
        Order order6 = new Order("Success", firstOrderDate, firstDeliveryDate, customerList.get(3));
        Order order7 = new Order("Success", firstOrderDate, firstDeliveryDate, customerList.get(3));

        order1.getProducts().add(product1);
        order1.getProducts().add(product3);
        order1.getProducts().add(product5);
        order2.getProducts().add(product2);
        order2.getProducts().add(product4);
        order3.getProducts().add(product6);
        order3.getProducts().add(product7);
        order3.getProducts().add(product8);
        order4.getProducts().add(product9);
        order4.getProducts().add(product10);
        order5.getProducts().add(product5);
        order5.getProducts().add(product2);
        order6.getProducts().add(product10);
        order6.getProducts().add(product6);
        order6.getProducts().add(product7);
        order7.getProducts().add(product1);
        order7.getProducts().add(product3);
        order7.getProducts().add(product10);

        List<Order> listOfOrders = new ArrayList<>();
        listOfOrders.add(order1);
        listOfOrders.add(order2);
        listOfOrders.add(order3);
        listOfOrders.add(order4);
        listOfOrders.add(order5);
        listOfOrders.add(order6);
        listOfOrders.add(order7);

        System.out.println("-----------------------------ESERCIZIO 1--------------------------------------------------");


        Map<Customer, List<Order>> orderFromCustomer = listOfOrders.stream().collect(Collectors.groupingBy(Order::getCustomer));
        orderFromCustomer.forEach((customer, orders) -> {
            System.out.println("Customer: " + customer);
            System.out.println("Orders:");
            orders.forEach(System.out::println);

        });

        System.out.println("-------------------------------ESERCIZIO 2------------------------------------------------");

        Map<Customer, Double> totalSumForEachCustomer = listOfOrders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer,
                        Collectors.summingDouble(order -> order.getProducts().stream()
                                .mapToDouble(Product::getPrice).sum())));

        totalSumForEachCustomer.forEach((customer, totalSum) -> {
            System.out.println("Customer: " + customer.getName() + ", Total Shopping Cart: " + totalSum + "€");
        });


        System.out.println("-------------------------------ESERCIZIO 3------------------------------------------------");

        List<Product> theMostExpensiveItems = wareHouse.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .limit(5)
                .toList();
        System.out.println(theMostExpensiveItems);

        System.out.println("-------------------------------ESERCIZIO 4------------------------------------------------");

        Double averagePrices = wareHouse.stream().mapToDouble(Product::getPrice).average().getAsDouble();
        System.out.println(averagePrices + "€");

        System.out.println("-------------------------------ESERCIZIO 5------------------------------------------------");

        Map<String, List<Product>> totalPriceForCategory = wareHouse.stream().collect(Collectors.groupingBy(Product::getCategory));Collectors.summingDouble(Product::getPrice);
        totalPriceForCategory.forEach((category, totalPrice)->{
            System.out.println("Category: " + category+ ", Total Sum: " + totalPrice + "€");
       });

        System.out.println("-------------------------------ESERCIZIO 6------------------------------------------------");
    }}
