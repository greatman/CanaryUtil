package net.larry1123.util.api.time;

import org.apache.commons.lang3.time.DateUtils;

public class StringTime {

    public static enum Part {

        DAYS("D"),
        HOUR("H"),
        MINUTES("M"),
        SECONDS("S"),
        NONE(" ");

        private final String ths;

        Part(String shortn) {
            ths = shortn;
        }

        public String getValue() {
            return ths;
        }

        public static Part getFromString(String type) {
            for (Part t : Part.values()) {
                if (t.getValue().toLowerCase().equals(type.toLowerCase())) {
                    return t;
                }
            }
            return NONE;
        }
    }

    /**
     * Takes just a number or a set of numbers with a D, H, M or, S fallowing it
     * The letters can be upper or lower case
     * 15h 50m 5s
     *
     * @param string Whole String to decode into parts
     * @return the amount of time in milliseconds that the time string Decodes to
     */
    public static long millisecondsFromString(String string) {
        if (string == null) throw new NullPointerException("String can not be null");
        string = string.trim();
        long ret = 0;
        try {
            ret = Long.parseLong(string);
        } catch (NumberFormatException e) {
            for (String part : string.split(" ")) {
                if (part.length() >= 2) {
                    String time = part.substring(part.length()-1);
                    switch (Part.getFromString(time)) {
                        case DAYS:
                            try {
                                Long days = Long.parseLong(part.substring(0, part.length()-1));
                                ret += days * DateUtils.MILLIS_PER_DAY;
                            } catch (NumberFormatException error) {
                                // DO nothing right now
                            }
                            break;
                        case HOUR:
                            try {
                                Long hours = Long.parseLong(part.substring(0, part.length()-1));
                                ret += hours * DateUtils.MILLIS_PER_HOUR;
                            } catch (NumberFormatException error) {
                                // DO nothing right now
                            }
                            break;
                        case MINUTES:
                            try {
                                Long minutes = Long.parseLong(part.substring(0, part.length()-1));
                                ret += minutes * DateUtils.MILLIS_PER_MINUTE;
                            } catch (NumberFormatException error) {
                                // DO nothing right now
                            }
                            break;
                        case SECONDS:
                            try {
                                Long seconds = Long.parseLong(part.substring(0, part.length()-1));
                                ret += seconds * DateUtils.MILLIS_PER_SECOND;
                            } catch (NumberFormatException error) {
                                // DO nothing right now
                            }
                            break;
                        default:
                            // Something is malformed just let it fly by and keep going
                            break;
                    }
                } else if (part.length() == 1) {
                    switch (Part.getFromString("" + part.charAt(1))) {
                        case DAYS:
                                ret += DateUtils.MILLIS_PER_DAY;
                            break;
                        case HOUR:
                                ret += DateUtils.MILLIS_PER_HOUR;
                            break;
                        case MINUTES:
                                ret += DateUtils.MILLIS_PER_MINUTE;
                            break;
                        case SECONDS:
                                ret += DateUtils.MILLIS_PER_SECOND;
                            break;
                        default:
                            // Something is malformed just let it fly by and keep going
                            break;
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Takes just a number or a set of numbers with a D, H, M or, S fallowing it
     * The letters can be upper or lower case
     * 15h 50m 5s
     *
     * @param strings
     * @return the amount of time in milliseconds that the time string(s) Decodes to
     */
    public static long millisecondsFromString(String[] strings) {
        if (strings == null) throw new NullPointerException("String Array can not be null");
        String send = "";
        for (String parts : strings) {
            send += parts + " ";
        }
        return millisecondsFromString(send);
    }

    /**
     * Decodes the given string(s) and looks to check if that amount of time has passed
     *
     * @param lastTime Time to compare current time in milliseconds
     * @param string Whole String to decode
     * @return true if that amount of time has passed
     */
    public static boolean hasPassed(long lastTime, String string) {
        return ((lastTime + millisecondsFromString(string)) > System.currentTimeMillis());
    }

    /**
     * Decodes the given string(s) and looks to check if that amount of time has passed
     *
     * @param lastTime Time to compare current time in milliseconds
     * @param strings Array of Strings to be decoded
     * @return true if that amount of time has passed
     */
    public static boolean hasPassed(long lastTime, String[] strings) {
        return ((lastTime + millisecondsFromString(strings)) > System.currentTimeMillis());
    }

}
