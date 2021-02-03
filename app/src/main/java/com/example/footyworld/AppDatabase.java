/*
package com.example.footyworld;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.footyworld.Squad.PlayerList;
import com.example.footyworld.Squad.PlayerListDao;
import com.example.footyworld.Squad.Squad;
import com.example.footyworld.Squad.SquadDao;
import com.example.footyworld.User.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Squad.class, PlayerList.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract UserDao userDao();
    public abstract SquadDao squadDao();
    public abstract PlayerListDao PlayerListDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

     public static AppDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (AppDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE =  Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database.db").addMigrations(MIGRATION_2_3).build();
                    //INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database").build();
                }
            }
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_2_3 = new Migration(3, 4)
    {
        @Override
        public void migrate(SupportSQLiteDatabase database)
        {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };





}
*/