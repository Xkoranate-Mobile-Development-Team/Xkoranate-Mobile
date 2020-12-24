package com.xkoranate

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.ContextMenu
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {

    private lateinit var motionLayout: MotionLayout
    private lateinit var button: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        motionLayout = findViewById(R.id.motionContainer)


        window.decorView.post {
            motionLayout.setTransition(R.id.begin, R.id.start)
            motionLayout.transitionToEnd()
        }




    }




}
