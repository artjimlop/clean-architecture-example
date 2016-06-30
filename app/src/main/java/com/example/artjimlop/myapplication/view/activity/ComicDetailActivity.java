package com.example.artjimlop.myapplication.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.artjimlop.myapplication.R;
import com.example.artjimlop.myapplication.injector.component.ApplicationComponent;
import com.example.artjimlop.myapplication.injector.component.DaggerComicsComponent;
import com.example.artjimlop.myapplication.injector.module.ActivityModule;
import com.example.artjimlop.myapplication.injector.module.ComicsModule;
import com.example.artjimlop.myapplication.model.ComicModel;
import com.example.artjimlop.myapplication.presenter.ComicDetailPresenter;
import com.example.artjimlop.myapplication.view.views.ComicDetailView;
import com.google.common.base.Strings;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

public class ComicDetailActivity extends BaseActivity implements ComicDetailView {

  private static final String EXTRA_COMIC = "EXTRA_COMIC";

  @Bind(R.id.comic_image)
  ImageView comicImage;

  @Bind(R.id.comic_title)
  TextView comicTitle;

  @Bind(R.id.comic_description)
  TextView comicDescription;

  @Inject ComicDetailPresenter comicDetailPresenter;

  public static Intent getCallingIntent(Context context, ComicModel comicModel) {
    Intent callingIntent = new Intent(context, ComicDetailActivity.class);
    callingIntent.putExtra(EXTRA_COMIC, comicModel);
    return callingIntent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_comic_detail);
    ButterKnife.bind(this);

    setUpToolbar(true);

    initializePresenter();

    ComicModel comicModel = (ComicModel) getIntent().getSerializableExtra(EXTRA_COMIC);
    setupComicInfo(comicModel);
  }

  @Override
  protected void initializeInjector(ApplicationComponent applicationComponent) {
    applicationComponent.inject(this);
    DaggerComicsComponent.builder()
        .applicationComponent(applicationComponent)
        .activityModule(new ActivityModule(this))
        .comicsModule(new ComicsModule()).build().inject(this);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      navigator.goToList(this);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void initializePresenter() {
    comicDetailPresenter.setView(this);
  }

  private void setupComicInfo(ComicModel comicModel) {
    if (comicModel != null) {
      String title = comicModel.getTitle();
      setupTitle(title);

      String description = comicModel.getDescription();
      setupDescription(description);

      comicDetailPresenter.loadRandomImage(comicModel);

      setupAppBar(title);
    } else {
      comicTitle.setText(getString(R.string.error_no_title));
      comicDescription.setText(getString(R.string.error_comic));
    }
  }

  private void setupAppBar(String title) {
    CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
    if (appBarLayout != null) {
      appBarLayout.setTitle(title);
      appBarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    }
  }

  private void setupDescription(String description) {
    if (Strings.isNullOrEmpty(description)) {
      description = getString(R.string.error_no_description);
    }
    comicDescription.setText(Html.fromHtml(description));
  }

  private void setupTitle(String title) {
    if (Strings.isNullOrEmpty(title)) {
      title = getString(R.string.error_no_title);
    }
    comicTitle.setText(title);
  }

  @Override public void showImage(String imageUrl) {
    Picasso.with(this).load(imageUrl)
        .fit().centerCrop().into(this.comicImage);
  }
}
