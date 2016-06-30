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
package com.example.artjimlop.myapplication.injector.module;

import android.content.Context;
import com.example.artjimlop.data.executor.JobExecutor;
import com.example.artjimlop.data.net.ApiConstants;
import com.example.artjimlop.data.net.ComicApiService;
import com.example.artjimlop.data.net.interceptor.AuthInterceptor;
import com.example.artjimlop.data.repository.ComicsRepositoryImpl;
import com.example.artjimlop.data.repository.datasource.ComicDataStore;
import com.example.artjimlop.data.repository.datasource.RetrofitComicDataStore;
import com.example.artjimlop.myapplication.AndroidApplication;
import com.example.artjimlop.myapplication.R;
import com.example.artjimlop.myapplication.UIThread;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.ComicsRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton ComicsRepository provideComicsRepository(ComicsRepositoryImpl comicsRepository) {
        return comicsRepository;
    }

    @Provides
    @Singleton
    ComicDataStore provideRetrofitComicDataStore(RetrofitComicDataStore retrofitComicDataStore) {
        return retrofitComicDataStore;
    }

    @Provides
    @Singleton
    @Named("public_key")
    String providePublicKey() {
        return application.getString(R.string.public_key);
    }

    @Provides
    @Singleton
    @Named("private_key")
    String providePrivateKey() {
        return application.getString(R.string.private_key);
    }

    @Provides
    @Singleton ComicApiService provideComicApiService(AuthInterceptor authInterceptor) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(authInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ComicApiService.class);
    }
}
