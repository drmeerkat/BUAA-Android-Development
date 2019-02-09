package com.wen.xin.xiao.ge.gao.haro;


import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_News_SectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int EmptyView = 0;
    public static final int SliderItem = 1;
    public static final int CardItem = 2;
    public static final int PicNewsItem = 3;
    public static final int SimNewsItem = 4;
    public static final int BannerItem = 5;
    public static final int ProgressView = 6;


    private List<Fragment_News_SubDataItem> mDatas;
    private Fragment_News_TabOne parenttab;
    private Context context;

    private SliderLayout sliderLayout;

    public Fragment_News_SectionAdapter(List<Fragment_News_SubDataItem> datas, @Nullable Fragment_News_TabOne tabOne) {
        this.mDatas = datas;
        this.parenttab = tabOne;
    }


    /**
     * OnClick回调
     */
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }
    private OnItemClickLitener clickListener;

    public void addOnclickListener(OnItemClickLitener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * ContentAnimation
     */
    private int lastAnimatedPosition = -1;
    private int itemsCount = 0;
    private static final int ANIMATED_ITEMS_COUNT = 4;

    private void runEnterAnimation(View view, int position) {
        if (position >= ANIMATED_ITEMS_COUNT - 1) {
            return;
        }

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(3*Utils.getScreenHeight(context));
            view.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
        }
    }


    public void updateItems() {
        itemsCount = getItemCount();
        notifyDataSetChanged();
    }


    /**
     * ViewType Create,Bind,ViewHolder
     */
    @Override
    public int getItemViewType(int position) {
        if (mDatas.size() == 0)  {
            return EmptyView;
        }
        else if (mDatas.get(position) == null) {
            return ProgressView;
        }
        else if (mDatas.get(position).getItemCat() == CardItem) {
            return CardItem;
        }
        else if (mDatas.get(position).getItemCat() == SliderItem) {
            return SliderItem;
        }
        else if (mDatas.get(position).getItemCat() == BannerItem) {
            return BannerItem;
        }
        else if (mDatas.get(position).getItemCat() == PicNewsItem) {
            return PicNewsItem;
        }
        else if (mDatas.get(position).getItemCat() == SimNewsItem) {
            return SimNewsItem;
        }
        else {
            return super.getItemViewType(position);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        View tempview;
        context = parent.getContext();
        if (viewtype == CardItem) {
            tempview = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_carditem,parent,false);
            final CardItemViewHolder holder = new Fragment_News_SectionAdapter.CardItemViewHolder(tempview);
            return holder;
        }
        else if (viewtype == PicNewsItem) {
            tempview = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_picnewsitem,parent,false);
            final PicNewsItemViewHolder holder = new Fragment_News_SectionAdapter.PicNewsItemViewHolder(tempview);
            return holder;
        }
        else if (viewtype == SliderItem) {
            tempview = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_slideritem,parent,false);
            final SliderItemViewHolder holder = new Fragment_News_SectionAdapter.SliderItemViewHolder(tempview);
            return holder;
        }
        else if (viewtype == SimNewsItem) {
            tempview = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_simnewsitem,parent,false);
            final SimNewsItemItemViewHolder holder = new Fragment_News_SectionAdapter.SimNewsItemItemViewHolder(tempview);
            return holder;
        }
        else if (viewtype == BannerItem) {
            tempview = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_banneritem,parent,false);
            final BannerItemViewHolder holder = new Fragment_News_SectionAdapter.BannerItemViewHolder(tempview);
            return holder;
        }
        // TODO: 下拉刷新的动画添加，swiprefreshlayout?还是直接做一个item?
        // TODO: 代码精简
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

        if (holder instanceof Fragment_News_SectionAdapter.CardItemViewHolder) {
            Fragment_News_SectionAdapter.CardItemViewHolder viewHolder = (Fragment_News_SectionAdapter.CardItemViewHolder) holder;
            viewHolder.title.setText(mDatas.get(position).getTitle());
            viewHolder.abs.setText(mDatas.get(position).getAbs());
            viewHolder.time.setText(mDatas.get(position).getTime());
            viewHolder.pic.setImageResource(mDatas.get(position).getPic());
            if (clickListener != null) {
                viewHolder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(v ,position);
                    }
                });
            }
        }
        else if (holder instanceof Fragment_News_SectionAdapter.PicNewsItemViewHolder) {
            Fragment_News_SectionAdapter.PicNewsItemViewHolder viewHolder = (Fragment_News_SectionAdapter.PicNewsItemViewHolder) holder;
            viewHolder.title.setText(mDatas.get(position).getTitle());
            viewHolder.abs.setText(mDatas.get(position).getAbs());
            viewHolder.time.setText(mDatas.get(position).getTime());
            viewHolder.pic.setImageResource(mDatas.get(position).getPic());
            if (clickListener != null) {
                viewHolder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(v ,position);
                    }
                });
            }
        }
