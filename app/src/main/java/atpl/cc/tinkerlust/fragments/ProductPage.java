package atpl.cc.tinkerlust.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import atpl.cc.tinkerlust.R;
import atpl.cc.tinkerlust.activities.First_page;
import atpl.cc.tinkerlust.adapter.ImageAdapter;
import atpl.cc.tinkerlust.classes.AppConstants;
import atpl.cc.tinkerlust.classes.SingleProduct;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductPage extends Fragment {

    ViewPager Scrollimage,pager;
    TabLayout tablayout;
    LinearLayout shareLayout;
    ImageView imageview1,imageview2,imageview3,imageview4,share;
    int Array[]={R.drawable.blazzer,R.drawable.blazzer,R.drawable.blazzer,R.drawable.blazzer};
ScrollView scrollView;
    final int Set_Details=111;
    String id="",type="",nameT="";
    TextView name,shortDesc,condition,availability,buy,price;

    SingleProduct singleProduct;
    Description description;
ProgressDialog pd;
    public ProductPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_product_page, container, false);

           id=  getArguments().getString("id");
           nameT=  getArguments().getString("name");
           type=  getArguments().getString("type");

        shareLayout=(LinearLayout)view.findViewById(R.id.shareLayout);
        Scrollimage=(ViewPager)view.findViewById(R.id.productImagePager);
        scrollView=(ScrollView)view.findViewById(R.id.scrollview);
        share=(ImageView)view.findViewById(R.id.share);
        name=(TextView)view.findViewById(R.id.name);
        buy=(TextView)view.findViewById(R.id.buy);
        price=(TextView)view.findViewById(R.id.price);
        shortDesc=(TextView)view.findViewById(R.id.shortDescription);
        availability=(TextView)view.findViewById(R.id.Availability);
        condition=(TextView)view.findViewById(R.id.condition);
        name.setText(nameT);
        condition.setText(type);
        pd=new ProgressDialog(getActivity());
        pd.setCancelable(false);
        description=new Description();
        imageview1=(ImageView)view.findViewById(R.id.imageview1);
        imageview2=(ImageView)view.findViewById(R.id.imageview2);
        imageview3=(ImageView)view.findViewById(R.id.imageview3);
        imageview4=(ImageView)view.findViewById(R.id.imageview4);
        tablayout = (TabLayout)view.findViewById(R.id.tab_layoutProductDetail);
        pager = (ViewPager)view.findViewById(R.id.pagerProductDetails);
        TabLayout.Tab tab1 =tablayout.newTab().setText("DESCRIPTION");
        TabLayout.Tab tab2 =tablayout.newTab().setText("COMMENT");
        TabLayout.Tab tab3 =tablayout.newTab().setText("SHIPPING");
        TabLayout.Tab tab4 =tablayout.newTab().setText("PAYMENT");
        tablayout.addTab(tab1);
        tablayout.addTab(tab2);
        tablayout.addTab(tab3);
        tablayout.addTab(tab4);
        ProductPage.EditPagerAdapter adapter = new ProductPage.EditPagerAdapter((getActivity()).getSupportFragmentManager(), tablayout.getTabCount());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
       tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        Scrollimage.setAdapter(new ImageAdapter(getActivity(),Array));
        Scrollimage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        imageview1.setImageResource(R.drawable.whitecircle);
                        imageview2.setImageResource(R.drawable.lightcircleimage);
                        imageview3.setImageResource(R.drawable.lightcircleimage);
                        imageview4.setImageResource(R.drawable.lightcircleimage);
                        break;
                    case 1:
                        imageview1.setImageResource(R.drawable.lightcircleimage);
                        imageview2.setImageResource(R.drawable.whitecircle);
                        imageview3.setImageResource(R.drawable.lightcircleimage);
                        imageview4.setImageResource(R.drawable.lightcircleimage);
                        break;
                    case 2:
                        imageview1.setImageResource(R.drawable.lightcircleimage);
                        imageview2.setImageResource(R.drawable.lightcircleimage);
                        imageview3.setImageResource(R.drawable.whitecircle);
                        imageview4.setImageResource(R.drawable.lightcircleimage);
                        break;
                    case 3:
                        imageview1.setImageResource(R.drawable.lightcircleimage);
                        imageview2.setImageResource(R.drawable.lightcircleimage);
                        imageview3.setImageResource(R.drawable.lightcircleimage);
                        imageview4.setImageResource(R.drawable.whitecircle);
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShareLayout();
            }
        });

        shareLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideShareLayout();
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
addProductToCart(id);
                pd.setMessage("Adding product to cart..");
            }
        });

        getProductInfo(id);

        return  view;
    }

    public class EditPagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs=0;

        public EditPagerAdapter( android.support.v4.app.FragmentManager fm, int NumOfTabs)
        {
            super(fm);
            //   super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {


            switch (position) {
                case 0:

                    return description;
                case 1:
                    Description tab2=new Description();
                    return tab2;
                case 2:

                    Description tab3=new Description();
                    return tab3;
                case 3:

                    Description tab4=new Description();
                    return tab4;
                default:
                    return null;
            }
        }

        @Override
        public int getCount ()
        {
            return mNumOfTabs;
        }
    }

    public void showShareLayout()
    {
        shareLayout.setVisibility(View.VISIBLE);
    }
    public void hideShareLayout()
    {
        shareLayout.setVisibility(View.GONE);
    }

    public void getProductInfo(final String id)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                    env.dotNet = false;
                    env.xsd = SoapSerializationEnvelope.XSD;
                    env.enc = SoapSerializationEnvelope.ENC;

                    SoapObject request = new SoapObject(AppConstants.NAMESPACE, "catalogProductInfo");

                    request.addProperty("sessionId",AppConstants.token);
                    request.addProperty("productId",id);
                    env.setOutputSoapObject(request);

                    HttpTransportSE androidHttpTransport = new HttpTransportSE(AppConstants.URL,7*10000);

                    androidHttpTransport.call("", env);
                    Object result = env.getResponse();

                    Log.d("productInfo", result.toString());
                    SoapObject object=(SoapObject)result;
