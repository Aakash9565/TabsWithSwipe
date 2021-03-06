package com.androidbeasts.tabswithswipe;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class Fragment_Three extends Fragment implements View.OnClickListener {

    private static final String STATE_CURRENT_SCENE = "current_scene";

    /** Tag for the logger */
    private static final String TAG = "CustomTransitionFragment";

    /** These are the Scenes we use. */
    private Scene[] mScenes;

    /** The current index for mScenes. */
    private int mCurrentScene;

    /** This is the custom Transition we use in this sample. */
    private Transition mTransition;

    public Fragment_Three() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Context context = getActivity();
        FrameLayout container = (FrameLayout) view.findViewById(R.id.container);
        view.findViewById(R.id.show_next_scene).setOnClickListener(this);
        if (null != savedInstanceState) {
            mCurrentScene = savedInstanceState.getInt(STATE_CURRENT_SCENE);
        }
        // We set up the Scenes here.
        mScenes = new Scene[] {
                Scene.getSceneForLayout(container, R.layout.scene1, context),
                Scene.getSceneForLayout(container, R.layout.scene2, context),
                Scene.getSceneForLayout(container, R.layout.scene3, context),
        };
        // This is the custom Transition.
        mTransition = new ChangeColor();
        // Show the initial Scene.
        TransitionManager.go(mScenes[mCurrentScene % mScenes.length]);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_CURRENT_SCENE, mCurrentScene);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_next_scene: {
                mCurrentScene = (mCurrentScene + 1) % mScenes.length;
                Log.i(TAG, "Transitioning to scene #" + mCurrentScene);
                // Pass the custom Transition as second argument for TransitionManager.go
                TransitionManager.go(mScenes[mCurrentScene], mTransition);
                break;
            }
        }
    }

}
