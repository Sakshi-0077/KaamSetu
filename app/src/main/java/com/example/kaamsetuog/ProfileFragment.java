package com.example.kaamsetuog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private TextView profileNameDisplay, viewPhone, viewEmail, viewLocation, viewAddress;
    private EditText editName, editPhone, editEmail, editGender, editCity, editState, editLanguage;
    private Button saveProfileBtn, btnEditProfile, btnCancelEdit;
    private LinearLayout layoutViewMode, layoutEditMode;
    private ImageView settingsBtn;

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Views
        profileNameDisplay = view.findViewById(R.id.profileNameDisplay);
        viewPhone = view.findViewById(R.id.viewPhone);
        viewEmail = view.findViewById(R.id.viewEmail);
        viewLocation = view.findViewById(R.id.viewLocation);
        viewAddress = view.findViewById(R.id.viewAddress);

        // Edit Fields
        editName = view.findViewById(R.id.editName);
        editPhone = view.findViewById(R.id.editPhone);
        editEmail = view.findViewById(R.id.editEmail);
        editGender = view.findViewById(R.id.editGender);
        editCity = view.findViewById(R.id.editCity);
        editState = view.findViewById(R.id.editState);
        editLanguage = view.findViewById(R.id.editLanguage);

        // Buttons and Layouts
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        btnCancelEdit = view.findViewById(R.id.btnCancelEdit);
        saveProfileBtn = view.findViewById(R.id.saveProfileBtn);
        layoutViewMode = view.findViewById(R.id.layoutViewMode);
        layoutEditMode = view.findViewById(R.id.layoutEditMode);
        settingsBtn = view.findViewById(R.id.settingsBtn);

        loadUserData();

        btnEditProfile.setOnClickListener(v -> toggleEditMode(true));
        btnCancelEdit.setOnClickListener(v -> toggleEditMode(false));

        saveProfileBtn.setOnClickListener(v -> {
            saveUserData();
            toggleEditMode(false);
            loadUserData(); // Refresh view mode
            Toast.makeText(getContext(), "Profile Updated!", Toast.LENGTH_SHORT).show();
        });

        settingsBtn.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
        });

        Button addPhotoBtn = view.findViewById(R.id.addPhotoBtn);
        Button editPhotoBtn = view.findViewById(R.id.editPhotoBtn);
        addPhotoBtn.setOnClickListener(v -> showPhotoMenu(v));
        editPhotoBtn.setOnClickListener(v -> showPhotoMenu(v));

        return view;
    }

    private void toggleEditMode(boolean isEdit) {
        if (isEdit) {
            layoutViewMode.setVisibility(View.GONE);
            layoutEditMode.setVisibility(View.VISIBLE);
            btnEditProfile.setVisibility(View.GONE);
        } else {
            layoutViewMode.setVisibility(View.VISIBLE);
            layoutEditMode.setVisibility(View.GONE);
            btnEditProfile.setVisibility(View.VISIBLE);
        }
    }

    private void loadUserData() {
        SharedPreferences sp = getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String name = sp.getString("username", "User Name");
        String phone = sp.getString("phone", sp.getString("mobile", "+91 0000000000"));
        String email = sp.getString("email", "Not set");
        String city = sp.getString("city", "Ahmedabad");
        String address = sp.getString("address", "No address provided");

        // Set View Mode
        profileNameDisplay.setText(name);
        viewPhone.setText(phone);
        viewEmail.setText(email);
        viewLocation.setText(city);
        viewAddress.setText(address);

        // Pre-fill Edit Mode
        editName.setText(name);
        editPhone.setText(phone);
        editEmail.setText(email);
        editCity.setText(city);
        editGender.setText(sp.getString("gender", ""));
        editState.setText(sp.getString("state", ""));
        editLanguage.setText(sp.getString("language", ""));
    }

    private void saveUserData() {
        SharedPreferences sp = getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", editName.getText().toString());
        editor.putString("phone", editPhone.getText().toString());
        editor.putString("email", editEmail.getText().toString());
        editor.putString("gender", editGender.getText().toString());
        editor.putString("city", editCity.getText().toString());
        editor.putString("state", editState.getText().toString());
        editor.putString("language", editLanguage.getText().toString());
        editor.apply();
    }

    private void showPhotoMenu(View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        popup.getMenu().add("Take Photo");
        popup.getMenu().add("Choose from Gallery");
        popup.setOnMenuItemClickListener(item -> {
            Toast.makeText(getContext(), item.getTitle() + " coming soon!", Toast.LENGTH_SHORT).show();
            return true;
        });
        popup.show();
    }
}