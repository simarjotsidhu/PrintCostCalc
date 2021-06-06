Challenge Description
To reduce printing waste, a school is implementing PaperCut and will
charge for printing as follows:
Paper size A4, job type single-sided:
• 15 cents per black and white page
• 25 cents per colour page
Paper size A4, job type double-sided:
• 10 cents per black and white page
• 20 cents per colour page
Write a program in Java (or if we have discussed it during our phone
conversation - another programming language you feel more
comfortable with) that helps the system administrator calculate the print
costs.


How to build and run
Apache Maven 3.x is needed to build and run the tests. To achieve this, launch the command line and run:

> mvn clean install
After this, launch the application by typing:

> mvn exec:java -Dexec.args="sample.csv" -quiet
