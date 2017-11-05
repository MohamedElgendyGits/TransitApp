package com.android.transitapp.application.custom;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.ArrayRes;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.transitapp.R;
import com.android.transitapp.data.entity.Segment;
import com.android.transitapp.data.modes.TravelModes;

/**
 * Created by Mohamed Elgendy.
 */


/**
 * Custom layout to show every segment view in fancy way.
 * <p>
 * Grouping all views together and enlarge the duration text
 */
public class SegmentCustomView extends LinearLayout {

    private Paint gradientPaint;
    private int[] currentGradient;

    private TextView segmentTotalTime;
    private TextView segmentTravelMode;
    private ImageView segmentImage;

    private ArgbEvaluator evaluator;

    public SegmentCustomView(Context context) {
        super(context);
    }

    public SegmentCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SegmentCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SegmentCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    {
        evaluator = new ArgbEvaluator();

        gradientPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setWillNotDraw(false);

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        inflate(getContext(), R.layout.view_segment, this);

        segmentTotalTime = findViewById(R.id.segment_total_time);
        segmentImage =  findViewById(R.id.segment_image);
        segmentTravelMode =  findViewById(R.id.segment_travel_mode);
    }

    private void initGradient() {
        float centerX = getWidth() * 0.5f;
        Shader gradient = new LinearGradient(
                centerX, 0, centerX, getHeight(),
                currentGradient, null,
                Shader.TileMode.MIRROR);
        gradientPaint.setShader(gradient);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (currentGradient != null) {
            initGradient();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), gradientPaint);
        super.onDraw(canvas);
    }

    public void setSegment(Segment segment) {
        currentGradient = segmentToGradient(segment);
        if (getWidth() != 0 && getHeight() != 0) {
            initGradient();
        }
        segmentTotalTime.setText(String.valueOf(segment.getSegmentDuration()));
        segmentTravelMode.setText(segment.getTravelMode());

        segmentImage.setImageResource(getIcon(segment));

        /* //most of icons urls that attached to the json response are broken so i will load them locally
        Log.d("IMAGE-URL",segment.getIconUrl());

        Picasso.with(context)
                .load(segment.getIconUrl())
                .fit()
                .into(image);
                */

        segmentImage.animate()
                .scaleX(1f).scaleY(1f)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(300)
                .start();
    }

    public void onScroll(float fraction, Segment oldS, Segment newS) {
        segmentImage.setScaleX(fraction);
        segmentImage.setScaleY(fraction);
        currentGradient = mix(fraction,
                segmentToGradient(oldS),
                segmentToGradient(newS));
        initGradient();
        invalidate();
    }

    private int[] mix(float fraction, int[] c1, int[] c2) {
        return new int[]{
                (Integer) evaluator.evaluate(fraction, c1[0], c2[0]),
                (Integer) evaluator.evaluate(fraction, c1[1], c2[1]),
                (Integer) evaluator.evaluate(fraction, c1[2], c2[2])
        };
    }

    private int[] segmentToGradient(Segment segment) {

        if (segment.getTravelMode().equals(TravelModes.bus.name())) {
            return colors(R.array.gradientBus);
        } else if (segment.getTravelMode().equals(TravelModes.change.name())) {
            return colors(R.array.gradientChange);
        } else if (segment.getTravelMode().equals(TravelModes.cycling.name())) {
            return colors(R.array.gradientCycling);
        } else if (segment.getTravelMode().equals(TravelModes.driving.name())) {
            return colors(R.array.gradientDriving);
        } else if (segment.getTravelMode().equals(TravelModes.parking.name())) {
            return colors(R.array.gradientParking);
        } else if (segment.getTravelMode().equals(TravelModes.setup.name())) {
            return colors(R.array.gradientSetup);
        } else if (segment.getTravelMode().equals(TravelModes.subway.name())) {
            return colors(R.array.gradientSubway);
        } else if (segment.getTravelMode().equals(TravelModes.walking.name())) {
            return colors(R.array.gradientWalking);
        }else {
            throw new IllegalArgumentException();
        }
    }

    private int getIcon(Segment segment) {

        if (segment.getTravelMode().equals(TravelModes.bus.name())) {
            return R.drawable.route_bus;
        } else if (segment.getTravelMode().equals(TravelModes.change.name())) {
            return R.drawable.route_change;
        } else if (segment.getTravelMode().equals(TravelModes.cycling.name())) {
            return R.drawable.route_cycling;
        } else if (segment.getTravelMode().equals(TravelModes.driving.name())) {
            return R.drawable.route_driving;
        } else if (segment.getTravelMode().equals(TravelModes.parking.name())) {
            return R.drawable.route_parking;
        } else if (segment.getTravelMode().equals(TravelModes.setup.name())) {
            return R.drawable.route_setup;
        } else if (segment.getTravelMode().equals(TravelModes.subway.name())) {
            return R.drawable.route_subway;
        } else if (segment.getTravelMode().equals(TravelModes.walking.name())) {
            return R.drawable.route_walking;
        }else {
            throw new IllegalArgumentException();
        }
    }

    private int[] colors(@ArrayRes int res) {
        return getContext().getResources().getIntArray(res);
    }

}

