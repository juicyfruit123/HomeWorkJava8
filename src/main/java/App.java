import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.Organization;
import model.Securities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    private static final String EU = "EU";
    private static final String USD = "USD";
    private static final String RUB = "RUB";
    private static List<Organization> organizations;
    private static final String USER_INPUT = "01.05.2000";
    private static final String USER_INPUT_1 = "01.05.20";
    private static final String USER_INPUT_2 = "01/05/2020";
    private static final String USER_INPUT_3 = "01/05/20";
    private static final DateTimeFormatter DATE_FORMAT_1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter DATE_FORMAT_2 = DateTimeFormatter.ofPattern("dd.MM.yy");
    private static final DateTimeFormatter DATE_FORMAT_3 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_FORMAT_4 = DateTimeFormatter.ofPattern("dd/MM/yy");

    public App() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader("/home/anton/Загрузки/HomeWorkJava8/src/main/resources/organization.json"));
        Type collectionType = new TypeToken<Collection<Organization>>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> {
            return LocalDate.parse(json.getAsString());
        }).create();
        organizations = gson.fromJson(reader, collectionType);

    }

    public static List<Organization> getOrganizations() {
        return organizations;
    }

    public static void main(String[] args) throws FileNotFoundException {
        App app = new App();
        //Выводит все имеющиеся компании
        printOrganization();
        System.out.println();
        //Выводит все ценные бумаги
        app.printSecurities();
        System.out.println();
        //Запрос пользователя с датой
        app.userInputDate(USER_INPUT, DATE_FORMAT_1).stream().map(organization -> organization.getName_full() + " " + organization.getEgrul_date())
                .forEach(System.out::println);
        System.out.println();
        //Запрос пользователя с кодом валюты
        for (Securities securities : app.userInputCode(USD)) {
            System.out.println(securities.getId() + " " + securities.getCode());
        }
    }


    private static void printOrganization() {
        organizations.forEach(organization -> System.out.println(organization.getName_short() + " - Дата основания " + organization.getEgrul_date().format(DATE_FORMAT_4)));
    }

    private void printSecurities() {
        int countSecurities = 0;
        for (Organization organization : organizations) {
            countSecurities += organization.getSecurities().stream()
                    .filter(securities1 -> securities1.getDate_to().isBefore(LocalDate.now()))
                    .count();
            organization.getSecurities().stream()
                    .filter(securities -> securities.getDate_to().isBefore(LocalDate.now()))
                    .map(securities -> securities.getCode() + " " + securities.getDate_to() + " " + organization.getName_full())
                    .forEach(System.out::println);
        }
        System.out.println("Число просроченных бумаг: " + countSecurities);
    }

    List<Organization> userInputDate(String userInput, DateTimeFormatter dateFormat) {
        LocalDate localDate = LocalDate.parse(userInput, dateFormat);
        return organizations.stream().filter(organization -> organization.getEgrul_date().isAfter(localDate))
                .collect(Collectors.toList());
    }

    List<Securities> userInputCode(String str) {
        List<Securities> list = new ArrayList<>();
        organizations.forEach(organization -> organization.getSecurities()
                .stream().filter(securities -> securities.getCurrency().getCode().equals(str))
                .forEach(list::add));
        return list;
    }
}
