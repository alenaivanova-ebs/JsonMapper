package tech.picnic.assignment.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import tech.picnic.assignment.api.OrderStreamProcessor;
import org.apache.commons.lang3.StringUtils;
import tech.picnic.assignment.impl.model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static tech.picnic.assignment.impl.Utils.*;

public class OrderStreamProcessorImpl implements OrderStreamProcessor {

    private final int maxOrders;
    private final Duration maxTime;

    private final ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper();

    public OrderStreamProcessorImpl(int maxOrders, Duration maxTime) {
        this.maxOrders = maxOrders;
        this.maxTime = maxTime;
    }

    @Override
    public void process(InputStream source, OutputStream sink) throws IOException {
        List<Order> inputOrders = readInputStream(source);
        Map<Delivery, List<Order>> filteredAndGroupedOrders = filterOrders(inputOrders);
        List<DeliveryOutput> outputDeliveries = convertOrdersToDeliveries(filteredAndGroupedOrders);
        objectMapper.writeValue(sink, outputDeliveries);
    }

    private List<Order> readInputStream(InputStream source) {
        List<Order> orders = new ArrayList<>();
        int counter = 0;
        String line;
        try (InputStreamReader streamReader = new InputStreamReader(source, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(streamReader);) {
            Instant beginning = Instant.now();
            while (maxOrders > counter && maxTime.compareTo(Duration.between(beginning, Instant.now())) > 0) {
                line = br.readLine();
                counter++;
                if (StringUtils.isBlank(line)) {
                    continue;
                }
                orders.add(objectMapper.readValue(line, Order.class));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to read lines from file", e);
        }
        return orders;
    }

    private static Map<Delivery, List<Order>> filterOrders(List<Order> orders) {
        return orders.stream()
                .filter(p -> p.orderStatus() == Status.CANCELLED || p.orderStatus() == Status.DELIVERED)
                .collect(Collectors.groupingBy((Order::delivery)));
    }

    private static List<DeliveryOutput> convertOrdersToDeliveries(Map<Delivery, List<Order>> filteredAndGroupedOrders) {
        List<DeliveryOutput> deliveriesOutput = new ArrayList<>();
        filteredAndGroupedOrders.forEach((key, value) -> {
            DeliveryOutput deliveryOutputR = new DeliveryOutput(key.deliveryId()
                    , key.deliveryTime()
                    , getDeliveryStatus(value)
                    , convertOrdersToOrdersOutput(value)
                    , value.stream().filter(p -> p.orderStatus() == Status.DELIVERED && p.Amount() != null).mapToInt(Order::Amount).sum());
            deliveriesOutput.add(deliveryOutputR);
        });
        deliveriesOutput.sort(getDeliveryComparator());
        return deliveriesOutput;
    }

    private static Status getDeliveryStatus(List<Order> orders) {
        Optional<Status> deliveryStatus = orders.stream().map(Order::orderStatus).max(getStatusComparator());
        return deliveryStatus.orElse(null);
    }

    private static List<OrderOutput> convertOrdersToOrdersOutput(List<Order> orders) {
        List<OrderOutput> ordersOutput = new ArrayList<>();
        orders.forEach(order -> ordersOutput.add(new OrderOutput(order.orderId(), order.Amount())));
        ordersOutput.sort(getOrderComparator());
        return ordersOutput;
    }
}