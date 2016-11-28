package com.example.hh.loadercursoractivity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks, TextWatcher {


    private EditText et_input;
    private ListView lv_show_contacts;
    private final int LOADER_CUROSR_ID = 1;

    private SimpleCursorAdapter scAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_input = (EditText) findViewById(R.id.et_input);
        lv_show_contacts = (ListView) findViewById(R.id.lv_show_contacts);

        et_input.addTextChangedListener(this);

        scAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                null,
                new String[]{
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.Contacts.CONTACT_STATUS,
                },
                new int[]{
                        android.R.id.text1,
                        android.R.id.text2
                },
                0
        );

        lv_show_contacts.setAdapter(scAdapter);

        Bundle bundle = new Bundle();
        bundle.putString("filter", null);

        LoaderManager lm = getLoaderManager();
        lm.initLoader(LOADER_CUROSR_ID, bundle, this);

    }


    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        Log.i("LoaderCursorActivity", "onCreateLoader");

        Uri uri;

        String filter = args.getString("filter", null);

        if (filter != null) {
            uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, Uri.encode(filter));
        } else {
            uri = ContactsContract.Contacts.CONTENT_URI;
        }


        String[] projection = new String[]{

                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.CONTACT_STATUS
        };


        String selection = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND " +
                "(" + ContactsContract.Contacts.HAS_PHONE_NUMBER + " =1) AND " +
                "(" + ContactsContract.Contacts.DISPLAY_NAME + " != ''))";

        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";


        return new CursorLoader(this, uri, projection, selection, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        Log.i("LoaderCursorActivity", "onLoadFinished");
        scAdapter.swapCursor((Cursor) data);

    }

    @Override
    public void onLoaderReset(Loader loader) {
        Log.i("LoaderCursorActivity", "onLoaderReset");
        scAdapter.swapCursor(null);
    }


    @Override
    public void afterTextChanged(Editable s) {

        Log.i("LoaderCursorActivity", "afterTextChanged");

        String filter = et_input.getText().toString();
        Bundle bFilter = new Bundle();
        bFilter.putString("filter", filter);
        LoaderManager lm = getLoaderManager();
        lm.restartLoader(LOADER_CUROSR_ID, bFilter, this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


}
