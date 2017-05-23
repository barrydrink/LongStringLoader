[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Download](https://api.bintray.com/packages/penrillian/penrillian-maven/LongStringLoader/images/download.svg) ](https://bintray.com/penrillian/penrillian-maven/LongStringLoader/_latestVersion)
[![Build Status](https://travis-ci.org/bellabling/LongStringLoader.svg?branch=master)](https://travis-ci.org/bellabling/LongStringLoader)
[![codecov](https://codecov.io/gh/bellabling/LongStringLoader/branch/master/graph/badge.svg)](https://codecov.io/gh/bellabling/LongStringLoader)
[![Code Climate](https://codeclimate.com/github/bellabling/LongStringLoader/badges/gpa.svg)](https://codeclimate.com/github/bellabling/LongStringLoader)
[![Issue Count](https://codeclimate.com/github/bellabling/LongStringLoader/badges/issue_count.svg)](https://codeclimate.com/github/bellabling/LongStringLoader)


# LongStringLoader

Solution to loading long strings in Android TextViews.

The motivation for this component was the requirement by Google to display their open source licence text if their mapping APIs were used. This text is 553253 characters long and a Motorola G5 running Android 6 takes 7 seconds to load it into a TextView. The loading into a TextView has to be done on the UI thread, blocking the UI. LongStringLoader will load the long string incrementally, keeping the UI responsive.

Loading such a long string will take longer using LongStringLoader, but the UI will not be blocked, allowing the user to scroll through the string as it loads.

See the example app or example code below for usage instructions. Tested on Android 4, 5 & 6.

###To include in your project:

#### Maven

```
<dependency>
  <groupId>com.penrillian</groupId>
  <artifactId>longstringloader</artifactId>
  <version>0.0.6-beta</version>
  <type>pom</type>
</dependency>
```

#### Gradle

```
compile 'com.penrillian:longstringloader:0.0.6-beta'
```

#### Ivy

```
<dependency org='com.penrillian' name='longstringloader' rev='0.0.6-beta'>
  <artifact name='$AID' ext='pom'></artifact>
</dependency>
```


###Example using Google's open source license string:
```
import com.penrillian.longstringloader.LongStringLoadCompleteListener;
import com.penrillian.longstringloader.LongStringLoader;
import com.penrillian.longstringloader.LongStringLoaderException;


public class MainActivity extends AppCompatActivity implements LongStringLoadCompleteListener
{
    private LongStringLoader longStringLoader;
	private LinearLayout linearLayout;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
		longStringLoader = new LongStringLoader(this, linearLayout);
		try
        {
            String longString = GoogleApiAvailability.getInstance().getOpenSourceSoftwareLicenseInfo(this);
            longStringLoader.load(longString);
        } catch (LongStringLoaderException ignored) {}
    }
    
    @Override
    public void onLongStringLoadComplete()
    {
        //do any post string loading work here, such as hiding a progress bar
    }
}
```

### Limitations
In tests it was found that if the LinearLayout which is passed to LongStringLoader is contained within a ScrollView or NestedScrollView, LongStringLoader will not be able to improve the loading speed or UI responsiveness. This was a real use case for LongStringLoader - the app in question had an about box which displayed version numbers and legal info as well as Google's open source licence information, all within a ScrollView within a DialogFragment. The licence string had to be moved to its own DialogFragment. See the example app for a demo.
