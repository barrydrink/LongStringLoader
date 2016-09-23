[ ![Download](https://api.bintray.com/packages/penrillian/penrillian-maven/LongStringLoader/images/download.svg) ](https://bintray.com/penrillian/penrillian-maven/LongStringLoader/_latestVersion)[![Build Status](https://travis-ci.org/Penrillian/LongStringLoader.svg?branch=dev)](https://travis-ci.org/Penrillian/LongStringLoader)

# LongStringLoader

Solution to loading long strings in Android TextViews.

The motivation for this component was the requirement by Google to display their open source licence text if their mapping APIs were used. This text is 553253 characters long and a Motorola G5 running Android 6 takes 7 seconds to load it into a TextView. The loading into a TextView has to be done on the UI thread, blocking the UI. LongStringLoader will load the long string incrementally, keeping the UI responsive.

Loading such a long string will take longer using LongStringLoader, but the UI will not be blocked, allowing the user to scroll through the string as it loads.

See the example app for usage instructions.

To include in your project:

### Maven

```
<dependency>
  <groupId>com.penrillian</groupId>
  <artifactId>longstringloader</artifactId>
  <version>0.0.1-beta</version>
  <type>pom</type>
</dependency>
```

### Gradle

```
compile 'com.penrillian:longstringloader:0.0.1-beta'
```

### Ivy

```
<dependency org='com.penrillian' name='longstringloader' rev='0.0.1-beta'>
  <artifact name='$AID' ext='pom'></artifact>
</dependency>
```
