/*
 * Author: Mehmet Sedat Kurt
 *         4pps Mobile Software
 */
package net.mskurt.neveremptylistviewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NeverEmptyListView extends RelativeLayout{

    private Context context;

    /*
     * Whole view elements for this custom view.
     */
    private ListView listview;
    private RelativeLayout holder;
    private LinearLayout imageAndTextContainerInHolder;
    private ImageView holderImage;
    private TextView holderText;


    /*
     * Constant fields about Holder Image and Holder Text.
     * This fields are active when there is no assignment.
     */
    private final int HOLDER_IMAGE_DEFAULT_SIZE_IN_DP=70;
    private final int HOLDER_TEXT_DEFAULT_RIGHT_LEFT_MARGIN_IN_DP=20;
    private final int HOLDER_TEXT_DEFAULT_MARGIN_TOP_IN_DP=10;
    private final int HOLDER_TEXT_DEFAULT_FONT_SIZE_IN_PX=14;
    private final int HOLDER_TEXT_DEFAULT_COLOR = Color.parseColor("#000000");
    private final int HOLDER_DEFAULT_BACKGROUND_COLOR = Color.parseColor("#ffffff");
    private final String HOLDER_DEFAULT_TEXT="This is a sample text. You need to set text of holder.";


    /*
     * Variables about Holder Image, Holder Text and Listview.
     * You can assign these fields with xml or programmatically at runtime.
     */
    private int HOLDER_IMAGE_SIZE_IN_PX;
    private int HOLDER_TEXT_MARGIN_TOP_IN_PX;
    private int HOLDER_TEXT_FONT_SIZE_IN_PX;
    private int HOLDER_TEXT_RIGHT_LEFT_MARGIN_IN_PX;
    private Drawable HOLDER_IMAGE_DRAWABLE;
    private String HOLDER_TEXT;
    private int HOLDER_TEXT_COLOR;
    private int HOLDER_BACKGROUND_COLOR;
    private Drawable LISTVIEW_DIVIDER_DRAWABLE;
    private int LISTVIEW_DIVIDER_HEIGHT;
    private boolean LISTVIEW_HEADER_DIVIDERS_ENABLED;
    private boolean LISTVIEW_FOOTER_DIVIDERS_ENABLED;
    private int LISTVIEW_CACHE_COLOR_HINT;
    private boolean LISTVIEW_DRAW_SELECTOR_ON_TOP;
    private boolean LISTVIEW_SCROLLING_CACHE;
    private boolean LISTVIEW_FAST_SCROLL_ENABLED;
    private int LISTVIEW_CHOICE_MODE;
    private Drawable LISTVIEW_LIST_SELECTOR_DRAWABLE;
    private boolean LISTVIEW_TEXT_FILTER_ENABLED;
    private boolean LISTVIEW_SMOOTH_SCROLLBAR_ENABLED;
    private boolean LISTVIEW_STACK_FROM_BOTTOM;
    private int LISTVIEW_TRANSCRIPT_MODE;


    /*
     * Variables which are used on calculations.
     */
    private int DEVICE_HEIGHT;
    private int DEVICE_WIDTH;
    private float DENSITY;
    private int SDK_VERSION;


    /**
     * Public constructors.
     * @param context
     */
    public NeverEmptyListView(Context context) {
        super(context);
        this.context=context;
        setSdkVersion();
        getDeviceDimens();
        HOLDER_IMAGE_DRAWABLE = null;
        HOLDER_IMAGE_SIZE_IN_PX = (int)(HOLDER_IMAGE_DEFAULT_SIZE_IN_DP * DENSITY);
        HOLDER_TEXT_RIGHT_LEFT_MARGIN_IN_PX = (int) (HOLDER_TEXT_DEFAULT_RIGHT_LEFT_MARGIN_IN_DP * DENSITY);
        HOLDER_TEXT_MARGIN_TOP_IN_PX = (int) (HOLDER_TEXT_DEFAULT_MARGIN_TOP_IN_DP * DENSITY);
        HOLDER_TEXT_FONT_SIZE_IN_PX = HOLDER_TEXT_DEFAULT_FONT_SIZE_IN_PX;
        HOLDER_TEXT = HOLDER_DEFAULT_TEXT;
        HOLDER_TEXT_COLOR = HOLDER_TEXT_DEFAULT_COLOR;
        HOLDER_BACKGROUND_COLOR = HOLDER_DEFAULT_BACKGROUND_COLOR;
        LISTVIEW_DIVIDER_HEIGHT = 0;
        LISTVIEW_DIVIDER_DRAWABLE = null;
        LISTVIEW_FOOTER_DIVIDERS_ENABLED = true;
        LISTVIEW_HEADER_DIVIDERS_ENABLED = true;
        LISTVIEW_CACHE_COLOR_HINT = Color.TRANSPARENT;
        LISTVIEW_DRAW_SELECTOR_ON_TOP = false;
        LISTVIEW_SCROLLING_CACHE = true;
        LISTVIEW_FAST_SCROLL_ENABLED = false;
        LISTVIEW_CHOICE_MODE = 0;
        LISTVIEW_LIST_SELECTOR_DRAWABLE = null;
        LISTVIEW_TEXT_FILTER_ENABLED=false;
        LISTVIEW_SMOOTH_SCROLLBAR_ENABLED=true;
        LISTVIEW_STACK_FROM_BOTTOM=false;
        LISTVIEW_TRANSCRIPT_MODE=0;
        init();
    }

    public NeverEmptyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        setSdkVersion();
        getDeviceDimens();
        getAllAttributes(context.getTheme().obtainStyledAttributes(attrs, R.styleable.NeverEmptyListView, 0, 0));
        init();
    }

    public NeverEmptyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        setSdkVersion();
        getDeviceDimens();
        getAllAttributes(context.getTheme().obtainStyledAttributes(attrs,R.styleable.NeverEmptyListView,0, 0));
        init();
    }

    public NeverEmptyListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context=context;
        setSdkVersion();
        getDeviceDimens();
        getAllAttributes(context.getTheme().obtainStyledAttributes(attrs, R.styleable.NeverEmptyListView, 0, 0));
        init();
    }


    /**
     * It starts with init() method when this custom view inflated.
     */
    private void init(){
        createListViewAndHolder();
        createHolderImageAndHolderText();
    }


    /**
     * Listview and Holder Container are prepared with their relevant fields.
     */
    private void createListViewAndHolder() {
        listview = new ListView(context);
        holder = new RelativeLayout(context);

        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        holder.setLayoutParams(params);
        holder.setGravity(Gravity.CENTER);
        holder.setBackgroundColor(HOLDER_BACKGROUND_COLOR);
        holder.setVisibility(View.GONE);

        listview.setLayoutParams(params);
        if(LISTVIEW_DIVIDER_DRAWABLE!=null){
            listview.setDivider(LISTVIEW_DIVIDER_DRAWABLE);
        }
        listview.setDividerHeight(LISTVIEW_DIVIDER_HEIGHT);
        listview.setFooterDividersEnabled(LISTVIEW_FOOTER_DIVIDERS_ENABLED);
        listview.setHeaderDividersEnabled(LISTVIEW_HEADER_DIVIDERS_ENABLED);
        listview.setCacheColorHint(LISTVIEW_CACHE_COLOR_HINT);
        listview.setDrawSelectorOnTop(LISTVIEW_DRAW_SELECTOR_ON_TOP);
        listview.setScrollingCacheEnabled(LISTVIEW_SCROLLING_CACHE);
        listview.setFastScrollEnabled(LISTVIEW_FAST_SCROLL_ENABLED);
        listview.setChoiceMode(LISTVIEW_CHOICE_MODE);
        if(LISTVIEW_LIST_SELECTOR_DRAWABLE!=null){
            listview.setSelector(LISTVIEW_LIST_SELECTOR_DRAWABLE);
        }
        listview.setTextFilterEnabled(LISTVIEW_TEXT_FILTER_ENABLED);
        listview.setSmoothScrollbarEnabled(LISTVIEW_SMOOTH_SCROLLBAR_ENABLED);
        listview.setStackFromBottom(LISTVIEW_STACK_FROM_BOTTOM);
        listview.setTranscriptMode(LISTVIEW_TRANSCRIPT_MODE);
        addView(listview);
        addView(holder);
    }


    /**
     * Holder Image and Holder Text prepared with their relevant fields.
     */
    private void createHolderImageAndHolderText(){
        RelativeLayout.LayoutParams paramsForContainer=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        imageAndTextContainerInHolder=new LinearLayout(context);
        imageAndTextContainerInHolder.setOrientation(LinearLayout.VERTICAL);
        imageAndTextContainerInHolder.setLayoutParams(paramsForContainer);
        imageAndTextContainerInHolder.setGravity(Gravity.CENTER);

        holderImage= new ImageView(context);
        holderText = new TextView(context);
        imageAndTextContainerInHolder.addView(holderImage);
        imageAndTextContainerInHolder.addView(holderText);

        LinearLayout.LayoutParams paramsForChilds = new LinearLayout.LayoutParams(HOLDER_IMAGE_SIZE_IN_PX,HOLDER_IMAGE_SIZE_IN_PX);
        holderImage.setLayoutParams(paramsForChilds);
        if(HOLDER_IMAGE_DRAWABLE==null){
            holderImage.setVisibility(View.GONE);
        }
        else{
            setImageViewBackground(holderImage,HOLDER_IMAGE_DRAWABLE);
        }

        paramsForChilds = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsForChilds.setMargins(HOLDER_TEXT_RIGHT_LEFT_MARGIN_IN_PX, HOLDER_TEXT_MARGIN_TOP_IN_PX, HOLDER_TEXT_RIGHT_LEFT_MARGIN_IN_PX, 0);
        holderText.setLayoutParams(paramsForChilds);
        holderText.setGravity(Gravity.CENTER_HORIZONTAL);
        holderText.setTextColor(HOLDER_TEXT_COLOR);
        holderText.setTextSize(HOLDER_TEXT_FONT_SIZE_IN_PX);
        holderText.setText(HOLDER_TEXT);

        addCustomViewToHolder(imageAndTextContainerInHolder);
    }


    /**
     * You can set your custom view to holder if you think that image and text are not enough for holder.
     * @param view Your custom view
     */
    public void addCustomViewToHolder(View view) {
        holder.removeAllViews();
        holder.addView(view);
    }


    /**
     * Classic setAdapter merhod. Checks a little condition to show holder or not.
     * @param adapter
     */
    public void setAdapter(BaseAdapter adapter){
        holder.setVisibility(adapter.getCount()==0 ? View.VISIBLE : View.GONE);
        listview.setAdapter(adapter);
    }


    /**
     * You must use this method when you notifyDataSetChanged your adapter.
     * @param adapter
     */
    public void notifyDataSetChanged(BaseAdapter adapter){
        adapter.notifyDataSetChanged();
        holder.setVisibility(adapter.getCount() == 0 ? View.VISIBLE : View.GONE);
    }


    /**
     * If you want to play with listview at runtime, you can get it.
     * @return
     */
    public ListView getListview() {
        return listview;
    }



    /*
     * Getter(for some) - Setter of Holder Image-Text, if you need at runtime.
     */
    public void setHolderClickListener(View.OnClickListener clickListener){
        imageAndTextContainerInHolder.setOnClickListener(clickListener);
    }

    public void setHolderImageBackground(int drawableId){
        setImageViewBackground(holderImage, drawableId);
        holderImage.setVisibility(View.VISIBLE);
    }

    public void setHolderImageSize(int sizePixel){
        if(sizePixel<=0){
            HOLDER_IMAGE_SIZE_IN_PX=0;
            holderImage.setVisibility(View.GONE);
        }
        else{
            HOLDER_IMAGE_SIZE_IN_PX=sizePixel;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holderImage.getLayoutParams();
            params.width = HOLDER_IMAGE_SIZE_IN_PX;
            params.height = HOLDER_IMAGE_SIZE_IN_PX;
            holderImage.setLayoutParams(params);
            holderImage.setVisibility(View.VISIBLE);
        }
    }

    public int getHolderImageSize(){
        return HOLDER_IMAGE_SIZE_IN_PX;
    }

    public void setHolderText(String text) {
        if(text==null || text.equals("")){
            HOLDER_TEXT="";
            holderText.setVisibility(View.GONE);
        }
        else{
            HOLDER_TEXT=text;
            holderText.setText(HOLDER_TEXT);
            holderText.setVisibility(View.VISIBLE);
        }
    }

    public String getHolderText(){
        return HOLDER_TEXT;
    }

    public void setHolderTextColor(int color){
        holderText.setTextColor(color);
    }

    public void setHolderTextFontSize(int unit,float size){
        //Units:
        //TypedValue.COMPLEX_UNIT_PX   //Pixels
        //TypedValue.COMPLEX_UNIT_SP   //Scaled Pixels
        //TypedValue.COMPLEX_UNIT_DIP  //Device Independent Pixels
        holderText.setTextSize(unit,size);
    }

    public void setHolderTextTypeFace(Typeface typeFace){
        holderText.setTypeface(typeFace);
    }

    public void setHolderTextLeftRightMargin(int marginPixel){
        HOLDER_TEXT_RIGHT_LEFT_MARGIN_IN_PX = marginPixel<=0 ? 0 : marginPixel;
        LinearLayout.LayoutParams params=(LinearLayout.LayoutParams)holderText.getLayoutParams();
        params.setMargins(HOLDER_TEXT_RIGHT_LEFT_MARGIN_IN_PX,HOLDER_TEXT_MARGIN_TOP_IN_PX,HOLDER_TEXT_RIGHT_LEFT_MARGIN_IN_PX,0);
        holderText.setLayoutParams(params);
    }

    public void setHolderTextTopMargin(int marginPixel){
        HOLDER_TEXT_MARGIN_TOP_IN_PX = marginPixel<=0 ? 0 : marginPixel;
        LinearLayout.LayoutParams params=(LinearLayout.LayoutParams)holderText.getLayoutParams();
        params.setMargins(HOLDER_TEXT_RIGHT_LEFT_MARGIN_IN_PX,HOLDER_TEXT_MARGIN_TOP_IN_PX,HOLDER_TEXT_RIGHT_LEFT_MARGIN_IN_PX,0);
        holderText.setLayoutParams(params);
    }

    public void setHolderBackgroundColor(int color){
        holder.setBackgroundColor(color);
    }




    private void setSdkVersion() {
        SDK_VERSION= Build.VERSION.SDK_INT;
    }

    private void getDeviceDimens() {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        DEVICE_HEIGHT=metrics.heightPixels;
        DEVICE_WIDTH=metrics.widthPixels;
        DENSITY=metrics.density;
    }

    private void setImageViewBackground(ImageView image,Drawable drawable){
        if(SDK_VERSION >= 16){
            image.setBackground(drawable);
        }else{
            image.setBackgroundDrawable(drawable);
        }
    }

    private void setImageViewBackground(ImageView image,int drawableId){
        if(SDK_VERSION >=22){
            image.setBackground(context.getResources().getDrawable(drawableId, null));
        }
        else if(SDK_VERSION >= 16){
            image.setBackground(context.getResources().getDrawable(drawableId));
        }else{
            image.setBackgroundDrawable(context.getResources().getDrawable(drawableId));
        }
    }

    private void getAllAttributes(TypedArray a){
        HOLDER_IMAGE_DRAWABLE = a.getDrawable(R.styleable.NeverEmptyListView_holderImageBackground);
        HOLDER_IMAGE_SIZE_IN_PX = a.getDimensionPixelSize(R.styleable.NeverEmptyListView_holderImageSize, (int) (HOLDER_IMAGE_DEFAULT_SIZE_IN_DP * DENSITY));
        HOLDER_TEXT_RIGHT_LEFT_MARGIN_IN_PX = a.getDimensionPixelSize(R.styleable.NeverEmptyListView_holderTextRightLeftMargin, (int) (HOLDER_TEXT_DEFAULT_RIGHT_LEFT_MARGIN_IN_DP * DENSITY));
        HOLDER_TEXT_MARGIN_TOP_IN_PX = a.getDimensionPixelSize(R.styleable.NeverEmptyListView_holderTextTopMargin, (int) (HOLDER_TEXT_DEFAULT_MARGIN_TOP_IN_DP * DENSITY));
        HOLDER_TEXT_FONT_SIZE_IN_PX = a.getDimensionPixelSize(R.styleable.NeverEmptyListView_holderTextFontSize,HOLDER_TEXT_DEFAULT_FONT_SIZE_IN_PX);
        HOLDER_TEXT = a.getString(R.styleable.NeverEmptyListView_holderText);
        HOLDER_TEXT = (HOLDER_TEXT==null || HOLDER_TEXT.equals("") ) ? HOLDER_DEFAULT_TEXT : HOLDER_TEXT;
        HOLDER_TEXT_COLOR = a.getColor(R.styleable.NeverEmptyListView_holderTextColor,HOLDER_TEXT_DEFAULT_COLOR);
        HOLDER_BACKGROUND_COLOR = a.getColor(R.styleable.NeverEmptyListView_holderBackgroundColor,HOLDER_DEFAULT_BACKGROUND_COLOR);
        LISTVIEW_DIVIDER_HEIGHT = a.getDimensionPixelSize(R.styleable.NeverEmptyListView_listviewDividerHeight, 0);
        LISTVIEW_DIVIDER_DRAWABLE = a.getDrawable(R.styleable.NeverEmptyListView_listviewDivider);
        LISTVIEW_FOOTER_DIVIDERS_ENABLED = a.getBoolean(R.styleable.NeverEmptyListView_listviewFooterDividersEnabled, true);
        LISTVIEW_HEADER_DIVIDERS_ENABLED = a.getBoolean(R.styleable.NeverEmptyListView_listviewHeaderDividersEnabled, true);
        LISTVIEW_CACHE_COLOR_HINT = a.getColor(R.styleable.NeverEmptyListView_listviewCacheColorHint, Color.TRANSPARENT);
        LISTVIEW_DRAW_SELECTOR_ON_TOP = a.getBoolean(R.styleable.NeverEmptyListView_listviewDrawSelectorOnTop, false);
        LISTVIEW_SCROLLING_CACHE = a.getBoolean(R.styleable.NeverEmptyListView_listviewScrollingCache,true);
        LISTVIEW_FAST_SCROLL_ENABLED = a.getBoolean(R.styleable.NeverEmptyListView_listviewFastScrollEnabled,false);
        LISTVIEW_CHOICE_MODE = a.getInt(R.styleable.NeverEmptyListView_listviewChoiceMode, 0);
        LISTVIEW_LIST_SELECTOR_DRAWABLE = a.getDrawable(R.styleable.NeverEmptyListView_listviewListSelector);
        LISTVIEW_TEXT_FILTER_ENABLED = a.getBoolean(R.styleable.NeverEmptyListView_listviewTextFilterEnabled, false);
        LISTVIEW_SMOOTH_SCROLLBAR_ENABLED = a.getBoolean(R.styleable.NeverEmptyListView_listviewSmoothScrollbar, true);
        LISTVIEW_STACK_FROM_BOTTOM = a.getBoolean(R.styleable.NeverEmptyListView_listviewStackFromBottom, false);
        LISTVIEW_TRANSCRIPT_MODE = a.getInt(R.styleable.NeverEmptyListView_listviewTranscriptMode, 0);
    }

}
