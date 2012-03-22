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

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class OutputController {
	
	private SeekBarDisplayer sdRed;
	private SeekBarDisplayer sdGreen;
	private SeekBarDisplayer sdBlue;
	
	private DemoKitActivity activity;
	public OutputController(DemoKitActivity activity) {
		this.activity = activity;
	}
	
	public void onAccesssoryAttached() {
		this.sdRed = new SeekBarDisplayer((TextView) activity.findViewById(R.id.seekLabelRed),
				(SeekBar) activity.findViewById(R.id.seekBarRed),
				(TextView) activity.findViewById(R.id.seekValueRed),6);
		this.sdGreen = new SeekBarDisplayer((TextView) activity.findViewById(R.id.seekLabelGreen),
				(SeekBar) activity.findViewById(R.id.seekBarGreen),
				(TextView) activity.findViewById(R.id.seekValueGreen),7);
		this.sdBlue = new SeekBarDisplayer((TextView) activity.findViewById(R.id.seekLabelBlue),
				(SeekBar) activity.findViewById(R.id.seekBarBlue),
				(TextView) activity.findViewById(R.id.seekValueBlue),8);
		setEnabled(true);
	}
	
	public void setEnabled(boolean enabled){
		this.sdRed.setEnabled(enabled);
		this.sdGreen.setEnabled(enabled);
		this.sdBlue.setEnabled(enabled);
	}
	
	class SeekBarDisplayer {
		
		private TextView seekLabel;
		private SeekBar seekBar;
		private TextView seekValue;
		
		SeekBarDisplayer(final TextView seekLabel, final SeekBar seekBar, final TextView seekValue, final int mCommandTarget){
			this.seekLabel = seekLabel;
			seekBar.setMax(255);
			this.seekBar = seekBar;
			this.seekValue = seekValue;
			this.seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
				private final byte _mCommandTarget = (byte)mCommandTarget;
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					seekValue.setText(String.valueOf(progress));
					if (activity != null) {
						activity.sendCommand(DemoKitActivity.LED_COMMAND,
								_mCommandTarget, (byte) progress);
					}
				}
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {}
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {}
			});
			setEnabled(false);
		}
		
		public void setEnabled(boolean enabled){
			seekBar.setProgress(0);
			seekLabel.setEnabled(enabled);
			seekBar.setEnabled(enabled);
			seekValue.setEnabled(enabled);
		}
	}
}
