package cotam_kolego.cookbook.DiscoverPackage;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cotam_kolego.cookbook.R;
import cotam_kolego.cookbook.api.Results;

/**
 * Created by Michał on 21.06.2017.
 * Tworzenie pojedyńczych obiektów Fragmentu, ustawianie obrazka oraz tytulu dla pojedyńczego obiektu.
 */

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolderDiscover>{


    private List<Results> resultss = new ArrayList<>();

    private ListenerCallBack listenerCallBack;



    @Override
    public ViewHolderDiscover onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolderDiscover(layoutInflater.inflate(R.layout.item_discover, parent, false));
    }

    public void setResultses(List<Results> lista) {
        if(lista != null) {
            resultss.addAll(lista);
            notifyDataSetChanged();
        }

    }

    public void setListenerCallBack(ListenerCallBack listenerCallBack) {
        this.listenerCallBack = listenerCallBack;
    }

    @Override
    public void onBindViewHolder(ViewHolderDiscover holder, int position) {
        holder.setRecipes(resultss.get(position));
    }

    @Override
    public int getItemCount() {
        return resultss.size();
    }

    public interface ListenerCallBack{

       void RecipesClicked(Results r);

    }

    private void itemClicked(Results resultRec) {

        if(listenerCallBack != null) listenerCallBack.RecipesClicked(resultRec);

    }

    class ViewHolderDiscover extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imageViewIdDisc)
        ImageView imageViewId;
        @BindView(R.id.textViewIdDisc)
        TextView nameTextView;

        private Results resultRec;

        public ViewHolderDiscover(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setRecipes(Results results) {
            this.resultRec = results;
            Log.d("results", results.imageUrl);

            nameTextView.setText(results.dishName);
            Glide.with(imageViewId.getContext()).load(results.imageUrl).placeholder(R.drawable.placeholder).into(imageViewId);


        }


        @Override
        public void onClick(View v) {
                itemClicked(resultRec);
        }
    }



}

