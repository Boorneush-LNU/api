package com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.Models.PropertyConfig;
import com.cucumbercraft.framework.WebDriverUtil;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CategoryUtils {

    protected WebDriverUtil webUtil;
    WebDriver driver;
    public By hdrlinkLbl = By.xpath("//*[@id='ltrCatTitle']");
    public  By commonFAQLinks = By.xpath("//*[@class='m14-help_content--listing']/ul/li/a");
    public By breadcrumbs = By.xpath("//*[@class='m-breadcrumb']");
    public By lnkHelpandSupport = By.xpath("//a[@title='Help and Support']");
    public final By articleHdr = By.xpath("//article/../h1");
    public By RelatedArticle = By.xpath("//article/h5");
    private By locatorPara=By.xpath("//article/p");
    private By locatorBullets=By.xpath("//article/descendant::li");
    private By locatorHdr=By.xpath("//article/descendant::h4");
    private By locatorSubHdr=By.xpath("//article/descendant::h5");
    private By locatorsublinks = By.xpath("//article//a[string-length(normalize-space(.)) > 1]");
    private By locatorOl=By.xpath("//article/ol/li");
    private By locatorTd=By.xpath("//article/descendant::tr/td");
    private By locatorArticle=By.xpath("//article");
    private List<WebElement> elements;
    public PropertyConfig config = ConfigFactory.create(PropertyConfig.class);
    public CategoryUtils(WebDriver driver) {
        this.driver =driver;
        this.webUtil = new WebDriverUtil(driver);
    }

    public void compare(List<WebElement> elements, List<TestData> data, Function<TestData, String> extractor, String label, BiPredicate<String, String> comparator, int initialIndex) {
        IntStream.range(0, elements.size())
                .forEach(i -> {
                    String actual = elements.get(i).getText().trim();
                    String expected = extractor.apply(data.get(initialIndex + i));
                    comparator.test(actual, expected);
                    webUtil.CompareString(actual, comparator, expected, label);
                });
    }

    public void compareHeader(List<TestData> data,int initialIndex)
    {
        elements=webUtil.getElements(locatorHdr);
        IntStream.range(0, elements.size())
                .forEach(i -> {
                    String actual = elements.get(i).getText().trim();
                    String expected = data.get(initialIndex + i).getSubHdr();
                    webUtil.CompareString(actual, String::equals, expected, "Sub Header");
                });
    }

    public void compareSubHeader(List<TestData> data,int initialIndex)
    {
        elements=webUtil.getElements(locatorSubHdr);
        IntStream.range(0, elements.size())
                .forEach(i -> {
                    String actual = elements.get(i).getText().trim();
                    String expected = data.get(initialIndex + i).getSubHdr2();
                    webUtil.CompareString(actual, String::equals, expected, "Sub Header");
                });
    }

    public void compareParagrph(List<TestData> data,int initialIndex)
    {
        elements=webUtil.getElements(locatorPara);
        IntStream.range(0, elements.size())
                .forEach(i -> {
                    String actual = elements.get(i).getText().trim();
                    String expected = data.get(initialIndex + i).getPara();
                    webUtil.CompareString(actual, String::equals, expected, "Paragraph");
                });
    }

    public void compareBullets(List<TestData> data,int initialIndex)
    {
        elements=webUtil.getElements(locatorBullets);
        IntStream.range(0, elements.size())
                .forEach(i -> {
                    String actual = elements.get(i).getText().trim();
                    String expected = data.get(initialIndex + i).getBulletPoint();

                    webUtil.CompareString(actual, String::equals, expected, "Bullet Point");
                });
    }

    public void compareBullet2(List<TestData> data,int initialIndex)
    {
        elements=webUtil.getElements(locatorOl);
        IntStream.range(0, elements.size())
                .forEach(i -> {
                    String actual = elements.get(i).getText().trim();
                    String expected = data.get(initialIndex + i).getBulletPointSecond();

                    webUtil.CompareString(actual, String::equals, expected,"Numbered List" );
                });
    }

    public void comparesubLinks(List<TestData> data, int initialIndex)
    {
        elements=webUtil.getElements(locatorsublinks);
        IntStream.range(0, elements.size())
                .forEach(i -> {
                    String actual = elements.get(i).getText().trim();
                    String expected = data.get(initialIndex + i).getAnchor();

                    webUtil.CompareString(actual, String::equals, expected, "SubLinks");
                });
    }

    public void compareTable(List<TestData> data, int initialIndex)
    {
        elements=webUtil.getElements(locatorTd);
        IntStream.range(0, elements.size())
                .forEach(i -> {
                    String actual = elements.get(i).getText().trim();
                    String expected = data.get(initialIndex + i).getTable();

                    webUtil.CompareString(actual, String::equals, expected,"Table content" );
                });
    }

    public void compareArticle(List<TestData> data,int initialIndex)
    {
        elements=webUtil.getElements(locatorArticle);
        IntStream.range(0, elements.size())
                .forEach(i -> {
                    String actual = elements.get(i).getText().trim();
                    String expected = data.get(initialIndex + i).getArticle();

                    webUtil.CompareString(actual, String::equals, expected,"Full Page" );
                });
    }
    public String getUrl()  {
        String baseUrl = null;
        try {
            URL url = new URL(webUtil.getDriver().getCurrentUrl());
             baseUrl= url.getProtocol() + "://" + url.getHost();
        }catch (MalformedURLException e)
        {
            System.err.println(e.getStackTrace()[0].getMethodName());
        }
        return  baseUrl;
    }

    public void validateSize(By by, String[] arr, String log) {
        compareList(driver.findElements(by).stream()
                .map(WebElement::getText).collect(Collectors.toList()), Arrays.asList(arr));
        List<String> nonMatchingElements = driver.findElements(by).stream()
                .map(WebElement::getText)
                .filter(elementText ->
                        Arrays.stream(arr).noneMatch(elementText::equals)
                )
                .collect(Collectors.toList());

        if (nonMatchingElements.isEmpty()) {
            System.out.println("All elements match the string list.");
            ExtentCucumberAdapter.getCurrentStep().pass(log + " Links validated");
        } else {
            System.out.println("Non-matching links: " + nonMatchingElements);
            ExtentCucumberAdapter.getCurrentStep().fail("Non-matching links: " + nonMatchingElements);
        }

    }

    public void compareList(List<String> actualList, List<String> expectedList) {
        BiPredicate<String, String> compareText = String::equals;
        IntStream.range(0, expectedList.size()).forEach(i ->
        {
            var actualText = actualList.get(i);
            var expectedText = expectedList.get(i);
            if (compareText.test(actualText, expectedText)) {
                ExtentCucumberAdapter.getCurrentStep().pass("Actual text: <font color='yellow'><b>" + actualText + "</b></font> : matches the expected text : <font color='yellow'><b>PASS</b></font>");
                System.out.println(">> Actual-" + actualText + " >> Expected -" + expectedText);
            } else {
                ExtentCucumberAdapter.getCurrentStep().fail("Actual text: <font color='red'><b>" + actualText + "</b></font> : does not match the expected text : <font color='red'><b>" + expectedText + "</b></font>: <font color='red'><b>FAIL</b></font>");
                System.err.println(">> Actual-" + actualText + " >> Expected-" + expectedText);
            }
        });
    }

}
