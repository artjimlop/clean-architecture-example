package com.example.artjimlop.myapplication.presenter;

import com.example.artjimlop.myapplication.model.mapper.ComicModelMapper;
import com.example.artjimlop.myapplication.view.views.ComicListView;
import com.example.bean.Comic;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetComicsUseCase;
import java.util.Collection;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class ComicsPresenterTest {

  public static final int CHARACTER_ID = 0;
  @Mock GetComicsUseCase getComicsUseCase;
  @Mock ComicListView comicListView;
  private ComicsPresenter comicsPresenter;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    ComicModelMapper comicModelMapper = new ComicModelMapper();
    comicsPresenter = new ComicsPresenter(getComicsUseCase, comicModelMapper);
    comicsPresenter.setView(comicListView);
  }

  @Test
  public void shouldSetComicsInViewWhenLoaded() throws Exception {
    setupGetComicsCallback();

    comicsPresenter.loadComics(CHARACTER_ID);

    verify(comicListView).setItems(anyList());
  }

  @Test
  public void shouldShowErrorInViewWhenErrorCallback() throws Exception {
    setupGetComicsErrorCallback();

    comicsPresenter.loadComics(CHARACTER_ID);

    verify(comicListView).showMessage();
  }

  private void setupGetComicsCallback() {
    doAnswer(new Answer() {
      @Override public Object answer(InvocationOnMock invocation) throws Throwable {
        GetComicsUseCase.Callback callback =
            (GetComicsUseCase.Callback) invocation.getArguments()[1];
        callback.onComicListLoaded(comics());
        return null;
      }
    }).when(getComicsUseCase).execute(anyInt(), any(GetComicsUseCase.Callback.class));
  }

  private void setupGetComicsErrorCallback() {
    doAnswer(new Answer() {
      @Override public Object answer(InvocationOnMock invocation) throws Throwable {
        GetComicsUseCase.Callback callback =
            (GetComicsUseCase.Callback) invocation.getArguments()[1];
        callback.onError(new ErrorBundle() {
          @Override public Exception getException() {
            return null;
          }

          @Override public String getErrorMessage() {
            return null;
          }
        });
        return null;
      }
    }).when(getComicsUseCase).execute(anyInt(), any(GetComicsUseCase.Callback.class));
  }

  private Collection<Comic> comics() {
    Comic comic = new Comic();
    comic.setDescription("description");
    comic.setId(1);
    comic.setImageUrls(Collections.<String>emptyList());
    comic.setPageCount(1);
    comic.setThumbnailUrl("thumb");
    comic.setTitle("title");
    return Collections.singletonList(comic);
  }
}