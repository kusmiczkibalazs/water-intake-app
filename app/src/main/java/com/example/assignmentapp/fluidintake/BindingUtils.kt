package com.example.assignmentapp.fluidintake

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.assignmentapp.database.FluidIntake
import java.text.SimpleDateFormat

@BindingAdapter("quantityString")
fun TextView.setIntakeQuantityFormatted(item: FluidIntake) {
    val concatenatedString = "${item.intakeQuantity}dl fogyasztás"
    text = concatenatedString
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("dateTimeString")
fun TextView.setIntakeDateTimeFormatted(item: FluidIntake) {
    text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(item.intakeTimeMilli).toString()
}