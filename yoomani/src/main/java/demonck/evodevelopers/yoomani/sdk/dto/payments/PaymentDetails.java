package demonck.evodevelopers.yoomani.sdk.dto.payments;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentDetails {

    private final String receiver;
    private final String quickpayForm;
    private final String paymentType;
    private final double sum;
    private final String label;
    private final String comment;
    private final String firstname;
    private final String lastname;
    private final String fathersname;
    private final String email;
    private final String phone;
    private final String city;
    private final String street;
    private final String building;
    private final String suite;
    private final String flat;
    private final String zip;
    private final String sender;
}
