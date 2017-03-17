package www.melody.chat.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.melody.chat.R;
import www.melody.chat.common.base.MBaseFragment;

/**
 * @author zhengxiuyuan
 * @date 2016/10/14
 *
 * 主页面--设置
 */
public class SettingFragment extends MBaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View settingLayout = inflater.inflate(R.layout.setting_layout,
				container, false);
		return settingLayout;
	}

}
