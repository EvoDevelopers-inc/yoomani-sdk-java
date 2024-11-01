import demonck.evodevelopers.yoomani.sdk.dto.payments.PaymentDetails;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;


public class QuickPay {

    @Test
    void createLink() throws UnsupportedEncodingException {
        PaymentDetails paymentDetails = new PaymentDetails("id кошелька", "shop","AC", 150.00, "LABEL", "коммент", "Artem", "kusi", "asd", "artem@", "79298483500", "Krasnodar", "street krasnay", "","", "","","");

        demonck.evodevelopers.yoomani.sdk.quickpay.QuickPay quickPay = new demonck.evodevelopers.yoomani.sdk.quickpay.QuickPay(paymentDetails);

        System.out.println(quickPay.requestData());
    }
}
