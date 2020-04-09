package com.yl.markremember.adapter;

import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

import com.standards.library.adapter.BaseItemDraggableAdapter;
import com.standards.library.adapter.BaseViewHolder;
import com.standards.library.adapter.entity.MultiItemEntity;
import com.standards.library.util.LogUtil;
import com.yl.markremember.bean.MenuSubBean;

import java.util.List;

public class BaseMultiItemDragQuickAdapter <T extends MultiItemEntity, K extends BaseViewHolder> extends BaseItemDraggableAdapter<T, K> {

    /**
     * layouts indexed with their types
     */
    private SparseArray<Integer> layouts;

    private static final int DEFAULT_VIEW_TYPE = -0xff;
    public static final int TYPE_NOT_FOUND = -404;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseMultiItemDragQuickAdapter(List<T> data) {
        super(data);
    }

    @Override
    protected int getDefItemViewType(int position) {
        Object item = mData.get(position);
        if (item instanceof MultiItemEntity) {
            return ((MultiItemEntity) item).getItemType();
        }
        return DEFAULT_VIEW_TYPE;
    }

    protected void setDefaultViewTypeLayout(@LayoutRes int layoutResId) {
        addItemType(DEFAULT_VIEW_TYPE, layoutResId);
    }

    @Override
    protected K onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, getLayoutId(viewType));
    }

    private int getLayoutId(int viewType) {
        return layouts.get(viewType, TYPE_NOT_FOUND);
    }

    protected void addItemType(int type, @LayoutRes int layoutResId) {
        if (layouts == null) {
            layouts = new SparseArray<>();
        }
        layouts.put(type, layoutResId);
    }

    @Override
    protected void convert(K helper, T item) {

    }

    /**
     * 重写BaseItemDraggableAdapter里面的onItemDragMoving方法，判断from Or to 是不是-1，
     * 当item拖动到头部的时候to是-1,必须判断，否则数组越界
     *
     * @param source
     * @param target
     */
    @Override
    public void onItemDragMoving(RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        int from = getViewHolderPosition(source);
        int to = getViewHolderPosition(target);
        if (from == -1 || to == -1) {
            return;
        }

        if (getItem(from) instanceof MenuSubBean) {
            MenuSubBean from_menuSubBean= (MenuSubBean) getItem(from);
            if (from_menuSubBean==null)return;
            LogUtil.d("from类型viewtype="+getItemViewType(from));
            if (getItem(to) instanceof MenuSubBean){
                MenuSubBean to_menuSubBean= (MenuSubBean) getItem(to);
                if (to_menuSubBean==null)return;
                LogUtil.d("to类型viewtype="+getItemViewType(to));
                if (from_menuSubBean.getParent_menu_id()!=to_menuSubBean.getParent_menu_id()){
                    return;
                }
            }else {
                return;
            }

        }


        super.onItemDragMoving(source, target);
    }
}

