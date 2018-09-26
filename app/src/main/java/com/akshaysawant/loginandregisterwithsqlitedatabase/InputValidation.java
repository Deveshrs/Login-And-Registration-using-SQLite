package com.akshaysawant.loginandregisterwithsqlitedatabase;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.Objects;

public class InputValidation {

    private Context context;
    private String value, value1, value2;

    /**
     * Constructor with @param context
     */
    public InputValidation(Context context) {
        this.context = context;
    }

    /**
     * Method to check input fields
     *
     * @param textInputEditText
     * @param textInputLayout
     * @param message
     * @return
     */
    public boolean isInputEditTextFilledFunc(TextInputEditText textInputEditText,
                                             TextInputLayout textInputLayout, String message) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            value = Objects.requireNonNull(textInputEditText.getText()).toString().trim();
        }

        if (value.isEmpty()) {
            textInputLayout.setError(message);
            hideKeyboardFunc(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    /**
     * Method to check input fields has valid email address or not
     *
     * @param textInputEditText
     * @param textInputLayout
     * @param message
     * @return
     */
    public boolean isInputEditTextEmailFunc(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            value = Objects.requireNonNull(textInputEditText.getText()).toString().trim();
        }

        if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputLayout.setError(message);
            hideKeyboardFunc(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    /**
     * Method to check whether input fields matches or not
     *
     * @param textInputEditText1
     * @param textInputEditText2
     * @param textInputLayout
     * @param message
     * @return
     */
    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1, TextInputEditText textInputEditText2,
                                          TextInputLayout textInputLayout, String message) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            value1 = Objects.requireNonNull(textInputEditText1.getText()).toString().trim();
            value2 = Objects.requireNonNull(textInputEditText2.getText()).toString().trim();
        }

        if (value1.contentEquals(value2)) {
            textInputLayout.setError(message);
            hideKeyboardFunc(textInputEditText2);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    /**
     * Method to hide the keyboard
     *
     * @param view
     */
    private void hideKeyboardFunc(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }

}
