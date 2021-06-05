package com.papercut.costcalc;

import com.papercut.a4paper.A4PaperPrintJob;
import com.papercut.a4paper.PrintJob;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class CostCalculatorTest {
    @Test
    public void getTotalCost() {
        final List<PrintJob> jobs = new ArrayList<>();
        jobs.add(new A4PaperPrintJob(15, 10));
        jobs.add(new A4PaperPrintJob(42, 13, true));
        jobs.add(new A4PaperPrintJob(480, 22, true));
        jobs.add(new A4PaperPrintJob(1, 0));

        final CostCalculator calc = new CostCalculator(jobs);
        assertTrue(calc.getTotalCost().compareTo(new BigDecimal("64.10")) == 0);
    }


    @Test
    public void produceCorrectOutput() {
        final List<PrintJob> jobs = new ArrayList<>();
        jobs.add(new A4PaperPrintJob(15, 10));
        jobs.add(new A4PaperPrintJob(42, 13, true));
        jobs.add(new A4PaperPrintJob(480, 22, true));
        jobs.add(new A4PaperPrintJob(1, 0));

        final CostCalculator calc = new CostCalculator(jobs);
        String output = calc.toString();

        assertTrue(output.contains("TOTAL COST: $64.10"));
    }
}