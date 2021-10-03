package com.example.note;

import android.view.View;

import java.util.ArrayList;

public interface OnItemClick {
    void onClick(View view, int position, boolean isLongClick);
}
