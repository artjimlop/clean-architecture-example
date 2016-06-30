package com.example.artjimlop.myapplication.view.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.artjimlop.myapplication.R;
import com.example.artjimlop.myapplication.injector.component.ApplicationComponent;
import com.example.artjimlop.myapplication.injector.component.DaggerComicsComponent;
import com.example.artjimlop.myapplication.injector.module.ActivityModule;
import com.example.artjimlop.myapplication.injector.module.ComicsModule;
import com.example.artjimlop.myapplication.model.ComicModel;
import com.example.artjimlop.myapplication.presenter.ComicsPresenter;
import com.example.artjimlop.myapplication.view.adapter.ComicsAdapter;
import com.example.artjimlop.myapplication.view.listener.OnComicClickedListener;
import com.example.artjimlop.myapplication.view.views.ComicListView;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends BaseActivity implements OnComicClickedListener,
    ComicListView {

  @Bind(R.id.item_list) RecyclerView recyclerView;

  @Inject ComicsPresenter comicsPresenter;

  @Inject
  @Named("character_id")
  int characterId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setUpToolbar(false);

    initializePresenter();
  }

  @Override
  protected void initializeInjector(ApplicationComponent applicationComponent) {
    applicationComponent.inject(this);
    DaggerComicsComponent.builder()
        .applicationComponent(applicationComponent)
        .activityModule(new ActivityModule(this))
        .comicsModule(new ComicsModule()).build().inject(this);
  }

  private void initializePresenter() {
    comicsPresenter.setView(this);
    comicsPresenter.loadComics(characterId);
  }

  @Override
  public void onResume() {
    super.onResume();
    this.comicsPresenter.resume();
  }

  @Override
  public void onPause() {
    super.onPause();
    this.comicsPresenter.pause();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    this.comicsPresenter.destroy();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  @Override
  public void setItems(List<ComicModel> models) {
    ComicsAdapter adapter = new ComicsAdapter(this, models);
    adapter.setListener(this);
    recyclerView.setAdapter(adapter);
  }

  @Override
  public void showMessage() {
    Snackbar.make(recyclerView, R.string.error_request, Snackbar.LENGTH_LONG).show();
  }

  @Override
  public void onComicClicked(ComicModel comicModel) {
    if (comicModel != null) {
      navigator.goToComicDetail(this, comicModel);
    }
  }
}