//        else if (holder instanceof ProgressViewHolder) {
//            ProgressViewHolder viewHolder = (SubTitleItemViewHolder) holder;
//
//
//        }

        else if (holder instanceof Fragment_News_SectionAdapter.SliderItemViewHolder) {
            Fragment_News_SectionAdapter.SliderItemViewHolder viewHolder = (Fragment_News_SectionAdapter.SliderItemViewHolder) holder;
            ArrayList<TextSliderView> sliders = (ArrayList<TextSliderView>) mDatas.get(position).getSliders();
            for (int i=0;i<sliders.size();i++) {
                viewHolder.news_slider.addSlider(sliders.get(i));
            }
            viewHolder.news_slider.setPresetTransformer(SliderLayout.Transformer.DepthPage);
            viewHolder.news_slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            viewHolder.news_slider.setCustomAnimation(new DescriptionAnimation());
            viewHolder.news_slider.setDuration(4000);
            viewHolder.news_slider.addOnPageChangeListener(parenttab);
            this.sliderLayout = viewHolder.news_slider;
        }

        else if (holder instanceof Fragment_News_SectionAdapter.SimNewsItemItemViewHolder) {
            Fragment_News_SectionAdapter.SimNewsItemItemViewHolder viewHolder = (Fragment_News_SectionAdapter.SimNewsItemItemViewHolder) holder;
            viewHolder.title.setText(mDatas.get(position).getTitle());
            viewHolder.abs.setText(mDatas.get(position).getAbs());
            viewHolder.time.setText(mDatas.get(position).getTime());
            if (clickListener != null) {
                viewHolder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(v ,position);
                    }
                });
            }
        }

        else if (holder instanceof Fragment_News_SectionAdapter.BannerItemViewHolder) {
            Fragment_News_SectionAdapter.BannerItemViewHolder viewHolder = (Fragment_News_SectionAdapter.BannerItemViewHolder) holder;
            viewHolder.banner.setImageResource(mDatas.get(position).getPic());
            if (clickListener != null) {
                viewHolder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(v ,position);
                    }
                });
            }
        }
    }


    public void onDestroySlider() {
        this.sliderLayout.stopAutoCycle();
    }


    @Override
    public int getItemCount() {

        return mDatas.size();
    }


    /**
     public static final int EmptyView = 0;
     public static final int SliderItem = 1;
     public static final int CardItem = 2;
     public static final int PicNewsItem = 3;
     public static final int SimNewsItem = 4;
     public static final int BannerItem = 5;
     public static final int ProgressView = 6;
     */

    class CardItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_cardtitle)
        TextView title;
        @BindView(R.id.news_cardabs)
        TextView abs;
        @BindView(R.id.news_cardtime)
        TextView time;
        @BindView(R.id.news_cardpic)
        ImageView pic;
        @BindView(R.id.news_card)
        CardView card;

        private View itemview;

        public CardItemViewHolder(View itemview) {
            super(itemview);
            this.itemview = itemview;
            ButterKnife.bind(this, itemview);
        }
    }

    class PicNewsItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_pictitle)
        TextView title;
        @BindView(R.id.news_picabs)
        TextView abs;
        @BindView(R.id.news_pictime)
        TextView time;
        @BindView(R.id.news_pic)
        ImageView pic;
        @BindView(R.id.news_piccard)
        CardView card;

        private View itemview;


        public PicNewsItemViewHolder(View itemview) {
            super(itemview);
            this.itemview = itemview;
            ButterKnife.bind(this, itemview);
        }

    }

    class SimNewsItemItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_simtitle)
        TextView title;
        @BindView(R.id.news_simabs)
        TextView abs;
        @BindView(R.id.news_simtime)
        TextView time;
        @BindView(R.id.news_simcard)
        CardView card;

        private View itemview;

        public SimNewsItemItemViewHolder(View itemview) {
            super(itemview);
            this.itemview = itemview;
            ButterKnife.bind(this, itemview);
        }
    }

    class BannerItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_banner)
        ImageView banner;
        @BindView(R.id.news_bannercard)
        CardView card;

        private View itemview;

        public BannerItemViewHolder(View itemview) {
            super(itemview);
            this.itemview = itemview;
            ButterKnife.bind(this, itemview);
        }
    }

//    class ProgressViewHolder extends RecyclerView.ViewHolder {
//
//        //        @BindView(R.id.progressBar) ProgressBar progressBar;
//        @BindView(R.id.textView) TextView textView;
//
//        public ProgressViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }

    class SliderItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_slider)
        SliderLayout news_slider;

        private View itemview;

        public SliderItemViewHolder(View itemview) {
            super(itemview);
            this.itemview = itemview;
            ButterKnife.bind(this, itemview);
        }
    }





}
