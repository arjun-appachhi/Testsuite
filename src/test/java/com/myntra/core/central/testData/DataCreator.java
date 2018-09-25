package com.myntra.core.central.testData;

import com.google.gson.JsonParser;
import com.myntra.core.exceptions.DataCreationException;
import com.myntra.testdata.TestDataHelper;
import com.myntra.testdata.models.pojo.response.Address;
import com.myntra.testdata.models.pojo.response.Coupon;
import com.myntra.testdata.models.pojo.response.MyntData;
import com.myntra.testdata.models.pojo.response.User;
import com.myntra.utils.logger.ILogger;
import io.qameta.allure.Step;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class DataCreator implements ILogger {

    private TestDataHelper testDataHelper;

    public DataCreator() {
        LOG.info("DataCreator constructor");
        LOG.info("Instantiating TestDataHelper");
        testDataHelper = new TestDataHelper();
    }

    @Step
    public synchronized Address createNewAddressForUser(User userData, String pincode, String addressType) {
        String userName = userData.getEmail();
        String password = userData.getPassword();
        LOG.info(String.format("Add address for %s::%s, having pincode:%s, addressType:%s", userName, password, pincode, addressType));
        Address newAddress = null;

        try {
            newAddress = testDataHelper.addAddressToUser(userName, password, pincode, userName, true, addressType.toUpperCase())
                                       .getAddress();
        } catch (Exception e) {
            throw new DataCreationException(
                    String.format("Error in adding address for user %s::%s, with pincode:%s and addressType:%s, \nError: %s", userName, password,
                            pincode, addressType, ExceptionUtils.getStackTrace(e)));
        }
        return newAddress;
    }

    @Step
    public synchronized User createNewUser(String testName, String userName, String password) throws DataCreationException {
        LOG.info(String.format("Test: %s - Create user with username - %s and password - %s", testName, userName, password));
        User newUser = null;
        String errorMessage = String.format("Error in creating user %s::%s", userName, password);
        try {
            MyntData myntData = testDataHelper.createNewUser(userName, password);
            newUser = myntData.getUser();
            LOG.info(String.format("New user created - %s::%s", newUser.getEmail(), newUser.getPassword()));
            String statusmessage = myntData.getStatus()
                                           .getStatusmessage();
            int statusCode = myntData.getStatus()
                                     .getStatusCode();
            throwExceptionIfStatusCodeIsNot200(errorMessage, statusmessage, statusCode);
            newUser.setAdditionalProperty("NewUserCreated", true);
        } catch (Exception e) {
            throw new DataCreationException(
                    String.format("Error in creating user %s::%s, \nError: %s", userName, password, ExceptionUtils.getStackTrace(e)));
        }
        return newUser;
    }

    @Step
    public Coupon createNewCoupon(String userName, String password) {
        LOG.info(String.format("Create Personalised Coupon for user %s::%s", userName, password));
        MyntData myntData = null;
        String errorMessage = String.format("Error in creating Personalised Coupon for %s::%s", userName, password);
        Coupon newCoupon = null;
        try {
            myntData = testDataHelper.addCouponToUser(userName, password);
            newCoupon = myntData.getCoupon();
            String statusmessage = myntData.getStatus()
                                           .getStatusmessage();
            int statusCode = myntData.getStatus()
                                     .getStatusCode();
            LOG.info(String.format("Personalised coupon created for user - %s::%s - %s", userName, password, newCoupon.getCouponCode()));
            throwExceptionIfStatusCodeIsNot200(errorMessage, statusmessage, statusCode);
            newCoupon.setAdditionalProperty("PersonalisedCouponCreated", true);
        } catch (Exception e) {
            throw new DataCreationException(String.format("Error in creating Personalised Coupon for %s::%s, \nError: %s", userName, password,
                    ExceptionUtils.getStackTrace(e)));
        }
        return newCoupon;
    }

    @Step
    public void addStylesToCouponGroup(List<String> styleIds) {
        LOG.info(String.format("Adding StyleIDs loaded from Test Data file to Coupon group - %s", styleIds.toString()));
        MyntData myntData = null;
        String errorMessage = String.format("Error in adding StyleIDs to Coupon group - %s", styleIds.toString());
        try {
            myntData = testDataHelper.addStyleToCouponGroup(styleIds);
            String statusmessage = myntData.getStatus()
                                           .getStatusmessage();
            int statusCode = myntData.getStatus()
                                     .getStatusCode();
            LOG.info(String.format("Added StyleIDs loaded from Test Data file to Coupon group - %d::%s", statusCode, statusmessage));

            throwExceptionIfStatusCodeIsNot200(errorMessage, statusmessage, statusCode);
        } catch (Exception e) {
            throw new DataCreationException(String.format("%s\n%s", errorMessage, ExceptionUtils.getStackTrace(e)));
        }
    }

    private void throwExceptionIfStatusCodeIsNot200(String errorMessage, String statusmessage, int statusCode) {
        if (statusCode != 200) {
            throw new DataCreationException(String.format("%s + \n%d::%s", errorMessage, statusCode, statusmessage));
        }
    }

    @Step
    public String createMyntCouponForUser(User user) {
        LOG.info(String.format("Enable MyntCoupon for user - %s::%s with UIDX: %s", user.getEmail(), user.getPassword(), user.getUidx()));
        String errorMessage = String.format("Error in enabling MyntCoupon for user - %s::%s with UIDX: %s", user.getEmail(), user.getPassword(),
                user.getUidx());
        String myntCode = "";
        String[] args = {"curl", "-vis", "-H", "Content-Type: application/json", "-H", "Accept: application/json", "-H", "Cache-Control: no-cache", "-X", "POST", "http://myntservice.stage.myntra.com/mynts/v1/createMynts", "-d",
                " { \"uidx\": \"" + user.getUidx() +
                        "\", \"percentageDiscount\" : \"10\", \"absoluteDiscount\" : \"null\", \"expiryDate\": \"1565233866007\", \"isBlocked\" : \"false\", \"myntType\" : \"PERCENTAGE\" } "};

        try {
            myntCode = new JsonParser().parse(executeCurlCommand(args))
                                       .getAsJsonObject()
                                       .get("data")
                                       .getAsJsonObject()
                                       .get("myntCode")
                                       .getAsString();

            LOG.info(String.format("Got new MyntCoupon - %s - for user - %s::%s with UIDX: %s", myntCode, user.getEmail(), user.getPassword(),
                    user.getUidx()));
        } catch (Exception e) {
            throw new DataCreationException(String.format("%s\n%s", errorMessage, ExceptionUtils.getStackTrace(e)));
        }
        return myntCode;
    }

    private String executeCurlCommand(String[] curlArgs) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(curlArgs);
        processBuilder.redirectErrorStream(true);
        Process process;
        BufferedReader reader = null;
        int errCode = 0;
        String responseDetails = "";
        try {
            LOG.info(String.format("Executing curl command - \n%s", Arrays.toString(curlArgs)));
            process = processBuilder.start();
            errCode = process.waitFor();
            LOG.info(String.format("Curl command exited, with '%s' errors", (errCode == 0) ? "NO" : "YES"));

            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            StringBuilder curlResponse = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                curlResponse.append(line + System.getProperty("line.separator"));
                if (line.startsWith("{") && line.endsWith("}")) {
                    responseDetails = line;
                }
            }
            LOG.info(String.format("Result - \n%s", curlResponse.toString()));
        } catch (IOException e) {
            LOG.info("error");
            e.printStackTrace();
            throw e;
        } finally {
            reader.close();
        }
        if (errCode != 0) {
            throw new DataCreationException("Unable to execute Curl command successdully");
        }
        return responseDetails;
    }

    @Step
    public void addLoyaltyPointsForUser(User user, String loyaltyPoint) {
        LOG.info(String.format("Add Loyalty Points (%s) for user - %s::%s with UIDX: %s", loyaltyPoint, user.getEmail(), user.getPassword(),
                user.getUidx()));
        String errorMessage = String.format("Error in adding Loyalty Point (%s) for user - %s::%s with UIDX: %s", loyaltyPoint, user.getEmail(),
                user.getPassword(), user.getUidx());

        String[] args = {"curl", "-vis", "-H", "Content-Type: application/json", "-H", "Accept: application/json", "-H", "Cache-Control: no-cache", "-X", "POST", "http://loyalty.stage.myntra.com/lp/loyalty/loyaltyPoints/credit", "-d",
                " { \"login\": \"" + user.getUidx() + "\", \"activeCreditPoints\" : \"" + loyaltyPoint +
                        "\", \"inActiveCreditPoints\" : \"2\", \"activeDebitPoints\": \"0\", \"inActiveDebitPoints\" : \"0\", \"description\" : \"test test\", \"itemType\":\"ORDER\", \"itemId\":123, \"businessProcess\":\"GOOD_WILL\" } "};

        try {
            executeCurlCommand(args);
            LOG.info(String.format("Added Loyalty Point (%s) for user - %s::%s with UIDX: %s", loyaltyPoint, user.getEmail(), user.getPassword(),
                    user.getUidx()));
        } catch (Exception e) {
            throw new DataCreationException(String.format("%s\n%s", errorMessage, ExceptionUtils.getStackTrace(e)));
        }
    }
}
