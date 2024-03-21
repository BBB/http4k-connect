package org.http4k.connect.google.authentication


typealias Scope = String
typealias Datestring = String
typealias Token = String
typealias RaptToken = String
typealias RefreshToken = String
typealias ClientId = String
typealias ClientSecret = String
typealias ProjectId = String
typealias TrustBoundary = String
typealias UniverseDomain = String

data class ApplicationCredentials(
    val type: CredentialType,
    val scopes: List<Scope>,
    val expiry: Datestring,
    val token: Token,
    val refreshToken: RefreshToken,
    val clientId: ClientId,
    val clientSecret: ClientSecret,
    val quotaProjectId: ProjectId?,
    val raptToken: RaptToken?,
    val trustBoundary: TrustBoundary?,
    val universeDomain: UniverseDomain?,
    val account: String?,
)

enum class CredentialType {
    authorized_user,
    service_account,
    external_account,
    external_account_authorized_user,
    impersonated_service_account,
    gdch_service_account
}



object Loader {
    fun invoke(): ApplicationCredentials {
        val fs = RealFileSystem
        val os = System.getProperty("os.name").lowercase()
        val appCredentialsPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS") ?: when {
            os.contains("win") -> "%APPDATA%\\gcloud\\application_default_credentials.json"
            else -> "~/.config/gcloud/application_default_credentials.json"
        }
        return when {
            RealFileSystem.exists(appCredentialsPath) -> {
                GoogleAuthenticationJson.asA<ApplicationCredentials>(
                    fs.readFile(
                        appCredentialsPath
                    )
                )
            }

            else -> TODO("Metadata Server")
        }
    }
}
