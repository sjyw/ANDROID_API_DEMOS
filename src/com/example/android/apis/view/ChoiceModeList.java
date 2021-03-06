/*
 * Copyright (C) 2010 The Android Open Source Project
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
package com.example.android.apis.view;

import com.example.android.apis.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 长按多选，添加了选择模式
 * 
 * @description：
 * @author ldm
 * @date 2016-4-21 上午10:47:55
 */
public class ChoiceModeList extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 获取ListActivity中所包含的ListView对象
		ListView lv = getListView();
		// 设置ListView选择模式
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		// 设置选择监听
		lv.setMultiChoiceModeListener(new ModeCallback());
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_checked, mStrings));
	}

	// Activity彻底运行起来之后的回调
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// 改变显示在程序图标旁边的文字
		getActionBar().setSubtitle("Long press to start selection");
	}

	private class ModeCallback implements ListView.MultiChoiceModeListener {
		// 创建菜单
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			MenuInflater inflater = getMenuInflater();
			// 添加菜单布局xml文件
			inflater.inflate(R.menu.list_select_menu, menu);
			mode.setTitle("Select Items");
			setSubtitle(mode);
			return true;
		}

		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return true;
		}

		// 菜单的点击事件
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			switch (item.getItemId()) {
			case R.id.share:
				Toast.makeText(
						ChoiceModeList.this,
						"Shared " + getListView().getCheckedItemCount()
								+ " items", Toast.LENGTH_SHORT).show();
				mode.finish();
				break;
			default:
				Toast.makeText(ChoiceModeList.this,
						"Clicked " + item.getTitle(), Toast.LENGTH_SHORT)
						.show();
				break;
			}
			return true;
		}

		public void onDestroyActionMode(ActionMode mode) {
		}

		public void onItemCheckedStateChanged(ActionMode mode, int position,
				long id, boolean checked) {
			setSubtitle(mode);
		}

		private void setSubtitle(ActionMode mode) {
			final int checkedCount = getListView().getCheckedItemCount();
			switch (checkedCount) {
			case 0:
				mode.setSubtitle(null);
				break;
			case 1:
				mode.setSubtitle("One item selected");
				break;
			default:
				mode.setSubtitle("" + checkedCount + " items selected");
				break;
			}
		}
	}

	private String[] mStrings = Cheeses.sCheeseStrings;
}
