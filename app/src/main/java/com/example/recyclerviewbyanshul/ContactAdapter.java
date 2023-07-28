package com.example.recyclerviewbyanshul;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Contact> contacts;
    ItemClicked activity;
    public interface ItemClicked{
        void onItemClicked(int index);
    }
    public ContactAdapter(Context context,ArrayList<Contact>list){
        contacts = list;
        activity = (ItemClicked) context;

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView genderPic;
        TextView name,number;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            genderPic = itemView.findViewById(R.id.ivGender);
            name = itemView.findViewById(R.id.tvName);
            number = itemView.findViewById(R.id.tvNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(contacts.indexOf((Contact) v.getTag()));
                }
            });
        }
    }
    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(contacts.get(position));
        holder.name.setText(contacts.get(position).getName());
        holder.number.setText(contacts.get(position).getNumber());
        String gender = contacts.get(position).getGender();
        if(gender.equals("Male")||gender.equals("male")){
            holder.genderPic.setImageResource(R.drawable.male);
        }
        else if (gender.equals("Female")||gender.equals("female")) {
            holder.genderPic.setImageResource(R.drawable.female);
        }
        else{
            holder.genderPic.setImageResource(R.drawable.person);
        }

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
