package com.wen.xin.xiao.ge.gao.haro;

import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;



public class Fragment_Wiki_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int EmptyView = 0;
    public static final int HeadItem = 1;
    public static final int SubTitleItem = 2;
    public static final int SliderItem = 3;
    public static final int BannerItem = 4;
    public static final int ProgressView = 5;

    private List<Fragment_Wiki_SubDataItem> mDatas;

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }
    private OnItemClickLitener clickListener;
    public void addOnclickListener(OnItemClickLitener clickListener) {
        this.clickListener = clickListener;
    }

    public Fragment_Wiki_Adapter(List<Fragment_Wiki_SubDataItem> datas) {
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
        else if (mDatas.get(position).getItemCat() == SubTitleItem) {
            return SubTitleItem;
        }
        else if (mDatas.get(position).getItemCat() == SliderItem) {
            return SliderItem;
        }
        else if (mDatas.get(position).getItemCat() == BannerItem) {
            return BannerItem;
        }
        else if (mDatas.get(position).getItemCat() == HeadItem) {
            return HeadItem;
        }
        else {
            return super.getItemViewType(position);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        View tempview;
        if (viewtype == HeadItem) {
            tempview = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_wiki_headitem,parent,false);
            final HeadItemViewHolder holder = new HeadItemViewHolder(tempview);
            return holder;
        }
        else if (viewtype == SubTitleItem) {
            tempview = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_wiki_subtitleitem,parent,false);
            final SubTitleItemViewHolder holder = new SubTitleItemViewHolder(tempview);
            return holder;
        }
        else if (viewtype == SliderItem) {
            tempview = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_wiki_slideritem,parent,false);
            final SliderItemViewHolder holder = new SliderItemViewHolder(tempview);
            return holder;
        }
        else if (viewtype == BannerItem) {
            tempview = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_wiki_banneritem,parent,false);
            final BannerItemViewHolder holder = new BannerItemViewHolder(tempview);
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
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickListener.onItemClick(v, position);
//            }
//        });
        // TODO: 点击事件绑定到底应该放在Oncreate还是OnBind
        // TODO：加载内容
        if (holder instanceof HeadItemViewHolder) {
            HeadItemViewHolder viewHolder = (HeadItemViewHolder) holder;
            viewHolder.title.setText(mDatas.get(position).getHeadName());
            viewHolder.icon.setImageResource(mDatas.get(position).getHeadIcon());
            viewHolder.itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(v, position);
                }
            });
        }
        else if (holder instanceof SubTitleItemViewHolder) {
            SubTitleItemViewHolder viewHolder = (SubTitleItemViewHolder) holder;
            viewHolder.subtitle.setText(mDatas.get(position).getHeadName());
            viewHolder.subtitle_icon.setImageResource(mDatas.get(position).getHeadIcon());
            viewHolder.itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(v, position);
                }
            });
        }
//        else if (holder instanceof ProgressViewHolder) {
//            ProgressViewHolder viewHolder = (SubTitleItemViewHolder) holder;
//
//
//        }
        else if (holder instanceof SliderItemViewHolder) {
            SliderItemViewHolder viewHolder = (SliderItemViewHolder) holder;
            viewHolder.image.setImageResource(mDatas.get(position).getPic());
            viewHolder.cardname.setText(mDatas.get(position).getHeadName());
            viewHolder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(v, position);
                }
            });
        }
        else if (holder instanceof BannerItemViewHolder) {
            BannerItemViewHolder viewHolder = (BannerItemViewHolder) holder;
            viewHolder.banner.setImageResource(mDatas.get(position).getPic());
            viewHolder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(v, position);
                }
            });
        }
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);

            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case HeadItem :
                            return 2;
                        case SliderItem:
                            return 3;
                        case SubTitleItem:
//                      均为一行
                        case BannerItem:
                            return 6;

                        default:
                            return 1;
                    }
                }
            });
        }
    }


    @Override
    public int getItemCount() {

        return mDatas.size();
    }

//    public int dpToPx(float dp){
//        float px = context.getResources().getDisplayMetrics().density;
//        return (int)(dp * px + 0.5f);
//    }


    /**
     * public static final int EmptyView = 0;
     public static final int HeadItem = 1;
     public static final int SubTitleItem = 2;
     public static final int SliderItem = 3;
     public static final int BannerItem = 4;
     public static final int ProgressView = 5;
     */

    class HeadItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.Wiki_title) TextView title;
        @BindView(R.id.Wiki_icon) ImageView icon;

        private View itemview;

        public HeadItemViewHolder(View itemview) {
            super(itemview);
            this.itemview = itemview;
            ButterKnife.bind(this, itemview);
        }
    }

    class SubTitleItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.Subtitle) TextView subtitle;
        @BindView(R.id.Subtitle_icon) ImageView subtitle_icon;

        private View itemview;

        public SubTitleItemViewHolder(View itemview) {
            super(itemview);
            this.itemview = itemview;
            ButterKnife.bind(this, itemview);
        }

    }

    class SliderItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardImage) ImageView image;
        @BindView(R.id.cardname) TextView cardname;
        @BindView(R.id.wiki_slidercard) CardView card;

        private View itemview;
        public SliderItemViewHolder(View itemview) {
            super(itemview);
            this.itemview = itemview;
            ButterKnife.bind(this, itemview);
        }
    }

    class BannerItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bannerimage) ImageView banner;
        @BindView(R.id.wiki_bannercard) CardView card;

        private View itemview;
        public BannerItemViewHolder(View itemview) {
            super(itemview);
            this.itemview = itemview;
            ButterKnife.bind(this, itemview);
        }
    }

    class ProgressViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.progressBar) ProgressBar progressBar;
        @BindView(R.id.textView) TextView textView;

        private View itemview;

        public ProgressViewHolder(View itemview) {
            super(itemview);
            this.itemview = itemview;
            ButterKnife.bind(this, itemview);
        }
    }

}
