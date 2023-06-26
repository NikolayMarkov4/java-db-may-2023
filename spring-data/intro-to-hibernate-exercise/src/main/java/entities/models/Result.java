package entities.models;

import java.math.BigDecimal;

public class Result {
    private String key;
    private BigDecimal value;

    public Result(String key, BigDecimal value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.key + " " + this.value;
    }
}