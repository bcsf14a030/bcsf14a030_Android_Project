package com.example.farooqkhalid.a31solid;

/**
 * Created by Farooq Khalid on 1/26/2018.
 */

import android.content.Context;
import android.widget.Toast;

public class Message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
