package com.rjstudio.customsviewdemo;

        import java.util.ArrayList;
        import java.util.List;

        import android.os.Bundle;
        import android.app.Activity;
        import android.graphics.drawable.ColorDrawable;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.BaseAdapter;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.PopupWindow;
        import android.widget.TextView;

public class SpinnerDemo extends Activity {

    private ImageView mArrow;
    private EditText mEdit;


    private PopupWindow mPopupWindow;

    private List<String> mListData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_demo);


        initListData();
        mEdit = (EditText) findViewById(R.id.edit);
        mArrow = (ImageView) findViewById(R.id.arrow);
        Log.d("Test","NULL");
        mArrow.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                handleOnArrowClick();
            }
        });
    }

    private void initListData() {
        mListData = new ArrayList<String>();
        for (int i = 0; i < 200; i++) {
            mListData.add("10000" + i);
        }
    }

    protected void handleOnArrowClick() {

        if (mPopupWindow == null) {
            ListView contentView = new ListView(this);
            //ListView -> setAdapter -> create Adapter -> create List data
            contentView.setBackgroundResource(R.drawable.listview_background);
            contentView.setAdapter(new MyAdapter());
            contentView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    mEdit.setText(mListData.get(position));
                    mEdit.setSelection(mListData.get(position).length());
                    mPopupWindow.dismiss();
                }
            });


            int width = mEdit.getWidth();
            int height = 300;

            mPopupWindow = new PopupWindow(width, height);
            mPopupWindow.setContentView(contentView);

            mPopupWindow.setFocusable(true);

            //��ʧ����
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());

        }

        mPopupWindow.showAsDropDown(mEdit);

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(R.id.id_text);
                viewHolder.delete = (ImageView) convertView.findViewById(R.id.delete);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final String data = mListData.get(position);
            viewHolder.textView.setText(data);
            viewHolder.delete.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    mListData.remove(data);
                    notifyDataSetChanged();
                }
            });

            return convertView;
        }

    }

    private class ViewHolder {
        TextView textView;
        ImageView delete;
    }

}
