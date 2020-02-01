package ar.com.javacuriosities.xml.rpc.server.handlers;

public class Weather {

    public String check(String country) {
        if ("Argentina".equals(country)) {
            return "Weather in " + country + " is hot";
        } else if ("Mexico".equals(country)) {
            return "Weather in " + country + " mild";
        } else if ("Canada".equals(country)) {
            return "Weather in " + country + " cold";
        } else if ("Venezuela".equals(country)) {
            return "Weather in " + country + " warm";
        } else {
            return country + " not available, please choose between [Argentina|Mexico|Canada|Venezuela]";
        }
    }
}
