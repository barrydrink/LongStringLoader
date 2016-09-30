[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Download](https://api.bintray.com/packages/penrillian/penrillian-maven/LongStringLoader/images/download.svg) ](https://bintray.com/penrillian/penrillian-maven/LongStringLoader/_latestVersion)
[![Build Status](https://travis-ci.org/Penrillian/LongStringLoader.svg?branch=master)](https://travis-ci.org/Penrillian/LongStringLoader)
[![codecov](https://codecov.io/gh/Penrillian/LongStringLoader/branch/master/graph/badge.svg)](https://codecov.io/gh/Penrillian/LongStringLoader)


# LongStringLoader

Solution to loading long strings in Android TextViews.

The motivation for this component was the requirement by Google to display their open source licence text if their mapping APIs were used. This text is 553253 characters long and a Motorola G5 running Android 6 takes 7 seconds to load it into a TextView. The loading into a TextView has to be done on the UI thread, blocking the UI. LongStringLoader will load the long string incrementally, keeping the UI responsive.

Loading such a long string will take longer using LongStringLoader, but the UI will not be blocked, allowing the user to scroll through the string as it loads.

See the example app or example code below for usage instructions.

###To include in your project:

#### Maven

```
<dependency>
  <groupId>com.penrillian</groupId>
  <artifactId>longstringloader</artifactId>
  <version>0.0.2-beta</version>
  <type>pom</type>
</dependency>
```

#### Gradle

```
compile 'com.penrillian:longstringloader:0.0.2-beta'
```

#### Ivy

```
<dependency org='com.penrillian' name='longstringloader' rev='0.0.2-beta'>
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
	private LinearLayout textViewLayout;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		textViewLayout = (LinearLayout) findViewById(R.id.text_view_layout);
		longStringLoader = new LongStringLoader(this, this, textViewLayout);
		try
        {
            String longString = GoogleApiAvailability.getInstance().getOpenSourceSoftwareLicenseInfo(this);
            longStringLoader.load(longString);
        } catch (LongStringLoaderException ignored) {}
    }
    
    @Override
    public void onStringLoadComplete()
    {
        //do any post string loading work here, such as hiding a progress bar
    }
}
```