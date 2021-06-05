package com.papercut.a4paper;

import java.math.BigDecimal;
/*
 * jobs for different paper sizes can be implemented in future
 */
public interface PrintJob {

    String getDetails();

    BigDecimal getJobCost();
}
