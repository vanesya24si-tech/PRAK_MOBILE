package com.example.nesoulapps.pertemuan_5
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.nesoulapps.databinding.ActivityWebViewBinding
import androidx.activity.OnBackPressedCallback
class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Mini Browser"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.cacheMode = android.webkit.WebSettings.LOAD_DEFAULT

        binding.webView.webViewClient = WebViewClient()

        binding.webView.loadUrl("https://www.instagram.com/nesssyyaaz")

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                } else {
                    finish()
                }
            }
        })
        // efek hide toolbar saat scroll
        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.appBar.setExpanded(false, true)
            } else {
                binding.appBar.setExpanded(true, true)
            }

        }
    }

}

