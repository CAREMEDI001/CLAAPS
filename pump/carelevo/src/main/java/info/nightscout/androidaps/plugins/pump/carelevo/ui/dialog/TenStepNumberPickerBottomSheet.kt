package info.nightscout.androidaps.plugins.pump.carelevo.ui.dialog

import android.os.Bundle
import android.view.View
import info.nightscout.androidaps.plugins.pump.carelevo.R
import info.nightscout.androidaps.plugins.pump.carelevo.databinding.DialogTenStepNumberPickerBinding
import info.nightscout.androidaps.plugins.pump.carelevo.ui.base.CarelevoBaseDialog

class TenStepNumberPickerBottomSheet(
    private val initialValue: Int = MAX_VALUE,
    private val onValueSelected: (Int) -> Unit
) : CarelevoBaseDialog<DialogTenStepNumberPickerBinding>(R.layout.dialog_ten_step_number_picker) {

    companion object {

        const val MIN_VALUE = 50
        const val MAX_VALUE = 300
        const val STEP = 10

        val TEMP_BASAL_VALUES: List<Int> = (MIN_VALUE..MAX_VALUE step STEP).toList()
    }

    private val values = TEMP_BASAL_VALUES

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPicker()
        setupButtons()
    }

    private fun setupPicker() = with(binding.numberPicker) {
        minValue = 0
        maxValue = values.size - 1
        displayedValues = values.map { it.toString() }.toTypedArray()

        wrapSelectorWheel = false

        val startIndex = values.indexOf(initialValue)
        value = if (startIndex >= 0) startIndex else 0
    }

    private fun setupButtons() = with(binding) {
        btnOk.setOnClickListener {
            onValueSelected(values[numberPicker.value])
            dismiss()
        }
        btnCancel.setOnClickListener {
            dismiss()
        }
    }
}