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
        if (paymentDetails.getStreet() != null) payload.put("street", paymentDetails.getStreet());
        if (paymentDetails.getBuilding() != null) payload.put("building", paymentDetails.getBuilding());
        if (paymentDetails.getSuite() != null) payload.put("suite", paymentDetails.getSuite());
        if (paymentDetails.getFlat() != null) payload.put("flat", paymentDetails.getFlat());
        if (paymentDetails.getZip() != null) payload.put("zip", paymentDetails.getZip());
        if (paymentDetails.getFirstname() != null) payload.put("firstname", paymentDetails.getFirstname());
        if (paymentDetails.getLastname() != null) payload.put("lastname", paymentDetails.getLastname());
        if (paymentDetails.getFathersname() != null) payload.put("fathersname", paymentDetails.getFathersname());
        if (paymentDetails.getEmail() != null) payload.put("email", paymentDetails.getEmail());
        if (paymentDetails.getPhone() != null) payload.put("phone", paymentDetails.getPhone());
        if (paymentDetails.getCity() != null) payload.put("city", paymentDetails.getCity());
        if (paymentDetails.getSender() != null) payload.put("sender", paymentDetails.getSender());
        payload.put("quickpay_form", paymentDetails.getQuickpayForm());
        payload.put("paymentType", paymentDetails.getPaymentType());
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
            // Создаем URL и соединение
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Отправляем запрос
            try (OutputStream os = connection.getOutputStream()) {
                os.write(new byte[0]); // Пустое тело запроса
                os.flush();
            }

            // Получаем ответ
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                // Возвращаем URI из ответа
                return connection.getURL().toString();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Логируем ошибку
        }

        return uri; // Возвращаем оригинальный URI, если запрос не удался
    }
}
