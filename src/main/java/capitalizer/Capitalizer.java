package main.java.capitalizer;

public class Capitalizer {

    String delimiter;
    String splitter;

    public Capitalizer(final String splitter, final String delimiter) {
        this.delimiter = delimiter;
        this.splitter = splitter;
    }

    public String capitalize(final String name) {
        return String.join(delimiter, capitalizeAll(splitName(name)));
    }

    private String[] capitalizeAll(final String...strings) {

        final String[] capitalizedStrings = new String[strings.length];
        capitalizedStrings[0] = strings[0].toLowerCase();
        for (int i = 1; i < strings.length; i++) {
            final char first = Character.toUpperCase(strings[i].charAt(0));
            capitalizedStrings[i] = first + strings[i].substring(1);
        }
        return capitalizedStrings;
    }

    private String[] splitName(final String name) {
        final String[] split = name.split(splitter);
        return split;
    }
}
