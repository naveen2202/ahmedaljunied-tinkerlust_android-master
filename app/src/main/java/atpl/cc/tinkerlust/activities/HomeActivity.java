package atpl.cc.tinkerlust.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import atpl.cc.tinkerlust.R;
import atpl.cc.tinkerlust.adapter.SubmenuAdapter;
import atpl.cc.tinkerlust.classes.AppConstants;
import atpl.cc.tinkerlust.fragments.MainFragment;
import atpl.cc.tinkerlust.fragments.Seller_Profile;

public class HomeActivity extends AppCompatActivity {
    ActionBarDrawerToggle mDrawerToggle;
    public DrawerLayout mDrawerLayout;
   public android.support.v7.widget.Toolbar toolbar;
   public TextView title,titleRight;
    public LinearLayout drawerList,drawerListRight,myAccount;
    FrameLayout bagLayout,container;
    public ListView submenu,submenuRight,filterList;
    FragmentManager fm;
ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.activity_home);
        drawerList=(LinearLayout)findViewById(R.id.drawerLayout);
        pd=new ProgressDialog(this);
        pd.setCancelable(false);
        drawerListRight=(LinearLayout)findViewById(R.id.drawerLayoutRight);
        myAccount=(LinearLayout)findViewById(R.id.myAccountSettings);
        bagLayout=(FrameLayout)findViewById(R.id.bagLayout);
        container=(FrameLayout)findViewById(R.id.container);
        title=(TextView)findViewById(R.id.subMenuTitle);
        titleRight=(TextView)findViewById(R.id.subMenuTitleRight);
        submenu=(ListView)findViewById(R.id.submenuList);
        filterList=(ListView)findViewById(R.id.filterList);
        submenuRight=(ListView)findViewById(R.id.submenuListRight);
        toolbar=(android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        String submenuForBags[]=new String[]{"bagpack","clutch","handbag","luggage","pouch","satchel","sling bag","shoulder bag","tote bag"};
        submenu.setAdapter(new SubmenuAdapter(this,submenuForBags));
        setSupportActionBar(toolbar);
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, mDrawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        {

            //            / Called when drawer is closed /
            public void onDrawerClosed(View view)
            {
//                //Put your code here
                mDrawerToggle.syncState();
            }

            //            / Called when a drawer is opened /
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);

            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        title.setVisibility(View.GONE);
        submenu.setVisibility(View.GONE);

        bagLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        title.setText("BAG");
        submenu.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
       //drawerList.setVisibility(View.GONE);

     }
        });
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         //       drawerList.setVisibility(View.VISIBLE);
                submenu.setVisibility(View.GONE);
                title.setVisibility(View.GONE);
            }
        });

        fm=getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, new MainFragment()).commit();


        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.closeDrawers();
                fm=getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.container, new Seller_Profile()).commit();

            }
        });
//getProductList();
   // getProductInfo();
    createShoppingCart();
    }

  /*  public void getProductList()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                    env.dotNet = false;
                    env.xsd = SoapSerializationEnvelope.XSD;
                    env.enc = SoapSerializationEnvelope.ENC;

                    SoapObject request = new SoapObject(AppConstants.NAMESPACE, "catalogProductList");

                    request.addProperty("sessionId",AppConstants.token);
                    env.setOutputSoapObject(request);

                    HttpTransportSE androidHttpTransport = new HttpTransportSE(AppConstants.URL);

                    androidHttpTransport.call("", env);
                    Object result = env.getResponse();

                    Log.d("productList", result.toString());


                }catch (SoapFault fault)
                {
                    Log.d("fault",fault.toString());
                    fault.printStackTrace();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }*/



    public void createShoppingCart()
    {
        pd.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                    env.dotNet = false;
                    env.xsd = SoapSerializationEnvelope.XSD;
                    env.enc = SoapSerializationEnvelope.ENC;

                    SoapObject request = new SoapObject(AppConstants.NAMESPACE, "shoppingCartCreate");

                    request.addProperty("sessionId",AppConstants.token);
                    request.addProperty("storeId","1");
                    env.setOutputSoapObject(request);

                    HttpTransportSE androidHttpTransport = new HttpTransportSE(AppConstants.URL);

                    androidHttpTransport.call("", env);
                    Object result = env.getResponse();

                    Log.d("shoppingCartCreate", result.toString());
                    AppConstants.ShoppingCartID=result.toString();
                    pd.dismiss();

                  /*  addProductToCart("6");
                    addProductToCart("4");
*/

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






    public void listProductsInCart()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                    env.dotNet = false;
                    env.xsd = SoapSerializationEnvelope.XSD;
                    env.enc = SoapSerializationEnvelope.ENC;

                    SoapObject request = new SoapObject(AppConstants.NAMESPACE, "shoppingCartProductList");

                    request.addProperty("sessionId",AppConstants.token);
                    request.addProperty("quoteId",AppConstants.ShoppingCartID);
                    request.addProperty("storeId","1");
                    env.setOutputSoapObject(request);

                    HttpTransportSE androidHttpTransport = new HttpTransportSE(AppConstants.URL);

                    androidHttpTransport.call("", env);
                    Object result = env.getResponse();

                    Log.d("shoppingCartProductList", result.toString());

                  SoapObject object=(SoapObject) result;
                   SoapObject item=(SoapObject) object.getProperty(0);
                  String id= item.getProperty(0).toString();
                   Log.d("values",id);


                }catch (SoapFault fault)
                {
                    Log.d("fault",fault.toString());
                    fault.printStackTrace();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }



}
