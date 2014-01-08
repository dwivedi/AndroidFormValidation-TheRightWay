package com.dwivedi.androidformvalidation;

import com.dwivedi.androidformvalidation.Validator.Field;
import com.dwivedi.androidformvalidation.Validator.Form;
import com.dwivedi.androidformvalidation.Validator.FormUtils;
import com.dwivedi.androidformvalidation.Validator.validations.InRange;
import com.dwivedi.androidformvalidation.Validator.validations.IsEmail;
import com.dwivedi.androidformvalidation.Validator.validations.NotEmpty;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends Activity {

	 private static final String GITHUB_PAGE = "https://github.com/zasadnyy/z-validations";

	    private EditText mName;
	    private EditText mEmail;
	    private EditText mAge;
	    private Button mSubmit;

	    // Form used for validation
	    private Form mForm;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);

	        initFields();
	        initValidationForm();
	        initCallbacks();
	    }

	    

	    private void initFields() {
	        mName = (EditText) findViewById(R.id.name);
	        mEmail = (EditText) findViewById(R.id.email);
	        mAge = (EditText) findViewById(R.id.age);
	        mSubmit = (Button) findViewById(R.id.submit);
	    }

	    private void initValidationForm() {
	        mForm = new Form(this);
	        mForm.addField(Field.using(mName).validate(NotEmpty.build(this)));
	        mForm.addField(Field.using(mEmail).validate(NotEmpty.build(this)).validate(IsEmail.build(this)));
	        mForm.addField(Field.using(mAge).validate(InRange.build(this, 0, 120)));
	    }

	    private void initCallbacks() {
	        mAge.setOnEditorActionListener(new TextView.OnEditorActionListener() {
	            @Override
	            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
	                if (actionId == EditorInfo.IME_ACTION_DONE) {
	                    submit();
	                    return true;
	                }
	                return false;
	            }

	        });

	        mSubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                submit();
	            }
	        });
	    }

	    private void submit() {
	        FormUtils.hideKeyboard(MainActivity.this, mAge);
	        if (mForm.isValid()) {
	         //   Crouton.makeText(this, getString(R.string.sample_activity_form_is_valid), Style.CONFIRM).show();
	        	Toast.makeText(this, getString(R.string.sample_activity_form_is_valid), Toast.LENGTH_LONG).show();
	        }
	    }

	    

}
