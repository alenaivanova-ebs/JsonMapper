package tech.picnic.assignment.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public record DeliveryOutput(@JsonProperty("delivery_id") String deliveryId,
                             @JsonProperty("delivery_time") String deliveryTime,
                             @JsonProperty("delivery_status") Status deliveryStatus,
                             List<OrderOutput> orders,
                             @JsonProperty("total_amount") Integer totalAmount) {
}