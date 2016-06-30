package com.example.callback;

import com.example.bean.Comic;
import com.example.exception.ErrorBundle;
import java.util.Collection;

public interface ComicListCallback {
  void onComicsLoaded(Collection<Comic> comicCollection);

  void onError(ErrorBundle errorBundle);
}
