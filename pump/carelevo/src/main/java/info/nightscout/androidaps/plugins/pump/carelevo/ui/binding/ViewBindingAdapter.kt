package info.nightscout.androidaps.plugins.pump.carelevo.ui.binding

import android.util.Log
import android.widget.Button
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import info.nightscout.androidaps.plugins.pump.carelevo.R
import info.nightscout.androidaps.plugins.pump.carelevo.common.model.PatchState
import info.nightscout.androidaps.plugins.pump.carelevo.ui.widget.CarelevoKeyValueRowView

@BindingAdapter("bleStateText")
fun CarelevoKeyValueRowView.bindBleStateText(state: PatchState?) {
    val txt = when (state) {
        PatchState.NotConnectedNotBooting -> context.getString(R.string.carelevo_state_none_value)
        PatchState.ConnectedBooted -> context.getString(R.string.carelevo_state_connected_value)
        else -> context.getString(R.string.carelevo_state_disconnected_value)
    }
    valueText = txt
}

@BindingAdapter("bleButtonText")
fun Button.bindBleButtonText(state: PatchState?) {
    val ctx = context
    text = when (state) {
        PatchState.NotConnectedBooted -> ctx.getString(R.string.carelevo_overview_communication_btn_label)
        else -> ctx.getString(R.string.carelevo_overview_connect_btn_label)
    }
    isEnabled = state != PatchState.ConnectedBooted
}

@BindingAdapter("bleButtonEnable")
fun Button.bindBleButtonEnable(state: PatchState?) {
    Log.d("bindBleButtonEnable", "state: $state")
    isEnabled = state != PatchState.NotConnectedNotBooting
}

@BindingAdapter(value = ["patchState", "isPump"])
fun Button.bindResumButtonState(state: PatchState?, isPump: Boolean) {
    val btnName = if (isPump) context.getString(R.string.carelevo_overview_pump_resume_btn_label) else context.getString(R.string.carelevo_overview_pump_stop_btn_label)
    text = btnName
    isVisible = state != PatchState.NotConnectedNotBooting
}
