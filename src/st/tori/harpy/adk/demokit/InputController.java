/*
 * Copyright (C) 2012 Toriningen Inc.
 * This source is based on DemoKit source of The Android Open Source Project, Licensed under the Apache License, Version 2.0.
 * http://developer.android.com/guide/topics/usb/adk.html
 *
 * This source is also licensed under the Apache License, Version 2.0 (the "License");
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

package st.tori.harpy.adk.demokit;

import android.widget.TextView;

public class InputController {
	
	private SwitchDisplayer sd;
	
	private DemoKitActivity activity;
	public InputController(DemoKitActivity activity) {
		this.activity = activity;
	}

	public void onAccesssoryAttached() {
		this.sd = new SwitchDisplayer((TextView)activity.findViewById(R.id.buttonText));
		setEnabled(true);
	}

	public void switchStateChanged(boolean switchState) {
		sd.onSwitchStateChange(switchState);
	}

	public void onSwitchStateChange(Boolean switchState) {
		switchStateChanged(switchState);
	}

	public void setEnabled(boolean enabled) {
		sd.setEnabled(enabled);
	}

	class SwitchDisplayer {
		
		private TextView buttonText;
		
		SwitchDisplayer(TextView buttonText) {
			this.buttonText = buttonText;
		}

		public void setEnabled(boolean enabled){
			if(!enabled)
				buttonText.setText(R.string.msg_please_connect);
			else
				buttonText.setText(R.string.msg_released);
		}
		
		public void onSwitchStateChange(Boolean switchState) {
			if (!switchState) {
				buttonText.setText(R.string.msg_released);
			} else {
				buttonText.setText(R.string.msg_clicked);
			}
		}

	}
}
