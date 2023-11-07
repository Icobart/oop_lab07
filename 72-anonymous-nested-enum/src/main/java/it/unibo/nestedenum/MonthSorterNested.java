package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    
    private static final Comparator<String> DATE_ORDER = new SortByDate();
    private static final Comparator<String> MONTH_ORDER = new SortByMonthOrder();


    @Override
    public Comparator<String> sortByDays() {
        return DATE_ORDER;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return MONTH_ORDER;
    }

    private enum Month{
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month (final int days){
            this.days = days;
        }

        public static Month fromString(final String month) {
            Objects.requireNonNull(month);
            try {
                return valueOf(month);
            } catch(IllegalArgumentException e) {
                Month name = null;
                for(final Month check : Month.values()) {
                    if(check.toString().toLowerCase(Locale.ROOT).startsWith(month.toLowerCase(Locale.ROOT))) {
                        if(name != null) {
                            throw new IllegalArgumentException(month+" ambiguous name: "+name+" and "+check+" are similiar");
                        }
                        name = check;
                    }
                }
                if(name == null) {
                    throw new IllegalArgumentException("No matching for: "+month, e);
                }
                return name;
            }
        }
        

    }

    private static class SortByMonthOrder implements Comparator<String> {
        public int compare(String s1, String s2) {
            return Month.fromString(s1).compareTo(Month.fromString(s2));
        }

    }

    private static class SortByDate implements Comparator<String> {
        public int compare(String s1, String s2) {
            final var m1 = Month.fromString(s1);
            final var m2 = Month.fromString(s2);
            return Integer.compare(m1.days, m2.days);
        }

    }
    

}
