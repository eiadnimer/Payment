package org.example.payments;

import java.util.ArrayList;
import java.util.List;

public class PaymentValidations {
    private final List<Validation> validations = new ArrayList<>();

    public PaymentValidations() {
        validations.add(new SenderAndReceiverValidation());
        validations.add(new AmountValidation());
        validations.add(new CurrencyValidation());
        validations.add(new SettlementValidation());
    }

    public ValidationResults checkIfValid(Payment payment) {
        ValidationResults results = new ValidationResults();
        for (Validation validation : validations) {
            ValidationResult result = validation.isValid(payment);
            results.add(result);
        }
        return results;
    }
}

