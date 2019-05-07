package UserDB;

import android.content.Context;
import android.os.AsyncTask;

import com.example.tema2.ApplicationController;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {
    private AppDatabase appDatabase;

    public UserRepository(Context context) {
        appDatabase = ApplicationController.getAppDatabase();
    }

    public void insertTask(final User user,
                           final OnUserRepositoryActionListener listener)   {
        new InsertTask(listener).execute(user);
    }

    public void removeTask(final User user,
                           final OnUserRepositoryActionListener listener)   {
        new RemoveTask(listener).execute(user);
    }

    public List<User> displayTask(final OnUserRepositoryActionListener listener)  {
        try {
            return new DisplayTask(listener).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByName(String firstName, String lastName){
        return appDatabase.userDao().findByName(firstName, lastName);
    }

    private class InsertTask extends AsyncTask<User, Void, Void> {
        OnUserRepositoryActionListener listener;
        InsertTask(OnUserRepositoryActionListener listener) {
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


    private class RemoveTask extends AsyncTask<User, Void, Void> {
        OnUserRepositoryActionListener listener;
        RemoveTask(OnUserRepositoryActionListener listener) {
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
        OnUserRepositoryActionListener listener;
        DisplayTask(OnUserRepositoryActionListener listener) {
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
}
