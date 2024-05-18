package org.example.payments;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationResults {

    private final List<ValidationResult> invalidResults = new ArrayList<>();

    public void add(ValidationResult result) {
        if (!result.isValid()) {
            invalidResults.add(result);
        }
    }

    public boolean isValid() {
        return invalidResults.isEmpty();
    }
}
