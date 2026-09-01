package com.cucumbercraft.POMPages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.FrameworkConstants;
import com.cucumbercraft.Models.TestData;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.POMPages.HelpandCategoriesLink.IrelandStateSavingsOnline.CategoryUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
@Log4j2
public class Kentico_FooterContentPage extends CategoryUtils {

    private final WebDriver driver;
    private WebDriverUtil webUtil;
    private HomePage home;

    public Kentico_FooterContentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
        home = new HomePage(driver);
    }
    public By FooterHeader = By.xpath("//h1[@class='m03-title_text_button--title']");
    public By AboutContent1 = By.xpath("(//div[@class='m11-body_copy--content general-content']//p)[1]");
    public By AboutContent2 = By.xpath("(//div[@class='m11-body_copy--content general-content']//p)[2]");
    public By AboutContent3 = By.xpath("(//div[@class='m11-body_copy--content general-content']//p)[3]");
    public By AboutContent4 = By.xpath("(//div[@class='m11-body_copy--content general-content']//p)[4]");
    public By AboutContent5 = By.xpath("(//div[@class='m11-body_copy--content general-content']//p)[5]");
    public By AboutContent6 = By.xpath("(//div[@class='m11-body_copy--content general-content']//p)[6]");
    public By AboutContent7 = By.xpath("(//div[@class='m11-body_copy--content general-content']//p)[7]");
    public By AboutContent8 = By.xpath("(//div[@class='m11-body_copy--content general-content']//p)[8]");
    public By AboutHead = By.xpath("//div[@class='m11-body_copy--content general-content']/h3");
    public By AboutLink1 = By.xpath("(//div[@class='m11-body_copy--content general-content']//a)[1]");
    public By AboutLink2 = By.xpath("(//div[@class='m11-body_copy--content general-content']//a)[2]");
    public By AboutLink3 = By.xpath("(//div[@class='m11-body_copy--content general-content']//a)[3]");

    public By SiteHeader = By.xpath("(//div[contains(@class,'general-content')]//h1)");

    public By btnAccessibility = By.xpath("//*[text()='Accessibility']");
    public By hdrAccessblity = By.xpath("//*[text()='Accessibility']");
    public By hdrDataProtection = By.xpath("//*[text()='Ireland State Savings Data Protection Notice']");
    public By hdrCookiePolicy = By.xpath("//*[text()='Ireland State Savings Cookie Policy']");
    public By hdrCookieUse = By.xpath("//*[text()='Cookie List']");
    public By btnDataProtection = By.xpath("//*[text()='Data Protection']");
    public By btnCookiePolicy = By.xpath("//*[text()='Cookie Policy']");
    public By btnCookieWeUse = By.xpath("//*[text()='Cookies We Use']");
    public By btnNTMA = By.xpath("//*[text()='NTMA']");
    public By btnAnPost = By.xpath("//*[text()='An Post']");
    public By btnPrizeBondCompany = By.xpath("//*[text()='Prize Bond Company']");
    public By hdrPrizeBond = By.xpath("//*[text()='What is the Prize Bond Company?']");

    public By hdrContactUs = By.xpath("//*[text()='Contact Us']");
    public By btnContactus = By.xpath("//ul[@class='footer-primary-nav--list']/li/a[@href='/help-support/contact-us']");
    public By p1ContactUs = By.xpath("//*[@class='contact-sidebar']//child::p[1]");
    public By p2ContactUs = By.xpath("//*[@class='contact-sidebar']//child::p[2]");
    public By p3ContactUs = By.xpath("//*[@class='contact-sidebar']//child::p[3]");
    public By p4ContactUs = By.xpath("//*[@class='contact-sidebar']//child::p[4]");
    public By l1ContactUs = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl02_M31ContactUsForm_pnlContactForm']//child::label[1]");
    public By l2ContactUs = By.xpath("//*[@class='contact-category-toggle']//following::label[1]");
    public By l3ContactUs = By.xpath("//*[@class='contact-category-toggle']//following::label[2]");
    public By l4ContactUs = By.xpath("//*[@class='contact-category-toggle']//following::label[3]");
    public By l5ContactUs = By.xpath("//*[@class='contact-category-toggle']//following::label[4]");
    public By l6ContactUs = By.xpath("//*[@class='contact-category-toggle']//following::label[5]");
    public By imgIrelandStateSaving = By.xpath("//*[@title='Go back to homepage' and @class='gtm-logo']/img");


    public void AboutUsPage() {
        webUtil.gettextlog(FooterHeader, String::equals, "About Ireland State Savings");
        webUtil.gettextlog(AboutContent1, String::equals, "Ireland State Savings is the brand name used by the National Treasury Management Agency (NTMA), for the range of savings products offered to personal savers.");
        webUtil.gettextlog(AboutContent2, String::equals, "When you save with us, every cent is protected by the State, so you can feel completely secure.");
        webUtil.gettextlog(AboutContent3, String::equals, "Ireland State Savings money forms part of the National Debt of Ireland and is under the management of the NTMA, on behalf of the Minister for Finance.");
        webUtil.gettextlog(AboutContent4, String::equals, "The repayment of all Ireland State Savings money is a direct, unconditional obligation of the Irish Government.");
        webUtil.gettextlog(AboutContent5, String::equals, "Repayment of your money includes Principal, Interest and bonus payments if due.");
        webUtil.gettextlog(AboutHead, String::equals, "About Our Agents");
        webUtil.gettextlog(AboutContent6, String::equals, "An Post acts as an agent of the NTMA in relation to the sale and administration of Ireland State Savings products (except Prize Bonds).");
        webUtil.gettextlog(AboutContent7, String::equals, "The Prize Bond Company, a joint venture between An Post www.anpost.com and Fexco www.fexco.com, operates the Prize Bond Scheme on behalf of the NTMA. An Post is responsible for the accounting, marketing and the conduct of the draw, the administration is carried out by Fexco.");
        webUtil.gettextlog(AboutContent8, String::equals, "If you require additional information about Ireland State Savings, please Contact Us.");
        webUtil.gettextlog(AboutLink1, String::equals, "www.anpost.com");
        webUtil.gettextlog(AboutLink2, String::equals, "www.fexco.com");
        webUtil.gettextlog(AboutLink3, String::equals, "Contact Us.");
        webUtil.gettextByAttribute(AboutLink1, String::equals, "http://www.anpost.com/", "href");
        webUtil.gettextByAttribute(AboutLink2, String::equals, "http://www.fexco.com/", "href");
        webUtil.gettextByAttribute(AboutLink3, String::equals, getUrl() + "/help-support/contact-us", "href");


    }



    public void userpermissionVerify() {
        try {
            if (driver.findElement(By.xpath("//*[contains(text(),'Privacy Preference Centre')]")).isDisplayed()) {
                ExtentCucumberAdapter.addTestStepLog("<b>Preferences permission has been enabled again/delayed display which is not expected</b>");
                log.info("<b>Preferences permission has been enabled again/delayed display which is not expected</b>");
                driver.findElement(By.xpath("//*[contains(text(),'Allow All')]")).click();
            }
        } catch (Exception ignored) {
            //ExtentCucumberAdapter.addTestStepLog("Preferences permission enabled already");
        }
    }


    public void validateAccessibilityLinkContent() throws Exception {
        try {
            webUtil.scrollToView(imgIrelandStateSaving);
            webUtil.clickLog(btnAccessibility, "Accessibility button");
            webUtil.waitUntilElementVisible(hdrAccessblity, 10);
            webUtil.gettextlog(hdrAccessblity, String::equals, "Accessibility");
            webUtil.gettextlog(By.xpath("//div[@class='main-content']//p[1]"), String::equals, "We are committed to accessibility and recognise the importance of ensuring that our website is accessible to as many of our customers as possible.\n" +
                    "\n" +
                    "The website is designed to be simple, engaging and a rewarding experience for our customers.");
//                    "\n" +
//                    "This website has been designed to achieve “Double-A” accessibility as set out by the World Wide Web Consortium’s (W3C) Web Content Accessibility Guidelines (WCAG) 2.1 in compliance with the National Disability Authority’s Code of Practice on accessibility of public services and information provided by public bodies.");
            webUtil.gettextlog(By.xpath("//div[@class='main-content']//p[2]"), String::equals, "The European Accessibility Act (EAA) 2025, effective from June 28, 2025, establishes new requirements for accessible products and services within Ireland and the European Union. We are committed to enhancing the accessibility of our website for all users.");
            webUtil.gettextlog(By.xpath("//div[@class='main-content']//p[3]"), String::equals, "This website has been designed to achieve “Double-A” accessibility as set out by the World Wide Web Consortium’s (W3C) Web Content Accessibility Guidelines (WCAG) 2.1 in compliance with the National Disability Authority’s Code of Practice on accessibility of public services and information provided by public bodies.");
            webUtil.gettextlog(By.xpath("//div[@class='main-content']//h4[1]"), String::equals, "Ongoing Accessibility Monitoring");
            webUtil.gettextlog(By.xpath("//div[@class='main-content']//p[4]"), String::equals, "A number of elements utilise modern coding languages such as JavaScript that need to be enabled to deliver an optimal user experience.");
            webUtil.gettextlog(By.xpath("//div[@class='main-content']//p[5]"), String::equals, "Our development methodology integrates accessibility considerations from the outset, supported by ongoing evaluation and refinement of our website's accessibility features. This continuous programme underscores our commitment to providing an inclusive online experience and ensuring compliance with applicable accessibility standards.");
            webUtil.gettextlog(By.xpath("//div[@class='main-content']//p[6]"), String::equals, "We want as many people as possible to be able to use this website. Guided by the Web Content Accessibility Guidelines (WCAG), here are some of the features we've added - or are working on - to make our website easier for everyone to use:");
            webUtil.gettextlog(By.xpath("//div[@class='main-content']//h4[2]"), String::equals, "How we're making this website more accessible:");
            webUtil.gettextlog(By.xpath("//div[@class='main-content']//h4[3]"), String::equals, "Browser Support");

            webUtil.gettextlog(By.xpath("//div[@class='main-content']//ul//li[1]"), String::equals, "Pages are easy to read on all common web browsers.");
            webUtil.gettextlog(By.xpath("//div[@class='main-content']//ul//li[2]"), String::equals, "Clear, intuitive, accessible navigation allows users to find information quickly and easily.");
                    webUtil.gettextlog(By.xpath("//div[@class='main-content']//ul//li[3]"), String::equals, "The website is responsive to different devices and will display the website in a format best suited to the device you are using.");
                            webUtil.gettextlog(By.xpath("//div[@class='main-content']//ul//li[4]"), String::equals, "Addition of alternative text to images to ensure that customers using screen readers can understand the image content.");
                                    webUtil.gettextlog(By.xpath("//div[@class='main-content']//ul//li[5]"), String::equals, "Where possible, the website text can be zoomed up to 200% - in some cases as much as 400% - to assist readability without assistive technology.");
            webUtil.gettextlog(By.xpath("//div[@class='main-content']//ul//li[6]"), String::equals, "Accessibility colour contrast on interactive elements and text to provide sufficient contrast between text (or other foreground elements) and the background. We continue to enhance this to improve readability.");
                    webUtil.gettextlog(By.xpath("//div[@class='main-content']//ul//li[7]"), String::equals, "Where possible, the website is structured in a way that screen readers can interpret it correctly.");
                            webUtil.gettextlog(By.xpath("//div[@class='main-content']//ul//li[8]"), String::equals, "The tab key order follows the most logical visual order of page elements making it possible to navigate through the interactive page elements with the tab key.");

            webUtil.gettextlog(By.xpath("//div[@class='main-content']//p[7]"), String::equals, "The website has been built to display correctly on all common web browsers and devices. It uses standards compliant code, meaning any future browsers should also display the website correctly. However, we have designed our website for optimal viewing on the last two versions of the browsers that the vast majority of our visitors use.\n" +
                    "\n" +
                    "Supported browsers based on the above guideline:\n" +
                    "\n" +
                    "Standard View:\n" +
                    "Chrome, current version\n" +
                    "Firefox, current version\n" +
                    "Safari, current version\n" +
                    "Edge, current version\n" +
                    " \n" +
                    "Mobile View:\n" +
                    "Chrome for Android, last 2 versions\n" +
                    "Mobile Safari (iPhone browser), last 2 versions");
            webUtil.gettextlog(By.xpath("//a[@href='https://www.w3.org/TR/WCAG21/']"),String::equals,"Web Content Accessibility Guidelines (WCAG) 2.1");
            webUtil.gettextByAttribute(By.xpath("//a[@href='https://www.w3.org/TR/WCAG21/']"),String::equals,"https://www.w3.org/TR/WCAG21/","href");

            webUtil.gettextlog(By.xpath("//div[@class='main-content']//h4[4]"), String::equals, "Last updated: September 2025");
            ExtentCucumberAdapter.addTestStepLog("Accessibility page Validated");
            webUtil.scrollToView(imgIrelandStateSaving);
            webUtil.waitFor(2000);

        } catch (Exception e) {
            Assert.fail("validateAccessibilityLinks " + e.getMessage());
        }
    }

    public void validateDataProtectionLinkContent() throws Exception {
        try {
            webUtil.clickLog(btnDataProtection, "Data Protection button");
            webUtil.waitFor(2000);
            userpermissionVerify();
            webUtil.waitUntilElementVisible(hdrDataProtection, 10);
            webUtil.gettextlog(hdrDataProtection, String::equals, "Ireland State Savings Data Protection Notice");
            webUtil.gettextlog(By.xpath("//div[@class='main-content']//p"), String::equals, "Ireland State Savings is the brand name used by the National Treasury Management Agency (\"NTMA\") when offering savings products to individuals on behalf of the Minister for Finance acting pursuant to the powers conferred on the NTMA by the National Treasury Management Agency Act 1990 (as amended) and related legislation. While visiting this website, An Post and/or the Prize Bond Company, acting as agents of the NTMA, who is the data controller, may process certain of your personal data as a data processor in accordance with applicable data protection legislation and for the purpose set out in the Ireland State Savings Data Protection Statement. Such personal data may for example include your email address (should you choose to contact Ireland State Savings through any email address listed on the website) and your Internet Protocol (or IP) address. For further information, particularly in relation your data protection rights and how to contact the Ireland State Savings Data Protection Co-ordinator, please refer to the Ireland State Savings Data Protection Statement which is available here. Links to other external websites are inserted for your convenience and Ireland State Savings is not responsible for the content or the privacy policies of any external websites including social media websites.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p/a"),String::equals,"here");
            webUtil.gettextlog(By.xpath("//h4[strong]"), String::equals, "Last updated: March 2026");
            webUtil.gettextByAttribute(By.xpath("//div[@class='m11-body_copy--content general-content']/p/a"),String::equals, getUrl()+"/help-support/help-articles/state-savings-data-protection-statement","href");


            ExtentCucumberAdapter.addTestStepLog("Data Protection page Validated");
            webUtil.scrollToView(imgIrelandStateSaving);
            webUtil.waitFor(2000);


        } catch (Exception e) {
            Assert.fail("validatedataprotectionLinks " + e.getMessage());
        }
    }

    public void validateCookiePolicyLinkContent() throws Exception {
        try {
            webUtil.clickLog(btnCookiePolicy, "Cookie Policy button");
            webUtil.waitUntilElementVisible(hdrCookiePolicy, 10);
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h1"), String::equals, "Ireland State Savings Cookie Policy");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[1]"), String::equals, "This Cookie Policy describes the different types of cookies and similar technologies that may be applied on the Ireland State Savings website.\n" +
                    "\n" +
                    "For information regarding how Ireland State Savings processes personal information, please refer to the Ireland State Savings Data Protection Notice and detailed Ireland State Savings Data Protection Statement which is found on the Ireland State Savings website.\n" +
                    "\n" +
                    "We may change this Cookie Policy at any time. Any changes in this Cookie Policy will become effective when we make the revised Cookie Policy available on or through the website.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[1]"), String::equals, "What is a cookie?");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[2]"), String::equals, "Cookies are text files containing small amounts of information which are downloaded to your device when you visit a website. Cookies are then sent back to the originating website on each subsequent visit, or to another website that recognises that cookie. Cookies are useful because they allow a site to recognise a user’s device.\n" +
                    "\n" +
                    "Cookies do lots of different jobs, like letting you navigate between pages efficiently remembering your preferences, and generally improve your website experience. They can also help to ensure that adverts you see online are more relevant to you and your interests.\n" +
                    "\n" +
                    "Most websites you visit will use cookies in order to improve your user experience by enabling that website to ‘remember’ you, either for the duration of your visit (using a ‘session cookie’) or for repeat visits (using a ‘persistent cookie’).\n" +
                    "\n" +
                    "Cookies may be set by the website you are visiting (‘first party cookies’) or they may be set by other websites who run content on the page you are viewing (‘third party cookies’). Ireland State Savings does not control the processing practices of those third parties so you should review their specific cookies and privacy policies and your own browser settings and user profiles with those third parties before agreeing to their use.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[2]"), String::equals, "Ireland State Savings Cookies");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[3]"), String::equals, "Ireland State Savings uses first party and third party cookies. We use cookies in order to make this website easier to use, to support the provision of information and functionality to you, as well as to provide us with information about how this website is used so that we can make sure it is as up to date, relevant and error free as we can.\n" +
                    "\n" +
                    "For further information about the types of cookies that are used on this website please see Cookies We Use.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[4]"), String::equals, "Further information about the types of cookies used on our sites is set out below.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[3]"), String::equals, "Strictly Necessary Cookies");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[5]"), String::equals, "Strictly necessary cookies are essential in order to enable you to move around the website and use its features and/or services and allow us to track our critical web business indicators. Any cookies used in this way are first party only in nature and are fully aggregated Without these absolutely necessary cookies, the website will not perform as smoothly for you as we would like it to and we may not be able to provide the website or certain services or features. In regard to our mobile apps, these technologies are necessary for our apps to function and cannot be switched off in our systems. They are usually only set in response to actions made by you which amount to a request for services, such as setting your privacy preferences, logging in or filling in forms. You can set your device to block or alert you about these technologies, but some parts of the app may not then work.");

            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/ol"), String::equals, "Select the cookie icon on the bottom of the webpage to open the Privacy Preference Centre.\n" +
                    "In the ‘Manage Consent Preferences’ section choose to accept or reject the different types of cookies.\n" +
                    "Select ‘Confirm My Choices’ to set your preferences.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[4]"), String::equals, "Performance Cookies");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[6]"), String::equals, "Performance cookies collect information about how visitors use a website, for instance which pages visitors go to most often, and if they get error messages from web pages. They also allow us to test design and website functionality for improved user experience. Performance Cookies are used to improve how a website works and Google Analytics uses online identifiers and may constitute personal data. The legal basis for the processing of data in this instance is consent.");


            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[5]"), String::equals, "Functional Cookies");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[7]"), String::equals,"Functional cookies allow this website to remember choices you make and any customisations you make to website pages during your visit and provide enhanced, more personal features. For instance, these cookies can be used to remember changes you have made to text size, fonts and other parts of web pages that you can customise. They may also be used to provide services you have asked for such as watching a video, using the Chat feature or commenting on a blog. The information these cookies collect cannot track your browsing activity on other websites. More information about such cookies can be found in the relevant third party website. The legal basis for the processing of data in this instance is consent.");
                    webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[6]"), String::equals, "Advertising & Marketing Cookies (EEA)");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[8]"), String::equals,"These cookies may be set through our site by our advertising software partners. We may use advertising cookies to build a profile of your interests and show you relevant advertising on other sites. If you do not allow them, you will experience less targeted advertising, and limit our ability to improve the website for you.  All data will be kept within the EEA. The legal basis for the processing of data in this instance is consent.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[7]"), String::equals, "Social Media Cookies");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[9]"), String::equals,"These cookies are set by a range of social media services that we have added to the website.  For example we use these cookies to enable you view video content from YouTube. They are capable of tracking your browser across other websites and building up a profile of your interests. This may impact the content and messages you see on other websites you visit. If you do not allow these cookies you may not be able to use or see our video content.  The legal basis for the processing of data in this instance is consent.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[8]"), String::equals, "Managing your Preferences");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[10]"), String::equals,"The Ireland State Savings website enables you to manage your cookie preferences at any time via the Privacy Preference Centre.\n" +
                    "\n" +
                    "To manage your cookie preferences:");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']//li[1]/p"), String::equals, "Select the cookie icon on the bottom of the webpage to open the Privacy Preference Centre.");
                    webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']//li[2]/p"), String::equals, "In the ‘Manage Consent Preferences’ section choose to accept or reject the different types of cookies.");
                            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']//li[3]/p"), String::equals, "Select ‘Confirm My Choices’ to set your preferences.");

            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[9]"), String::equals, "International Transfers of Personal Data");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[11]"), String::equals,"Personal Data may be transferred outside of Ireland where necessary to provide our services to you, including for example where you instruct a transfer from one of your accounts to an account outside Ireland, in accordance with your instructions, where you have explicitly consented, and / or as otherwise required or permitted by law.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[12]"), String::equals,"Many of the countries will be within the European Economic Area (the “EEA”), or will be ones which the European Commission has approved, and will have data protection laws which are the same as or broadly equivalent to those in Ireland.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[13]"), String::equals,"However, some transfers may be to countries which do not have equivalent protections, and in that case An Post shall use reasonable efforts to implement contractual protections for your Personal Data.  While this will not always be possible where we are required to transfer your Personal Data in order to comply with and perform our agreement(s) with you or where we have a legal obligation to do so, any transfers will be done in accordance with applicable data protection laws, including through the implementation of appropriate or suitable safeguards in accordance with such applicable data protection laws.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[14]"), String::equals,"Below we set out the names of the cookies that create international transfers, the destination countries and the safeguards used.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[15]"), String::equals,"Vendor: Google Analytics 4 (GA4)");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[16]"), String::equals,"Cookie Name(s): _ga, _ga_xxxxxxxxxx");

            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[17]"), String::equals,"Purpose: Analytics, Cross-Device Tracking.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[18]"), String::equals,"Destination: United States (with initial processing and automatic IP anonymization in the EU).");

            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[19]"), String::equals,"International Transfer: Yes.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[20]"), String::equals,"Safeguard: EU-US Data Privacy Framework (Adequacy Decision).");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[21]"), String::equals,"Lawful Basis: Explicit User Consent.");

            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[10]"), String::equals, "Your Legal Rights");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[22]"), String::equals,"For Updating and correcting your Personal Data");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[23]"), String::equals,"If we hold incorrect Personal Data about you, you have the right to have the data amended.  While we will use reasonable efforts to keep your Personal Data up to date, you will need to notify us without delay in the event of any change in your personal circumstances, so that we can keep the Personal Data up to date.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[24]"), String::equals,"There can be no changes made to any personal data provided for the purpose of posting an item after the item has been posted");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[25]"), String::equals,"You can update your Personal Data by writing to the Data Privacy Office, An Post, Exo Building, North Wall Quay, Dublin 1, D01 W5Y2; together with:");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/ol[2]"), String::equals,"Your name and address; and" +

                    "\n" + "A description of the specific Personal Data you wish to have rectified.");


            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[11]"), String::equals, "Right of Erasure");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[26]"), String::equals,"You have the right in some circumstances to have your Personal Data, which we hold, erased by writing to the Data Privacy Office, An Post, Exo Building, North Wall Quay, Dublin 1, D01 W5Y2;or privacyoffice@anpost.ie together with:");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/ol[3]"), String::equals,"Your name and address; and" +

                    "\n" + "A description of the specific Personal Data you wish to have erased.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[27]"), String::equals,"If you request an erasure of your Personal Data, all your data will be erased subject to the following important notice.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[28]"), String::equals,"We will not be required to erase your Personal Data where to do so would prevent us from meeting our respective contractual obligations, or where we are required to process (including retaining) your Personal Data in order to comply with a legal obligation, or if the Personal Data is necessary to establish, exercise or defend our legal rights or for the purpose of legal proceedings.");


            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[12]"), String::equals, "Right of Access");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[29]"), String::equals,"You have a right to be given a copy of your Personal Data on request, subject to certain exceptions.  Please note that we have the right to require that you identify yourself before we will respond to any access request.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[30]"), String::equals,"For more information, see our Data Access Policy https://www.anpost.com/Privacy/Access-Request-Policy.");

            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[12]"), String::equals, "Right of Access");


            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[31]"), String::equals,"You also have the right, in certain circumstances, to request restriction on the use, of your Personal Data, and to object to certain uses of your Personal Data, in each case subject to the restrictions set out in applicable data protection laws.  Further information on these rights, and the circumstances in which they may arise in connection with our processing of your Personal Data can be obtained by writing to the Data Privacy Office, An Post, Exo Building, North Wall Quay, Dublin 1, D01 W5Y2low.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[32]"), String::equals,"In any case where we rely on your consent to process your Personal Data, you have the right to change your mind and withdraw consent by writing to the Data Privacy Office, An Post, Exo Building, North Wall Quay, Dublin 1, D01 W5Y2.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[33]"), String::equals,"Where we rely on a legitimate purpose of ours or of a third party recipient of the Personal Data, in order to use and disclose Personal Data, you are entitled to object to such use or disclosure of your Personal Data, and if you do so, we will cease to use and process your Personal Data for that purpose, unless we can show there are compelling legitimate reasons for us to continue or we need to use the Personal Data for the purposes of legal claims.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[34]"), String::equals,"In limited circumstances, you may also have the right to data portability in respect of certain of your Personal Data, which means you can request that we provide it to you in a structured, commonly used and machine-readable format, or transmit it to your third party nominee where this is technically feasible.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[35]"), String::equals,"You have the right to lodge a complaint about our processing of your Personal Data with the Data Protection Commission at www.dataprotection.ie.");

            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[13]"), String::equals, "Your Other Rights");
            webUtil.gettextlog(
                    By.xpath("//div[@class='m11-body_copy--content general-content']/p[36]"),
                    String::equals,
                    "If you would like to find out more about advertising networks and affiliate programmes and how cookies are used for these purposes, please visit https://www.youronlinechoices.com. This is an information portal which is run by the Internet Advertising Bureau (IAB), the trade association for digital advertising, and will provide you with all the information you need about online behavioural advertising and the choices available to you.\n\n" +
                            "For further information about Google Analytics please see Safeguarding your data.\n\n" +
                            "To opt out of being tracked by Google Analytics across all websites, visit https://tools.google.com/dlpage/gaoptout.\n\n" +
                            "If you would like further information about the cookies used in relation to YouTube videos, the YouTube privacy notice, including details about YouTube related cookies, is available at: https://www.google.co.uk/policies/privacy/."
            );
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[15]"), String::equals, "Glossary of technical terms used");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[37]"), String::equals, "IP address: The identifying details for your computer (or your internet company’s computer), expressed in “internet protocol” code (for example 192.168.72.34). Every computer connected to the web has a unique IP address, although the address may not be the same every time a connection is made.\n" +
                    "\n" +
                    "personal data: information about you by which you can be identified, which is in the possession of Ireland State Savings (e.g. your name and address)\n" +
                    "\n" +
                    "web browser: The piece of software you use to read web pages. Examples are Microsoft Edge, Google Chrome, Firefox and Safari.");



                    webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[1]/a[1]"),String::equals,"Ireland State Savings Data Protection Notice");
            webUtil.gettextByAttribute(By.xpath("//div[@class='m11-body_copy--content general-content']/p[1]/a[1]"),String::equals, getUrl()+"/data-protection","href");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[1]/a[2]"),String::equals,"Ireland State Savings Data Protection Statement");
            webUtil.gettextByAttribute(By.xpath("//div[@class='m11-body_copy--content general-content']/p[1]/a[2]"),String::equals, getUrl()+"/help-support/help-articles/state-savings-data-protection-statement","href");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/p[3]/a"),String::equals,"Cookies We Use");
            webUtil.gettextByAttribute(By.xpath("//div[@class='m11-body_copy--content general-content']/p[3]/a"),String::equals,getUrl()+"/cookies-we-use","href");
            webUtil.gettextlog(By.xpath("//a[@href='https://www.youronlinechoices.com/']"),String::equals,"https://www.youronlinechoices.com");
            webUtil.gettextByAttribute(By.xpath("//a[@href='https://www.youronlinechoices.com/']"),String::equals,"https://www.youronlinechoices.com/","href");
            webUtil.gettextlog(By.xpath("//a[text()='Safeguarding your data']"),String::equals,"Safeguarding your data");
            webUtil.gettextByAttribute(By.xpath("//a[text()='Safeguarding your data']"),String::equals,"https://support.google.com/analytics/answer/6004245?hl=en","href");
            webUtil.gettextlog(By.xpath("//a[@href='https://tools.google.com/dlpage/gaoptout']"),String::equals,"https://tools.google.com/dlpage/gaoptout");
            webUtil.gettextByAttribute(By.xpath("//a[@href='https://tools.google.com/dlpage/gaoptout']"),String::equals,"https://tools.google.com/dlpage/gaoptout","href");
            webUtil.gettextlog(By.xpath("//a[@href='https://policies.google.com/privacy']"),String::equals,"https://www.google.co.uk/policies/privacy/");
            webUtil.gettextByAttribute(By.xpath("//a[@href='https://policies.google.com/privacy']"),String::equals,"https://policies.google.com/privacy","href");

            webUtil.gettextByAttribute(By.xpath("//a[@href='https://support.google.com/analytics/answer/6004245?hl=en']"),String::equals,"https://support.google.com/analytics/answer/6004245?hl=en","href");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']/h4[16]"), String::equals, "Last updated March 2026");

            ExtentCucumberAdapter.addTestStepLog("Cookie Policy page validated");
            webUtil.scrollToView(imgIrelandStateSaving);
            webUtil.waitFor(2000);


        } catch (Exception e) {
            Assert.fail("validatedcookiepolicyLinks " + e.getMessage());
        }
    }

    public void validateCookieWeUseLinkContent() throws Exception {
        try {
            webUtil.clickLog(btnCookieWeUse, "Cookies We Use button");
            webUtil.waitFor(2000);
            userpermissionVerify();
            webUtil.waitUntilElementVisible(hdrCookieUse, 10);
            webUtil.gettextlog(hdrCookieUse, String::equals, "Cookie List");
            webUtil.gettextlog(By.xpath("//div[@id='cookie-policy-description']"), String::equals, "When you visit any website, it may store or retrieve information on your browser, mostly in the form of cookies. This information might be about you, your preferences or your device and is mostly used to make the website work as you expect it to. The information does not usually directly identify you, but it can give you a more personalised web experience. Because we respect your right to privacy, you can choose not to allow some types of cookies. A description of each category is given below. However, blocking some types of cookies may impact your experience of the website and the services we are able to offer.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']//section[1]/h4"), String::equals, "Strictly Necessary");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']//section[1]/p"), String::equals, "Strictly necessary cookies are essential in order to enable you to move around the website and use its features and/or services and allow us to track our critical web business indicators. Any cookies used in this way are first party only in nature and are fully aggregated and anonymous. Without these absolutely necessary cookies, the website will not perform as smoothly for you as we would like it to and we may not be able to provide the website or certain services or features.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']//section[1]//table"), String::equals, "Strictly Necessary\n" +
                    "Host page Cookie Name(s) 1st/3rd Party Lifespan\n" +
                    "www.statesavings.ie .AspNetCore First Party Session\n" +
                    "statesavings.ie OptanonConsent , OptanonAlertBoxClosed First Party 179 Days, 179 Days");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']//section[2]/h4"), String::equals, "Performance");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']//section[2]/p"), String::equals, "Performance cookies collect information about how visitors use a website, for instance which pages visitors go to most often, and if they get error messages from web pages. These cookies don’t collect information that identifies a visitor. Google Analytics is being used in first party mode and does not capture IP addresses or similar personal data. Performance Cookies are only used to improve how a website works.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']//section[2]//table"), String::equals, "Performance\n" +
                    "Host page Cookie Name(s) 1st/3rd Party Lifespan\n" +
                    "statesavings.ie _ga_xxxxxxxxxx First Party 1 Year\n" +
                    "statesavings.ie _ga Third Party 1 Year");


            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']//section[3]/h4"), String::equals, "Functional");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']//section[3]/p"), String::equals, "Functional cookies allow this website to remember choices you make and any customisations you make to website pages during your visit and provide enhanced, more personal features. For instance, these cookies can be used to remember changes you have made to text size, fonts and other parts of web pages that you can customise. They may also be used to provide services you have asked for such as watching a video or commenting on a blog. The information these cookies collect cannot track your browsing activity on other websites. More information about such cookies can be found in the relevant third party website.");
            webUtil.gettextlog(By.xpath("//div[@class='m11-body_copy--content general-content']//section[3]//table"), String::equals, "Functional\n" +
                    "Host page Cookie Name(s) 1st/3rd Party Lifespan\n" +
                    "statesavings.ie _genesysJsCanary First Party A few seconds");
            ExtentCucumberAdapter.addTestStepLog("Cookies We Use page Validated");
            webUtil.scrollToView(imgIrelandStateSaving);
            webUtil.waitFor(2000);


        } catch (Exception e) {
            Assert.fail("validatedcookieweuseLinks " + e.getMessage());
        }
    }

    public void validatePrizeBondCompanyLinkContent() throws Exception {
        try {
            webUtil.clickLog(btnPrizeBondCompany, "Prize Bond Company button");
            userpermissionVerify();
            webUtil.waitFor(2000);
            webUtil.waitUntilElementVisible(hdrPrizeBond, 10);
            webUtil.gettextlog(hdrPrizeBond, String::equals, "What is the Prize Bond Company?");
            webUtil.gettextlog(By.xpath("//article/p"), String::equals, "The Prize Bond Company (PBC) DAC is a joint venture operated between An Post DAC www.anpost.ie and Fexco www.fexco.com.\n" +
                    "\n" +
                    "The PBC was set up in 1989 to operate the scheme for the Minister for Finance. The scheme is now operated on behalf of the National Treasury Management Agency (NTMA) which manages the national debt on behalf of the Minister for Finance.\n" +
                    "\n" +
                    "Download Prize Bonds Annual Report 2025\n" +  //Newly Added Shan
                    "Download Prize Bonds Annual Report 2024\n" +
                    "Download Prize Bonds Annual Report 2023\n" +
                    "Download Prize Bonds Annual Report 2022\n" +
                    "Download Prize Bonds Annual Report 2021\n" +
                    "Download Prize Bonds Annual Report 2020\n" +
                    "Download Prize Bonds Annual Report 2019");

            webUtil.gettextlog(By.xpath("//article/p/a[1]"),String::equals,"www.anpost.ie");
            webUtil.gettextlog(By.xpath("//article/p/a[2]"),String::equals,"www.fexco.com");
            webUtil.gettextlog(By.xpath("//article/p/a[3]"),String::equals,"Download Prize Bonds Annual Report 2025");
            webUtil.gettextByAttribute(By.xpath("//article/p/a[4]"),String::equals,getUrl()+"/getmedia/8f40085e-ba5a-466e-bbdf-cc714994bc06/Prizebonds_AnnualReport_2024.pdf","href");
            webUtil.gettextlog(By.xpath("//article/p/a[4]"),String::equals,"Download Prize Bonds Annual Report 2024");
            webUtil.gettextByAttribute(By.xpath("//article/p/a[5]"),String::equals,getUrl()+"/getmedia/4448a0f2-44a1-4c72-a066-8decef948b3f/Prizebonds_AnnualReport_2023.pdf","href");
            webUtil.gettextlog(By.xpath("//article/p/a[5]"),String::equals,"Download Prize Bonds Annual Report 2023");
            webUtil.gettextByAttribute(By.xpath("//article/p/a[6]"),String::equals,getUrl()+"/getmedia/e63e1f34-b218-4a4f-aaff-b66b8248fdc7/prizebonds_annualreport_2022.pdf","href");
            webUtil.gettextlog(By.xpath("//article/p/a[6]"),String::equals,"Download Prize Bonds Annual Report 2022");
            webUtil.gettextByAttribute(By.xpath("//article/p/a[7]"),String::equals,getUrl()+"/getmedia/edfe2354-db5c-4b54-9045-9da1b416ff3d/prizebonds_annualreport_2021.pdf","href");
            webUtil.gettextlog(By.xpath("//article/p/a[7]"),String::equals,"Download Prize Bonds Annual Report 2021");
            webUtil.gettextByAttribute(By.xpath("//article/p/a[8]"),String::equals,getUrl()+"/getmedia/b0bbba0f-f230-458f-908a-8c9e6574e550/prizebonds_annualreport_2020.pdf","href");
            webUtil.gettextlog(By.xpath("//article/p/a[8]"),String::equals,"Download Prize Bonds Annual Report 2020");
            webUtil.gettextByAttribute(By.xpath("//article/p/a[9]"),String::equals,getUrl()+"/getmedia/150eb77d-3588-4a8a-b861-faadbd984e52/prizebonds_annualreport_2019.pdf","href");


            ExtentCucumberAdapter.addTestStepLog("Prize Bond Company page displayed");
            driver.navigate().back();
            webUtil.scrollToView(imgIrelandStateSaving);


        } catch (Exception e) {
            Assert.fail("validatedprizebondcompanyLinks " + e.getMessage());
        }
    }
    public void ClickContactus()
    {
        try {
            webUtil.clickLog(btnContactus, "Contact Us button");
        }catch (Exception e){
            Assert.fail("ClickContactUsFooterLink " + e.getMessage());
        }
    }

    public void validateContactUsLinkContent() throws Exception {
        try {
            webUtil.clickLog(btnContactus, "Contact Us button");
            webUtil.waitUntilElementVisible(hdrContactUs, 10);
            webUtil.gettextlog(hdrContactUs, String::equals, "Contact Us");
            webUtil.gettextlog(By.xpath("//form/div[1]/p"), String::equals, "Please complete all mandatory fields below.");
            webUtil.gettextlog(By.xpath("//form/div[1]/label"), String::equals, "Product");
            webUtil.gettextlog(By.xpath("//form/div[1]/select/option[1]"), String::equals, "Please select a product");
            webUtil.gettextlog(By.xpath("//form/div[1]/select/option[2]"), String::equals, "Fixed Term");
            webUtil.gettextlog(By.xpath("//form/div[1]/select/option[3]"), String::equals, "Prize Bonds");
            webUtil.gettextlog(By.xpath("//form/div[1]/select/option[4]"), String::equals, "Both");
            webUtil.selectListItem(By.xpath("//form/div[1]/select"), select -> select.selectByVisibleText("Fixed Term"));
            webUtil.gettextlog(By.xpath("//select[@id='category']/option[1]"), String::equals, "Please select a category");
            webUtil.gettextlog(By.xpath("//select[@id='category']/option[2]"), String::equals, "Product Information");
            webUtil.gettextlog(By.xpath("//select[@id='category']/option[3]"), String::equals, "Statement of Accounts Request");
            webUtil.gettextlog(By.xpath("//select[@id='category']/option[4]"), String::equals, "Change to Personal Details");
            webUtil.gettextlog(By.xpath("//select[@id='category']/option[5]"), String::equals, "Repayment Re-investment");
            webUtil.gettextlog(By.xpath("//select[@id='category']/option[6]"), String::equals, "Deceased Accounts");
            webUtil.gettextlog(By.xpath("//select[@id='category']/option[7]"), String::equals, "Identification Documents requirements");
            webUtil.gettextlog(By.xpath("//select[@id='category']/option[8]"), String::equals, "New Purchase Queries");
            webUtil.gettextlog(By.xpath("//select[@id='category']/option[9]"), String::equals, "Website Technical Query");
            webUtil.gettextlog(By.xpath("//select[@id='category']/option[10]"), String::equals, "Media Enquiries");
            webUtil.gettextlog(By.xpath("//select[@id='category']/option[11]"), String::equals, "Complaint");
            webUtil.gettextlog(By.xpath("//select[@id='category']/option[12]"), String::equals, "Other");
            webUtil.gettextlog(By.xpath("//form//div[2]/label[1]"), String::equals, "Name");
            webUtil.gettextlog(By.xpath("//form//div[2]/label[2]"), String::equals, "SSCN (optional)");
            webUtil.gettextlog(By.xpath("//form//div[2]/label[3]"), String::equals, "Email (We will respond to your query using this email address)");
            webUtil.gettextlog(By.xpath("//form//div[2]/label[4]"), String::equals, "Phone (optional)");
            webUtil.gettextlog(By.xpath("//form//div[2]/label[5]"), String::equals, "Query");
            webUtil.clickLog(By.xpath("//span[@class='tooltip']"), "tooltip");
            webUtil.gettextlog(By.xpath("//span[@id='ppsn_sscn_info']"), String::equals, "State Savings Customer Number/Prize Bond Account Number/Fixed Term Number");
            webUtil.gettextByAttribute(By.xpath("//input[@class='button button--primary']"),String::equals,"Submit Query","value");
            webUtil.gettextlog(By.xpath("//h2[text()='Forms & Downloads']"), String::equals, "Forms & Downloads");
            webUtil.gettextlog(By.xpath("//div[@class='m13-3col_category_listing--container']"), String::equals, "Prize Bonds\n" +
                    "Application Forms\n" +
                    "Fixed Term Products\n" +
                    "Deposit Accounts\n" +
                    "Regular Saving Products\n" +
                    "Terms & Conditions\n" +
                    "Repayments\n" +
                    "Deceased Cases\n" +
                    "Complaints\n" +
                    "Change of Details\n" +
                    "Brochures\n" +
                    "Other");
            webUtil.gettextlog(By.xpath("//div[@class='contact-sidebar']/p[1]"), String::equals, "If you have any issues with our contact form or wish to send us an attachment, please contact us below:");
            webUtil.gettextlog(By.xpath("//div[@class='contact-sidebar']/h4[1]"), String::equals, "Ireland State Savings");
            webUtil.gettextlog(By.xpath("//div[@class='contact-sidebar']/p[2]"), String::equals, "Ireland State Savings\n" +
                    "GPO\n" +
                    "FREEPOST\n" +
                    "Dublin 1\n" +
                    "D01 F5P2\n" +
                    "service@statesavings.ie");
            webUtil.gettextlog(By.xpath("//div[@class='contact-sidebar']/h4[2]"), String::equals, "Prize Bonds");
            webUtil.gettextlog(By.xpath("//div[@class='contact-sidebar']/p[3]"), String::equals, "Ireland State Savings\n" +
                    "Prize Bonds,\n" +
                    "Fexco Centre,\n" +
                    "Killorglin,\n" +
                    "FREEPOST,\n" +
                    "Co. Kerry,\n" +
                    "V93 WN9T\n" +
                    "prizebonds@statesavings.ie");
            webUtil.gettextlog(By.xpath("//div[@class='contact-sidebar']/p[4]"), String::equals, "Phone us at:\n" +
                    "0818 20 50 60 / 01 705 7200\n" +
                    "(lines open Mon-Fri, 08:00 - 20:00)");


            ExtentCucumberAdapter.addTestStepLog("Contact us page Validated");
            webUtil.scrollToView(imgIrelandStateSaving);
            webUtil.waitFor(2000);


        } catch (Exception e) {
            Assert.fail("validatedContactUsLinks " + e.getMessage());
        }
    }






    public Excelutils testData = new Excelutils();

    public void SiteUseTermsAndConditionsPage() throws IOException {
        webUtil.gettextlog(FooterHeader, String::equals, "Ireland State Savings Website Terms & Conditions of Use");
        List<WebElement> hTag = driver.findElements(By.xpath("(//div[contains(@class,'general-content')]//h1)"));
        List <TestData>dataList=testData.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), "Site Use terms and conditions",TestData.class);
        System.out.println(System.currentTimeMillis());
        for (int e = 0; e < hTag.size(); e++) {
            String s = hTag.get(e).getText();
            if (s.isEmpty()) {
                continue;
            } else {
                webUtil.CompareString(s, String::equals, dataList.get(e).getH1());
            }

        }

        List<WebElement> h4Tag = driver.findElements(By.xpath("(//div[contains(@class,'general-content')]//h4)"));

        System.out.println(System.currentTimeMillis());
        for (int f = 0; f < h4Tag.size(); f++) {
            String st = h4Tag.get(f).getText();

            if (st.isEmpty()) {

                continue;
            } else {
                webUtil.CompareString(st, String::equals, dataList.get(f).getH4());
            }
        }
        System.out.println(System.currentTimeMillis());


        List<WebElement> pTag = driver.findElements(By.xpath("(//div[contains(@class,'general-content')]//p)"));


        System.out.println(System.currentTimeMillis());
        for (int g = 0; g < pTag.size(); g++) {
            String str = pTag.get(g).getText();
            if (str == null || str.trim().isEmpty() || str.trim().equals(":")) {

                continue;
            } else {
                webUtil.CompareString(str, String::equals, dataList.get(g).getP());
            }
        }
        System.out.println(System.currentTimeMillis());


        List<WebElement> aTag = driver.findElements(By.xpath("(//div[contains(@class,'general-content')]//a)"));
        System.out.println(System.currentTimeMillis());
        for(int h = 0;h<aTag.size();h++)

        {
            String s1 = aTag.get(h).getText();

            if (!s1.isEmpty()) {
                webUtil.CompareString(s1, String::equals, dataList.get(h).getA());
            }
        }
        System.out.println(System.currentTimeMillis());


    }
  public void TermsAndConditionsPage(){

      webUtil.gettextlog(FooterHeader, String::equals, "General Terms and Conditions and the Specific Conditions for Ireland State Savings");
      List<WebElement> h2Tag = driver.findElements(By.xpath("//div[contains(@class,'general-content')]//h2[string-length(normalize-space(.)) > 1]"));
      System.out.println(System.currentTimeMillis());
//      List <TestData>dataList=testData.getTestData(config.getK13ContentExcelPath(), "Terms and conditions",TestData.class);
      List <TestData>dataList=testData.getTestData(FrameworkConstants.getExcelLocationContentSliderModalFooterArticles(), "Terms and conditions",TestData.class);
      for (int e = 0; e < h2Tag.size(); e++) {
          String s = h2Tag.get(e).getText();

              webUtil.CompareString(s, String::equals, dataList.get(e).getH2());


      }

      List<WebElement> h3Tag = driver.findElements(By.xpath("//div[contains(@class,'general-content')]//h3[string-length(normalize-space(.)) > 1]"));
      System.out.println(System.currentTimeMillis());
      for (int f = 0; f < h3Tag.size(); f++) {
          String st = h3Tag.get(f).getText();

              webUtil.CompareString(st, String::equals, dataList.get(f).getH3());


      }


      List<WebElement> pTag = driver.findElements(By.xpath("//div[contains(@class,'general-content')]//p[string-length(normalize-space(.)) > 1]"));
      System.out.println(System.currentTimeMillis());
      for (int g = 0; g < pTag.size(); g++) {
          String str = pTag.get(g).getText();
          if(!str.isEmpty())
              webUtil.CompareString(str, String::equals, dataList.get(g).getP());

      }

      List<WebElement> LiTag = driver.findElements( By.xpath("//h2[@id='general-terms-and-conditions']/following::ol[1]/li") );
      for (int h = 0; h < LiTag.size(); h++) {
          String s1 = LiTag.get(h).getText().trim().replaceAll("\\s+", " ");
          if (!s1.isEmpty()) {
              String expected = dataList.get(h).getLI().trim().replaceAll("\\s+", " ");
              webUtil.CompareString(s1, String::equalsIgnoreCase, expected);

          }
      }


      List<WebElement> Li2Tag = driver.findElements(By.xpath("//h2[@id='general-terms-and-conditions']/following::ol[1]/following-sibling::ol"));
      System.out.println(System.currentTimeMillis());
      for (int i = 0; i < Li2Tag.size(); i++) {
          String s2 = Li2Tag.get(i).getText();
          if (s2.trim().isEmpty()) {
              continue;
          } else {
              String normalizedWebText = s2.replaceAll("\\s+", " ").trim();
              String normalizedExpectedText = dataList.get(i).getLI2().replaceAll("\\s+", " ").trim();

              webUtil.CompareString(normalizedWebText, String::equals, normalizedExpectedText);
          }
      }


    }


}




