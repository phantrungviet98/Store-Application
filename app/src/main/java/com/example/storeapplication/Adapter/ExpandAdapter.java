package com.example.storeapplication.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.storeapplication.Model.Home.MenuProcessing.JsonMenuProcessing;
import com.example.storeapplication.Model.OjectClass.ProductType;
import com.example.storeapplication.R;

import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    List<ProductType> productTypeList;
    ViewHolderMenu viewHolderMenu;


    public ExpandAdapter(Context context, List<ProductType> productTypeList){
        this.context = context;
        this.productTypeList = productTypeList;

        JsonMenuProcessing xuLyJSONMenu = new JsonMenuProcessing();

        int count = productTypeList.size();
        for (int i=0;i<count;i++){
            int maloaisp = productTypeList.get(i).getProductTypeId();
            productTypeList.get(i).setChilList(xuLyJSONMenu.getProductTypeFProductTypeId(maloaisp));
        }


    }

    @Override
    public int getGroupCount() {
        return productTypeList.size();
    }

    @Override
    public int getChildrenCount(int parentGroupPosition) {
        if(productTypeList.get(parentGroupPosition).getChilList().size() != 0){
            return 1;
        }else{
            return 0;
        }

    }

    @Override
    public Object getGroup(int parentGroupPosition) {
        return productTypeList.get(parentGroupPosition);
    }

    @Override
    public Object getChild(int parentGroupPosition, int childGroupPosition) {
        return productTypeList.get(parentGroupPosition).getChilList().get(childGroupPosition);
    }

    @Override
    public long getGroupId(int parentGroupPosition) {
        return productTypeList.get(parentGroupPosition).getProductTypeId();
    }

    @Override
    public long getChildId(int parentGroupPosition, int childGroupPosition) {
        return productTypeList.get(parentGroupPosition).getChilList().get(childGroupPosition).getProductTypeId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolderMenu{
        TextView txtProductTypeName;
        ImageView menuImage;
    }

    @Override
    public View getGroupView(final int parentGroupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {

        View viewParentGroup = view;
        if(viewParentGroup == null){
            viewHolderMenu = new ViewHolderMenu();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewParentGroup = layoutInflater.inflate(R.layout.custom_layout_parent_group,viewGroup,false);

            viewHolderMenu.txtProductTypeName = viewParentGroup.findViewById(R.id.txtProductTypeName);
            viewHolderMenu.menuImage =viewParentGroup.findViewById(R.id.imMenuPlus);

            viewParentGroup.setTag(viewHolderMenu);

        }else{
            viewHolderMenu = (ViewHolderMenu) viewParentGroup.getTag();
        }


        viewHolderMenu.txtProductTypeName.setText(productTypeList.get(parentGroupPosition).getProductTypeName());


        int countChildProductType = productTypeList.get(parentGroupPosition).getChilList().size();

        if(countChildProductType > 0){
            viewHolderMenu.menuImage.setVisibility(View.VISIBLE);
        }else{
            viewHolderMenu.menuImage.setVisibility(View.INVISIBLE);
        }

        if (isExpanded){
            viewHolderMenu.menuImage.setImageResource(R.drawable.ic_remove_black_24dp);
            viewParentGroup.setBackgroundResource(R.color.colorPrimary);
        }else{
            viewHolderMenu.menuImage.setImageResource(R.drawable.ic_add_black_24dp);
        }

        viewParentGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        return viewParentGroup;
    }


    @Override
    public View getChildView(int parentGroupPosition, int childGroupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {

        SecondExpanable secondExpanable = new SecondExpanable(context);
        ExpandAdapter secondAdapter = new ExpandAdapter(context,productTypeList.get(parentGroupPosition).getChilList());
        secondExpanable.setAdapter(secondAdapter);

        secondExpanable.setGroupIndicator(null);
        notifyDataSetChanged();

        return secondExpanable;
    }

    public class SecondExpanable extends ExpandableListView{

        public SecondExpanable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int width = size.x;
            int height = size.y;

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }


}