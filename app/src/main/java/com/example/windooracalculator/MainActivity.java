package com.example.windooracalculator;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;

public class MainActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLockScreen();
    }

    private void showLockScreen() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(android.view.Gravity.CENTER);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("পাসওয়ার্ড দিন");
        title.setTextSize(20);
        title.setGravity(android.view.Gravity.CENTER);

        final EditText pinBox = new EditText(this);
        pinBox.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        pinBox.setTextSize(20);
        pinBox.setGravity(android.view.Gravity.CENTER);
        pinBox.setHint("****");

        Button unlockBtn = new Button(this);
        unlockBtn.setText("Unlock");

        layout.addView(title);
        layout.addView(pinBox);
        layout.addView(unlockBtn);
        setContentView(layout);

        unlockBtn.setOnClickListener(v -> {
            if (pinBox.getText().toString().equals("7769")) {
                showCalculator();
            } else {
                Toast.makeText(this, "ভুল পাসওয়ার্ড", Toast.LENGTH_SHORT).show();
                pinBox.setText("");
            }
        });
    }

    private void showCalculator() {
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        webView.loadUrl("file:///android_asset/calculator.html");
        setContentView(webView);
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
