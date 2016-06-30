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

import android.app.Activity;
import com.example.artjimlop.myapplication.R;
import com.example.artjimlop.myapplication.injector.PerActivity;
import com.example.interactor.GetComicsUseCase;
import com.example.interactor.GetComicsUseCaseImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

@Module
public class ComicsModule {

  @Provides
  @PerActivity
  @Named("character_id")
  int provideCharacterId(Activity activity) {
    return Integer.valueOf(activity.getString(R.string.character_id));
  }

  @Provides
  @PerActivity GetComicsUseCase provideGetComicsUseCase(GetComicsUseCaseImpl getComicsUseCase) {
    return getComicsUseCase;
  }
}
