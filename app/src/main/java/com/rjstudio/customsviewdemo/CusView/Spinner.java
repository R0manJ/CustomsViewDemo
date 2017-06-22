package com.rjstudio.customsviewdemo.CusView;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.rjstudio.customsviewdemo.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Spinner extends LinearLayout {

    private EditText et_input;
    private ImageView iv_click;

    public Spinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialization();
    }

    private void initialization()
    {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_spinner_demo2,this);
        et_input = (EditText)view.findViewById(R.id.et_input);
        iv_click = (ImageView)view.findViewById(R.id.iv_click);

        iv_click.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                handleListViewButton();
            }
        });
    }

    private void handleListViewButton()
    {
        ListView list = new ListView(getContext());

        PopupWindow addWindows = new PopupWindow(et_input.getWidth(),600);
        //设置新添加的窗口的宽度与高度
        //addWindows.setWidth(et_input.getWidth());//与编辑窗口同宽
        //addWindows.setHeight(600); // 高度设置为100
        addWindows.setContentView(list);


        final String content[] = {"a","b","c","a","b"};
        int iconId = R.drawable.delete;
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter();
        spinnerAdapter.initializationData(content,iconId);
        list.setAdapter(spinnerAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                et_input.setText(content[position]);
                et_input.setSelection(content[position].length());
            }
        });

        //ArrayAdapter<String> ap = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,content);
        //list.setAdapter(ap);


        //即如果点击PopupWindow以外的区域，PopupWindow是否会消失。
        addWindows.setOutsideTouchable(true);
        //PopupWindow是否具有获取焦点的能力，默认为False。
        addWindows.setFocusable(true);
        addWindows.setBackgroundDrawable(new ColorDrawable());

        addWindows.showAsDropDown(et_input);//在哪里显示下拉

    }

    private class SpinnerAdapter extends BaseAdapter
    {


        int iconResourceId;
        List<String> mListData;
        private ViewHolder viewHolder;

        private void initializationData(String[] content , int iconResourceId)
        {
            this.iconResourceId = iconResourceId;
            mListData = new ArrayList<String>();
            for (int i = 0 ; i < content.length;i++)
            {
                mListData.add(content[i]);

            }
            for (String a : mListData)
            {
                Log.d("Iterator:", "initializationData: "+"."+a);
            }
        }

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null)
            {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item_layout,null);

                ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_delete);
                TextView textView = (TextView)convertView.findViewById(R.id.tv_content);

                imageView.setImageResource(iconResourceId);
                textView.setText(mListData.get(position));

                viewHolder = new ViewHolder(textView,imageView);

                //TODO:不解...
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder)convertView.getTag();
            }
            viewHolder.tv_item.setText(mListData.get(position));
            viewHolder.iv_icon.setImageResource(iconResourceId);

            viewHolder.iv_icon.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Delete button", "onClick: "+mListData.size()+"---"+position);
                    //如果集合是由数组遍历过来(asList)的,那么在使用list.add或者list.romove方法时,会报:UnsupportedOperationException异常
                    //数组的长度是固定的,而list的长度可改变
                    Toast.makeText(getContext(), mListData.size()+"--"+position, Toast.LENGTH_SHORT).show();
                    mListData.remove(position);
                    Log.d("Delete button", "onClick: "+mListData.size()+"---"+position);
                    notifyDataSetChanged();

                    //Error : Toast.makeText(getContext(), "Delete button is clicked!"+mListData.get(position), Toast.LENGTH_SHORT).show();
                    //这语句话会报错,在notifyDataSetChanged后,数组的长度已经改变为删减后的长度
                }
            });

            return convertView;
        }

        //TODO:为什么使用viewholder
        private class ViewHolder{
            TextView tv_item;
            ImageView iv_icon;

            public ViewHolder(TextView tv_item, ImageView iv_icon) {
                this.tv_item = tv_item;
                this.iv_icon = iv_icon;
            }

        }
    }


}
