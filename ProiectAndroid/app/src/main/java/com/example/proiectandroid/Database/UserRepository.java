package com.example.proiectandroid.Database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {
    private AppDatabase appDatabase;

    public UserRepository(Context context) {
        appDatabase = ApplicationController.getAppDatabase();
    }

    public void insertTask(final User user,
                           final OnRepositoryActionListener listener)   {
        new InsertTask(listener).execute(user);
    }

    public void removeTask(final User user,
                           final OnRepositoryActionListener listener)   {
        new RemoveTask(listener).execute(user);
    }

    public List<User> displayTask(final OnRepositoryActionListener listener)  {
        try {
            return new DisplayTask(listener).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByTelTask(final String tel, final OnRepositoryActionListener listener)  {
        try {
            return new UserTelTask(listener).execute(tel).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(final int id, final OnRepositoryActionListener listener)  {
        try {
            return new UserByIdTask(listener).execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String displayCityTask(final int id,final OnRepositoryActionListener listener)
    {
        try {
            return new CityTask(listener).execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class InsertTask extends AsyncTask<User, Void, Void> {
        OnRepositoryActionListener listener;
        InsertTask(OnRepositoryActionListener listener) {
            this.listener = listener;
        }
        @Override
        protected Void doInBackground(User... users) {
            appDatabase.userDao().insert(users[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.actionSuccess();
        }
    }

    private class CityTask extends AsyncTask<Integer, String, String> {
        OnRepositoryActionListener listener;
        CityTask(OnRepositoryActionListener listener) {
            this.listener = listener;
        }
        @Override
        protected String doInBackground(Integer... integers) {
            return appDatabase.userDao().findCityById(integers[0]);
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            listener.actionSuccess();
        }
    }


    private class RemoveTask extends AsyncTask<User, Void, Void> {
        OnRepositoryActionListener listener;
        RemoveTask(OnRepositoryActionListener listener) {
            this.listener = listener;
        }
        @Override
        protected Void doInBackground(User... users) {
            appDatabase.userDao().delete(users[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.actionSuccess();
        }
    }

    private class DisplayTask extends AsyncTask<User, List<User>, List<User>> {
        OnRepositoryActionListener listener;
        DisplayTask(OnRepositoryActionListener listener) {
            this.listener = listener;
        }
        @Override
        protected List<User> doInBackground(User... users) {
            return appDatabase.userDao().getAll();
        }
        @Override
        protected void onPostExecute(List<User> list) {
            super.onPostExecute(list);
            listener.actionSuccess();
        }
    }

    private class UserTelTask extends AsyncTask<String, User, User> {
        OnRepositoryActionListener listener;
        UserTelTask(OnRepositoryActionListener listener) {
            this.listener = listener;
        }
        @Override
        protected User doInBackground(String...strings) {
            return appDatabase.userDao().findByTelephone(strings[0]);
        }

        @Override
        protected void onPostExecute(User myuser) {
            super.onPostExecute(myuser);
            listener.actionSuccess();
        }
    }

    private class UserByIdTask extends AsyncTask<Integer, User, User> {
        OnRepositoryActionListener listener;
        UserByIdTask(OnRepositoryActionListener listener) {
            this.listener = listener;
        }
        @Override
        protected User doInBackground(Integer... integers) {
            return appDatabase.userDao().findUserById(integers[0]);
        }

        @Override
        protected void onPostExecute(User myuser) {
            super.onPostExecute(myuser);
            listener.actionSuccess();
        }
    }

}
