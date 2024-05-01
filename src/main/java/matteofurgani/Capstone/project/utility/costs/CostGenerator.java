package matteofurgani.Capstone.project.utility.costs;

import matteofurgani.Capstone.project.utility.constants.ServiceConstants;

public class CostGenerator {

    /*
     * SERVIZI:
     * TOSATURA, costo base 8 Euro
     * LAVAGGIO, costo base 10 euro
     * TOSATURA + LAVAGGIO, costo base 15 euro
     * */

    /**
     * Generates the proper cost for a given hair service based on the hair type, size, and service base cost.
     *
     * @param hairType The type of hair (e.g., SHORT, MEDIUM, LONG).
     * @param size The size of the service (e.g., S, M, L, XL).
     * @param serviceBaseCost The base cost of the service.
     * @return The formatted cost of the service.
     */
    public String generateProperCost(String hairType, String size, double serviceBaseCost) {

        switch (size) {

            case "S":

                if ("SHORT".equals(hairType)) {
                    return formatCosto(serviceBaseCost);
                } else if ("MEDIUM".equals(hairType)) {
                    return formatCosto(serviceBaseCost + ServiceConstants.ADDENDUM_MEDIUM_HAIR);
                } else if ("LONG".equals(hairType)) {
                    return formatCosto(serviceBaseCost + ServiceConstants.ADDENDUM_LONG_HAIR);
                }

                break;

            case "M":

                if ("SHORT".equals(hairType)) {
                    return formatCosto(serviceBaseCost * ServiceConstants.MULTIPLIER_M);
                } else if ("MEDIUM".equals(hairType)) {
                    return formatCosto((serviceBaseCost * ServiceConstants.MULTIPLIER_M) + ServiceConstants.ADDENDUM_MEDIUM_HAIR);
                } else if ("LONG".equals(hairType)){
                    return formatCosto((serviceBaseCost * ServiceConstants.MULTIPLIER_M) + ServiceConstants.ADDENDUM_LONG_HAIR);
                }
                break;

            case "L":

                if ("SHORT".equals(hairType)) {
                    return formatCosto(serviceBaseCost * ServiceConstants.MULTIPLIER_L);
                } else if ("MEDIUM".equals(hairType)) {
                    return formatCosto((serviceBaseCost * ServiceConstants.MULTIPLIER_L) + ServiceConstants.ADDENDUM_MEDIUM_HAIR);
                } else if ("LONG".equals(hairType)){
                    return formatCosto((serviceBaseCost * ServiceConstants.MULTIPLIER_L) + ServiceConstants.ADDENDUM_LONG_HAIR);
                }
                break;

            case "XL":

                if ("SHORT".equals(hairType)) {
                    return formatCosto(serviceBaseCost * ServiceConstants.MULTIPLIER_XL);
                } else if ("MEDIUM".equals(hairType)) {
                    return formatCosto((serviceBaseCost * ServiceConstants.MULTIPLIER_XL) + ServiceConstants.ADDENDUM_MEDIUM_HAIR);
                } else if ("LONG".equals(hairType)){
                    return formatCosto((serviceBaseCost * ServiceConstants.MULTIPLIER_XL) + ServiceConstants.ADDENDUM_LONG_HAIR);
                }
                break;

        }
        return null;

    }

    /**
     * Formats the given cost to always display two decimal places, rounding down if the decimal part is less than 0.5,
     * showing 0.5 if the decimal part is greater than or equal to 0.5 but less than 1, and rounding otherwise.
     *
     * @param costo The cost to format.
     * @return The formatted cost as a string.
     */
    private static String formatCosto(double costo) {
        // Arrotonda il costo alla cifra più vicina
        double roundedCosto = Math.floor(costo * 100) / 100;

        // Se la parte decimale è inferiore a 0.5, mostra solo la parte intera
        if (roundedCosto - (int) roundedCosto < 0.5) {
            return String.format("%.2f", Math.floor(roundedCosto));
        }
        // Se la parte decimale è maggiore o uguale a 0.5 ma inferiore a 1, mostra 0.5
        else if (roundedCosto - (int) roundedCosto < 1) {
            return String.format("%.2f", (int) roundedCosto + 0.5);
        }
        // Altrimenti, mostra il costo arrotondato
        else {
            return String.format("%.2f", roundedCosto);
        }
    }

}


