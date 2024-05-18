package org.example.payments;

public class SettlementValidation implements Validation {
    @Override
    public ValidationResult isValid(Payment payment) {
        if (payment.isValidDate()) {
            return new ValidationResult(true);
        }
        return new ValidationResult(false, "Settlement date is not today");
    }
}
