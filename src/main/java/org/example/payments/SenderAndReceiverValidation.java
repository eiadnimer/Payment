package org.example.payments;

public class SenderAndReceiverValidation implements Validation {
    @Override
    public ValidationResult isValid(Payment payment) {
        if (payment.isValidSenderAndReceiver()) {
            return new ValidationResult(true);
        }
        return new ValidationResult(false, "Sender or receiver are empty or null");
    }
}
