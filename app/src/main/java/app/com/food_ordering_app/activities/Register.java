package app.com.food_ordering_app.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.Login_Api;
import app.com.food_ordering_app.Web_Service.Register_Api;

public class Register extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{

    TextView login_text,txt_codes;
    Button btn_register;
    EditText edt_fname,edt_lname,edt_email,edt_password,edt_confirm_pass,edt_number;
    Register_Api register_api;
    EditText edt1_mPhone_et,edt2_mPhone_et,edt3_mPhone_et;
    String lastChar = " ";
    int digits;
    String txt="";
    ListView list;
    SharedPreferences sharedPreferences;
    String res_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //---------shared preferences-----------------------------------
        sharedPreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        res_reg=sharedPreferences.getString("user_id","");
        if (res_reg.equalsIgnoreCase(""))
        {

        }
        else  {

            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        init();
        work();

    }
    //--------custom text view just like spinner(registercode,codelist,codes added in string)--------------

    public String opencode(final TextView textView)
    {
        final Dialog openDialogCodes = new Dialog(Register.this, android.R.style.Theme_Translucent_NoTitleBar);
        openDialogCodes.setContentView(R.layout.registercode);


        EditText matchTxt = (EditText) openDialogCodes.findViewById(R.id.matchTxt);
        final ListView    list1=(ListView) openDialogCodes.findViewById(R.id.codeview);

        //----------------to add codes to string--------------------------------------
        String[] country=getApplication().getResources().getStringArray(R.array.code);
        Log.e("here_country_list",country+"");

        //-----------passing array of codes to arrayAdapter----------------------------
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Register.this, R.layout.codelist, R.id.code, country);
        list1.setAdapter(adapter);
        matchTxt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        //------------list onClick Listener-----------------------------------------
         list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //-----getting position of list---------
                String val=(String) list1.getItemAtPosition(position);
                Log.e("ssvalue1", val);



                int l=val.indexOf("(");
                int l1=val.indexOf(")");
                Log.e("ssvalue2", l+"");
                Log.e("ssvalue21", l1+"");
                txt=val.substring(l+1,l1);
                Log.e("ssvalue3", txt);

                textView.setText(txt);
                openDialogCodes.dismiss();




            }
        });

        openDialogCodes.show();
        return txt;
    }


    //----------end------------------------------------


    public void init()
    {
        login_text=(TextView)findViewById(R.id.login_text);
        txt_codes=(TextView) findViewById(R.id.codes);
        btn_register=(Button)findViewById(R.id.btn_register);
        edt_fname=(EditText)findViewById(R.id.edit_FName);
        edt_lname=(EditText)findViewById(R.id.edit_LName);
        edt_email=(EditText)findViewById(R.id.email_register);
      //== edt_number=(EditText)findViewById(R.id.edt_number);
        edt_password=(EditText)findViewById(R.id.password_register);
        edt_confirm_pass=(EditText)findViewById(R.id.confirm_pass_register);
        edt1_mPhone_et=(EditText)findViewById(R.id.edt1_mPhone_et);
        edt2_mPhone_et=(EditText)findViewById(R.id.edt2_mPhone_et);
        edt3_mPhone_et=(EditText)findViewById(R.id.edt3_mPhone_et);
      //==  mPasswordField = (EditText) findViewById(R.id.edt_number);
        register_api=new Register_Api();

    }
    public void work()
    {

        //-----------------------edt phone field 1---------------------------------------
        edt1_mPhone_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              if (edt1_mPhone_et.length()==3)
              {
                  edt2_mPhone_et.requestFocus();
              }
              else if (edt1_mPhone_et.length()==0)
                {
                    edt1_mPhone_et.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {



            }
        });

        //--------------------edt phone field 2-------------------------------------
        edt2_mPhone_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (edt2_mPhone_et.length()==3)
                {
                    edt3_mPhone_et.requestFocus();
                }
                else if (edt2_mPhone_et.length()==0)
                {
                    edt1_mPhone_et.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //----------------------edt phone field 3------------------------------------
        edt3_mPhone_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (edt3_mPhone_et.length()==0)
                {
                    edt2_mPhone_et.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //----------------------Login button onClick Listener---------------------------
        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
                finish();
            }
        });

        //---------------Register button onClick Listener--------------------------------
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //----------getting all 3 fields of no into 1 string----------------
                String mPhone = edt1_mPhone_et.getText().toString()+edt2_mPhone_et.getText().toString()+edt3_mPhone_et.getText().toString();
                Log.e("val_phone",mPhone);

                String check_password=edt_password.getText().toString();
                String check_cpassword=edt_confirm_pass.getText().toString();

                String email = edt_email.getText().toString().trim();
                String fname = edt_fname.getText().toString().trim();
                String lname = edt_lname.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String namePattern = "[a-zA-Z ]+";
                if (edt_fname.length() == 0 && edt_lname.length()==0 && edt_email.length() == 0 && edt_password.length() == 0 && edt_confirm_pass.length() == 0)
                {
                    Toast.makeText(Register.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }

              else  if (edt_fname.length() ==0)
                {
                    edt_fname.setError("Enter First name");
                }
                else  if (edt_fname.length() < 3 )
                {
                    edt_fname.setError("First name should be atleast 3 characters");
                }
                else if (!fname.matches(namePattern))
                {
                    edt_fname.setError("Enter only text");
                }

               else if (edt_lname.length() ==0)
                {
                    edt_lname.setError("Enter Last name");
                }

                else  if (edt_lname.length() < 3 )
                {
                    edt_lname.setError("Last name should be atleast 3 characters");
                }
                else if (!lname.matches(namePattern))
                {
                    edt_lname.setError("Enter only text");
                }
               else if (email.length() == 0 )
                {
                    edt_email.setError("Enter email ");
                }
              else  if (!email.matches(emailPattern))
                {
                    edt_email.setError("Invalid email");
                }
                else if (edt_password.length()<6) {
                    edt_password.setError("Password should be atleast 6 characters");
                }

                else if (edt_confirm_pass.length()<6)
                {
                    edt_confirm_pass.setError("Password should be atleast 6 characters");
                }
                else if (!check_cpassword.equals(check_password))
                {
                    edt_confirm_pass.setError("Password does not match");
                }



                else if (mPhone.length()==0)
                {
                    edt1_mPhone_et.setError("Enter number");
                }
                else
                {

                    register_api.register(Register.this,edt_fname.getText().toString(),edt_lname.getText().toString(),edt_email.getText().toString(),edt_password.getText().toString(),edt_confirm_pass.getText().toString(),txt_codes.getText().toString(),mPhone);


                 }



            }
        });

        //----------------------Country codes onClick Listener------------------------------------
        txt_codes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opencode(txt_codes);
            }
        });
    }



    //--------------spinner overrided methods--------------------------------------
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}