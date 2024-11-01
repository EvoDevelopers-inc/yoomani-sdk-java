package demonck.evodevelopers.yoomani.sdk.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPaymentDetails {

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
