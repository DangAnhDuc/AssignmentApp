//package com.example.admin.pet_managing;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//
//import java.util.List;
//
//public class Chat_List_Adapter extends BaseAdapter {
//
//    private List<ChatMessage> chatMessageList;
//    private Context context;
//    private LayoutInflater layoutInflater;
//
//    public Chat_List_Adapter(List<ChatMessage> chatMessageList,Context context){
//        this.chatMessageList=chatMessageList;
//        this.context=context;
//        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//
//    @Override
//    public int getCount() {
//        return chatMessageList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return chatMessageList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view=convertView;
//        if(view==null){
//            if(chatMessageList.get(position).isSend()){
//                view=layoutInflater.inflate(R.layout.list_message_send,null);
//            }
//            else {
//                view=layoutInflater.inflate(R.layout.list_message_receive,null);
//            }
//        }
//        return view;
//    }
//}
