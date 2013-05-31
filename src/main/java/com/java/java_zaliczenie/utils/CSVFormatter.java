/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie.utils;

import java.util.Formatter;

/**
 *
 * @author Wojciech
 */
public class CSVFormatter implements DataLineFormatter {

    private final static String DELIMITER = ";";
    private final int COLUMN_1_LENGTH = 30; //number of characters for field in csv file
    private final int COLUMN_2_LENGTH = 30;
    private final int COLUMN_3_LENGTH = 30;
    private final int COLUMN_4_LENGTH = 30;
    private final int COLUMN_5_LENGTH = 16;
    private final int COLUMN_6_LENGTH = 40;
    private final int COLUMN_7_LENGTH = 8;
    private final int COLUMN_8_LENGTH = 7;
    private final int FLOAT_POINT_PRECISION = 2; //number of digits after decmimal points in float values

    @Override
    public String formatLine(String[] dataLine) {
        return format(false, dataLine);
    }

    private String format(boolean header, String[] dataLine) {

        StringBuilder stringBuilder = new StringBuilder();
        Formatter formatter = new Formatter(stringBuilder);

        //      %[argument_index$][flags][width][.precision]conversion
        String formatString = getFormatString(header);

        if (header) {
            formatter.format(formatString, dataLine);
        } else {
            formatter.format(formatString,
                    dataLine[0],
                    dataLine[1],
                    dataLine[2],
                    dataLine[3],
                    dataLine[4],
                    dataLine[5],
                    dataLine[6],
                    Float.parseFloat(dataLine[7]));
        }

        String line = formatter.toString();

        return line;
    }

    /*
     * Build format string either for header or data line.
     */
    private String getFormatString(boolean forHeader) {
        String stringCol = "s";
        String decimalCol = "d"; //d - decimal value
        String floatCol = "." + FLOAT_POINT_PRECISION + "f"; //. - decmial point, f - float value

        StringBuilder sb = new StringBuilder();

        //put code here that creates format string:
        sb.append("%1$-").append(COLUMN_1_LENGTH).append("s").append(DELIMITER); //1st column -      %1$-20s;
        sb.append(" %2$-").append(COLUMN_2_LENGTH).append(stringCol).append(DELIMITER); //2nd column %2$-3d;
        sb.append(" %3$-").append(COLUMN_3_LENGTH).append(stringCol).append(DELIMITER); //3rd column  %3$-10.2f;
        sb.append(" %4$-").append(COLUMN_4_LENGTH).append(stringCol).append(DELIMITER); //4th column  %4$-10.2f;
        sb.append(" %5$-").append(COLUMN_5_LENGTH).append(stringCol).append(DELIMITER); //5th column  %5$-10.2f;
        sb.append(" %6$-").append(COLUMN_6_LENGTH).append(stringCol).append(DELIMITER); //5th column  %5$-10.2f;
        sb.append(" %7$-").append(COLUMN_7_LENGTH).append(stringCol).append(DELIMITER); //5th column  %5$-10.2f;
        sb.append(" %8$-").append(COLUMN_8_LENGTH).append(floatCol).append(DELIMITER); //5th column  %5$-10.2f;

        //" %1$-20s; %2$-3d; %3$-10.2f; %4$-10.2f; %5$-10.2f;"
        //" %1$-20s; %2$-3s; %3$-10.2s; %4$-10.2s; %5$-10.2s;"



        return sb.toString();

    }
}
