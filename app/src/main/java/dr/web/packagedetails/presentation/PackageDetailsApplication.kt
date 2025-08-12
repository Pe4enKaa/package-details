package dr.web.packagedetails.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PackageDetailsApplication: Application() {
    // Вместо QUERY_ALL_PACKAGES используем <queries>, чтобы:
    // 1. Повысить безопасность и приватность, ограничивая видимость других приложений.
    // 2. Соответствовать требованиям Google Play (API 30+).
    // 3. Улучшить производительность, так как система предоставляет только нужные Intent-ы.
    // Это рекомендуемый и более современный подход.
}