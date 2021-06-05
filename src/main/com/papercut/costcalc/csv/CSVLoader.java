package com.papercut.costcalc.csv;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {
    private static final String VAL_SEPARATOR = ",";

    private String file;

    private List<String[]> lines;

    public CSVLoader(String file) {
        super();
        this.setFile(file);
        validate();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public List<String[]> getLines() {
        if (lines == null)
            fetchLines();
        return lines;
    }

    public void setLines(List<String[]> lines) {
        this.lines = lines;
    }

    private void validate() {
        if (this.getFile() == null || this.getFile().isEmpty())
            throw new RuntimeException("Invalid file name");
    }

    private void fetchLines() {
        this.lines = new ArrayList<>();

        try {
            // loads file from resources folder
            final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            final InputStream is = classloader.getResourceAsStream(file);
            assert is != null;
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8));

            String readLine;
            String[] line;

            while ((readLine = reader.readLine()) != null) {
                line = readLine.trim().split(VAL_SEPARATOR);
                for (int i = 0; i < line.length; i++)
                    line[i] = line[i].trim();
                lines.add(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
