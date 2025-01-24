package br.com.snowpet.core.viewstate

sealed interface ViewState<out T> {
    object Loading : ViewState<Nothing>
    data class Success<T>(val data: T) : ViewState<T>
    open class Error(open val title: String? = null, open val message: String? = null) :
        ViewState<Nothing> {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Error) return false

            return title == other.title && message == other.message
        }

        override fun hashCode() = 31 * (title?.hashCode() ?: 0) + (message?.hashCode() ?: 0)
    }
}

fun <T> ViewState<T>.onSuccess(action: (data: T) -> Unit): ViewState<T> {
    if (this is ViewState.Success) action(data)
    return this
}

fun <T> ViewState<T>.onLoading(action: () -> Unit): ViewState<T> {
    if (this is ViewState.Loading) action()
    return this
}

fun <T> ViewState<T>.onError(action: (title: String?, message: String?) -> Unit): ViewState<T> {
    if (this is ViewState.Error) action(title, message)
    return this
}