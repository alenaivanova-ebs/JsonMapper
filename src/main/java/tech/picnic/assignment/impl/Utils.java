package tech.picnic.assignment.impl;

import tech.picnic.assignment.impl.model.DeliveryOutput;
import tech.picnic.assignment.impl.model.OrderOutput;
import tech.picnic.assignment.impl.model.Status;

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
