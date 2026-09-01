package com.cucumbercraft.POMPages.PreLoginPages;
import com.cucumbercraft.POMPages.Kentico13_MasterPages;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Log4j2
public class Kentico13_OurProducts extends Kentico13_MasterPages {
    private final WebDriver driver;
    private Kentico13_ComponentHeader comp_header;

    String pageName=null;
//    static Logger log = LogManager.getLogger(Kentico13_MasterPages.class);
    public Kentico13_OurProducts(WebDriver driver) {
        this.driver = driver;
        comp_header=new Kentico13_ComponentHeader(driver);
    }

    private final By sectionHeadersListArr = By.xpath("//*[@class='m03-title_text_button--title']");
    private final By tileHeader=By.xpath("//*[@class='m08-product_listing_cards--row']//h3");

    private String pathSectionLocator(int sectionNumber){
        return String.format("%s%d%s","//*[@class='m08-product_listing_cards brand-white'][",sectionNumber,"]");
    }

    private String pathRowLocator(int rowNumber){
        return String.format("%s%d%s","//div[@class='m08-product_listing_cards--row'][",rowNumber,"]");
    }

    private String locatorRowSection(int sectionValue){
        return String.format("%s%s",pathSectionLocator(sectionValue),"//div[@class='m08-product_listing_cards--row']");
    }
    private String locatorRow(int sectionValue, int rowValue){
        return String.format("%s%s",pathSectionLocator(sectionValue),pathRowLocator(rowValue));
    }

    private String tileLocatorDynamic(int sectionValue, int rowValue){
        return locatorRow(sectionValue, rowValue);
    }

    private By tileYearIssueNumber(String tileLocatorDynamicValue){
        return By.xpath(String.format("%s%s",tileLocatorDynamicValue,"//div[@class='details-wrapper']"));
    }

    private By tileHeader(String tileLocatorDynamicValue){
        return By.xpath(String.format("%s%s",tileLocatorDynamicValue,"//h3"));
    }

    private By anchorLinkTileHeader(String tileLocatorDynamicValue){
        return By.xpath(String.format("%s%s",tileLocatorDynamicValue,"//h3/parent::a"));
    }

    private By tileStatsList(String tileLocatorDynamicValue){
        return By.xpath(String.format("%s%s",tileLocatorDynamicValue,"//div[@class='m08-product_listing_cards--stats']//li"));
    }

    private By tileLearnMoreBtn(String tileLocatorDynamicValue){
        return By.xpath(String.format("%s%s",tileLocatorDynamicValue,"//a[@title='Learn more']"));
    }

    private By tileBuyNowBtn(String tileLocatorDynamicValue){
        return By.xpath(String.format("%s%s",tileLocatorDynamicValue,"//a[@title='Buy now']"));
    }

    private By tileGiftNowBtn(String tileLocatorDynamicValue){
        return By.xpath(String.format("%s%s",tileLocatorDynamicValue,"//a[@title='Gift now']"));
    }

    private final By needLittleMoreHelpDecidingTxt=By.xpath("//h1[@class='m9-CTA_banner--title']");
    private final By visitHelpAndSupportBtn=By.xpath("//h1[@class='m9-CTA_banner--title']/following-sibling::a");

    public String getBannerTitle(String pageName){
        this.pageName=pageName;
        return getTextByLocator(bannerTitle);
    }

    public String getBannerText(){
        return getTextByLocator(bannerText);
    }

    public List<String> getSectionHeadersList(){
        List <String> listLoadTempHdrSection=new ArrayList<>();
        webUtil.waitUntilElementVisible(sectionHeadersListArr,4);
        for(WebElement elemVar: driver.findElements(sectionHeadersListArr)){
            listLoadTempHdrSection.add(elemVar.getText());
        }
        return listLoadTempHdrSection;
    }


    private String getTileStatsList(String tileDynamicLocator){
        List<String> tempLoadStats=new ArrayList<>();
        webUtil.waitUntilElementVisible(tileStatsList(tileDynamicLocator),4);
        for (WebElement elemVar: driver.findElements(tileStatsList(tileDynamicLocator))){
            tempLoadStats.add(elemVar.getText());
        }
        return listToStringConv(tempLoadStats);
    }

    private String getTileYearIssueNumber(String tileDynamicLocator){
        return getTextByLocator(tileYearIssueNumber(tileDynamicLocator));
    }

    private String getTileHeader(String tileDynamicLocator){
        return getTextByLocator(tileHeader(tileDynamicLocator));
    }

    private String getTileBuyNowBtnListDetails(String tileDynamicLocator){
        return listToStringConv(getBtnClickAndNewTabLocator(tileBuyNowBtn(tileDynamicLocator)));
    }

    private String getTileLearnMoreBtnListDetails(String tileDynamicLocator){
        return listToStringConv(getBtnClickAndNewTabLocator(tileLearnMoreBtn(tileDynamicLocator)));
    }

