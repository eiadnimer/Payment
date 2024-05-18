package org.example.payments;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class Payment {
    private final String sender;
    private final String receiver;
    private final BigDecimal amount;
    private final String senderCurrency;
    private final String receiverCurrency;
    private final LocalDate settlementDate;

    public Payment(String sender, String receiver, BigDecimal amount,
                   String senderCurrency, String receiverCurrency,
                   LocalDate settlementDate) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.senderCurrency = senderCurrency;
        this.receiverCurrency = receiverCurrency;
        this.settlementDate = settlementDate;
    }

    public boolean isValidSenderAndReceiver() {
        return !sender.isBlank() && !receiver.isBlank();
    }

    public boolean isValidAmount() {
        return amount.doubleValue() > 0;
    }

    public boolean isValidCurrency() {
        return senderCurrency.equalsIgnoreCase(receiverCurrency);
    }

    public boolean isValidDate() {
        return settlementDate.equals(LocalDate.now());
    }

}
