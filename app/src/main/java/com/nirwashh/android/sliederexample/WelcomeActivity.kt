package com.nirwashh.android.sliederexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.nirwashh.android.sliederexample.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var b: ActivityWelcomeBinding
    private val layouts = intArrayOf(R.layout.welcome_slide1, R.layout.welcome_slide2)
    private val dots: Array<TextView?> = arrayOfNulls(layouts.size)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(b.root)

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
            b.viewPager.currentItem = -1

        }
    }

    private fun getItem(i: Int): Int {
        return b.viewPager.currentItem + i
    }

    private fun addBottomDots(currentPage: Int) {
        val colorActive = resources.getIntArray(R.array.array_dot_active)
        val colorInActive = resources.getIntArray(R.array.array_dot_inactive)
        b.layoutDots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("?")
            dots[i]!!.textSize = 35f
            dots[i]!!.setTextColor(colorInActive[currentPage])
            b.layoutDots.addView(dots[i])
        }
        if (dots.isNotEmpty()) dots[currentPage]!!.setTextColor(colorActive[currentPage])
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