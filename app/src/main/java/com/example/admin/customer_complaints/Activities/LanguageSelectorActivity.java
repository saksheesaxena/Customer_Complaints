package com.example.admin.customer_complaints.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.customer_complaints.R;

import java.util.Locale;


/**
 * Created by admin on 22-Aug-15.
 */
public class LanguageSelectorActivity extends BaseActivity {
    Locale mLocale;
    Button mLanguageSelector;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.language_selector);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            selectLanguage();



    }
    private void selectLanguage() {
        new MaterialDialog.Builder(this)
                .dividerColorRes(R.color.colorPrimary)
                .title(R.string.language_lang)
                .items(R.array.app_language)
                .itemsCallbackSingleChoice(1, new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if (which == 0) {
                            setLocale("hi");
                            editor.putString("language", "hi");
                            editor.commit();
                        } else if (which == 1) {
                            setLocale("en");
                            editor.putString("language", "en");
                            editor.commit();
                        }
                    }
                })
                .positiveText("Ok")
                .show();
    }





    public void setLocale(String lang)
    {
        mLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        cf.locale = mLocale;
        res.updateConfiguration(cf,dm);
        Intent refresh = new Intent(this , HomeActivity.class);
        startActivity(refresh);
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.final_activity_home;
    }

}