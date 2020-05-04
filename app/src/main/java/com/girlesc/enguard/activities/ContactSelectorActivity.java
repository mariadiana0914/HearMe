package com.girlesc.enguard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.girlesc.enguard.R;

public class ContactSelectorActivity extends AppCompatActivity {
        public static final int PICK_CONTACT    = 1;
        private Button btnContacts;
        private TextView txtContacts1;
        private TextView txtContacts2;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_contact_selector);
            btnContacts = (Button) findViewById(R.id.btn_contacts);
            txtContacts1 = (TextView) findViewById(R.id.txt_contacts_name);
            txtContacts2 = (TextView) findViewById(R.id.txt_contacts_number);
            btnContacts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(Intent.ACTION_PICK, Contacts.People.CONTENT_URI);
                    startActivityForResult(intent, PICK_CONTACT);
                }
            });
        }
        @Override
        public void onActivityResult(int reqCode, int resultCode, Intent data) {
            super.onActivityResult(reqCode, resultCode, data);
            switch (reqCode) {
                case (PICK_CONTACT):
                    if (resultCode == Activity.RESULT_OK) {
                        Uri contactData = data.getData();
                        Cursor c = managedQuery(contactData, null, null, null, null);
                        if (c.moveToFirst()) {
                            String name = c.getString(c.getColumnIndexOrThrow(Contacts.People.NAME))+" : c.getInt(c.getColumnIndexOrThrow(People.NUMBER))";
                            txtContacts1.setText(name);
                        }
                    }
                    break;
            }
        }
}


