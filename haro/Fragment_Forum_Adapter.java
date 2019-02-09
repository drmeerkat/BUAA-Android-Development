package com.wen.xin.xiao.ge.gao.haro;

import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import java.util.List;



public class Fragment_Forum_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int EmptyView = 0;
    public static final int PlainTextItem = 1;
    public static final int OnePicItem = 2;
    public static final int ThreePicItem = 3;
    public static final int NinePicItem = 4;
    public static final int ProgressView = 5;

    private List<Fragment_Forum_SubDataItem> mDatas;

    // 点击事件回调接口
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onCommentClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;
    public void addOnclickListener(OnItemClickLitener clickListener) {
        this.mOnItemClickLitener = clickListener;
    }



    public Fragment_Forum_Adapter(List<Fragment_Forum_SubDataItem> datas) {
        this.mDatas = datas;
    }

    // TODO: Progress view 和 empty view有什么区别？
    @Override
    public int getItemViewType(int position) {
        if (mDatas.size() == 0)  {
            return EmptyView;
        }
        else if (mDatas.get(position) == null) {
            return ProgressView;
        }
        else if (mDatas.get(position).getItemCat() == PlainTextItem) {
            return PlainTextItem;
        }
        else if (mDatas.get(position).getItemCat() == OnePicItem) {
            return OnePicItem;
        }
        else if (mDatas.get(position).getItemCat() == ThreePicItem) {
            return ThreePicItem;
        }
        else if (mDatas.get(position).getItemCat() == NinePicItem) {
            return NinePicItem;
        }
        else {
            return super.getItemViewType(position);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        View tempview;
        if (viewtype == PlainTextItem) {
            tempview = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_forum_textitem,parent,false);
            final PlainTextItemViewHolder holder = new PlainTextItemViewHolder(tempview);
            return holder;
        }

        // TODO: 下拉刷新的动画添加，swiprefreshlayout?还是直接做一个item?
//        else if (viewtype == ProgressView) {
//            tempview = LayoutInflater.from(parent.getContext()).inflate(R.layout.ProgressView,parent,false);
//            return new ProgressViewHolder(tempview);
//        }
        else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        // TODO：加载内容
        if (holder instanceof PlainTextItemViewHolder) {
            final PlainTextItemViewHolder viewHolder = (PlainTextItemViewHolder) holder;
            viewHolder.icon.setImageResource(mDatas.get(position).getUsericon());
            viewHolder.username.setText(mDatas.get(position).getUsername());
            viewHolder.time.setText(mDatas.get(position).getTime());
            viewHolder.textcontent.setText(mDatas.get(position).getLongtext());


            if (mOnItemClickLitener != null) {
//                Log.i("Information","Coming in");

                viewHolder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Log.i("Information","Adapter child item listener added");
                        mOnItemClickLitener.onItemClick(viewHolder.itemView, viewHolder.getLayoutPosition());
                    }
                });

                viewHolder.commentbutton.setTag(position);
                viewHolder.commentbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickLitener.onCommentClick(v, (Integer) v.getTag());
                    }
                });
            }
        }

    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    /**
     * public static final int EmptyView = 0;
     public static final int HeadItem = 1;
     public static final int SubTitleItem = 2;
     public static final int SliderItem = 3;
     public static final int BannerItem = 4;
     public static final int ProgressView = 5;
     */

    class PlainTextItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.forum_time) TextView time;
        @BindView(R.id.forum_username) TextView username;
        @BindView(R.id.forum_text_content) TextView textcontent;
        @BindView(R.id.forum_usericon) ImageView icon;
        @BindView(R.id.forum_plaincard) CardView card;
        @BindView(R.id.btnComments) ImageButton commentbutton;

        private View itemview;

        public PlainTextItemViewHolder(View itemview) {
            super(itemview);
            this.itemview = itemview;
            ButterKnife.bind(this, itemview);
        }
    }




}
