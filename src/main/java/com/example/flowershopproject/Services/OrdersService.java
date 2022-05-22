package com.example.flowershopproject.Services;

import com.example.flowershopproject.Actions.Flower;
import com.example.flowershopproject.Actions.Order;
import com.example.flowershopproject.Exceptions.CannotFindOrder;
import com.example.flowershopproject.Exceptions.CouldNotMakeOrdersException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class OrdersService {
    private static List<Order> orders;
    private static final Path ORDERS_PATH = FileSystemService.getPathToFile(new String[]{"config", "orders.json"});

    public OrdersService() {
    }

    public static void loadOrdersFromFile() throws IOException {
        if (!Files.exists(ORDERS_PATH, new LinkOption[0])) {
         //   FileUtils.copyURLToFile(OrdersService.class.getClassLoader().getResource("orders.json"), ORDERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        orders = (List)objectMapper.readValue(ORDERS_PATH.toFile(), new TypeReference<List<Order>>() {
        });
    }
    public static void addOrder( String clientUsername,Flower flower)  {

        Random rand = new Random();
        int orderNo = rand.nextInt(1000);

        orders.add( new Order(clientUsername, flower, orderNo, "pending"));
        persistOrders();
    }


    private static void persistOrders() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(ORDERS_PATH.toFile(), orders);
        } catch (IOException var1) {
            throw new CouldNotMakeOrdersException();
        }
    }
    public static List<Order> getClientOrders(String Username)throws Exception {
        OrdersService.loadOrdersFromFile();
        List<Order> order = new ArrayList<>();
        for (Order or : orders) {
            if (Objects.equals(Username, or.getClientUsername())) {
                order.add(or);
            }
        }
        return order;
    }

    public static List<Order> getFloristOrders(String Username)throws Exception {
        OrdersService.loadOrdersFromFile();
        List<Order> order = new ArrayList<>();
        for (Order or : orders) {
            if (Objects.equals(Username, or.getFlower().getFloristUsername())) {
                order.add(or);
            }
        }
        return order;
    }
    public static List<Order> getCustomerOrders(String Username)throws Exception {
        OrdersService.loadOrdersFromFile();
        List<Order> order = new ArrayList<>();
        for (Order or : orders) {
            if (Objects.equals(Username, or.getClientUsername())) {
                order.add(or);
            }
        }
        return order;
    }

    public static void changeOrderStatus(Order or, String stat) throws Exception {
        for (Order o:orders){
            if(o.equals(or)){
                o.setStatus(stat);
                OrdersService.persistOrders();
                return;

            }
        }
        throw new CannotFindOrder();
    }
}
