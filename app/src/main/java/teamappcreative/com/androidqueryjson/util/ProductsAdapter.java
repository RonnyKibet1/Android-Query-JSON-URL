package teamappcreative.com.androidqueryjson.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import teamappcreative.com.androidqueryjson.R;
import teamappcreative.com.androidqueryjson.model.Product;

/**
 * Created by ronnykibet on 3/22/15.
 */
public class ProductsAdapter extends ArrayAdapter<Product> {

    protected Context mContext;
    protected List<Product> mProducts;

    public ProductsAdapter(Context context, List<Product> products) {
        super(context, R.layout.single_row, products);
        mContext = context;
        mProducts = products;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.single_row, null);
                holder = new ViewHolder();

                holder.image = (ImageView) convertView
                        .findViewById(R.id.image);
                holder.title = (TextView) convertView
                        .findViewById(R.id.title);
                holder.description = (TextView) convertView
                        .findViewById(R.id.description);



                convertView.setTag(holder);
            } else {

                holder = (ViewHolder) convertView.getTag();



            }

            try {
                Product product = mProducts.get(position);
                Picasso.with(mContext).load(product.getProductImage()).placeholder(R.mipmap.ic_launcher).into(holder.image);
                holder.title.setText(product.getProductTitle());
                holder.description.setText(product.getProductDescription());


            } catch (Exception e) {
                e.printStackTrace();
            }


        return convertView;
    }

    public static class ViewHolder {
        ImageView image;
        TextView title;
        TextView description;



    }
}
