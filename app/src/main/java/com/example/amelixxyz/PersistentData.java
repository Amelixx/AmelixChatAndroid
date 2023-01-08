package com.example.amelixxyz;

import androidx.lifecycle.ViewModel;

/** This class extends ViewModel, which keeps data through configuration changes.
 *  This is useful to make the app load the same web page again rather than hard restarting. */
public class PersistentData extends ViewModel {
    public String currentUrl;
}
