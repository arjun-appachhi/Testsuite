## Pre-Requisites

### Running tests on Android Phones

### Adding some things here for testing Github

#### Setting up Android Debug Bridge (ADB) tools


- Download Android SDK from [here](https://dl.google.com/android/android-sdk_r24.4.1-macosx.zip) for Mac

- Extract the archive
From terminal, run the following command

   ```
     $ cd <extracted-folder>
     $ tools/android
   ```

- Click on - Android SDK Tools, Android SDK Platform-tools, Android Build-tool → Install Packages


- Create soft link (to maintain unanimity)

```$ sudo ln -s <extracted-folder> /opt/android-sdk```


#### Setting up Appium


Run the following commands on terminal

```
$ xcode-select --install

$ /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

$ brew update && brew upgrade

$ brew install node

$ npm -g install appium

$ npm -g install wd
```        

##### Create soft link (optional: to maintain unanimity)

```
$ sudo ln -s /usr/local/bin /opt/homebrew

$ sudo ln -s `/usr/libexec/java_home` /opt/java
```

#### Running Tests on iOS Phones

Run the following commands on terminal. <br/>
    
    $ brew install libimobiledevice
    
    $ brew install ios-deploy
    
    $ brew install carthage 
    
 Rest all configurations just as same as android, except for running native iOS tests,
 **```-Dchannel=ios```**
 
### Versions of my local installation
 
* xcode - Version 9.4.1 (9F2000)
* node - 10.4.1
* appium - 1.8.1
* npm - 6.1.0

## Building the project

For eclipse

```shell
$ ./gradlew clean build compileTestJava --refresh-dependencies cleanEclipse eclipse -x test
```

For Idea (or IntelliJ)

```shell
$ ./gradlew clean build compileTestJava  --refresh-dependencies cleanIdea idea -x test
```

## Running test
```shell
$ ./gradlew test -DsuiteXmlFile=src/test/resources/test-suites/testng.xml -Dchannel=mobile 
```

#### other overridable configuration parameters
```
channel=mobile
browser=chrome
device=iphonex
use.emulation=true

java.home=<path>
android.sdk.home=<path>
homebrew.home=<path>

```

Refer **`src/test/resources/config.properties`** or **`default.properties`** that is bundled in the framework.


```
$ ./gradlew test -DsuiteXmlFile=src/test/resources/test-suites/testng.xml -Dchannel=desktop -Dbrowser=chrome
$ ./gradlew test -DsuiteXmlFile=src/test/resources/test-suites/testng.xml -Dchannel=mobile -Ddevice=iphonex
$ ./gradlew test -DsuiteXmlFile=src/test/resources/test-suites/testng.xml -Dchannel=mobile -Duse.emulation=false -DmwebExecuteOn=android
$ ./gradlew test -DsuiteXmlFile=src/test/resources/test-suites/testng.xml -Dchannel=mobile -Duse.emulation=false -DmwebExecuteOn=iphone
$ ./gradlew test -DsuiteXmlFile=src/test/resources/test-suites/testng.xml -Dchannel=android
$ ./gradlew test -DsuiteXmlFile=src/test/resources/test-suites/testng.xml -Dchannel=ios
```


## Opening Allure Report after test execution

```shell
$ ./gradlew openAllureReport
```
The above command works for Mac and Linux.  
  

## Test Implementation Documentation
  
### Folder Structure
![Folder Structure](doc/FolderStructure.jpg)

### Framework Layers
![Framework Layers](doc/FrameworkLayers.jpg)

### Page Object Structure
![Page Objects Structure](doc/PageObjectsStructure.jpg)

### How to write a new Test?
![How to write a new test?](doc/WritingANewTest.jpg)

## FAQs

### Generating and opening Allure reports for tests run from IDE

* To generate the report:

    ``` $ .allure/allure-2.6.0/bin/allure generate allure-results ```

* To see the report:

    ```$ .allure/allure-2.6.0/bin/allure open allure-report```

* To generate and see the report:

    ```$ .allure/allure-2.6.0/bin/allure serve allure-results```
   
### Supported devices for mobile emulation on chrome

Use **`alias`** name from the below list when providing **`-Ddevice=<name>`**
To have custom set of devices list, create a file **`src/test/resources/devices.json`** and create content like below. Device properties were picked up from [here](https://cs.chromium.org/chromium/src/chrome/test/chromedriver/chrome/mobile_device_list.cc)

```json
{
    "devices": [
        {
            "name": "BlackBerry Z30",
            "alias": "blackberry",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 640,
                "width": 360,
                "touch": true,
                "userAgent": "Mozilla/5.0 (BB10; Touch) AppleWebKit/537.10+ (KHTML, like Gecko) Version/10.0.9.2372 Mobile Safari/537.10+"
            }
        },
        {
            "name": "Google Nexus 6",
            "alias": "nexus6",
            "metrics": {
                "deviceScaleFactor": 3.5,
                "mobile": true,
                "height": 732,
                "width": 412,
                "touch": true,
                "userAgent": "Mozilla/5.0 (Linux; Android 6.0.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.20 Mobile Safari/537.36"
            }
        },
        {
            "name": "Google Nexus 7",
            "alias": "nexus7",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 960,
                "width": 600,
                "touch": true,
                "userAgent": "Mozilla/5.0 (Linux; Android 6.0.1;  Nexus 7 Build/JSS15Q) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.20 Safari/537.36"
            }
        },
        {
            "name": "Google Nexus 4",
            "alias": "nexus4",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 640,
                "width": 384,
                "touch": true,
                "userAgent": "Mozilla/5.0 (Linux; Android 6.0.1; Nexus 4 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.20 Mobile Safari/537.36"
            }
        },
        {
            "name": "Google Nexus 5",
            "alias": "nexus5",
            "metrics": {
                "deviceScaleFactor": 3,
                "mobile": true,
                "height": 640,
                "width": 360,
                "touch": true,
                "userAgent": "Mozilla/5.0 (Linux; Android 6.0.1; Nexus 5 Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.20 Mobile Safari/537.36"
            }
        },
        {
            "name": "Apple iPad",
            "alias": "ipad",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 1024,
                "width": 768,
                "touch": true,
                "userAgent": "Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53"
            }
        },
        {
            "name": "Apple iPad Mini",
            "alias": "ipadmini",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 1024,
                "width": 768,
                "touch": true,
                "userAgent": "Mozilla/5.0 (iPad; CPU OS 7_0_4 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11B554a Safari/9537.53"
            }
        },
        {
            "name": "Samsung Galaxy Note II",
            "alias": "galaxynote2",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 640,
                "width": 360,
                "touch": true,
                "userAgent": "Mozilla/5.0 (Linux; U; Android 6.0.1; en-us; GT-N7100 Build/JRO03C) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/44.0.2403.20 Version/4.0 Mobile Safari/534.30"
            }
        },
        {
            "name": "Nokia N9",
            "alias": "n9",
            "metrics": {
                "deviceScaleFactor": 1,
                "mobile": true,
                "height": 640,
                "width": 360,
                "touch": true,
                "userAgent": "Mozilla/5.0 (MeeGo; NokiaN9) AppleWebKit/534.13 (KHTML, like Gecko) NokiaBrowser/8.5.0 Mobile Safari/534.13"
            }
        },
        {
            "name": "Samsung Galaxy S4",
            "alias": "galaxys4",
            "metrics": {
                "deviceScaleFactor": 3,
                "mobile": true,
                "height": 640,
                "width": 360,
                "touch": true,
                "userAgent": "Mozilla/5.0 (Linux; Android 6.0.1; GT-I9505 Build/JDQ39) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.20 Mobile Safari/537.36"
            }
        },
        {
            "name": "Nokia Lumia 520",
            "alias": "lumia520",
            "metrics": {
                "deviceScaleFactor": 1.4,
                "mobile": true,
                "height": 533,
                "width": 320,
                "touch": true,
                "userAgent": "Mozilla/5.0 (compatible; MSIE 10.0; Windows Phone 8.0; Trident/6.0; IEMobile/10.0; ARM; Touch; NOKIA; Lumia 520)"
            }
        },
        {
            "name": "BlackBerry PlayBook",
            "alias": "playbook",
            "metrics": {
                "deviceScaleFactor": 1,
                "mobile": true,
                "height": 1024,
                "width": 600,
                "touch": true,
                "userAgent": "Mozilla/5.0 (PlayBook; U; RIM Tablet OS 2.1.0; en-US) AppleWebKit/536.2+ (KHTML like Gecko) Version/7.2.1.0 Safari/536.2+"
            }
        },
        {
            "name": "Apple iPhone 5",
            "alias": "iphone5",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 568,
                "width": 320,
                "touch": true,
                "userAgent": "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X; en-us) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53"
            }
        },
        {
            "name": "Apple iPhone 4",
            "alias": "iphone4",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 480,
                "width": 320,
                "touch": true,
                "userAgent": "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_2_1 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148 Safari/6533.18.5"
            }
        },
        {
            "name": "Apple iPhone 6",
            "alias": "iphone6",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 667,
                "width": 375,
                "touch": true,
                "userAgent": "Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4"
            }
        },
        {
            "nane": "LG Optimus L70",
            "alias": "optimusl70",
            "metrics": {
                "deviceScaleFactor": 1.25,
                "mobile": true,
                "height": 640,
                "width": 384,
                "touch": true,
                "userAgent": "Mozilla/5.0 (Linux; U; Android 6.0.1; en-us; LGMS323 Build/KOT49I.MS32310c) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/44.0.2403.20 Mobile Safari/537.36"
            }
        },
        {
            "name": "Apple iPhone 6 Plus",
            "alias": "iphone6plus",
            "metrics": {
                "deviceScaleFactor": 3,
                "mobile": true,
                "height": 736,
                "width": 414,
                "touch": true,
                "userAgent": "Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4"
            }
        },
        {
            "name": "Amazon Kindle Fire HDX",
            "alias": "kindlefire",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 2560,
                "width": 1600,
                "touch": true,
                "userAgent": "Mozilla/5.0 (Linux; U; en-us; KFAPWI Build/JDQ39) AppleWebKit/535.19 (KHTML, like Gecko) Silk/3.13 Safari/535.19 Silk-Accelerated=true"
            }
        },
        {
            "name": "Samsung Galaxy S III",
            "alias": "galaxys3",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 640,
                "width": 360,
                "touch": true,
                "userAgent": "Mozilla/5.0 (Linux; U; Android 6.0.1; en-us; GT-I9300 Build/IMM76D) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/44.0.2403.20 Version/4.0 Mobile Safari/534.30"
            }
        },
        {
            "name": "Samsung Galaxy Note 3",
            "alias": "galaxynote3",
            "metrics": {
                "deviceScaleFactor": 3,
                "alias": "galaxys3",
                "mobile": true,
                "height": 640,
                "width": 360,
                "touch": true,
                "userAgent": "Mozilla/5.0 (Linux; U; Android 6.0.1; en-us; SM-N900T Build/JSS15J) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/44.0.2403.20 Version/4.0 Mobile Safari/534.30"
            }
        },
        {
            "name": "Google Nexus 10",
            "alias": "nexus10",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 1280,
                "width": 800,
                "touch": true,
                "userAgent": "Mozilla/5.0 (Linux; Android 6.0.1; Nexus 10 Build/JSS15Q) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.20 Safari/537.36"
            }
        },
        {
            "name": "Pixel 2",
            "alias": "pixel2",
            "metrics": {
                "deviceScaleFactor": 2.625,
                "mobile": true,
                "height": 731,
                "width": 411,
                "touch": true,
                "userAgent": "Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3329.0 Mobile Safari/537.36"
            }
        },
        {
            "name": "Nexus 6 P",
            "alias": "nexus6p",
            "metrics": {
                "deviceScaleFactor": 3.5,
                "mobile": true,
                "height": 732,
                "width": 412,
                "touch": true,
                "userAgent": "Mozilla/5.0 (Linux; Android 8.0.0; Nexus 6P Build/OPP3.170518.006) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3329.0 Mobile Safari/537.36"
            }
        },
        {
            "name": "iPhone 8 Plus",
            "alias": "iphone8plus",
            "metrics": {
                "deviceScaleFactor": 3,
                "mobile": true,
                "height": 736,
                "width": 414,
                "touch": true,
                "userAgent": "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version / 11.0 Mobile / 15 A372 Safari / 604.1 "
            }
        },
        {
            "name": "iPhone 7 Plus",
            "alias": "iphone7plus",
            "metrics": {
                "deviceScaleFactor": 3,
                "mobile": true,
                "height": 736,
                "width": 414,
                "touch": true,
                "userAgent": "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile / 15 A372 Safari / 604.1"
            }
        },
        {
            "name": "iPhone 7",
            "alias": "iphone7",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 667,
                "width": 375,
                "touch": true,
                "userAgent": "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML,like Gecko) Version / 11.0  Mobile / 15 A372 Safari / 604.1 "
            }
        },
        {
            "name": "iPhone 8",
            "alias": "iphone8",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 667,
                "width": 375,
                "touch": true,
                "userAgent": "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari / 604.1 "
            }
        },
        {
            "name": "iPhone SE",
            "alias": "iphonese",
            "metrics": {
                "deviceScaleFactor": 2,
                "mobile": true,
                "height": 568,
                "width": 320,
                "touch": true,
                "userAgent": "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari / 602.1 "
            }
        },
        {
            "name": "iPhone X",
            "alias": "iphonex",
            "metrics": {
                "deviceScaleFactor": 3,
                "mobile": true,
                "height": 812,
                "width": 375,
                "touch": true,
                "userAgent": "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari / 604.1 "
            }
        },
        {
            "name": "Pixel 2 XL",
            "alias": "pixel2xl",
            "metrics": {
                "deviceScaleFactor": 3.5,
                "mobile": true,
                "height": 823,
                "width": 411,
                "touch": true,
                "userAgent": "Mozilla/5.0 (Linux; Android 8.0.0; Pixel 2 XL Build/OPD1.170816.004) AppleWebKit/537.36 (KHTML, like Gecko) Chrome / 66.0.3329.0 Mobile Safari / 537.36"
            }
        }
    ]
}
``` 
