package com.technosoul.milkwala.Auth;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.R;

import java.io.DataInput;

public class LoginFragment extends Fragment {
    private LoginListener listener;
    EditText signInEmail, signInPassword;
    Button signInLogin;
//    TextView forgotPassword;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginListener) {
            listener = (LoginListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement LoginListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        signInEmail = view.findViewById(R.id.signInEmail);
        signInPassword = view.findViewById(R.id.signInPassword);

        signInLogin = view.findViewById(R.id.singInLogin);
        signInLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = signInEmail.getText().toString().trim();
                String password = signInPassword.getText().toString().trim();

                // Validate the input values
                if (TextUtils.isEmpty(email)) {
                    signInEmail.setError("Please enter a Email");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    signInPassword.setError("Please enter a password");
                    return;
                }

                if (!isValidEmail(email)) {
                    signInEmail.setError("Please enter a valid email address");
                    return;
                }

                if (password.length() < 6) {
                    signInPassword.setError("Password must be at least 6 characters");
                    return;
                }

                validateCredentials(email, password);

            }
        });

//        goToRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RegisterFragment registerFragment = new RegisterFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.auth_container, registerFragment);
//                fragmentTransaction.commit();
//            }
//        });

//        forgotPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Button sendOtpBtn, cancelBtn;
//
//                Dialog dialog = new Dialog(getContext());
//                dialog.setCancelable(false);
//                dialog.setContentView(R.layout.forgot_dialog_design);
//                cancelBtn = dialog.findViewById(R.id.cancelForgot);
//                sendOtpBtn = dialog.findViewById(R.id.sendOTPBtn);
//
//                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                Window window = dialog.getWindow();
//                lp.copyFrom(window.getAttributes());
//                lp.width = (int) (getResources().getDisplayMetrics().widthPixels * 1.0);
//                lp.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.4);
//                window.setAttributes(lp);

//                cancelBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });

//                sendOtpBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                        Toast.makeText(getContext(), "OTP is sent to the registered emailID !!", Toast.LENGTH_SHORT).show();
//                    }
//                });

//                dialog.show();
//
//
//            }
//        });


        return view;
    }

    private void validateCredentials(String email, String password) {
        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        Login login = myDbHelper.loginDao().getLoginCredentials(email);
        if(login != null && login.getPassword().equals(password))
        {
            listener.onLoginSuccess();
        }
        else{
            Toast.makeText(getContext(), "Invalid EmailId and Password", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}