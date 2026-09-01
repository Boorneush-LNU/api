package com.cucumbercraft.Models;

import com.cucumbercraft.framework.Settings;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class FrameworkConstants {

    // ── Properties loaded from Global Settings.properties ──
    static Properties propertiesVar = Settings.getInstance();
    private static final String resourcesLocation = System.getProperty("user.dir") + File.separator + "src\\test\\resources";
    private static final String envNameRR         = (String) propertiesVar.get("NCRepayReinvestLink");
    private static final String envNameCelebrate  = (String) propertiesVar.get("NCCelebrateLink");
    private static final String envNamePB         = (String) propertiesVar.get("NCPBPaymentWinningLink");
    private static final String envNamesscn         = (String) propertiesVar.get("NCSSCN");
    private static final String testDataFolderName                          = (String) propertiesVar.get("TestDataFolderName");
    private static final String TestDataWorkbookPreLoginContent             = (String) propertiesVar.get("TestDataWorkbook.PreLoginContent");
    private static final String TestDataWorkbookAutomationRegression        = (String) propertiesVar.get("TestDataWorkbook.AutomationRegression");
    private static final String TestDataWorkbookContentSliderModalFooterArticles = (String) propertiesVar.get("TestDataWorkbook.ContentSliderModalArticlesFooter");
    private static final String TestDataTxtCredentialFile                   = (String) propertiesVar.get("TestDataTxt.CredentialFile");

    // ── All Excel-backed maps: ThreadLocal so each parallel test thread has its own
    //    copy and the data is released when clearThreadLocalCache() is called in @AfterTest ──
    private static final ThreadLocal<HashMap<String, HashMap<String, String>>> expMasterMapIndProdPgs        = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, HashMap<String, String>>> expMasterMapIntRatesReturns   = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, HashMap<String, String>>> expMasterMapSavingsIdeasPgs   = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, String>>                  expMapAboutUsPg               = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, String>>                  expMapHomePg                  = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, String>>                  expMapSignInPg                = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, String>>                  expMapBeginRegPg              = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, String>>                  expMapOurProdPg               = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, String>>                  expMapNewCommsPg              = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, String>>                  expMapPbResultsPg             = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, String>>                  expMapCompHdrFtrSvIdPg        = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, HashMap<String, String>>> expMasterMapCompCarouselPg    = new ThreadLocal<>();
    private static final ThreadLocal<List<String>>                             expFooterListCreatorPg        = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, String>>                  mapFooterExtractPg            = new ThreadLocal<>();
    private static final ThreadLocal<List<String>>                             expHeaderListCreatorPg        = new ThreadLocal<>();
    private static final ThreadLocal<List<String>>                             expHeaderListSecNavCreatorPg  = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, String>>                  mapTitleExtractPg             = new ThreadLocal<>();
    private static final ThreadLocal<List<String>>                             expSavingIdeasSectionListCreatorPg          = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, String>>                  mapSavingIdeasSectionTitleExtractPg         = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, HashMap<String, String>>> expMasterMapOurProdTileDetails              = new ThreadLocal<>();
    private static final ThreadLocal<String>                                   expCarouselValues             = new ThreadLocal<>();
    private static final ThreadLocal<String>                                   expCompleteCarouselTileDisplay = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, String>>                  expMapPrivacyPrefPg           = new ThreadLocal<>();

    // ── Helper: ensure the CompHdrFtrSvId map (and all maps derived from it) are loaded ──
    private static void loadCompHdrFtrSvIdGroup() {
        if (expMapCompHdrFtrSvIdPg.get() == null) {
            HashMap<String, String> base = expectedExtract.extractExpectedNonMultipleMap("Comp Header_Footer_Saving Ideas");
            expMapCompHdrFtrSvIdPg.set(base);
            expFooterListCreatorPg.set(expectedExtract.compHdrFtrSvIdLoadList(base, "expFooterList"));
            mapFooterExtractPg.set(expectedExtract.compHdrFtrSvIdLoadMap(base,  "mapFooterExtract"));
            expHeaderListCreatorPg.set(expectedExtract.compHdrFtrSvIdLoadList(base, "expHeaderList"));
            expHeaderListSecNavCreatorPg.set(expectedExtract.compHdrFtrSvIdLoadList(base, "expHeaderListSecNav"));
            mapTitleExtractPg.set(expectedExtract.compHdrFtrSvIdLoadMap(base,  "expMapTitleExtract"));
            expSavingIdeasSectionListCreatorPg.set(expectedExtract.compHdrFtrSvIdLoadList(base, "expSavingIdeasSectionList"));
            mapSavingIdeasSectionTitleExtractPg.set(expectedExtract.compHdrFtrSvIdLoadMap(base, "mapSavingIdeasSectionTitleExtract"));
        }
    }

    /**
     * Call this in @AfterTest (or @After) to release all Excel data held by the
     * current thread and allow GC to reclaim the memory during parallel runs.
     */
    public static void clearThreadLocalCache() {
        expMasterMapIndProdPgs.remove();
        expMasterMapIntRatesReturns.remove();
        expMasterMapSavingsIdeasPgs.remove();
        expMapAboutUsPg.remove();
        expMapHomePg.remove();
        expMapSignInPg.remove();
        expMapBeginRegPg.remove();
        expMapOurProdPg.remove();
        expMapNewCommsPg.remove();
        expMapPbResultsPg.remove();
        expMapCompHdrFtrSvIdPg.remove();
        expMasterMapCompCarouselPg.remove();
        expFooterListCreatorPg.remove();
        mapFooterExtractPg.remove();
        expHeaderListCreatorPg.remove();
        expHeaderListSecNavCreatorPg.remove();
        mapTitleExtractPg.remove();
        expSavingIdeasSectionListCreatorPg.remove();
        mapSavingIdeasSectionTitleExtractPg.remove();
        expMasterMapOurProdTileDetails.remove();
        expCarouselValues.remove();
        expCompleteCarouselTileDisplay.remove();
        expMapPrivacyPrefPg.remove();
    }

    // ── Environment-aware accessors (read ThreadLocal at call time) ──

    public static String getEnvName() {
        return Settings.getInstance().getProperty("Environment");
    }

    public static String getEnableDownloadInd() {
        return Settings.getInstance().getProperty("EnableDownloadInd");
    }

    public static String getEnvNameRR()        { return envNameRR; }
    public static String getEnvNameCelebrate() { return envNameCelebrate; }
    public static String getEnvNamePB()        { return envNamePB; }
    public static String getEnvNamesscn()        { return envNamesscn; }

    public static String envLinkFetch() {
        return (String) Settings.getInstance().get(String.format("%s_%s", "ApplicationUrl", getEnvName()));
    }

    public static String envLinkWorkflowFetch() {
        return (String) Settings.getInstance().get(String.format("%s_%s", "WorkflowURL", getEnvName()));
    }

    public static String baseUriTrustXHandlerAPILinkFetch() {
        return (String) Settings.getInstance().get(String.format("%s_%s", "TrustXHandlerBaseUri", getEnvName()));
    }
    public static String baseUriMyAccountFEAPILinkFetch() {
        return (String) Settings.getInstance().get(String.format("%s_%s", "MyAccountFEBaseUri", getEnvName()));
    }
    public static String subKeyMyAccountFE() {
        return (String) Settings.getInstance().get(String.format("%s_%s", "MyAccountFE_subKey", getEnvName()));
    }
    public static String subKeyTrustXHandler() {
        return (String) Settings.getInstance().get(String.format("%s_%s", "TrustXHandler_subKey", getEnvName()));
    }
    public static String apiKeyMyAccountFE() {
        return (String) Settings.getInstance().get(String.format("%s_%s", "MyAccountFE_apiKey", getEnvName()));
    }
    public static String envPBAdminLinkFetch() {
        return (String) Settings.getInstance().get(String.format("%s_%s", "ApplicationUrlPBAdmin", getEnvName()));
    }
    public static String envLinkFetchRepayReinvest() {
        return String.format("%s%s", envLinkFetch(), getEnvNameRR());
    }
    public static String envLinkFetchCelebrate() {
        return String.format("%s%s", envLinkFetch(), getEnvNameCelebrate());
    }
    public static String envLinkFetchPB() {
        return String.format("%s%s", envLinkFetch(), getEnvNamePB());
    }

    public static String envLinkFetchSSCN() {
        return String.format("%s%s", envLinkFetch(), getEnvNamesscn());
    }
    // ── Excel file path accessors (resolved at call time using current Environment) ──

    public static String getExcelLocationPreLoginContent() {
        String envValue = getEnvName();
        return resourcesLocation + File.separator + testDataFolderName
                + File.separator + envValue + File.separator + envValue + "_" + TestDataWorkbookPreLoginContent;
    }
    public static String getExcelLocationAutomationRegression() {
        String envValue = getEnvName();
        return resourcesLocation + File.separator + testDataFolderName
                + File.separator + envValue + File.separator + envValue + "_" + TestDataWorkbookAutomationRegression;
    }
    public static String getExcelLocationContentSliderModalFooterArticles() {
        return resourcesLocation + File.separator + testDataFolderName
                + File.separator + getEnvName() + File.separator + getEnvName() + "_" + TestDataWorkbookContentSliderModalFooterArticles;
    }
    public static String getTxtCredentialFileLocation() {
        return resourcesLocation + File.separator + testDataFolderName
                + File.separator + getEnvName() + File.separator + getEnvName() + "_" + TestDataTxtCredentialFile;
    }

    public static String getDataSetName(String dataSetKey) {
        return (String) propertiesVar.get(dataSetKey);
    }

    // ── Lazy-loaded ThreadLocal Excel-backed getters ──

    public static HashMap<String, HashMap<String, String>> getExpMasterMapIndProdPgs() {
        if (expMasterMapIndProdPgs.get() == null)
            expMasterMapIndProdPgs.set(expectedExtract.returnMasterMapValues("Individual Product Pages"));
        return expMasterMapIndProdPgs.get();
    }

    public static HashMap<String, HashMap<String, String>> getExpMasterMapIntRatesReturns() {
        if (expMasterMapIntRatesReturns.get() == null)
            expMasterMapIntRatesReturns.set(expectedExtract.returnMasterMapValues("Interest Rates and Returns"));
        return expMasterMapIntRatesReturns.get();
    }

    public static HashMap<String, HashMap<String, String>> getExpMasterMapSavingsIdeasPgs() {
        if (expMasterMapSavingsIdeasPgs.get() == null)
            expMasterMapSavingsIdeasPgs.set(expectedExtract.returnMasterMapValues("Savings Ideas Pages"));
        return expMasterMapSavingsIdeasPgs.get();
    }

    public static HashMap<String, String> getExpMapAboutUsPg() {
        if (expMapAboutUsPg.get() == null)
            expMapAboutUsPg.set(expectedExtract.extractExpectedNonMultipleMap("About Us Page"));
        return expMapAboutUsPg.get();
    }

    public static HashMap<String, String> getExpMapHomePg() {
        if (expMapHomePg.get() == null)
            expMapHomePg.set(expectedExtract.extractExpectedNonMultipleMap("Homepage"));
        return expMapHomePg.get();
    }

    public static HashMap<String, String> getExpMapSignInPg() {
        if (expMapSignInPg.get() == null)
            expMapSignInPg.set(expectedExtract.extractExpectedNonMultipleMap("Sign in Page"));
        return expMapSignInPg.get();
    }

    public static HashMap<String, String> getExpMapBeginRegPg() {
        if (expMapBeginRegPg.get() == null)
            expMapBeginRegPg.set(expectedExtract.extractExpectedNonMultipleMap("Begin Registration Page"));
        return expMapBeginRegPg.get();
    }

    public static HashMap<String, String> getExpMapOurProdPg() {
        if (expMapOurProdPg.get() == null)
            expMapOurProdPg.set(expectedExtract.extractExpectedNonMultipleMap("Our Products Page"));
        return expMapOurProdPg.get();
    }

    public static HashMap<String, String> getExpMapNewCommsPg() {
        if (expMapNewCommsPg.get() == null)
            expMapNewCommsPg.set(expectedExtract.extractExpectedNonMultipleMap("NewComms"));
        return expMapNewCommsPg.get();
    }

    public static HashMap<String, String> getExpMapPbResultPg() {
        if (expMapPbResultsPg.get() == null)
            expMapPbResultsPg.set(expectedExtract.extractExpectedNonMultipleMap("PBResults"));
        return expMapPbResultsPg.get();
    }

    public static HashMap<String, HashMap<String, String>> expMasterMapCompCarousel() {
        if (expMasterMapCompCarouselPg.get() == null)
            expMasterMapCompCarouselPg.set(expectedExtract.returnMasterMapValues("Comp Carousel"));
        return expMasterMapCompCarouselPg.get();
    }

    public static HashMap<String, HashMap<String, String>> getExpMasterMapOurProdTileDetails() {
        if (expMasterMapOurProdTileDetails.get() == null)
            expMasterMapOurProdTileDetails.set(expectedExtract.returnMasterMapValues("Our Products-Tile Details"));
        return expMasterMapOurProdTileDetails.get();
    }

    public static String getExpCarouselOrderBaseRef() {
        if (expCarouselValues.get() == null)
            expCarouselValues.set(getExpMapHomePg().get("expCarouselTileOrder"));
        return expCarouselValues.get();
    }

    public static String getExpCompleteCarouselTileDisplay() {
        if (expCompleteCarouselTileDisplay.get() == null)
            expCompleteCarouselTileDisplay.set(getExpMapHomePg().get("expCompleteCarouselTileDisplay"));
        return expCompleteCarouselTileDisplay.get();
    }

    // ── CompHdrFtrSvId group — all loaded together since they share the same source map ──

    public static HashMap<String, String> expMapCompHdrFtrSvId() {
        loadCompHdrFtrSvIdGroup();
        return expMapCompHdrFtrSvIdPg.get();
    }

    public static List<String> expFooterListCreator() {
        loadCompHdrFtrSvIdGroup();
        return expFooterListCreatorPg.get();
    }

    public static HashMap<String, String> mapFooterExtract() {
        loadCompHdrFtrSvIdGroup();
        return mapFooterExtractPg.get();
    }

    public static List<String> expHeaderListCreator() {
        loadCompHdrFtrSvIdGroup();
        return expHeaderListCreatorPg.get();
    }

    public static List<String> expHeaderListSecNavCreator() {
        loadCompHdrFtrSvIdGroup();
        return expHeaderListSecNavCreatorPg.get();
    }

    public static HashMap<String, String> mapTitleExtract() {
        loadCompHdrFtrSvIdGroup();
        return mapTitleExtractPg.get();
    }

    public static List<String> expSavingIdeasSectionListCreator() {
        loadCompHdrFtrSvIdGroup();
        return expSavingIdeasSectionListCreatorPg.get();
    }

    public static HashMap<String, String> mapSavingIdeasSectionTitleExtract() {
        loadCompHdrFtrSvIdGroup();
        return mapSavingIdeasSectionTitleExtractPg.get();
    }

    public static HashMap<String, String> getExpMapPrivacyPrefPg() {
        if (expMapPrivacyPrefPg.get() == null)
            expMapPrivacyPrefPg.set(expectedExtract.extractExpectedNonMultipleMap("Privacy Preference Section"));
        return expMapPrivacyPrefPg.get();
    }
}
