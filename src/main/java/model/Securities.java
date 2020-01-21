package model;

import java.time.LocalDate;
import java.util.StringJoiner;

public class Securities {
    private int id;
    private LocalDate date_to;
    private String code;
    Currency currency;

    public LocalDate getDate_to() {
        return date_to;
    }

    public void setDate_to(LocalDate date_to) {
        this.date_to = date_to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Securities.class.getSimpleName() + "[", "]")
                .add("date_to=" + date_to)
                .add("code='" + code + "'")
                .add("currency=" + currency)
                .toString();
    }
}
