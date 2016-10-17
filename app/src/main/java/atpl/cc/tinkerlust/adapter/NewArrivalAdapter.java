package atpl.cc.tinkerlust.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import atpl.cc.tinkerlust.R;
import atpl.cc.tinkerlust.classes.Products;
import atpl.cc.tinkerlust.fragments.NewArrivals;
import atpl.cc.tinkerlust.fragments.ProductPage;

/**
 * Created by vikram
 */

public class NewArrivalAdapter extends RecyclerView.Adapter<NewArrivalAdapter.Holder> {

    Context context;
Fragment fragment;
    ArrayList<Products> al;
    public NewArrivalAdapter(Context context, Fragment fragment,ArrayList<Products>al) {
        this.fragment=fragment;
        this.al=al;
        this.context = context;
    }

    @Override
    public NewArrivalAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_column, parent, false);
        return new NewArrivalAdapter.Holder(itemView);
    }

    @Override
    public void onBindViewHolder(final NewArrivalAdapter.Holder holder, final int position) {

        holder.name.setText(al.get(position).getName());
        holder.type.setText(al.get(position).getType());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Bundle bundle=new Bundle();
        bundle.putString("id",al.get(position).getProduct_id());
        bundle.putString("name",al.get(position).getName());
        bundle.putString("type",al.get(position).getType());
        ProductPage frag=new ProductPage();
        frag.setArguments(bundle);
        fragment.getFragmentManager().beginTransaction().replace(R.id.container,frag).addToBackStack(null).commit();
            }
});
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name,type,price,brandName;

        public Holder(View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.productImg);
            name=(TextView)itemView.findViewById(R.id.name);
            type=(TextView)itemView.findViewById(R.id.type);
            brandName=(TextView)itemView.findViewById(R.id.BrandName);
            price=(TextView)itemView.findViewById(R.id.price);
        }
    }


}
