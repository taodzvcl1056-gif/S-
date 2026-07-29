package com.ff.aim

import android.app.Service
import android.content.Intent
import android.graphics.*
import android.os.IBinder
import android.view.*

class AimCircleService : Service() {
    private lateinit var wm: WindowManager
    private lateinit var view: View
    override fun onCreate() {
        super.onCreate()
        wm = getSystemService(WINDOW_SERVICE) as WindowManager
        view = object : View(this) {
            private val p = Paint().apply { color = Color.GREEN; style = Paint.Style.STROKE; strokeWidth = 5f }
            override fun onDraw(canvas: Canvas) {
                canvas.drawCircle(width / 2f, height / 2f, 30f, p)
            }
        }
        val p = WindowManager.LayoutParams(200, 200, WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT).apply {
            gravity = Gravity.CENTER
        }
        wm.addView(view, p)
    }
    override fun onDestroy() { wm.removeView(view); super.onDestroy() }
    override fun onBind(intent: Intent?): IBinder? = null
}
