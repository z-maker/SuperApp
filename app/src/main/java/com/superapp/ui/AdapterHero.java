package com.superapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.superapp.R;
import com.superapp.api.model.HeroModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XNGEL
 * @Date: 04/10/21
 */
public class AdapterHero extends RecyclerView.Adapter<AdapterHero.HeroViewHolder> {

    private OnHeroSelectedListener selectedListener;

    private List<HeroModel> heroModelList = new ArrayList<>();

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hero,parent,false);

        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {
        holder.bind(heroModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return heroModelList.size();
    }

    public void setSelectedListener(OnHeroSelectedListener selectedListener) {
        this.selectedListener = selectedListener;
    }

    public void setHeroModelList(List<HeroModel> heroModelList) {
        this.heroModelList = heroModelList;
        notifyDataSetChanged();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName;
        private ImageView imgHero;

        public HeroViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            imgHero = itemView.findViewById(R.id.imgHero);
            itemView.setOnClickListener(view -> {
                selectedListener.onHeroSelected(heroModelList.get(getAdapterPosition()));
            });
        }

        public void bind(HeroModel model){
            txtName.setText(model.getName());
            Glide.with(imgHero).load(model.getImage().getUrl())
                    .placeholder(R.drawable.ic_baseline_hourglass_top_24)
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .into(imgHero);
        }
    }

    public interface OnHeroSelectedListener{
        void onHeroSelected(HeroModel item);
    }

}
