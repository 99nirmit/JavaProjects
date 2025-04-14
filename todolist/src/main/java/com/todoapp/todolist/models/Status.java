package com.todoapp.todolist.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Status {

    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");

    private final String value;


    Status(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Status fromString(String value){
        Status[]  statuses = Status.values();
//        for(int i = 0; i < statuses.length; i++){
//            if (statuses[i].value.equalsIgnoreCase(value)) {
//                return statuses[i];
//            }
//        }
//        throw new IllegalArgumentException("Invalid Status " + value);

        return Arrays.stream(statuses)
                .filter(status -> status.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Status " + value));
    }
}
