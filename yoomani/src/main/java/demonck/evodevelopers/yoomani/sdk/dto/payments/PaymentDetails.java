package demonck.evodevelopers.yoomani.sdk.dto.payments;

import demonck.evodevelopers.yoomani.sdk.dto.user.UserPaymentDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentDetails {

    private final String receiver;
    private final String quickpayForm;
    private final PaymentType paymentType;
    private final double sum;
    private final String label;
    private final UserPaymentDetails userPaymentDetails;
}
