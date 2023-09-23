package tech.picnic.assignment.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record OrderOutput(@JsonProperty("order_id") String orderId,
                          @JsonProperty("amount") Integer amount) {

}
