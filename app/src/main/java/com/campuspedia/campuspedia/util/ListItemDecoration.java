package com.campuspedia.campuspedia.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by misbahulard on 10/13/2017.
 */

public class ListItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paint;
    private int dividerHeight;

    public ListItemDecoration() {
        paint = new Paint();
        paint.setColor(Color.rgb(240, 240, 240));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        dividerHeight = 4;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = child.getTop() + dividerHeight;
            c.drawRect(left, top, right, bottom, paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(dividerHeight, dividerHeight, dividerHeight, dividerHeight);
    }
}
