package com.example.interactor;

import com.example.callback.ComicListCallback;
import com.example.executor.PostExecutionThread;
import com.example.executor.TestPostExecutionThread;
import com.example.executor.TestThreadExecutor;
import com.example.executor.ThreadExecutor;
import com.example.repository.ComicsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

public class GetComicsUseCaseImplTest {

  public static final int CHARACTER_ID = 0;
  @Mock ComicsRepository comicsRepository;
  @Mock GetComicsUseCase.Callback callback;
  private GetComicsUseCaseImpl interactor;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    PostExecutionThread testPostExecutionThread = new TestPostExecutionThread();
    ThreadExecutor testThreadExecutor = new TestThreadExecutor();
    interactor = new GetComicsUseCaseImpl(testThreadExecutor, testPostExecutionThread, comicsRepository);
  }

  @Test
  public void shouldGetComicsFromRepository() throws Exception {
    interactor.execute(CHARACTER_ID, callback);

    verify(comicsRepository).getComics(anyInt(), any(
        ComicListCallback.class));
  }
}