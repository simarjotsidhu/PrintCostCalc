package com.papercut.costcalc;


import com.papercut.a4paper.PrintJob;
import com.papercut.costcalc.csv.CSVLoader;
import com.papercut.printdetails.PrintJobOutput;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

public class CostCalculator {

    private List<PrintJob> jobs;

    /**
     * Total cost for all print jobs
     */
    private BigDecimal totalCost;

    public List<PrintJob> getJobs() {
        return jobs;
    }

    public void setJobs(List<PrintJob> jobs) {
        this.jobs = jobs;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public CostCalculator(List<PrintJob> jobs) {
        super();
        this.setJobs(jobs);
        this.setTotalCost(calculateTotalCost());
    }

    private BigDecimal calculateTotalCost() {
        BigDecimal totalCost = BigDecimal.ZERO;

        for (PrintJob job : this.jobs) {
            totalCost = totalCost.add(job.getJobCost());
        }

        return totalCost;
    }

    /**
     * Outputs details for all print jobs and total cost.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (PrintJob job : this.jobs) {
            sb.append(job.getDetails()).append("\n");
        }

        sb.append("TOTAL COST: " + NumberFormat.getCurrencyInstance().format(totalCost));
        return sb.toString();
    }

    public static void main(String[] args)
    {
       if (args == null || args.length == 0 || args[0].isEmpty())
           throw new RuntimeException("Usage: CostCalculator sample.csv Error");

        // Parses CSV lines into a list of printable jobs
       final List<PrintJob> jobs = PrintJobOutput.parse(
                new CSVLoader(args[0]).getLines());

        // Outputs job details and total cost
        final CostCalculator calc = new CostCalculator(jobs);
        System.out.println(calc.toString());
    }

}
