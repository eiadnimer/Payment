package org.example.payments;

public class CurrencyValidation implements Validation {
    @Override
    public ValidationResult isValid(Payment payment) {
        if (payment.isValidCurrency()) {
            return new ValidationResult(true);
        }
        return new ValidationResult(false,
                "Sender currency and receiver currency are not equal");
    }
}