    public List<String> getTileHeaderOrder(){
        List <String> headerListLoad=new ArrayList<>();
        for(WebElement elemVar: driver.findElements(tileHeader)){
            headerListLoad.add(elemVar.getText());
        }
        return headerListLoad;
    }
    private String getTileGiftNowBtnListDetails(String tileDynamicLocator)  {
        return listToStringConv(getBtnClickAndNewTabLocator(tileGiftNowBtn(tileDynamicLocator)));

    }

    private HashMap<String,String> getTileValuesTemp(String tileDynamicLocator, String ind)  {
        HashMap<String,String> mapValue=new HashMap<>();
        mapValue.put("Tile Year and Issue Number",strPreLoadConvertor(getTileYearIssueNumber(tileDynamicLocator)));
        mapValue.put("Tile Header",strPreLoadConvertor(getTileHeader(tileDynamicLocator)));
        mapValue.put("Tile Stats",strPreLoadConvertor(getTileStatsList(tileDynamicLocator)));
        if (ind.equalsIgnoreCase("Gift now")){
            mapValue.put("Tile Primary button", strPreLoadConvertor(getTileGiftNowBtnListDetails(tileDynamicLocator)));
        }
        else if(!(ind.equalsIgnoreCase("No Buy now"))){
            mapValue.put("Tile Primary button", strPreLoadConvertor(getTileBuyNowBtnListDetails(tileDynamicLocator)));
        }else{
            mapValue.put("Tile Primary button", "No Buy Now");
        }
        mapValue.put("Tile Secondary button",strPreLoadConvertor(getTileLearnMoreBtnListDetails(tileDynamicLocator)));
        return mapValue;
    }

    private String strPreLoadConvertor(String value){
        String tempLoad=null;
        if (value.isEmpty()){
            tempLoad="Not Present";
        }
        else{
            tempLoad=value;
        }
        return tempLoad;
    }

    private String listToStringConv(List <String> valueList){
        String dataLoad = "initialLoad";
        if(valueList.size()>1){
            for (String dataTemp : valueList) {
                dataLoad = String.format("%s;%s", dataLoad, dataTemp);
            }
        }else{
            dataLoad=valueList.get(0);
        }
        return dataLoad.replaceAll("initialLoad;","");
    }

    public HashMap <String, HashMap<String,HashMap<String,String>>> getOuterMapLoadFinal()  {
        HashMap <String, HashMap<String,HashMap<String,String>>> outerMapLoad=new HashMap<>();
        for(int iteratorMaster=1;iteratorMaster<=driver.findElements(sectionHeadersListArr).size();iteratorMaster++){
            outerMapLoad.put((driver.findElements(sectionHeadersListArr).get(iteratorMaster-1).getText()),getInternalMap(iteratorMaster));
        }
        log.info("getOuterMapLoadFinal: "+outerMapLoad);
        return outerMapLoad;
    }
    private HashMap <String, HashMap<String,String>> getInternalMap(int i)  {
        HashMap<String, HashMap<String,String>> internalMapLoad=new HashMap<>();
        String dynamicValueInternal=null;
        for (int j=1;j<=driver.findElements(By.xpath(locatorRowSection(i))).size();j++){
            dynamicValueInternal=tileLocatorDynamic(i,j);
            if(i==1) {
                internalMapLoad.put(driver.findElement(tileHeader(dynamicValueInternal)).getText(), getTileValuesTemp(dynamicValueInternal, "Buy now"));
            }
            else if(i==2){
                if(j==2){
                    internalMapLoad.put(driver.findElement(tileHeader(dynamicValueInternal)).getText(),getTileValuesTemp(dynamicValueInternal, "Gift now"));
                }
                else{
                    internalMapLoad.put(driver.findElement(tileHeader(dynamicValueInternal)).getText(),getTileValuesTemp(dynamicValueInternal, "Buy now"));
                }
            } else {
                internalMapLoad.put(driver.findElement(tileHeader(dynamicValueInternal)).getText(),getTileValuesTemp(dynamicValueInternal, "No Buy now"));
            }
            dynamicValueInternal=null;
        }
        log.info("getInternalMap: "+internalMapLoad);
        return internalMapLoad;
    }

    public String getNeedALittleMoreHelpDecidingHdr(){
        return getTextByLocator(needLittleMoreHelpDecidingTxt);
    }

    public List <String> getVisitHelpAndSupportBtnListDetails(){
        return getBtnClickAndNewTabLocator(visitHelpAndSupportBtn);
    }

    public boolean clickVisitHelpAndSupportBtn(String buttonName, String nextPgHeader){
        webUtil.clickLogW(visitHelpAndSupportBtn,buttonName);
        return (getTextByLocator(hdrBannerTitleHelpSupport)).equalsIgnoreCase(nextPgHeader);
    }

    public boolean navigateBackPg(String titleOurProducts) {
        webUtil.navigateBackFunc();
        return (getTextByLocator(bannerTitle)).equalsIgnoreCase(titleOurProducts);
    }

}
