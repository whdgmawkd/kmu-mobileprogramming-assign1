package com.test.mobileprogrammingassign1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.test.mobileprogrammingassign1.models.Item;

import java.util.List;

@Dao
public interface ItemDAO {
    @Insert
    public void insert(Item... items);

    @Update
    public void update(Item... items);

    @Delete
    public void delete(Item item);

    @Query("SELECT * FROM items WHERE username = :username and password =:password LIMIT 1")
    public Item getItemByLoginInfo(String username, String password);

    @Query("SELECT * FROM items")
    public List<Item> getItems();
}
