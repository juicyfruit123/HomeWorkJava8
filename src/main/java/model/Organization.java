package model;

import java.time.LocalDate;
import java.util.List;
import java.util.StringJoiner;

public class Organization {
    private int id;
    private String code;
    private String name_full;
    private String name_short;
    private String inn;
    private LocalDate egrul_date;
    private CompanyType company_Type;
    private List<Securities> securities;

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

    public String getName_full() {
        return name_full;
    }

    public void setName_full(String name_full) {
        this.name_full = name_full;
    }

    public String getName_short() {
        return name_short;
    }

    public void setName_short(String name_short) {
        this.name_short = name_short;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public CompanyType getCompany_Type() {
        return company_Type;
    }

    public void setCompany_Type(CompanyType company_Type) {
        this.company_Type = company_Type;
    }

    public List<Securities> getSecurities() {
        return securities;
    }

    public void setSecurities(List<Securities> securities) {
        this.securities = securities;
    }

    public LocalDate getEgrul_date() {
        return egrul_date;
    }

    public void setEgrul_date(LocalDate egrul_date) {
        this.egrul_date = egrul_date;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Organization.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("code='" + code + "'")
                .add("name_full='" + name_full + "'")
                .add("name_short='" + name_short + "'")
                .add("inn='" + inn + "'")
                .add("egrul_date=" + egrul_date)
                .add("company_Type=" + company_Type)
                .add("securities=" + securities)
                .toString();
    }
}
