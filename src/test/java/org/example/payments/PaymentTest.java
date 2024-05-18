package org.example.payments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentTest {

    @Test
    public void payment_should_fail_if_sender_is_blank_or_null()  {
        BigDecimal amount = new BigDecimal(4);
        Payment payment = new Payment("", "rafa", amount,
                "Dollar", "Dollar", LocalDate.now());

        Assertions.assertFalse(payment.isValidSenderAndReceiver());
    }

    @Test
    public void payment_should_fail_if_receiver_is_blank_or_null() {
        BigDecimal amount = new BigDecimal(4);
        Payment payment = new Payment("eiad", "", amount,
                "Dollar", "Dollar", LocalDate.now());

        Assertions.assertFalse(payment.isValidSenderAndReceiver());
    }

    @Test
    public void if_amount_is_minus_should_return_false() {
        BigDecimal amount = new BigDecimal(-6);
        Payment payment = new Payment("eiad", "rafa", amount,
                "Dollar", "Dollar", LocalDate.now());

        Assertions.assertFalse(payment.isValidAmount());
    }

    @Test
    public void payment_should_fail_if_senderCurrency_is_not_equal_to_receiverCurrency() {
        BigDecimal amount = new BigDecimal(4);
        Payment payment = new Payment("", "rafa", amount,
                "jd", "Dollar", LocalDate.now());

        Assertions.assertFalse(payment.isValidCurrency());
    }

    @Test
    public void payment_should_fail_if_settlementDate_is_not_now() {
        BigDecimal amount = new BigDecimal(4);
        Payment payment = new Payment("eiad", "rafa", amount,
                "Dollar", "Dollar", LocalDate.parse("2022-06-06"));

        Assertions.assertFalse(payment.isValidDate());
    }
}

