package net.sourceforge.simcpux;


import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 支付页面
 */
public class PayActivity extends Activity {
	
	private IWXAPI api;
	private Button appayBtn;

	Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what){
				case 0:
					Toast.makeText(PayActivity.this, (String)msg.obj, Toast.LENGTH_SHORT).show();
					break;
				case 1:
					appayBtn.setEnabled(true);
					break;
			}
			super.handleMessage(msg);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay);
		
		api = WXAPIFactory.createWXAPI(this, "wxb4ba3c02aa476ea1");

		appayBtn = (Button) findViewById(R.id.appay_btn);
		appayBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				appayBtn.setEnabled(false);
				Toast.makeText(PayActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();
				pay();

			}
		});		
		Button checkPayBtn = (Button) findViewById(R.id.check_pay_btn);
		checkPayBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
				Toast.makeText(PayActivity.this, String.valueOf(isPaySupported), Toast.LENGTH_SHORT).show();
			}
		});
	}


	private void pay(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				String url = "https://wxpay.wxutil.com/pub_v2/app/app_pay.php";
				Message msg = mHandler.obtainMessage();
				try{
					byte[] buf = Util.httpGet(url);
					if (buf != null && buf.length > 0) {
						String content = new String(buf);
						Log.e("wgw_httpGet_content:",content);
						JSONObject json = new JSONObject(content);
						if(null != json && !json.has("retcode") ){
							PayReq req = new PayReq();
							//req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
							req.appId			= json.getString("appid");
							req.partnerId		= json.getString("partnerid");
							req.prepayId		= json.getString("prepayid");
							req.nonceStr		= json.getString("noncestr");
							req.timeStamp		= json.getString("timestamp");
							req.packageValue	= json.getString("package");
							req.sign			= json.getString("sign");
							req.extData			= "app data"; // optional
//							Toast.makeText(PayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
							// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
							msg.obj = "正常调起支付";
							mHandler.sendMessage(msg);
							api.sendReq(req);//调起支付
						}else{
							Log.d("wgw_PAY_GET", "返回错误"+json.getString("retmsg"));
//							Toast.makeText(PayActivity.this, "返回错误"+json.getString("retmsg"), Toast.LENGTH_SHORT).show();
							msg.obj = "返回错误"+json.getString("retmsg");
							mHandler.sendMessage(msg);
						}
					}else{
						Log.d("PAY_GET", "服务器请求错误");
//						Toast.makeText(PayActivity.this, "服务器请求错误", Toast.LENGTH_SHORT).show();
						msg.obj = "服务器请求错误";
						mHandler.sendMessage(msg);
					}
				}catch(Exception e){
					Log.e("PAY_GET", "异常："+e.getMessage());
//					Toast.makeText(PayActivity.this, "异常："+e.getMessage(), Toast.LENGTH_SHORT).show();
					msg.obj = "异常："+e.getMessage();
					mHandler.sendMessage(msg);
				}
				mHandler.sendEmptyMessage(1);
			}
		}).start();
	}
}
