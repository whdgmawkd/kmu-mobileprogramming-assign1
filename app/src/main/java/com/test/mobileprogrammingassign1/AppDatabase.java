package com.test.mobileprogrammingassign1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.test.mobileprogrammingassign1.dao.ItemDAO;
import com.test.mobileprogrammingassign1.models.Item;

@Database(entities = {Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDAO getItemDAO();
}
