package atpl.cc.tinkerlust.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import atpl.cc.tinkerlust.R;
import atpl.cc.tinkerlust.classes.AppConstants;

public class Login extends AppCompatActivity {
TextView forgotpassword;
    Button loginBtn;
    LinearLayout registerLayout;
ProgressDialog pd;


public static String USERNAME="customer";
public static String APIKEY="apikey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerLayout=(LinearLayout)findViewById(R.id.registerLayout);
        pd=new ProgressDialog(this);
        pd.setCancelable(false);
        forgotpassword=(TextView)findViewById(R.id.forgotpassword);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,forgot_password.class);
                startActivity(i);
            }
        });
    loginBtn=(Button)findViewById(R.id.Loginbutn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  startActivity(new Intent(Login.this,HomeActivity.class));
                pd.show();
                login();

            }
        });
registerLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(Login.this,Sign_up.class));
        finish();
    }
});
    }

    public void login()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                    env.dotNet = false;
                    env.xsd = SoapSerializationEnvelope.XSD;
                    env.enc = SoapSerializationEnvelope.ENC;

                    SoapObject request = new SoapObject(AppConstants.NAMESPACE, "login");

                    request.addProperty("username", USERNAME);
                    request.addProperty("apiKey", APIKEY);

                    env.setOutputSoapObject(request);
                    HttpTransportSE androidHttpTransport = new HttpTransportSE(AppConstants.URL);

                    androidHttpTransport.call("", env);
                    Object result = env.getResponse();

                    Log.d("sessionId", result.toString());
                    AppConstants.token=result.toString();
                    pd.dismiss();
                    startActivity(new Intent(Login.this,HomeActivity.class));


                }catch (SoapFault fault)
                {
                    Log.d("fault",fault.toString());
                    fault.printStackTrace();
                    pd.dismiss();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    pd.dismiss();
                }
            }
        }).start();

    }

}
