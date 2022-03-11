package com.example.card_server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ObjectHandler {

    @Autowired
    private Validator validator;

    public <T> T handleObject(String content, Class<T> objectClass) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        T handleObject = mapper.readValue(content, objectClass);

        validateObject(handleObject);

        return handleObject;
    }

    private <T> void validateObject(T handleObject) {
        Set<ConstraintViolation<T>> violations = validator.validate(handleObject);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }
    }
}
