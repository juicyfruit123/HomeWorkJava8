package model;

public class CompanyType {
    private int id;
    private String name_short;
    private String name_full;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompanyType{");
        sb.append("id=").append(id);
        sb.append(", name_short='").append(name_short).append('\'');
        sb.append(", name_full='").append(name_full).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
