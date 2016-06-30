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
package com.example.artjimlop.myapplication.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import com.example.artjimlop.myapplication.AndroidApplication;
import com.example.artjimlop.myapplication.R;
import com.example.artjimlop.myapplication.injector.component.ApplicationComponent;
import com.example.artjimlop.myapplication.navigation.Navigator;
import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject Navigator navigator;

    @Nullable
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeInjector(getApplicationComponent());
    }

    protected void setUpToolbar(boolean showUpButton) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(showUpButton);
            }
        }
    }

    private ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication)getApplication()).getComponent();
    }

    protected abstract void initializeInjector(ApplicationComponent applicationComponent);
}
