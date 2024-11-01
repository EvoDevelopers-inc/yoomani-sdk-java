package demonck.evodevelopers.yoomani.sdk.dto.payments;

public enum PaymentType {

    walletYoomani("PC"),
    bankCard("AC");

    private String type;

    PaymentType(String type)
    {
        this.type = type;
    }

    public String getType(){return type;}
}
