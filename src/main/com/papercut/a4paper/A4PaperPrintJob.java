package com.papercut.a4paper;

import java.math.BigDecimal;
import java.text.NumberFormat;
/*
* Job for A4 size
 */
public class A4PaperPrintJob implements PrintJob {

    private int bwPages;
    private int colorPages;
    private boolean isDoubleSided;
    /* Default costs for print jobs */
    public static BigDecimal BW_SINGLE_PRICE = new BigDecimal("0.15");
    public static BigDecimal COLOUR_SINGLE_PRICE = new BigDecimal("0.25");
    public static BigDecimal BW_DOUBLE_PRICE = new BigDecimal("0.10");
    public static BigDecimal COLOUR_DOUBLE_PRICE = new BigDecimal("0.20");

    public int getBwPages() {
        return bwPages;
    }

    public void setBwPages(int bwPages) {
        this.bwPages = bwPages;
    }

    public int getColorPages() {
        return colorPages;
    }

    public void setColorPages(int colorPages) {
        this.colorPages = colorPages;
    }

    public boolean isDoubleSided() {
        return isDoubleSided;
    }

    private void setDoubleSided(boolean isDoubleSided) {
        this.isDoubleSided = isDoubleSided;
    }

    public A4PaperPrintJob(int bwPages, int colourPages) {
        this(bwPages, colourPages, false);
    }

    public A4PaperPrintJob(int bwPages, int colourPages, boolean doubleSided) {
        super();
        this.bwPages = bwPages;
        this.colorPages = colourPages;
        this.setDoubleSided(doubleSided);
    }


    public String toString() {
        return "Job type: Paper size A4 " + (isDoubleSided? "double sided" : "single sided") +
                "; Black & White pages: " + bwPages +
                "; Colour pages: " + colorPages +
                "; Total cost: " + NumberFormat.getCurrencyInstance().format(getJobCost());
    }

    public static A4PaperPrintJob parse(String[] params) {
        if (params == null || params.length != 3)
            throw new RuntimeException("Invalid number of parameters for an A4 print job, should be three");

        int totalPages, colourPages;
        boolean doubleSided;

        // Check for invalid totalPages parameter:
        try {
            totalPages = Integer.parseInt(params[0]);
        } catch (NumberFormatException e) {
            throw new RuntimeException(
                    "Invalid total pages :" + params[0]);
        }

        // Check that totalPages is a positive integer:
        if (totalPages < 0)
            throw new RuntimeException(
                    "Total number of pages must be a positive number, got: " + totalPages);

        // Check for invalid colourPages parameter:
        try {
            colourPages = Integer.parseInt(params[1]);
        } catch (NumberFormatException e) {
            throw new RuntimeException(
                    "invalid colourPages: " + params[1]);
        }

        // Check that colourPages is a positive integer:
        if (colourPages < 0)
            throw new RuntimeException(
                    "Number of colour pages must be a positive number, got: " + colourPages);

        // Check that colourPages <= totalPages:
        if (colourPages > totalPages)
            throw new RuntimeException(
                    "Number of colour pages must not exceed total pages");

        // check for invalid double-sided parameter:
        if (!params[2].toLowerCase().equals(Boolean.TRUE.toString()) &&
                !params[2].toLowerCase().equals(Boolean.FALSE.toString()))
            throw new RuntimeException(
                    "Expected whether to print a double sided sheet, got: " + params[2]);

        doubleSided = Boolean.parseBoolean(params[2]);

        return new A4PaperPrintJob(totalPages - colourPages, colourPages, doubleSided);
    }

    public String getDetails() {
        return toString();
    }

    public BigDecimal getJobCost() {
        BigDecimal cost = BigDecimal.ZERO;

        if (!isDoubleSided) {
            cost = cost.add(BW_SINGLE_PRICE.multiply(
                    BigDecimal.valueOf(bwPages)));
            cost = cost.add(COLOUR_SINGLE_PRICE.multiply(
                    BigDecimal.valueOf(colorPages)));
        }
        else {
            cost = cost.add(BW_DOUBLE_PRICE.multiply(
                    BigDecimal.valueOf(bwPages)));
            cost = cost.add(COLOUR_DOUBLE_PRICE.multiply(
                    BigDecimal.valueOf(colorPages)));
        }

        return cost;
    }

}
