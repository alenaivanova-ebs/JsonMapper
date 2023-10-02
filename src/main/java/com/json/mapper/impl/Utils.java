package com.json.mapper.impl;

import com.json.mapper.impl.model.DeliveryOutput;
import com.json.mapper.impl.model.OrderOutput;
import com.json.mapper.impl.model.Status;

import java.util.Comparator;

public class Utils {

     static Comparator<OrderOutput> getOrderComparator() {
        return Comparator.comparing(OrderOutput::orderId).reversed();
    }

    static Comparator<Status> getStatusComparator() {
        return Comparator.comparing(Status::getValue);
    }

    static Comparator<DeliveryOutput> getDeliveryComparator() {
        return Comparator.comparing(DeliveryOutput::deliveryTime).thenComparing(DeliveryOutput::deliveryId);
    }
}
