package org.example.payments;

public class AmountValidation implements Validation {
    @Override
    public ValidationResult isValid(Payment payment) {
        if (payment.isValidAmount()) {
            return new ValidationResult(true);
        }
        return new ValidationResult(false, "Amount is not positive");
    }
}
