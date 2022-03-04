package com.example.managementlibrary.common;

import lombok.Builder;

import java.util.List;

@Builder
public class Filter {
    private String field;
    private EOperator operator;
    private String value;
    private List<String> values;

    public Filter(String field, EOperator operator, String value, List<String> values) {
        this.field = field;
        this.operator = operator;
        this.value = value;
        this.values = values;
    }

    public Filter() {

    }



    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public EOperator getOperator() {
        return operator;
    }

    public void setOperator(EOperator operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
