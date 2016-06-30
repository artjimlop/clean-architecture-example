package com.example.artjimlop.myapplication.presenter;

import com.example.artjimlop.myapplication.injector.PerActivity;
import com.example.artjimlop.myapplication.model.ComicModel;
import com.example.artjimlop.myapplication.view.views.ComicDetailView;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;

@PerActivity
public class ComicDetailPresenter implements Presenter {

  private static final int MIN_POSITION = 0;
  private ComicDetailView view;

  @Inject public ComicDetailPresenter() {
  }

  public void setView(ComicDetailView view) {
    this.view = view;
  }

  public void loadRandomImage(ComicModel model) {
    if (model != null) {
      List<String> imageUrls = model.getImageUrls();
      if (imageUrls != null && !imageUrls.isEmpty()) {
        String imageUrl = imageUrls.get(getRandomPosition(imageUrls.size() - 1));
        view.showImage(imageUrl);
      }
    }
  }

  private int getRandomPosition(int maxPosition) {
    Random random = new Random();
    return random.nextInt(maxPosition - MIN_POSITION + 1) + MIN_POSITION;
  }

  @Override public void resume() {

  }

  @Override public void pause() {

  }

  @Override public void destroy() {

  }
}
