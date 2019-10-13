package com.test.mobileprogrammingassign1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GridLayout board;
    private ImageButton[] items;
    private int[] b;
    private TextView status;

    private enum turn {O, X}

    private turn currentTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        board = findViewById(R.id.board);
        items = new ImageButton[9];
        for (int a = 0; a < 9; a++) {
            items[a] = findViewById(board.getChildAt(a).getId());
            items[a].setOnClickListener(this);
        }
        status = findViewById(R.id.status);
        refreshBoard();
    }

    @Override
    public void onClick(View v) {
        for (int a = 0; a < 9; a++) {
            if (v.getId() == items[a].getId()) {
                if (b[a] == 0) {
                    b[a] = (currentTurn == turn.O ? 1 : -1);
                    updateView();
                }
                checkBoard();
            }
        }
    }

    private void updateView() {
        for (int a = 0; a < 9; a++) {
            if (b[a] == 1) {
                items[a].setImageDrawable(getDrawable(R.drawable.board_o));
            } else if (b[a] == -1) {
                items[a].setImageDrawable(getDrawable(R.drawable.board_x));
            } else {
                items[a].setImageDrawable(getDrawable(R.drawable.board_empty));
            }
        }
    }

    private void refreshBoard() {
        currentTurn = turn.X;
        b = new int[9];
        updateView();
        nextTurn();
    }

    private void checkBoard() {
        if (b[0] + b[1] + b[2] == 3) {
            status.setText("O Win!");
        } else if (b[3] + b[4] + b[5] == 3) {
            status.setText("O Win!");
        } else if (b[6] + b[7] + b[8] == 3) {
            status.setText("O Win!");
        } else if (b[0] + b[3] + b[6] == 3) {
            status.setText("O Win!");
        } else if (b[1] + b[4] + b[7] == 3) {
            status.setText("O Win!");
        } else if (b[2] + b[5] + b[8] == 3) {
            status.setText("O Win!");
        } else if (b[0] + b[4] + b[8] == 3) {
            status.setText("O Win!");
        } else if (b[2] + b[4] + b[6] == 3) {
            status.setText("O Win!");
        } else if (b[0] + b[1] + b[2] == -3) {
            status.setText("X Win!");
        } else if (b[3] + b[4] + b[5] == -3) {
            status.setText("X Win!");
        } else if (b[6] + b[7] + b[8] == -3) {
            status.setText("X Win!");
        } else if (b[0] + b[3] + b[6] == -3) {
            status.setText("X Win!");
        } else if (b[1] + b[4] + b[7] == -3) {
            status.setText("X Win!");
        } else if (b[2] + b[5] + b[8] == -3) {
            status.setText("X Win!");
        } else if (b[0] + b[4] + b[8] == -3) {
            status.setText("X Win!");
        } else if (b[2] + b[4] + b[6] == -3) {
            status.setText("X Win!");
        } else {
            for (int a = 0; a < 9; a++) {
                if (b[a] == 0) {
                    nextTurn();
                    return;
                }
            }
            status.setText("Draw!");
        }
    }

    private void nextTurn() {
        currentTurn = (currentTurn == turn.O ? turn.X : turn.O);
        status.setText("Current Turn : " + (currentTurn == turn.O ? "O" : "X"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_refresh) {
            refreshBoard();
        } else if (item.getItemId() == R.id.menu_logout) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
