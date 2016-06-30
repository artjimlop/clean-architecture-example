package com.example.artjimlop.myapplication.view.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.artjimlop.myapplication.R;
import com.example.artjimlop.myapplication.model.ComicModel;
import com.example.artjimlop.myapplication.view.listener.OnComicClickedListener;
import com.squareup.picasso.Picasso;

public class ComicViewHolder extends RecyclerView.ViewHolder {

  @Bind(R.id.thumbnail)
  ImageView thumbnail;

  @Bind(R.id.title)
  TextView title;

  private View itemView;

  public ComicViewHolder(View itemView) {
    super(itemView);

    ButterKnife.bind(this, itemView);

    this.itemView = itemView;
  }

  public void bind(final ComicModel model, Context context,
      final OnComicClickedListener onComicClickedListener) {
    title.setText(model.getTitle());

    Picasso.with(context).load(model.getThumbnailUrl()).fit().centerCrop().into(
        thumbnail);

    itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (onComicClickedListener != null) {
          onComicClickedListener.onComicClicked(model);
        }
      }
    });
  }
}
