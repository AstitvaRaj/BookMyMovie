package com.bookmymovie.payments.dto.request;


import com.bookmymovie.payments.enums.Currency;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateOrderRequest {

    Long amount;
    Currency currency = Currency.INR;
    String notes;
    String receipt;

}
