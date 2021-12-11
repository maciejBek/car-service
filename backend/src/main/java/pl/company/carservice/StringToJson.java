package pl.company.carservice;

public class StringToJson {

    public static String parse(String keyString, String valueString) {
        String finallyString = "{\n" + "\t\"" + keyString + "\": \"" + valueString +
                "\"\n}";

        return finallyString;
    }
}
