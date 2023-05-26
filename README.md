Location Updates in the Background (Kotlin)
===========================================
Demonstrates retrieving location updates(Baidu) in the background.

Introduction
============
This sdk allows a user to receive location updates in the background via a `PendingIntent`.

In addition to the FINE location permission (`android.permission.ACCESS_FINE_LOCATION`), if you do
have an approved use case for receiving location updates in the background, it will require an
additional permission (`android.permission.ACCESS_BACKGROUND_LOCATION`).

To run this sample, **location must be enabled**.

**IMPORTANT NOTE**: You should generally prefer 'while-in-use' for location updates, i.e., receiving
location updates while the app is in use and create a foreground service (tied to a Notification)
when the user navigates away from the app. To learn how to do that instead, review the
[Receive location updates in Android 10 with Kotlin](https://codelabs.developers.google.com/codelabs/while-in-use-location/index.html?index=..%2F..index#0)
codelab.

Prerequisites
--------------

- Android API Level > v21
- Google Support Repository

Getting Started
---------------

```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION" />
```

Support
-------

If you've found an error in this sample, please file an issue:
https://github.com/kuloud/HiveLocation/issues

Patches are encouraged, and may be submitted according to the instructions in CONTRIBUTING.md.
