package SeleniumFrameworkDesign.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int count = 0;
    int maxTry = 1;// Change this to 2 or 3 if you want more retries

    @Override
    public boolean retry(ITestResult result) {
        // IRetryAnalyzer Gets called automatically whenever a test fails..
        // ..and post completing listeners class - onTestFailure method
        // Asks: "Should I retry this test one more time?"
        if (count < maxTry) {
            count++; // Increment before returning
            return true; // ← Yes, please retry!

        }
        return false; // ← No more retries – mark as real failure
    }

}
