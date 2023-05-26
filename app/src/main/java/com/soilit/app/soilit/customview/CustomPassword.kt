package com.soilit.app.soilit.customview

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.soilit.app.soilit.R

class CustomPassword: TextInputEditText {
    private var signInButton: AppCompatButton? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attrs,
        defStyleAttrs
    ) {
        init()
    }
    
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    
        background = ContextCompat.getDrawable(context, R.drawable.rounded_edit_text)
    }

    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
//                val passwordLength = p0.toString().length
//                error = if (passwordLength in 1..7) context.getString(R.string.alert_password_less_than_6) else null
//                signInButton?.isEnabled = passwordLength >= 8
            }

        })
    }
}
