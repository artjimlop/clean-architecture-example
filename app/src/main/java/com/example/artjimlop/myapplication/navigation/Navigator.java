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
package com.example.artjimlop.myapplication.navigation;

import android.app.Activity;
import android.content.Intent;
import com.example.artjimlop.myapplication.model.ComicModel;
import com.example.artjimlop.myapplication.view.activity.ComicDetailActivity;
import com.example.artjimlop.myapplication.view.activity.MainActivity;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void goToList(Activity activity) {
        activity.navigateUpTo(new Intent(activity, MainActivity.class));
    }

    public void goToComicDetail(Activity activity, ComicModel comicModel) {
        if (activity != null) {
            Intent intentToLaunch = ComicDetailActivity.getCallingIntent(activity, comicModel);
            activity.startActivity(intentToLaunch);
        }
    }
}
