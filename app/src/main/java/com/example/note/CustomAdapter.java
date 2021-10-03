package com.example.note;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList note_title, note_content, note_date;

    CustomAdapter(Context context, ArrayList note_title, ArrayList note_content, ArrayList note_date) {
        this.context = context;
        this.note_title = note_title;
        this.note_content = note_content;
        this.note_date = note_date;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_note, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.note_title_txt.setText(String.valueOf(note_title.get(position)));
        holder.note_content_txt.setText(String.valueOf(note_content.get(position)));
        holder.note_date_txt.setText(String.valueOf(note_date.get(position)));
        holder.layoutNode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tham số 1: màn hình hiện tại
                //tham số 2: màn hình muốn chuyển
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("title", holder.note_title_txt.getText());
                intent.putExtra("content", holder.note_content_txt.getText());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return note_title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public void setOnItemClick(OnItemClick onItemClick) {
            this.onItemClick = onItemClick;
        }

        private OnItemClick onItemClick;
        TextView note_title_txt, note_content_txt, note_date_txt;
        LinearLayout layoutNode;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            note_title_txt = itemView.findViewById(R.id.note_title_txt);
            note_content_txt = itemView.findViewById(R.id.note_content_txt);
            note_date_txt = itemView.findViewById(R.id.note_date_txt);
            layoutNode = itemView.findViewById(R.id.layoutNode);

        }

    }
}
