package pt.ipleiria.estg.dei.ei.dae.academics.exceptions;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ConstraintViolation;
import java.util.stream.Collectors;

public class MyConstraintViolationException extends Exception {
    public MyConstraintViolationException(ConstraintViolationException e) {
        super(getConstraintViolationMessages(e));
    }

    private static String getConstraintViolationMessages(ConstraintViolationException e) {
        return e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));
    }

}