package com.technosoul.milkwala.ui.auth;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.accounts.auth.AuthTokenManager;
import com.technosoul.milkwala.managers.SharedPreferenceManager;
import com.technosoul.milkwala.utils.Constants;

public class LoginFragment extends Fragment {
    private LoginListener listener;
    private EditText signInEmail;
    private EditText signInPassword;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof LoginListener) {
            listener = (LoginListener) context;
        } else {
            throw new RuntimeException(context + " must implement LoginListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        signInEmail = view.findViewById(R.id.signInEmail);
        signInPassword = view.findViewById(R.id.signInPassword);

        Button btnLogin = view.findViewById(R.id.singInLogin);
        btnLogin.setOnClickListener(view1 -> initiateLogin());

        return view;
    }

    private void initiateLogin() {
        String email = signInEmail.getText().toString().trim();
        String password = signInPassword.getText().toString().trim();

        // Validate the input values
        if (TextUtils.isEmpty(email)) {
            signInEmail.setError(getString(R.string.err_empty_email));
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signInEmail.setError(getString(R.string.err_invalid_email));
            return;
        }

        if (TextUtils.isEmpty(password)) {
            signInPassword.setError(getString(R.string.err_empty_pwd));
            return;
        }

        if (password.length() < 6) {
            signInPassword.setError(getString(R.string.err_pwd_min_length));
            return;
        }

        sendLoginRequest(email, password);
    }

    private void sendLoginRequest(String email, String password) {

        // TODO: Add code to send Login request when Backend it ready.

        if (TextUtils.equals(email, "db@gmail.com")) {
            AuthTokenManager.instance().set("TempDeliveryBoyLoginToken");
            SharedPreferenceManager.getInstance().putInt(Constants.KEY_LOGIN_TYPE, Constants.LOGIN_TYPE_DELIVERY_BOY);
            listener.onDeliveryBoyLoginSuccess();

        } else if (TextUtils.equals(email, "admin@gmail.com")) {
            AuthTokenManager.instance().set("TempAdminLoginToken");
            SharedPreferenceManager.getInstance().putInt(Constants.KEY_LOGIN_TYPE, Constants.LOGIN_TYPE_ADMIN);
            listener.onAdminLoginSuccess();

        } else {
            Toast.makeText(context, "Not authorized to login.", Toast.LENGTH_SHORT).show();
        }
    }

}