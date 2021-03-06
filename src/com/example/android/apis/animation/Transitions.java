/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.apis.animation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import com.example.android.apis.R;

/**
 * Android API19以上可以使用的Scene新动画
 * 
 * @description：
 * @author ldm
 * @date 2016-4-26 下午4:26:32
 */
public class Transitions extends Activity {
	/**
	 * 一个Scene保存了一个ViewGroup中所有元素的状。
	 * 同时他还拥有一个关于这个ViewGroup的父ViewGroup的引用，这个父ViewGroup称为scene root。
	 */
	private Scene mScene1, mScene2, mScene3;
	private ViewGroup mSceneRoot;
	// 平移动画管理工具类
	private TransitionManager mTransitionManager;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transition);
		mSceneRoot = (ViewGroup) findViewById(R.id.sceneRoot);
		TransitionInflater inflater = TransitionInflater.from(this);
		mScene1 = Scene.getSceneForLayout(mSceneRoot,
				R.layout.transition_scene1, this);
		mScene2 = Scene.getSceneForLayout(mSceneRoot,
				R.layout.transition_scene2, this);
		mScene3 = Scene.getSceneForLayout(mSceneRoot,
				R.layout.transition_scene3, this);
		mTransitionManager = inflater.inflateTransitionManager(
				R.transition.transitions_mgr, mSceneRoot);
	}

	@SuppressLint("NewApi")
	public void selectScene(View view) {
		switch (view.getId()) {
		case R.id.scene1:
			mTransitionManager.transitionTo(mScene1);
			break;
		case R.id.scene2:
			mTransitionManager.transitionTo(mScene2);
			break;
		case R.id.scene3:
			mTransitionManager.transitionTo(mScene3);
			break;
		case R.id.scene4:
			TransitionManager.beginDelayedTransition(mSceneRoot);
			setNewSize(R.id.view1, 150, 25);
			setNewSize(R.id.view2, 150, 25);
			setNewSize(R.id.view3, 150, 25);
			setNewSize(R.id.view4, 150, 25);
			break;
		}
	}

	private void setNewSize(int id, int width, int height) {
		View view = findViewById(id);
		ViewGroup.LayoutParams params = view.getLayoutParams();
		params.width = width;
		params.height = height;
		view.setLayoutParams(params);
	}
}
