package com.nirwashh.android.sliederexample

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.nirwashh.android.sliederexample.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var b: ActivityWelcomeBinding
    private val layouts = intArrayOf(R.layout.welcome_slide1, R.layout.welcome_slide2, R.layout.welcome_slide3)
    private val dots: Array<ImageView?> = arrayOfNulls(layouts.size)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(b.root)

        setStatusBarColor()

        addBottomDots(0)
        b.viewPager.adapter = MyViewPagerAdapter()
        b.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                addBottomDots(position)
                if (position == layouts.size - 1) {
                    b.btnNext.text = getString(R.string.start)
                } else {
                    b.btnNext.text = getString(R.string.next)
                }
                if (position == 0) {
                    b.btnSkip.visibility = View.VISIBLE
                    b.btnBack.visibility = View.GONE
                } else {
                    b.btnSkip.visibility = View.GONE
                    b.btnBack.visibility = View.VISIBLE
                }

            }

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}

        })
        b.btnSkip.setOnClickListener {
            launchHomeScreen()
        }
        b.btnNext.setOnClickListener {
            val current = getItem(+1)
            if (current < layouts.size) {
                // move to next screen
                b.viewPager.currentItem = current
            } else {
                launchHomeScreen()
            }
        }
        b.btnBack.setOnClickListener {
            b.viewPager.currentItem -= 1

        }
    }

    private fun setStatusBarColor() {
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.welcome_status_bar_color)

    }

    private fun getItem(i: Int): Int {
        return b.viewPager.currentItem + i
    }

    private fun addBottomDots(currentPage: Int) {
        b.layoutDots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageResource(R.drawable.ic_star_outlined)
            b.layoutDots.addView(dots[i])
        }
        if (dots.isNotEmpty()) dots[currentPage]!!.setImageResource(R.drawable.ic_star)
    }

    private fun launchHomeScreen() {
        startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
        finish()
    }

    inner class MyViewPagerAdapter : PagerAdapter() {
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = layoutInflater.inflate(layouts[position], container, false)
            container.addView(view)
            return view
        }

        override fun getCount() = layouts.size

        override fun isViewFromObject(view: View, `object`: Any) = view === `object`

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }

    }


}