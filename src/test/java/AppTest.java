import model.Organization;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppTest {
    private static final String EU = "EU";
    private static final String USD = "USD";
    private static final String RUB = "RUB";
    private static final String USER_INPUT = "01.05.2000";
    private static final String USER_INPUT_1 = "01.05.20";
    private static final String USER_INPUT_2 = "01/05/2020";
    private static final String USER_INPUT_3 = "01/05/20";
    private static final DateTimeFormatter DATE_FORMAT_1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter DATE_FORMAT_2 = DateTimeFormatter.ofPattern("dd.MM.yy");
    private static final DateTimeFormatter DATE_FORMAT_3 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_FORMAT_4 = DateTimeFormatter.ofPattern("dd/MM/yy");
    private App app;

    {
        try {
            app = new App();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private LocalDate localDate = LocalDate.parse(USER_INPUT, DATE_FORMAT_1);
    private LocalDate localDate1 = LocalDate.parse(USER_INPUT_1, DATE_FORMAT_2);
    private LocalDate localDate2 = LocalDate.parse(USER_INPUT_2, DATE_FORMAT_3);
    private LocalDate localDate3 = LocalDate.parse(USER_INPUT_3, DATE_FORMAT_4);

    private List<Organization> list = app.userInputDate(USER_INPUT, DATE_FORMAT_1);
    private List<Organization> list1 = app.userInputDate(USER_INPUT_1, DATE_FORMAT_2);
    private List<Organization> list2 = app.userInputDate(USER_INPUT_2, DATE_FORMAT_3);
    private List<Organization> list3 = app.userInputDate(USER_INPUT_3, DATE_FORMAT_4);


    @Test
    public void userInputTest() {
        list.stream().map(organization -> organization.getEgrul_date().isAfter(localDate)).forEach(Assert::assertTrue);
        list1.stream().map(organization -> organization.getEgrul_date().isAfter(localDate1)).forEach(Assert::assertTrue);
        list2.stream().map(organization -> organization.getEgrul_date().isAfter(localDate2)).forEach(Assert::assertTrue);
        list3.stream().map(organization -> organization.getEgrul_date().isAfter(localDate3)).forEach(Assert::assertTrue);

    }

    @Test
    public void userInputCodeTest() {
        app.userInputCode(USD).forEach(securities -> Assert.assertEquals(securities.getCurrency().getCode(), USD));
        app.userInputCode(RUB).forEach(securities -> Assert.assertEquals(securities.getCurrency().getCode(), RUB));
        app.userInputCode(EU).forEach(securities -> Assert.assertEquals(securities.getCurrency().getCode(), EU));
    }
}
