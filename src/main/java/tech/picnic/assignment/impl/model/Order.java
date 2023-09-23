package tech.picnic.assignment.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public record Order(@JsonProperty("order_id") String orderId,
                    @JsonProperty("order_status") Status orderStatus,
                    @JsonProperty("delivery") Delivery delivery,
                    @JsonProperty("amount") Integer Amount
) {

}

