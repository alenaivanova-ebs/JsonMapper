package tech.picnic.assignment.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public record OrderOutput(@JsonProperty("order_id") String orderId,
                          @JsonProperty("amount") Integer amount) {

}
