package ag.ui.mod.web;

import com.ag.ui.mod.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebPageActivity extends Activity {

	WebView mBrowser = null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_page);
        
        //WebView
        mBrowser=(WebView)findViewById(R.id.web_content);  
        
        mBrowser.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        mBrowser.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        mBrowser.getSettings().setSupportZoom(true);//是否可以缩放，默认true
        mBrowser.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        mBrowser.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        mBrowser.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        mBrowser.getSettings().setAppCacheEnabled(true);//是否使用缓存
        mBrowser.getSettings().setDomStorageEnabled(true);//DOM Storage
        // mBrowser.getSettings().setUserAgentString("User-Agent:Android");//设置用户代理，一般不用
        
        mBrowser.loadUrl("http://www.baidu.com");  
          
        //设置可自由缩放网页  
        //mBrowser.getSettings().setSupportZoom(true);  
        //mBrowser.getSettings().setBuiltInZoomControls(true);  
          
        // 如果页面中链接，如果希望点击链接继续在当前browser中响应，  
        // 而不是新开Android的系统browser中响应该链接，必须覆盖webview的WebViewClient对象  
        mBrowser.setWebViewClient(new WebViewClient() {  
            public boolean shouldOverrideUrlLoading(WebView view, String url)  
            {   
                //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边  
                view.loadUrl(url);  
                	return true;  
            }         
        }); 
    }  
    
    //go back
    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        // Check if the key event was the Back button and if there's history  
        if (mBrowser != null && (keyCode == KeyEvent.KEYCODE_BACK) && mBrowser.canGoBack()) {  
        	mBrowser.goBack();  
            return true;  
        }  
      //  return true;  
        // If it wasn't the Back key or there's no web page history, bubble up to the default  
        // system behavior (probably exit the activity)  
        return super.onKeyDown(keyCode, event);  
    } 
}
