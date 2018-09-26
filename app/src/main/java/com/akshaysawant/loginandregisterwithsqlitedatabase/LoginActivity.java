package com.akshaysawant.loginandregisterwithsqlitedatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;

    private NestedScrollView mNestedScrollView;
    private TextInputLayout mTextInputLayoutEmail, mTextInputLayoutPassword;
    private TextInputEditText mTextInputEditTextEmail, mTextInputEditTextPassword;

    private AppCompatButton mAppCompatButtonLogin;
    private AppCompatTextView mAppCompatTextViewLinkRegister;
    private InputValidation mInputValidation;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /**
         * Hides the Action bar
         *
         * Checks if the SDK version is greater than KITKAT or not
         * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }

        context = LoginActivity.this;

        /**
         * Calling functions
         *
         * Binding function
         * Initialize Listeners function
         * Initialize Objects function
         * */
        bindingViewsByIdFunc();
        initializeListenersFunc();
        initializeObjectsFunc();
    }

    public void bindingViewsByIdFunc() {

        //Binding the nested scrollview
        mNestedScrollView = findViewById(R.id.nestedScrollView);

        //Binding the text input layout fields for EMAIL and PASSWORD
        mTextInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        mTextInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);

        //Binding the text input edit text for EMAIL and PASSWORD
        mTextInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        mTextInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);

        //Binding the Login Button
        mAppCompatButtonLogin = findViewById(R.id.appCompatButtonLogin);

        //Binding the link for REGISTRATION
        mAppCompatTextViewLinkRegister = findViewById(R.id.textViewLinkRegister);
    }

    /**
     * This method is to initialize listeners
     */
    private void initializeListenersFunc() {
        mAppCompatButtonLogin.setOnClickListener(this);
        mAppCompatTextViewLinkRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initializeObjectsFunc() {
        mDatabaseHelper = new DatabaseHelper(activity);
        mInputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.appCompatButtonLogin:
                verifyFromSQLiteFunc();
                break;
            case R.id.textViewLinkRegister:

                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    /**
     * Method to validate and verify
     *
     * Validate the input text fields
     * Verify login credentials using SQLite
     */
    private void verifyFromSQLiteFunc() {
        if (!mInputValidation.isInputEditTextFilledFunc(mTextInputEditTextEmail, mTextInputLayoutEmail,
                getString(R.string.error_message_email))) {
            return;
        }
        if (!mInputValidation.isInputEditTextEmailFunc(mTextInputEditTextEmail, mTextInputLayoutEmail,
                getString(R.string.error_message_email))) {
            return;
        }
        if (!mInputValidation.isInputEditTextFilledFunc(mTextInputEditTextPassword, mTextInputLayoutPassword,
                getString(R.string.error_message_email))) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (mDatabaseHelper.checkForUsers(Objects.requireNonNull(mTextInputEditTextEmail.getText()).toString().trim()
                    , Objects.requireNonNull(mTextInputEditTextPassword.getText()).toString().trim())) {


                Intent accountsIntent = new Intent(context, UsersListActivity.class);
                accountsIntent.putExtra("EMAIL", mTextInputEditTextEmail.getText().toString().trim());
                emptyInputEditText();
                startActivity(accountsIntent);


            } else {
                // Snack Bar to show success message that record is wrong
                Snackbar.make(mNestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
            }
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        mTextInputEditTextEmail.setText(null);
        mTextInputEditTextPassword.setText(null);
    }
}
