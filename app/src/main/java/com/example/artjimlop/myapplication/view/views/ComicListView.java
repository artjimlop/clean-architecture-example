package com.example.artjimlop.myapplication.view.views;

import com.example.artjimlop.myapplication.model.ComicModel;
import java.util.List;

public interface ComicListView {
  void setItems(List<ComicModel> models);

  void showMessage();
}
