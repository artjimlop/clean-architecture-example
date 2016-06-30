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
package com.example.artjimlop.data.repository.datasource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ComicDataStoreFactory {

    private final ComicDataStore retrofitComicDataStore;

    @Inject
    public ComicDataStoreFactory(ComicDataStore retrofitComicDataStore) {
        this.retrofitComicDataStore = retrofitComicDataStore;
    }

    public ComicDataStore create() {
        ComicDataStore comicDataStore;
        comicDataStore = createCloudDataStore();
        return comicDataStore;
    }

    private ComicDataStore createCloudDataStore() {
        return retrofitComicDataStore;
    }

}
