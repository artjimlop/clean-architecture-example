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

import com.example.artjimlop.data.bean.ComicsResponseDto;
import com.example.artjimlop.data.bean.mapper.ComicsResponseDtoMapper;
import com.example.artjimlop.data.exception.ComicsNotFoundException;
import com.example.artjimlop.data.net.ComicApiService;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitComicDataStore implements ComicDataStore {

    private ComicApiService comicApiService;
    private ComicsResponseDtoMapper comicsResponseDtoMapper;

    @Inject
    public RetrofitComicDataStore(ComicApiService comicApiService, ComicsResponseDtoMapper comicsResponseDtoMapper) {
        this.comicApiService = comicApiService;
        this.comicsResponseDtoMapper =  comicsResponseDtoMapper;
    }

    @Override
    public void getComics(int characterId, final ComicListCallback comicListCallback) {
        Call<ComicsResponseDto> call = comicApiService.getComicsByCharacterId(characterId);
        call.enqueue(new Callback<ComicsResponseDto>() {
            @Override
            public void onResponse(Call<ComicsResponseDto> call, Response<ComicsResponseDto> response) {
                if (response != null) {
                    comicListCallback.onComicListLoaded(comicsResponseDtoMapper.toBusinessObjects(response.body()));
                } else {
                    comicListCallback.onError(new ComicsNotFoundException());
                }
            }

            @Override
            public void onFailure(Call<ComicsResponseDto> call, Throwable t) {
                comicListCallback.onError(new ComicsNotFoundException(t.getMessage()));
            }
        });
    }
}
