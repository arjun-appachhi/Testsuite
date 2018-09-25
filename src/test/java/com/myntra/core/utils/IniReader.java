package com.myntra.core.utils;

import com.myntra.core.exceptions.InvalidTestDataException;
import com.myntra.testdata.models.pojo.response.Address;
import com.myntra.testdata.models.pojo.response.Coupon;
import com.myntra.testdata.models.pojo.response.MyntData;
import com.myntra.testdata.models.pojo.response.User;
import com.myntra.testdata.models.pojo.response.userobjects.PhoneDetail;
import com.myntra.utils.logger.ILogger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IniReader implements ILogger {
    private HashMap<String, HashMap<String, String>> data = new HashMap<>();
    private HashMap<String, Integer> keysInDataFile = new HashMap<>();
    private ArrayList<String> styleIds = new ArrayList<>();

    public IniReader(String commonTestDataFilePath, String testDataFilePath) {
        try {
            Ini commonIniFile = new Ini(new File(commonTestDataFilePath));
            readTestDataFile(commonIniFile);
            LOG.info(String.format("Loaded test data file - %s", commonTestDataFilePath));
        } catch (IOException e) {
            throw new InvalidTestDataException(
                    String.format("Error in loading test data from INI file - %s\n%s", commonTestDataFilePath, ExceptionUtils.getStackTrace(e)));
        }

        try {
            Ini testDataIniFile = new Ini(new File(testDataFilePath));
            readTestDataFile(testDataIniFile);
            LOG.info(String.format("Loaded test data file - %s", testDataFilePath));
        } catch (IOException e) {
            throw new InvalidTestDataException(
                    String.format("Error in loading test data from INI file - %s\n%s", testDataFilePath, ExceptionUtils.getStackTrace(e)));
        }
        LOG.info(String.format("Test data - keys found in file - \n%s", keysInDataFile));
    }

    public HashMap<String, Integer> getKeysInDataFile() {
        return keysInDataFile;
    }

    public ArrayList<String> getStyleIds() {
        return styleIds;
    }

    private void readTestDataFile(Ini iniFile) {
        for (String name : iniFile.keySet()) {
            Section section = iniFile.get(name);
            HashMap<String, String> sectionData = new HashMap<>();
            if (null != data.get(name.toLowerCase())) {
                sectionData.putAll(data.get(name.toLowerCase()));
            } else if (null != data.get("commontestdata")) {
                sectionData.putAll(data.get("commontestdata"));
            }
            readTestDataSection(name, section, sectionData);
        }
    }

    private void readTestDataSection(String name, Section section, HashMap<String, String> sectionData) {
        for (String key : section.keySet()) {
            sectionData.put(key, section.get(key));
            Integer keyCount = keysInDataFile.get(key);
            keysInDataFile.put(key, (keyCount == null) ? 0 : keyCount + 1);
        }
        data.put(name.toLowerCase(), sectionData);
    }

    public Map<String, String> getTestDataFor(String testName) {
        return data.get(testName.toLowerCase());
    }

    public HashMap<String, MyntData> convert() {
        HashMap<String, MyntData> loadedAndConvertedTestData = new HashMap<>();

        for (String eachTestData : data.keySet()) {
            LOG.info(String.format("Loading data for test %s", eachTestData));
            MyntData myntData = new MyntData();
            User loadedUser = new User();
            Coupon loadedCoupon = new Coupon();
            Address loadedAddress = new Address();

            Map<String, String> loadedTestData = data.get(eachTestData);
            loadedTestData.forEach((k, v) -> {
                switch (k.toLowerCase()) {

                    // User
                    case "username":
                        loadedUser.setEmail(v);
                        break;
                    case "password":
                        loadedUser.setPassword(v);
                        break;
                    case "mobilenumber":
                        PhoneDetail phoneDetail = new PhoneDetail();
                        phoneDetail.setPhone(v);
                        List phoneDetails = new ArrayList();
                        phoneDetails.add(phoneDetail);
                        loadedUser.setPhoneDetails(phoneDetails);
                        break;

                    // Coupon
                    case "personalisedcoupons":
                        loadedCoupon.setCouponCode(v);
                        break;
                    case "anotherpersonalisedcoupon":
                        loadedCoupon.setAdditionalProperty(k, v);
                        break;
                    case "invalidpersonalisedcoupons":
                        loadedCoupon.setAdditionalProperty(k, v);
                        break;
                    case "myntcoupon":
                        loadedCoupon.setAdditionalProperty(k, v);
                        break;
                    case "loyaltypoint":
                        loadedCoupon.setAdditionalProperty(k, v);
                        break;

                    // Address
                    case "rdotype":
                        loadedAddress.setAddressType(v);
                        break;
                    case "name":
                        loadedAddress.setName(v);
                        break;
                    case "address":
                        loadedAddress.setAddress(v);
                        break;
                    case "pincode":
                        loadedAddress.setPincode(v);
                        break;
                    case "locality":
                        loadedAddress.setLocality(v);
                        break;
                    case "editedname":
                        loadedAddress.setAdditionalProperty(k, v);
                        break;


                    // additional data from ini files
                    case "category":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    case "productcode":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    case "searchitem":
                        myntData.setAdditionalProperty(k, v);
                        if ((NumberUtils.isDigits(v)) && (!styleIds.contains(v))) {
                            styleIds.add(v);
                        }
                        break;
                    case "anothersearchitem":
                        myntData.setAdditionalProperty(k, v);
                        if ((NumberUtils.isDigits(v)) && (!styleIds.contains(v))) {
                            styleIds.add(v);
                        }
                        break;
                    case "sortrequired":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    case "filterone":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    case "gender":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    case "creditcardnumber":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    case "nameoncreditcard":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    case "cvv":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    case "sendername":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    case "giftmessage":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    case "recipientname":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    case "expirymonth":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    case "expiryyear":
                        myntData.setAdditionalProperty(k, v);
                        break;

//                        User Details
                    case "firstname":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    case "lastname":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    case "yourbio":
                        myntData.setAdditionalProperty(k, v);
                        break;
                    default:
                        throw new InvalidTestDataException(String.format("Invalid key - '%s' with value - '%s' provided", k, v));
                }
            });

            myntData.setUser(loadedUser);
            myntData.setAddress(loadedAddress);
            myntData.setCoupon(loadedCoupon);

            loadedAndConvertedTestData.put(eachTestData, myntData);
        }

        return loadedAndConvertedTestData;
    }
}
