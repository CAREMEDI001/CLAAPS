package info.nightscout.androidaps.plugins.pump.carelevo.ui.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.NumberPicker

class CarelevoNumberPicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.numberPickerStyle
) : NumberPicker(context, attrs, defStyleAttr) {

    // 공통 스타일 적용 함수
    private fun applyStyleToChild(child: View?) {
        if (child is EditText) {
            child.textSize = 22f          // 원하는 sp 크기
            child.typeface = Typeface.DEFAULT_BOLD
            child.includeFontPadding = false
        }
    }

    override fun addView(child: View?) {
        super.addView(child)
        applyStyleToChild(child)
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)
        applyStyleToChild(child)
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        super.addView(child, params)
        applyStyleToChild(child)
    }
}