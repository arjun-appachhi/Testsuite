package com.myntra.core.pages;

import com.myntra.core.pages.Desktop.DesktopSavedCardsPage;
import com.myntra.core.pages.MobileWeb.MobileWebSavedCardsPage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidSavedCardsPage;
import com.myntra.core.pages.NativeIOS.NativeIOSSavedCardsPage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Calendar;
import java.util.List;

public class SavedCardsPage extends Page {

    public static SavedCardsPage createInstance() {
        SavedCardsPage derivedSavedCardsPage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedSavedCardsPage = (SavedCardsPage) DynamicEnhancer.create(NativeAndroidSavedCardsPage.class, new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedSavedCardsPage = (SavedCardsPage) DynamicEnhancer.create(NativeIOSSavedCardsPage.class, new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedSavedCardsPage = (SavedCardsPage) DynamicEnhancer.create(DesktopSavedCardsPage.class, new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedSavedCardsPage = (SavedCardsPage) DynamicEnhancer.create(MobileWebSavedCardsPage.class, new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedSavedCardsPage;
    }

    @Override
    public String pageName() {
        return SavedCardsPage.class.getSimpleName();
    }

    private String monthSelected = "";

    @Step
    public SavedCardsPage addNewCardInSavedCards() {
        addNewCardNameAndNumber();
        selectExpiryMonth();
        selectExpiryYear();
        utils.click(getLocator("btnSave"), true);
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("btnSave")));
        return this;
    }

    @Step
    public boolean isAddNewCardSuccessful() {
        boolean isNewCardAdded = false;
        List<WebElement> allSavedCards = utils.findElements(getLocator("txaSavedCardNumbers"));
        for (WebElement savedCard : allSavedCards) {
            isNewCardAdded = savedCard.getText()
                                      .substring(16, 19)
                                      .equalsIgnoreCase(getTestData().getAdditionalProperties()
                                                                     .get("CreditCardNumber")
                                                                     .toString()
                                                                     .substring(13, 16));
        }
        return isNewCardAdded;
    }

    @Step
    public SavedCardsPage removeAllCardsInSavedCardsPage() {
        int cardCount = 0;
        int maxCardCount = 10;
        if (!(isSavedCardsAvailable())) {
            addNewCardInSavedCards();
        }
        while (isSavedCardsAvailable() && (cardCount < maxCardCount)) {
            WebElement removeItem = utils.findElement(getLocator("btnRemove"));
            utils.click(getLocator("btnRemove"));
            utils.click(getLocator("btnDelete"));
            cardCount++;
        }
        return this;
    }

    @Step
    public boolean isRemoveAllCardsSuccessful() {
        return (!(isSavedCardsAvailable()));
    }

    @Step
    public boolean isSavedCardsAvailable() {
        return (!(utils.isElementPresent(getLocator("txaNoSavedCardMessage"), 2)));
    }

    @Step
    private SavedCardsPage selectExpiryMonth() {
        utils.click(getLocator("ddlExpiryMonth"), true);
        WebElement firstAvailableMonth = utils.findElement(getLocator("lstMonth"));
        firstAvailableMonth.click();
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("lstMonth")));
        return this;
    }

    @Step
    private SavedCardsPage selectExpiryYear() {
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("lstMonth")));
        utils.click(getLocator("ddlExpiryYear"), true);
        List<WebElement> yearsAvailable = utils.findElements(getLocator("lstYear"));
        yearsAvailable.get(2).click();
        return this;
    }

    @Step
    public SavedCardsPage removeDuplicateCard() {
        List<WebElement> allSavedCards = utils.findElements(getLocator("txaSavedCardNumbers"));
        for (WebElement savedCard : allSavedCards) {
            if (savedCard.getText()
                         .substring(16, 19)
                         .equalsIgnoreCase(getTestData().getAdditionalProperties()
                                                        .get("CreditCardNumber")
                                                        .toString()
                                                        .substring(13, 16))) {
                utils.click(getLocator("btnRemove"), true);
                utils.click(getLocator("btnDelete"), true);
                break;
            }
        }
        return this;
    }

    @Step
    private SavedCardsPage addNewCardNameAndNumber() {
        if (isSavedCardsAvailable()) {
            removeDuplicateCard();
            if (utils.isElementPresent(getLocator("btnAddCardIfSavedCardsAvailable"), 2)) {
                utils.click(getLocator("btnAddCardIfSavedCardsAvailable"));
            } else {
                utils.click(getLocator("btnAddCardForNoSavedCard"));
            }
        } else {
            utils.click(getLocator("btnAddCardForNoSavedCard"), true);
        }
        utils.sendKeys(getLocator("txtCardNumber"), (String) getTestData().getAdditionalProperties()
                                                                          .get("CreditCardNumber"));
        utils.sendKeys(getLocator("txtCardName"), (String) getTestData().getAdditionalProperties()
                                                                        .get("NameOnCreditCard"));
        return this;
    }

    @Step
    public SavedCardsPage editSavedCard() {
        List<WebElement> allSavedCards = utils.findElements(getLocator("txaSavedCardNumbers"));
        for (WebElement savedCard : allSavedCards) {
            if (savedCard.getText()
                         .substring(16, 19)
                         .equalsIgnoreCase(getTestData().getAdditionalProperties()
                                                        .get("CreditCardNumber")
                                                        .toString()
                                                        .substring(13, 16))) {
                utils.click(getLocator("btnEdit"));
                editExpiryMonth();
                utils.click(getLocator("btnSave"));
                utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("btnSave")));
            }
        }
        return this;
    }

    @Step
    public boolean isEditSavedCardSuccessful() {
        boolean isSavedCardEdited = false;
        List<WebElement> allSavedCards = utils.findElements(getLocator("txaSavedCardNumbers"));
        for (WebElement savedCard : allSavedCards) {
            if (savedCard.getText()
                         .substring(16, 19)
                         .equalsIgnoreCase(getTestData().getAdditionalProperties()
                                                        .get("CreditCardNumber")
                                                        .toString()
                                                        .substring(13, 16))) {
                if (utils.findElement(getLocator("txaCardValidity"))
                         .getText()
                         .substring(0, 2)
                         .equalsIgnoreCase(monthSelected)) {
                    isSavedCardEdited = true;
                }
            }
        }
        return isSavedCardEdited;
    }

    @Step
    private SavedCardsPage editExpiryMonth() {
        utils.click(getLocator("ddlExpiryMonth"));
        List<WebElement> monthsAvailable = utils.findElements(getLocator("lstMonth"));
        for (WebElement month : monthsAvailable) {
            if (month.getText()
                     .substring(0, 2)
                     .equals(String.format("%02d", ((Calendar.getInstance()
                                                             .get(Calendar.MONTH) + 2) % 12)))) {
                monthSelected = (String.format("%02d", (Calendar.getInstance()
                                                                .get(Calendar.MONTH) + 2) % 12));
                month.click();
                break;
            }
        }
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("lstMonth")));
        return this;
    }

}
