package com.example.sbitar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sbitar.Model.Maladie;
import com.example.sbitar.R;

import java.util.ArrayList;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleHolder> {
    Context context ;
    ArrayList<Maladie> maladies;
    MyClickInterface myClickInterface;

    public ArticlesAdapter(Context context, ArrayList<Maladie> maladies, MyClickInterface myClickInterface) {
        this.context = context;
        this.maladies = maladies;
        this.myClickInterface = myClickInterface;
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.article_holder , parent , false);
        return new ArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        holder.title.setText(maladies.get(position).getTitle());
        holder.description.setText(maladies.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return maladies.size();
    }

    public class ArticleHolder extends RecyclerView.ViewHolder {

        TextView title , description  ;

        public ArticleHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.article_title);
            description = itemView.findViewById(R.id.article_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myClickInterface.onItemClicked(itemView , getAdapterPosition());
                }
            });

        }
    }

    public interface MyClickInterface{

        void onItemClicked(View view , int position);
    }
}
