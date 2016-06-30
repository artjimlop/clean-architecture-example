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
package com.example.artjimlop.myapplication.presenter;

import android.support.annotation.NonNull;
import com.example.artjimlop.myapplication.injector.PerActivity;
import com.example.artjimlop.myapplication.model.ComicModel;
import com.example.artjimlop.myapplication.model.mapper.ComicModelMapper;
import com.example.artjimlop.myapplication.view.views.ComicListView;
import com.example.bean.Comic;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetComicsUseCase;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

@PerActivity
public class ComicsPresenter implements Presenter {

    private ComicListView view;
    private final GetComicsUseCase getComicsUseCase;
    private final ComicModelMapper comicModelMapper;
    private List<ComicModel> models;

    @Inject
    public ComicsPresenter(GetComicsUseCase getComicsUseCase, ComicModelMapper comicModelMapper) {
        this.getComicsUseCase = getComicsUseCase;
        this.comicModelMapper = comicModelMapper;
    }

    public void setView(@NonNull ComicListView mainActivity) {
        this.view = mainActivity;
    }

    public void loadComics(int characterId) {
        getComicsUseCase.execute(characterId, new GetComicsUseCase.Callback() {
            @Override
            public void onComicListLoaded(Collection<Comic> comics) {
                models = comicModelMapper.toModel(comics);
                setModels();
            }

            @Override
            public void onError(ErrorBundle errorBundle) {
                showMessage();
            }
        });
    }

    private void setModels() {
        view.setItems(models);
    }

    private void showMessage() {
        view.showMessage();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
