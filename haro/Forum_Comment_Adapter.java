package com.wen.xin.xiao.ge.gao.haro;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Forum_Comment_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int EmptyView = 0;
    public static final int PlainTextItem = 1;
    public static final int OnePicItem = 2;
    public static final int ThreePicItem = 3;
    public static final int NinePicItem = 4;
    public static final int ProgressView = 5;

    private List<Fragment_Forum_SubDataItem> mDatas;

    private Context context;
    private int itemsCount = 0;
    private int lastAnimatedPosition = -1;
    private int avatarSize;

    private boolean animationsLocked = false;
    private boolean delayEnterAnimation = true;

//    public CommentsAdapter(Context context) {
//        this.context = context;
//        avatarSize = context.getResources().getDimensionPixelSize(R.dimen.comment_avatar_size);
//    }



    public Forum_Comment_Adapter(List<Fragment_Forum_SubDataItem> datas, Context context) {
        this.mDatas = datas;
        this.context = context;
        avatarSize = this.context.getResources().getDimensionPixelSize(R.dimen.comment_avatar_size);

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
            tempview = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_forum_commentitem,parent,false);
            final Forum_Comment_Adapter.PlainTextItemViewHolder holder = new Forum_Comment_Adapter.PlainTextItemViewHolder(tempview);
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
        runEnterAnimation(holder.itemView, position);


        // TODO：加载内容
        if (holder instanceof Fragment_Forum_Adapter.PlainTextItemViewHolder) {
            final Fragment_Forum_Adapter.PlainTextItemViewHolder viewHolder = (Fragment_Forum_Adapter.PlainTextItemViewHolder) holder;
            viewHolder.icon.setImageResource(mDatas.get(position).getUsericon());
            viewHolder.username.setText(mDatas.get(position).getUsername());
            viewHolder.time.setText(mDatas.get(position).getTime());
            viewHolder.textcontent.setText(mDatas.get(position).getLongtext());

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
        @BindView(R.id.forum_comment_time)
        TextView time;
        @BindView(R.id.forum_comment_username) TextView username;
        @BindView(R.id.forum_comment_text_content) TextView textcontent;
        @BindView(R.id.forum_comment_icon)
        ImageView icon;
        @BindView(R.id.forum_comment_plaincard)
        CardView card;
        @BindView(R.id.forum_comment_btnLike)
        ImageButton likebutton;

        private View itemview;

        public PlainTextItemViewHolder(View itemview) {
            super(itemview);
            this.itemview = itemview;
            ButterKnife.bind(this, itemview);
        }
    }

    private void runEnterAnimation(View view, int position) {
        if (animationsLocked) return;

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(100);
            view.setAlpha(0.f);
            view.animate()
                    .translationY(0).alpha(1.f)
                    .setStartDelay(delayEnterAnimation ? 20 * (position) : 0)
                    .setInterpolator(new DecelerateInterpolator(2.f))
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animationsLocked = true;
                        }
                    })
                    .start();
        }
    }


    public void updateItems() {
        itemsCount = 10;
        notifyDataSetChanged();
    }

    public void addItem() {
        itemsCount++;
        notifyItemInserted(itemsCount - 1);
    }

    public void setAnimationsLocked(boolean animationsLocked) {
        this.animationsLocked = animationsLocked;
    }

    public void setDelayEnterAnimation(boolean delayEnterAnimation) {
        this.delayEnterAnimation = delayEnterAnimation;
    }
}
