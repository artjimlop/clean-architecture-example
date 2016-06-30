/**
 * Copyright (C) 2016 Arturo Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.artjimlop.myapplication.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.artjimlop.myapplication.R;
import com.example.artjimlop.myapplication.model.ComicModel;
import com.example.artjimlop.myapplication.view.holder.ComicViewHolder;
import com.example.artjimlop.myapplication.view.listener.OnComicClickedListener;
import java.util.List;

public class ComicsAdapter extends RecyclerView.Adapter<ComicViewHolder> {

    private Context context;
    private List<ComicModel> items;
    private OnComicClickedListener onComicClickedListener;

    public ComicsAdapter(Context context, List<ComicModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_list_content, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComicViewHolder holder, int position) {
        final ComicModel model = items.get(position);
        holder.bind(model, context, onComicClickedListener);
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    public void setListener(OnComicClickedListener listener) {
        this.onComicClickedListener = listener;
    }
}
