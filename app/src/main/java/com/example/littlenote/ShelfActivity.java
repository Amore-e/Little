package com.example.littlenote;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ShelfActivity extends Activity {

    private ListView list_view;
    private ArrayList<String> sentences;
    private SentenceAdapter sentenceAdapter;
    private String json = "{" +
            " \"title\": \"佳句锦集\",      " +
            " \"sentence\": [                               " +
            "    {                                           " +
            "        \"book\": \"《你要去相信，没有到不了的明天》\",               " +
            "        \"detail\": \"        这一次要把自己以前所有没有用到的倔强都用完，把所有的半途而废都弥补上。 \"           " +
            "    },                                           " +
            "    {                                           " +
            "        \"book\": \"《像我这样笨拙的生活》\",               " +
            "        \"detail\": \"        我是个过分认真的人，总想给生命一个交代。这种愚蠢的努力简直成了我的噩梦，当然，也是最终的救赎。\"           " +
            "    },                                           " +
            "    {                                           " +
            "        \"book\": \"《楚门的世界》\",               " +
            "        \"detail\": \"        如果再也不能见到你，祝你早安！午安！晚安！\"            " +
            "    },                                           " +
            "    {                                           " +
            "        \"book\": \"《秒速五厘米》\",               " +
            "        \"detail\": \"         我就像一株向日葵追寻着赖以生存的太阳，然而即便再努力也还是无法靠近，即使陪伴在你身边却依然触及不到你的存在。\"            " +
            "    },                                           " +
            "    {                                           " +
            "        \"book\": \"《瓦尔登湖》\",               " +
            "        \"detail\": \"        在任何气候任何时辰，我都希望及时改善我当前的状况，并要在手杖上刻下记号；过去和未来的交叉点正是现在，我就站在这个起点上。\"            " +
            "    },                                           " +
            "    {                                           " +
            "        \"book\": \"《历史》\",               " +
            "        \"detail\": \"        公元前我们还小，公元后我们又太老，没有谁见过那一次真正的美丽。\"            " +
            "    },                                           " +
            "    {                                           " +
            "        \"book\": \"《顾城哲思录》\",               " +
            "        \"detail\": \"        执者失之。我想当一个诗人的时候，我就失去了诗，我想当一个人的时候，我就失去了我自己。在你什么也不想要的时候，一切如期而来。\"            " +
            "    },                                           " +
            "    {                                           " +
            "        \"book\": \"《你是人间四月天》\",               " +
            "        \"detail\": \"        渺小的日子也要有一点想念，像灵魂失落在街边。\"            " +
            "    },                                           " +
            "    {                                           " +
            "        \"book\": \"《张爱玲语录集》\",               " +
            "        \"detail\": \"        如果你认识从前的我，那么你就会原谅现在的我。\"            " +
            "    },                                           " +
            "    {                                           " +
            "        \"book\": \"《说给自己听》\",               " +
            "        \"detail\": \"        如果有来生要做一棵树，站成永恒。没有悲欢的姿势，一半在尘土里安详，一半在风里飞扬；一半洒落阴凉，一半沐浴阳光。非常沉默，非常骄傲。从不寻找，从不依靠。\"           " +
            "    }                                           " +
            "]                                               " +
            " }";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);
        list_view= (ListView) findViewById(R.id.list_view);
        jsonTest();
        sentenceAdapter = new SentenceAdapter();
        list_view.setAdapter(sentenceAdapter);
    }

//解析数据
    private void jsonTest() {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String id = jsonObject.getString("title");
            JSONArray jsonArray = jsonObject.getJSONArray("sentence");

            sentences = new ArrayList<>();
            sentences.add(id);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
                String coverage = jsonObject2.getString("book");
                String name = jsonObject2.getString("detail");
                sentences.add(coverage);
                sentences.add(name);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public class SentenceAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return sentences.size();
        }

        @Override
        public Object getItem(int position) {
            return sentences.get(position);

        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.sentence_list, null);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.sentence);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(sentences.get(position));
            return convertView;
        }
    }


    final static class ViewHolder {
        TextView textView;
    }
}
