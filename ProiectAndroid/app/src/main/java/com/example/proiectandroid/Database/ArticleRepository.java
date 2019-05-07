package com.example.proiectandroid.Database;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ArticleRepository {
    private AppDatabase appDatabase;

    public ArticleRepository(Context context) {
        appDatabase = ApplicationController.getAppDatabase();
    }

    public void insertTask(final Article article,
                           final OnRepositoryActionListener listener)   {
        new InsertTask(listener).execute(article);
    }

    public void removeTask(final Article article,
                           final OnRepositoryActionListener listener)   {
        new RemoveTask(listener).execute(article);
    }

    public List<Article> displayAllTask(final OnRepositoryActionListener listener)  {
        try {
            return new DisplayAllTask(listener).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Article> getArticlesByTitle(String title,final OnRepositoryActionListener listener){
        try {
            return new SearchTask(listener).execute(title).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Article> getArticlesByAuthor(int authorId,final OnRepositoryActionListener listener){
        try {
            return new GetAuthorsTask(listener).execute(authorId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Article getArticleById(int articleId,final OnRepositoryActionListener listener){
        try {
            return new GetArticleByIdTask(listener).execute(articleId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    private class InsertTask extends AsyncTask<Article, Void, Void> {
        OnRepositoryActionListener listener;
        InsertTask(OnRepositoryActionListener listener) {
            this.listener = listener;
        }
        @Override
        protected Void doInBackground(Article... articles) {
            appDatabase.articleDao().insert(articles[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.actionSuccess();
        }
    }

    public void updateArticle(final Article article,
                           final OnRepositoryActionListener listener)   {
        new UpdateTask(listener).execute(article);
    }

    private class RemoveTask extends AsyncTask<Article, Void, Void> {
        OnRepositoryActionListener listener;
        RemoveTask(OnRepositoryActionListener listener) {
            this.listener = listener;
        }
        @Override
        protected Void doInBackground(Article... articles) {
            appDatabase.articleDao().delete(articles[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.actionSuccess();
        }
    }

    private class UpdateTask extends AsyncTask<Article, Void, Void> {
        OnRepositoryActionListener listener;
        UpdateTask(OnRepositoryActionListener listener) {
            this.listener = listener;
        }
        @Override
        protected Void doInBackground(Article... articles) {
            appDatabase.articleDao().updateArticle(articles[0].getAid(),articles[0].getTitle(),articles[0].getDescription(),articles[0].getPrice());
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.actionSuccess();
        }
    }


    private class DisplayAllTask extends AsyncTask<Article, List<Article>, List<Article>> {
        OnRepositoryActionListener listener;
        DisplayAllTask(OnRepositoryActionListener listener) {
            this.listener = listener;
        }
        @Override
        protected List<Article> doInBackground(Article... articles) {
            return appDatabase.articleDao().getAll();
        }
        @Override
        protected void onPostExecute(List<Article> list) {
            super.onPostExecute(list);
            listener.actionSuccess();
        }
    }

    private class SearchTask extends AsyncTask<String, List<Article>, List<Article>> {
        OnRepositoryActionListener listener;
        SearchTask(OnRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<Article> doInBackground(String... strings) {
            return appDatabase.articleDao().findByTitle(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Article> list) {
            super.onPostExecute(list);
            listener.actionSuccess();
        }
    }

    private class GetAuthorsTask extends AsyncTask<Integer, List<Article>, List<Article>> {
        OnRepositoryActionListener listener;
        GetAuthorsTask(OnRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<Article> doInBackground(Integer... integers) {
            return appDatabase.articleDao().findByAuthor(integers[0]);
        }

        @Override
        protected void onPostExecute(List<Article> list) {
            super.onPostExecute(list);
            listener.actionSuccess();
        }
    }

    private class GetArticleByIdTask extends AsyncTask<Integer, Article, Article> {
        OnRepositoryActionListener listener;
        GetArticleByIdTask(OnRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected Article doInBackground(Integer... integers) {
            return appDatabase.articleDao().findByArticleId(integers[0]);
        }

        @Override
        protected void onPostExecute(Article article) {
            super.onPostExecute(article);
            listener.actionSuccess();
        }
    }
}
