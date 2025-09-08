package info.nightscout.androidaps.plugins.pump.carelevo.ui.ext

import androidx.fragment.app.Fragment
import info.nightscout.androidaps.plugins.pump.carelevo.R
import info.nightscout.androidaps.plugins.pump.carelevo.ui.dialog.CarelevoInsulinInputDialog
import info.nightscout.androidaps.plugins.pump.carelevo.ui.dialog.CarelevoPumpResumeConfirmDialog
import info.nightscout.androidaps.plugins.pump.carelevo.ui.dialog.CarelevoPumpStopDurationSelectDialog
import info.nightscout.androidaps.plugins.pump.carelevo.ui.dialog.TextBottomSheetDialog

internal fun Fragment.showDialogInsulinInput(
    insulin: Int,
    positiveCallback: ((value: Int) -> Unit)? = null
) {
    CarelevoInsulinInputDialog
        .getInstance(insulin)
        .apply {
            setPositiveClickListener(positiveCallback)
        }.show(childFragmentManager, CarelevoInsulinInputDialog.TAG_DIALOG)
}

internal fun Fragment.showDialogConnect(
    address: String,
    negativeCallback: (() -> Unit)? = null,
    positiveCallback: (() -> Unit)? = null
) {
    TextBottomSheetDialog.Builder().setTitle(
        requireContext().getString(R.string.carelevo_dialog_patch_connect_message_title)
    ).setContent(
        address
    ).setSecondaryButton(
        TextBottomSheetDialog.Button(
            text = requireContext().getString(R.string.carelevo_btn_research),
            onClickListener = {
                negativeCallback?.invoke()
            }
        )
    ).setPrimaryButton(
        TextBottomSheetDialog.Button(
            text = requireContext().getString(R.string.carelevo_btn_confirm),
            onClickListener = {
                positiveCallback?.invoke()
            }
        )).build().show(childFragmentManager, "dialog_carelevo_discard_confirm")
}

internal fun Fragment.showDialogDiscardConfirm(
    positiveCallback: (() -> Unit)?
) {
    TextBottomSheetDialog.Builder().setTitle(
        requireContext().getString(R.string.carelevo_dialog_patch_discard_message_title)
    ).setContent(
        requireContext().getString(R.string.carelevo_dialog_patch_discard_message_desc)
    ).setSecondaryButton(
        TextBottomSheetDialog.Button(
            text = requireContext().getString(R.string.carelevo_btn_cancel),
        )
    ).setPrimaryButton(
        TextBottomSheetDialog.Button(
            text = requireContext().getString(R.string.carelevo_btn_confirm),
            onClickListener = {
                positiveCallback?.invoke()
            }
        )).build().show(childFragmentManager, "dialog_carelevo_discard_confirm")
}

internal fun Fragment.showDialogPumpStopDurationSelect(
    positiveCallback: ((duration: Int) -> Unit)?
) {
    CarelevoPumpStopDurationSelectDialog
        .getInstance()
        .apply {
            setPositiveClickListener(positiveCallback)
        }.show(childFragmentManager, CarelevoPumpStopDurationSelectDialog.TAG_DIALOG)
}

internal fun Fragment.showDialogPumpResumeConfirm(
    positiveCallback: (() -> Unit)?
) {
    CarelevoPumpResumeConfirmDialog
        .getInstance()
        .apply {
            setPositiveClickListener(positiveCallback)
        }.show(childFragmentManager, CarelevoPumpResumeConfirmDialog.TAG_DIALOG)
}