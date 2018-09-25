package com.myntra.core.business;

import com.myntra.core.pages.HomePage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;

public class Search extends BusinessFlow {

    public static Search getInstance() {
        return (Search) getInstance(Of.SEARCH);
    }

    @Step
    public Product searchProductUsingStyleID() {
        boolean anyResultsFound = HomePage.createInstance()
                                          .searchProductUsingStyleID()
                                          .isProductDisplayed();
        Assert.assertTrue(anyResultsFound, "Product NOT found with provided search criteria");
        return Product.getInstance();
    }

    @Step
    public Product searchProductUsingName() {
        boolean isProductListDisplayed = HomePage.createInstance()
                                                 .searchProductUsingName()
                                                 .isProductListDisplayed();
        Assert.assertTrue(isProductListDisplayed, "Product NOT found with provided search criteria");
        return Product.getInstance();
    }

    @Step
    public Product searchProductUsingHovering() {
        boolean isProductListDisplayed = HomePage.createInstance()
                                                 .searchProductUsingHovering()
                                                 .isProductListDisplayed();
        Assert.assertTrue(isProductListDisplayed, "Product NOT found with provided search criteria");
        return Product.getInstance();
    }

    @Step
    public Product searchProductUsingBanner() {
        boolean isProductListDisplayed = HomePage.createInstance()
                                                 .searchProductUsingBanner()
                                                 .isProductListDisplayed();
        Assert.assertTrue(isProductListDisplayed, "Product NOT found with provided search criteria");
        return Product.getInstance();
    }

    @Step
    public Product searchAnotherProductUsingStyleID() {
        boolean anyResultsFound = HomePage.createInstance()
                .searchAnotherProductUsingStyleID()
                .isProductDisplayed();
        Assert.assertTrue(anyResultsFound, "Product NOT found with provided search criteria");
        return Product.getInstance();
    }
}
