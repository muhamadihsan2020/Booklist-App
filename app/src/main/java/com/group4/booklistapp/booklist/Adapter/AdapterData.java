package com.group4.booklistapp.booklist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group4.booklistapp.booklist.Model.DataModel;
import com.group4.booklistapp.booklist.R;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {

    private Context ctx;
    private List<DataModel> bookList;

    public AdapterData(Context ctx, List<DataModel> bookList) {
        this.ctx = ctx;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dataModel = bookList.get(position);

        holder.tv_id.setText(String.valueOf(dataModel.getId()));
        holder.tv_title.setText(dataModel.getTitle());
        holder.tv_author.setText(dataModel.getAuthor());
        holder.tv_pages.setText(String.valueOf(dataModel.getPages()));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tv_id, tv_title, tv_author, tv_pages;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.book_id_txt);
            tv_title = itemView.findViewById(R.id.book_title_txt);
            tv_author = itemView.findViewById(R.id.book_author_txt);
            tv_pages = itemView.findViewById(R.id.book_pages_txt);
        }
    }

}
