package cotam_kolego.cookbook.MainPackage;

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
import cotam_kolego.cookbook.DiscoverPackage.DiscoverAdapter;
import cotam_kolego.cookbook.R;
import cotam_kolego.cookbook.api.Results;

/**
 * Created by Michał on 25.06.2017.
 */

public class UserSessionAdapter extends RecyclerView.Adapter<UserSessionAdapter.ViewHolderSessions> {



    private List<Results> resultss = new ArrayList<>();

private SessionCallback sessionCallback;


    @Override
    public ViewHolderSessions onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolderSessions(layoutInflater.inflate(R.layout.item_user_session, parent, false));
    }

    public void setResultses(List<Results> lista) {
        if (lista != null) {
            Log.d("cotam", lista.toString());
            resultss.addAll(lista);
            notifyDataSetChanged();
        }

    }


    @Override
    public void onBindViewHolder(ViewHolderSessions holder, int position) {

        holder.setRecipes(resultss.get(position));
    }

    public void setSessionCallback(SessionCallback listenerCallBack) {

        this.sessionCallback = listenerCallBack;
    }

    @Override
    public int getItemCount() {
        return resultss.size();
    }

    public interface ListenerCallBack {

        void RecipesClicked(Results r);

    }

    public interface SessionCallback{

        void RecipesClicked(Results r);

    }

    private void itemClicked(Results resultRec) {

        if(sessionCallback != null) sessionCallback.RecipesClicked(resultRec);

    }


    class ViewHolderSessions extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.UserSessionImageView)
        ImageView UserSessionImageView;
        @BindView(R.id.UserSessionTextView)
        TextView UserSessionTextView;

        private Results resultRec;

        public ViewHolderSessions(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setRecipes(Results results) {
            this.resultRec = results;
            UserSessionTextView.setText(results.dishName);
            Glide.with(UserSessionImageView.getContext()).load(results.imageUrl).placeholder(R.drawable.placeholder).into(UserSessionImageView);

        }

        @Override
        public void onClick(View v) { itemClicked(resultRec);

        }
    }




}
