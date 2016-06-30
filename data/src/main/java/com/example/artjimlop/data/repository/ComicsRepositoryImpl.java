/**
 * Copyright (C) 2016 Arturo Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.artjimlop.data.repository;

import com.example.artjimlop.data.exception.RepositoryErrorBundle;
import com.example.artjimlop.data.repository.datasource.ComicDataStore;
import com.example.artjimlop.data.repository.datasource.ComicDataStoreFactory;
import com.example.bean.Comic;
import com.example.callback.ComicListCallback;
import com.example.repository.ComicsRepository;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ComicsRepositoryImpl implements ComicsRepository {

    private final ComicDataStoreFactory comicDataStoreFactory;

    @Inject
    public ComicsRepositoryImpl(ComicDataStoreFactory comicDataStoreFactory) {
        this.comicDataStoreFactory = comicDataStoreFactory;
    }

    @Override
    public void getComics(final int characterId,
        final ComicListCallback comicListCallback) {
        ComicDataStore remoteComicDataStore = comicDataStoreFactory.create();
        remoteComicDataStore.getComics(characterId, new ComicDataStore.ComicListCallback() {
            @Override
            public void onComicListLoaded(Collection<Comic> comicCollection) {
                comicListCallback.onComicsLoaded(comicCollection);
            }

            @Override
            public void onError(Exception exception) {
                comicListCallback.onError(new RepositoryErrorBundle(exception));
            }
        });
    }
}
