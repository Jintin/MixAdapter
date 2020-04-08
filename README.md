# MixAdapter

[![Build Status](https://travis-ci.org/Jintin/MixAdapter.svg?branch=master)](https://travis-ci.org/Jintin/MixAdapter)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/da2c855d2b8341f39205f4d87d6790db)](https://www.codacy.com/app/Jintin/MixAdapter?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Jintin/MixAdapter&amp;utm_campaign=Badge_Grade)
**Deprecated since AndroidX introduce MergeAdapter can do same thing as we do.**
Compose multiple Adapter for RecyclerView in Android

## Installation

Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
  repositories {
    // ...
    maven { url 'https://jitpack.io' }
  }
}
```
Step 2. Add the dependency
```groovy
dependencies {
  compile 'com.github.jintin:MixAdapter:1.1'
}
```

## Usage

For simple usage add all your adapter into `MixAdapter`then done.
```java
MixAdapter<RecyclerView.ViewHolder> adapter = new MixAdapter<>();
adapter.addAdapter(adapterA);
adapter.addAdapter(adapterB);
recyclerView.setAdapter(adapter);
```
You can see more example in `app` module or clone to run.

## Contributing

Bug reports and pull requests are welcome on GitHub at <https://github.com/Jintin/MixAdapter>.

## License

The module is available as open source under the terms of the [MIT License](http://opensource.org/licenses/MIT).