String created_at=object.getProperty("created_at").toString();
String updated_at=object.getProperty("updated_at").toString();
String description=object.getProperty("description").toString();
String short_description=object.getProperty("short_description").toString();
String weight=object.getProperty("weight").toString();
String status=object.getProperty("status").toString();
String url_key=object.getProperty("url_key").toString();
String url_path=object.getProperty("url_path").toString();
String visibility=object.getProperty("visibility").toString();
String price=object.getProperty("price").toString();
String has_options=object.getProperty("has_options").toString();
String tax_class_id=object.getProperty("tax_class_id").toString();
String options_container=object.getProperty("options_container").toString();

 singleProduct=new SingleProduct(price,status,visibility,url_key,url_path,weight,options_container,has_options,tax_class_id,created_at,updated_at,description,short_description);
                    Message msg = handler.obtainMessage();
                    msg.what = Set_Details;
                    handler.sendMessage(msg);

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

    public void addProductToCart(final String productId)
    {   pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                    env.dotNet = false;
                    env.xsd = SoapSerializationEnvelope.XSD;
                    env.enc = SoapSerializationEnvelope.ENC;

                    SoapObject request = new SoapObject(AppConstants.NAMESPACE, "shoppingCartProductAdd");

                    request.addProperty("sessionId",AppConstants.token);
                    request.addProperty("quoteId",AppConstants.ShoppingCartID);

                    SoapObject  productentity=new SoapObject(AppConstants.NAMESPACE,"shoppingCartProductEntity");
                    productentity.addProperty("product_id",productId);
                    productentity.addProperty("qty","1");

                    SoapObject  productentityArray=new SoapObject(AppConstants.NAMESPACE,"shoppingCartProductEntityArray");
                    productentityArray.addProperty("productData",productentity);

                    request.addProperty("products",productentityArray);

                    env.setOutputSoapObject(request);

                    HttpTransportSE androidHttpTransport = new HttpTransportSE(AppConstants.URL);

                    androidHttpTransport.call("", env);
                    Object result = env.getResponse();

                    Log.d("shoppingCartProductAdd", result.toString());
                    if(result.toString().equalsIgnoreCase("true"))
                    {  Log.d("calling", "list function");
                    }
                    else
                    {
                        Log.d("calling", "list function in not true condition"+ result.toString()+"true");
                    }
pd.dismiss();
                }catch (SoapFault fault)
                {
                    Log.d("fault",fault.toString());
             pd.dismiss();
                    fault.printStackTrace();
                }
                catch (Exception e) {
                    e.printStackTrace();
                pd.dismiss();
                }
            }
        }).start();

    }






    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==Set_Details){
                shortDesc.setText(singleProduct.getShort_description());
                price.setText(singleProduct.getPrice());
                description.setDescription(singleProduct.getDescription());
            }
            super.handleMessage(msg);
        }
    };


}
