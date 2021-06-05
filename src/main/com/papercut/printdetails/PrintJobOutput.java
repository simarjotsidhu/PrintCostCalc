package com.papercut.printdetails;

import com.papercut.a4paper.A4PaperPrintJob;
import com.papercut.a4paper.PrintJob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintJobOutput {
    /**
     *  headers corresponding to each paper size can be defined, can be extended in future
     */
    public static Map<Class<? extends PrintJob>, String[]> paperSizeHeaders;

    static {
        paperSizeHeaders = new HashMap<>();
        paperSizeHeaders.put(A4PaperPrintJob.class, new String[] {
                "Total Pages", "Color Pages", "Double Sided" });

        // add more paper sizes here...
    }

    public static List<PrintJob> parse(List<String[]> params) {
        validate(params);

        final List<PrintJob> jobs = new ArrayList<PrintJob>();
        final Class<? extends PrintJob> clazz = getPrintJobPaperSize(params.get(0));

        if (clazz.equals(A4PaperPrintJob.class)) {
            for (int i = 1; i < params.size(); i++) {
                jobs.add(A4PaperPrintJob.parse(params.get(i)));
            }
        }

        // add new parsers here...

        return jobs;
    }

    private static void validate(List<String[]> params) {
        if (params == null || params.size() < 2) {
            throw new RuntimeException("At least 2 lines are required: one for headers and one for data");
        }
    }

    public static Class<? extends PrintJob> getPrintJobPaperSize(String[] headers){
        if (headers != null) {
            for (Class<? extends PrintJob> clas : paperSizeHeaders.keySet()) {
                // checks for same number of headers/columns
                if (headers.length != paperSizeHeaders.get(clas).length)
                    continue;

                // compares all headers to find the corresponding paper size
                boolean found = true;
                for (int i = 0; i < headers.length && found; i++) {
                    if (!headers[i].equalsIgnoreCase(paperSizeHeaders.get(clas)[i])) {
                        found = false;
                    }
                }

                if (!found) {
                }
                else
                    return clas;
            }
        }

        throw new RuntimeException("Unknown print job paper size.");
    }

}
