package br.com.snowpet.presentation.register.viewstate

sealed class FormInputState {
    object Default : FormInputState()
    object Valid : FormInputState()
    data class Invalid(
        val error: String? = null,
    ) : FormInputState()

    fun isValid() = this is Valid
}
