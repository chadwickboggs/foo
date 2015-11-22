package com.chadwickboggs.foo.currency;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;


//
// IMPORTANT NOTE: I did only the scaffolding believing it would reveal more details than did the algorithm.
//

//
// Design Note: Utility classes should be final and devoid of public constructors.
//
public final class ExchangeUtil {

    private ExchangeUtil() {
    }

    /**
     * Finds the cheapest exchange path between two currencies, returning empty string when
     * no path exists and throwing assertion exceptions if unknown currencies specified.
     *
     * @param fromCurrency the currency to exchange from.
     * @param toCurrency   the currency to exchange to.
     * @return the cheapest exchange path between two currencies, empty string if no path exists.
     */
    //
    // Design Note: Java 8 Optional possibly preferable over null and empty encoded semantics.
    //
    @NotNull
    public static String cheapestExchangePathAsString(
            @NotNull final String fromCurrency, @NotNull final String toCurrency ) {
        ExchangePath exchangePath = cheapestExchangePath( fromCurrency, toCurrency );
        if ( exchangePath == null ) {
            return "";
        }

        final List< String > path = extractPath( exchangePath );
        String exchangePathAsString = DelimitedStringUtil.toString( path, true );
        if ( exchangePathAsString == null || exchangePathAsString.length() == 0 ) {
            //
            // Design Note: Shown merely to communicate knowledge of the technique.
            //
            assert false : "Programming error.  Unreachable line reached.";
        }

        //
        // Design Note: Assigning a return value to a variable before returning facilitates debugging.
        //

        return exchangePathAsString;
    }

    private static List< String > extractPath( final ExchangePath exchangePath ) {
        assert exchangePath != null
                : "Provided exchange path is null.";

        final List< String > pathList = new ArrayList<>();
        final List< ExchangeRate > path = exchangePath.getPath();
        for ( int i = 0; i < path.size(); i++ ) {
            final ExchangeRate exchangeRate = path.get( i );
            pathList.add( exchangeRate.getFromCurrency() );
            if ( i == path.size() - 1 ) {
                pathList.add( exchangeRate.getToCurrency() );
            }
        }

        return pathList;
    }

    @Nullable
    public static ExchangePath cheapestExchangePath(
            @NotNull final String fromCurrency, @NotNull final String toCurrency ) {
        //
        // Design Note: Guava preconditions possibly preferable over assert statements as assertions may be disabled.
        //
        assert CurrencyUtil.isKnown( fromCurrency )
                : String.format( "Specified currency unknown.  fromCurrency; %s", fromCurrency );
        assert CurrencyUtil.isKnown( toCurrency )
                : String.format( "Specified currency unknown.  toCurrency; %s", toCurrency );

        List< ExchangePath > everyPossiblePath = everyExchangePath( fromCurrency, toCurrency );
        if ( everyPossiblePath == null || everyPossiblePath.size() == 0 ) {
            return null;
        }

        final SortedSet< ExchangePath > pathTree = new TreeSet< ExchangePath >();
        for ( int i = 0; i < everyPossiblePath.size(); i++ ) {
            ExchangePath path = everyPossiblePath.get( i );
            if ( path == null ) {
                continue;
            }

            pathTree.add( path );
        }

        return pathTree.first();
    }

    //
    // Design Note: This is one point where the scaffolding ends and the algorithm begins.  It must detect avoid
    //              infinite loops.  One idea would be for every possible path between every node to be
    //              pre-calculated and cached and/or persisted, then this method would be a lookup.
    //
    private static List< ExchangePath > everyExchangePath( final String fromCurrency, final String toCurrency ) {
        return null;
    }

    //
    // Design Note: If values may be quoted strings and/or contain embedded or escaped delimiters,
    //              a third party library possibly preferable.
    //
    private static final class DelimitedStringUtil {

        public static final String DEFAULT_DELIMITER = ",";

        public static List< String > fromString( final String csv ) {
            List< String > list = fromString( csv, DEFAULT_DELIMITER );

            return list;
        }

        public static List< String > fromString( final String csv, final String delimiter ) {
            assert csv != null && csv.length() > 0
                    : "Provided comma separated list is null or empty.";

            String[] values = csv.split( delimiter );
            List< String > list = Arrays.asList( values );

            return list;
        }

        public static String toString( final List< String > everyPossiblePath, final boolean ignoreEmptyValues ) {
            String csv = toString( everyPossiblePath, ignoreEmptyValues, DEFAULT_DELIMITER );

            return csv;
        }

        public static String toString(
                final List< String > everyPossiblePath, final boolean ignoreEmptyValues, final String delimiter ) {
            //
            // Design Note: StringUtils.isEmpty may be more useful and readable than explicit code.
            //
            if ( everyPossiblePath == null || everyPossiblePath.size() == 0 ) {
                //
                // Design Note: It may be useful to debug log a message here.
                //
                return "";
            }

            //
            // Design Note: Although square brackets were required, JSON may be worth considering
            //              and for it Google's GSON library as well.
            //
            StringBuilder buf = new StringBuilder( "[" );
            for ( int i = 0; i < everyPossiblePath.size(); i++ ) {
                final String value = everyPossiblePath.get( i );
                if ( value == null || value.length() == 0 && ignoreEmptyValues ) {
                    continue;
                }
                if ( i > 0 ) {
                    buf.append( delimiter );
                }
                buf.append( value );
            }
            buf.append( "]" );

            return buf.toString();
        }
    }

    public static final class CurrencyUtil {
        private CurrencyUtil() {
        }

        public static boolean isKnown( final String currency ) {
            return false;
        }
    }

    private static class ExchangePath implements Comparable {

        private List< ExchangeRate > path;
        private float exchangeRate;

        public ExchangePath( final List< ExchangeRate > path, final float exchangeRate ) {
            this.path = path;
            this.exchangeRate = exchangeRate;
        }

        public List< ExchangeRate > getPath() {
            return path;
        }

        public float getExchangeRate() {
            return exchangeRate;
        }

        @Override
        public int compareTo( final Object o ) {
            if ( o instanceof ExchangePath ) {
                if ( exchangeRate == ( ( ExchangePath ) o ).exchangeRate ) {
                    return 0;
                }
                if ( exchangeRate < ( ( ExchangePath ) o ).exchangeRate ) {
                    return -1;
                }

                return 1;
            }

            if ( hashCode() == o.hashCode() ) {
                return 0;
            }
            if ( hashCode() < o.hashCode() ) {
                return -1;
            }

            return 1;
        }
    }

    public class ExchangeRate {

        private String fromCurrency;
        private String toCurrency;
        private float exchangeRate;

        public ExchangeRate( final String fromCurrency, final String toCurrency, final float exchangeRate ) {
            this.fromCurrency = fromCurrency;
            this.toCurrency = toCurrency;
            this.exchangeRate = exchangeRate;
        }

        public String getFromCurrency() {
            return fromCurrency;
        }

        public String getToCurrency() {
            return toCurrency;
        }

        public float getExchangeRate() {
            return exchangeRate;
        }
    }
}
