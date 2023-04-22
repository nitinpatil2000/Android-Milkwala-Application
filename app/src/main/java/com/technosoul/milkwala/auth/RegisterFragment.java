//package com.technosoul.milkwala.Auth;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import android.text.TextUtils;
//import android.util.Log;
//import android.util.Patterns;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.technosoul.milkwala.R;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class RegisterFragment extends Fragment {
//    EditText signUpName, signUpDairyName, signUpEmail, signUpMobile, signUpPass;
//    Button signUpRegister, goToSignIn;
//
//    public RegisterFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_register, container, false);
//
//        signUpName = view.findViewById(R.id.signUpName);
//        signUpDairyName = view.findViewById(R.id.signUpDairyName);
//        signUpEmail = view.findViewById(R.id.signUpEmail);
//        signUpMobile = view.findViewById(R.id.signUpMobileNo);
//        signUpPass = view.findViewById(R.id.signUpPassword);
//
//        signUpRegister = view.findViewById(R.id.signUpRegister);
//        goToSignIn = view.findViewById(R.id.goToSignIn);
//
//
//        RetrofitService retrofitService = new RetrofitService();
//        RegisterApi registerApi = retrofitService.getRetrofit().create(RegisterApi.class);
//
//        signUpRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = signUpName.getText().toString();
//                String dairyName = signUpDairyName.getText().toString();
//                String mobile = signUpMobile.getText().toString();
//                String email = signUpEmail.getText().toString();
//                String password = signUpPass.getText().toString();
//
//                // Validate the input values
//                if (TextUtils.isEmpty(name)) {
//                    signUpName.setError("Please enter a Name");
//                    return;
//                }
//                if (TextUtils.isEmpty(dairyName)) {
//                    signUpDairyName.setError("Please enter a Dairy Name");
//                    return;
//                }
//
//                if (TextUtils.isEmpty(email)) {
//                    signUpEmail.setError("Please enter a Email");
//                    return;
//                }
//
//                if (TextUtils.isEmpty(password)) {
//                    signUpPass.setError("Please enter a password");
//                    return;
//                }
//
//                if (!isValidEmail(email)) {
//                    signUpEmail.setError("Please enter a valid email address");
//                    return;
//                }
//
//                if (password.length() < 6) {
//                    signUpPass.setError("Password must be at least 6 characters");
//                    return;
//                }
//
//
//                if (TextUtils.isEmpty(mobile)) {
//                    signUpMobile.setError("Please enter a mobile number");
//                    return;
//                }
//
//                if (!isValidMobileNumber(mobile)) {
//                    signUpMobile.setError("Please enter a valid mobile number");
//                    return;
//                }
//
//                UserRegister userRegister = new UserRegister();
//                userRegister.setName(name);
//                userRegister.setDairyName(dairyName);
//                userRegister.setMobileNo(mobile);
//                userRegister.setEmailId(email);
//                userRegister.setPassword(password);
//
//                registerApi.save(userRegister)
//                        .enqueue(new Callback<UserRegister>() {
//                            @Override
//                            public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {
//                                Toast.makeText(getActivity(), "Registration Success", Toast.LENGTH_SHORT).show();
//                                loadFragment(new LoginFragment());
//                                Log.d("RegisterFragment", "Response code = " + response.code() + " , " + response.toString());
//
//                            }
//
//                            @Override
//                            public void onFailure(Call<UserRegister> call, Throwable t) {
//                                Toast.makeText(getActivity(), "Registration failed", Toast.LENGTH_SHORT).show();
//                                Log.e("RegisterFragment", "Error " + t.getMessage());
//
//                            }
//                        });
//            }
//        });
//
//        goToSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadFragment(new LoginFragment());
//            }
//        });
//
//
//        return view;
//    }
//
//    private boolean isValidMobileNumber(String mobile) {
//        String regex = "^[+]?[0-9]{10,13}$";
//        return mobile.matches(regex);
//
//    }
//
//    private boolean isValidEmail(String email) {
//        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
//    }
//
//    private void loadFragment(Fragment fragment) {
//        FragmentManager fm = getActivity().getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.auth_container, fragment);
//        ft.addToBackStack(null);
//        ft.commit();
//    }
//}