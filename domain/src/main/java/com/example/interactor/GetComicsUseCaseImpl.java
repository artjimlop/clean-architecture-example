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
package com.example.interactor;

import com.example.bean.Comic;
import com.example.callback.ComicListCallback;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.ComicsRepository;
import java.util.Collection;
import javax.inject.Inject;

public class GetComicsUseCaseImpl implements GetComicsUseCase {

    private final ComicsRepository comicsRepository;
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private int characterId;
    private Callback callback;

    @Inject
    public GetComicsUseCaseImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
            ComicsRepository comicsRepository) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.comicsRepository = comicsRepository;
    }

    @Override
    public void execute(int characterId, Callback callback) {
        this.characterId = characterId;
        this.callback = callback;
        this.threadExecutor.execute(this);
    }

    @Override
    public void run() {
        this.comicsRepository.getComics(characterId, new ComicListCallback() {
            @Override
            public void onComicsLoaded(Collection<Comic> comicCollection) {
                notifyLoaded(comicCollection);
            }

            @Override
            public void onError(ErrorBundle errorBundle) {
                notifyError(errorBundle);
            }
        });
    }

    private void notifyLoaded(final Collection<Comic> comicsCollection) {
        this.postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onComicListLoaded(comicsCollection);
            }
        });
    }

    private void notifyError(final ErrorBundle errorBundle) {
        this.postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(errorBundle);
            }
        });
    }
}
