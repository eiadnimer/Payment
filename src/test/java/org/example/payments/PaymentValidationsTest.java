package org.example.payments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class PaymentValidationsTest {

    @Test
    public void sender_and_receiver_should_not_be_null() {
        BigDecimal bd = new BigDecimal(4);
        Payment payment = new Payment("eiad", "rafa", bd,
                "Dollar", "Dollar", LocalDate.now());

        PaymentValidations pv = new PaymentValidations();
        boolean isValid = pv.checkIfValid(payment).isValid();

        Assertions.assertTrue(isValid);
    }

    @Test
    public void sender_and_receiver_should_return_false_if_one_of_them_is_null_or_empty() {
        BigDecimal bd = new BigDecimal(5);
        Payment payment = new Payment("", "rafa", bd,
                "USD", "usd", LocalDate.now());
        PaymentValidations pv = new PaymentValidations();

        ValidationResults validationResults = pv.checkIfValid(payment);

        Assertions.assertFalse(validationResults.isValid());
        Assertions.assertEquals("Sender or receiver are empty or null",
                validationResults.getInvalidResults().get(0).getErrorMessage());
    }

    @Test
    public void sender_and_receiver_should_return_false_if_both_are_null_or_empty() {
        BigDecimal bd = new BigDecimal(5);
        Payment payment = new Payment("", "", bd,
                "USD", "usd", LocalDate.now());
        PaymentValidations pv = new PaymentValidations();

        ValidationResults validationResults = pv.checkIfValid(payment);

        Assertions.assertFalse(validationResults.isValid());
        Assertions.assertEquals("Sender or receiver are empty or null",
                validationResults.getInvalidResults().get(0).getErrorMessage());
    }

    @Test
    public void amount_should_not_be_negative() {
        BigDecimal bd = new BigDecimal(54);
        Payment payment = new Payment("eiad", "rafa", bd,
                "Dollar", "Dollar", LocalDate.now());
        PaymentValidations pv = new PaymentValidations();
        boolean isValid = pv.checkIfValid(payment).isValid();
        Assertions.assertTrue(isValid);
    }

    @Test
    public void if_amount_is_zero_should_return_false() {
        BigDecimal bd = new BigDecimal(0);
        Payment payment = new Payment("eiad", "rafa", bd,
                "USD", "usd", LocalDate.now());
        PaymentValidations pv = new PaymentValidations();

        ValidationResults validationResults = pv.checkIfValid(payment);

        Assertions.assertFalse(validationResults.isValid());
        Assertions.assertEquals("Amount is not positive",
                validationResults.getInvalidResults().get(0).getErrorMessage());
    }

    @Test
    public void settlement_day_must_be_today() {
        BigDecimal bd = new BigDecimal(522);
        Payment payment = new Payment("eiad", "rafa", bd,
                "Dollar", "Dollar", LocalDate.now());
        PaymentValidations pv = new PaymentValidations();
        boolean isValid = pv.checkIfValid(payment).isValid();
        Assertions.assertTrue(isValid);
    }

    @Test
    public void if_settlement_day_is_before_today_then_the_return_result_should_be_false() {
        BigDecimal bd = new BigDecimal(55);
        Payment payment = new Payment("eiad", "rafa", bd,
                "USD", "usd", LocalDate.parse("2023-08-01"));
        PaymentValidations pv = new PaymentValidations();

        ValidationResults validationResults = pv.checkIfValid(payment);

        Assertions.assertFalse(validationResults.isValid());
        Assertions.assertEquals("Settlement date is not today",
                validationResults.getInvalidResults().get(0).getErrorMessage());
    }

    @Test
    public void if_settlement_day_is_after_today_then_the_return_result_should_be_false() {
        BigDecimal bd = new BigDecimal(55);
        Payment payment = new Payment("eiad", "rafa", bd,
                "USD", "usd", LocalDate.parse("2050-08-01"));
        PaymentValidations pv = new PaymentValidations();

        ValidationResults validationResults = pv.checkIfValid(payment);

        Assertions.assertFalse(validationResults.isValid());
        Assertions.assertEquals("Settlement date is not today",
                validationResults.getInvalidResults().get(0).getErrorMessage());
    }


    @Test
    public void sender_currency_and_receiver_currency_should_be_the_same_currency() {
        BigDecimal bd = new BigDecimal(522);
        Payment payment = new Payment("eiad", "rafa", bd,
                "USD", "usd", LocalDate.now());
        PaymentValidations pv = new PaymentValidations();
        boolean isValid = pv.checkIfValid(payment).isValid();
        Assertions.assertTrue(isValid);
    }

    @Test
    public void sender_currency_and_receiver_currency_are_not_equal_so_the_return_value_is_false() {
        BigDecimal bd = new BigDecimal(55);
        Payment payment = new Payment("eiad", "rafa", bd,
                "JD", "usd", LocalDate.now());
        PaymentValidations pv = new PaymentValidations();

        ValidationResults validationResults = pv.checkIfValid(payment);

        Assertions.assertFalse(validationResults.isValid());
        Assertions.assertEquals("Sender currency and receiver currency are not equal",
                validationResults.getInvalidResults().get(0).getErrorMessage());
    }

    @Test
    public void negative_amount() {
        BigDecimal bd = new BigDecimal(-2);
        Payment payment = new Payment("eiad", "rafa", bd,
                "USD", "usd", LocalDate.now());
        PaymentValidations pv = new PaymentValidations();

        ValidationResults validationResults = pv.checkIfValid(payment);

        Assertions.assertFalse(validationResults.isValid());
        Assertions.assertEquals("Amount is not positive",
                validationResults.getInvalidResults().get(0).getErrorMessage());
    }
}