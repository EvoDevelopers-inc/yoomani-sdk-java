package demonck.evodevelopers.yoomani.sdk.quickpay;

import demonck.evodevelopers.yoomani.sdk.dto.payments.PaymentDetails;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class QuickPay {

    protected final PaymentDetails paymentDetails;

    public QuickPay(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String requestData() throws UnsupportedEncodingException {

        Map<String, String> payload = new HashMap<>();
        payload.put("receiver", paymentDetails.getReceiver());
        if (paymentDetails.getUserPaymentDetails().getStreet() != null) payload.put("street", paymentDetails.getUserPaymentDetails().getStreet());
        if (paymentDetails.getUserPaymentDetails().getBuilding() != null) payload.put("building", paymentDetails.getUserPaymentDetails().getBuilding());
        if (paymentDetails.getUserPaymentDetails().getSuite() != null) payload.put("suite", paymentDetails.getUserPaymentDetails().getSuite());
        if (paymentDetails.getUserPaymentDetails().getFlat() != null) payload.put("flat", paymentDetails.getUserPaymentDetails().getFlat());
        if (paymentDetails.getUserPaymentDetails().getZip() != null) payload.put("zip", paymentDetails.getUserPaymentDetails().getZip());
        if (paymentDetails.getUserPaymentDetails().getFirstname() != null) payload.put("firstname", paymentDetails.getUserPaymentDetails().getFirstname());
        if (paymentDetails.getUserPaymentDetails().getLastname() != null) payload.put("lastname", paymentDetails.getUserPaymentDetails().getLastname());
        if (paymentDetails.getUserPaymentDetails().getFathersname() != null) payload.put("fathersname", paymentDetails.getUserPaymentDetails().getFathersname());
        if (paymentDetails.getUserPaymentDetails().getEmail() != null) payload.put("email", paymentDetails.getUserPaymentDetails().getEmail());
        if (paymentDetails.getUserPaymentDetails().getPhone() != null) payload.put("phone", paymentDetails.getUserPaymentDetails().getPhone());
        if (paymentDetails.getUserPaymentDetails().getCity() != null) payload.put("city", paymentDetails.getUserPaymentDetails().getCity());
        if (paymentDetails.getUserPaymentDetails().getSender() != null) payload.put("sender", paymentDetails.getUserPaymentDetails().getSender());
        payload.put("quickpay_form", paymentDetails.getQuickpayForm());
        payload.put("paymentType", paymentDetails.getPaymentType().getType());
        payload.put("sum", String.valueOf(paymentDetails.getSum()));
        payload.put("label", paymentDetails.getLabel());

        // Формируем строку запроса
        StringBuilder queryParams = new StringBuilder();
        for (Map.Entry<String, String> entry : payload.entrySet()) {
            if (queryParams.length() != 0) {
                queryParams.append("&");
            }
            queryParams.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            queryParams.append("=");
            queryParams.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        String uri = "https://yoomoney.ru/quickpay/confirm.xml?" + queryParams.toString().replace("_", "-");

        try {

            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


            try (OutputStream os = connection.getOutputStream()) {
                os.write(new byte[0]);
            }


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uri;
    }
}
