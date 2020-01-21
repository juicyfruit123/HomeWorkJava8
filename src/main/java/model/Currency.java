package model;

import java.util.StringJoiner;

public class Currency {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Currency.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .toString();
    }
}
