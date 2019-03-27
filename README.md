# What’s this app does (words from author @PhilippeBoisney) ?
This simple app is composed of a single screen. When this screen appears, we will fetch (Retrofit) the Github information of Jake Wharton and persist those immediately in the application storage (Room).

Next, when the screen is re-launched (or recreated due to rotation), we will get those same information, first in Room database and only if necessary, refresh those from the Github Api.

# Libs:
Includes: Dagger 2, ButterKnife, Glide, Gson

# Guide:
https://developer.android.com/topic/libraries/architecture/guide.html
