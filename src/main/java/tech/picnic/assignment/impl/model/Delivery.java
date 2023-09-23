package tech.picnic.assignment.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record Delivery(@JsonProperty("delivery_id") String deliveryId,
                       @JsonProperty("delivery_time") String deliveryTime) {

}
