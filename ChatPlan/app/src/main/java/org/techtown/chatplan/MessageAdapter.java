package org.techtown.chatplan;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends BaseAdapter {
    private ArrayList<User> chat;
    private static final int CHATBOT=0;
    private static final int USER=1;

    public MessageAdapter(ArrayList<User> userList){
        chat=userList;
    }

    @Override
    public int getItemViewType(int position) {
        return chat.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return chat.size();
    }

    @Override
    public Object getItem(int i) {
        return chat.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        int viewType = getItemViewType(position) ;
        ViewHolder holder;

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
            holder=new ViewHolder();

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            User userItem = chat.get(position);

            switch (viewType) {
                case CHATBOT:
                    convertView = inflater.inflate(R.layout.receive_message,parent, false);
                    ImageView profile=(ImageView)convertView.findViewById(R.id.profile);
                    TextView message=(TextView)convertView.findViewById(R.id.txtMessage);

                    //message.setText(userItem.getMessage());
                    //profile.setImageResource(userItem.getProfile());
                    holder.profile=profile;
                    holder.txtMessage=message;

                    break;
                case USER:
                    convertView = inflater.inflate(R.layout.send_message, parent, false);
                    ImageView profile2=(ImageView)convertView.findViewById(R.id.profile2);
                    TextView message2=(TextView)convertView.findViewById(R.id.txtMessage2);

                    //message2.setText(userItem.getMessage());
                    //profile2.setImageResource(userItem.getProfile());
                    holder.profile=profile2;
                    holder.txtMessage=message2;

                    break;
            }

            convertView.setTag(holder);

        User item=chat.get(position);
        holder.profile.setImageResource(item.getProfile());
        holder.txtMessage.setText(item.getMessage());

        return convertView;
    }
}

