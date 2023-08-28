package org.http4k.connect.amazon.cognito

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.http4k.connect.amazon.cognito.model.AttributeName
import org.http4k.connect.amazon.cognito.model.ClientName
import org.http4k.connect.amazon.cognito.model.CloudFrontDomain
import org.http4k.connect.amazon.cognito.model.Destination
import org.http4k.connect.amazon.cognito.model.PoolName
import org.http4k.connect.amazon.cognito.model.SecretCode
import org.http4k.connect.amazon.cognito.model.Session
import org.http4k.connect.amazon.cognito.model.UserCode
import org.http4k.connect.amazon.cognito.model.UserPoolId
import org.http4k.connect.amazon.core.model.Password
import org.http4k.connect.amazon.core.model.Username
import org.http4k.format.AwsCoreJsonAdapterFactory
import org.http4k.format.ConfigurableMoshi
import org.http4k.format.ListAdapter
import org.http4k.format.MapAdapter
import org.http4k.format.asConfigurable
import org.http4k.format.value
import org.http4k.format.withAwsCoreMappings
import org.http4k.format.withStandardMappings
import se.ansman.kotshi.KotshiJsonAdapterFactory

object CognitoMoshi : ConfigurableMoshi(
    Moshi.Builder()
        .add(CognitoJsonAdapterFactory)
        .add(AwsCoreJsonAdapterFactory())
        .add(ListAdapter)
        .add(MapAdapter)
        .asConfigurable()
        .withStandardMappings()
        .withAwsCoreMappings()
        .value(AttributeName)
        .value(ClientName)
        .value(Destination)
        .value(Password)
        .value(PoolName)
        .value(SecretCode)
        .value(Session)
        .value(UserCode)
        .value(Username)
        .value(CloudFrontDomain)
        .value(UserPoolId)
        .done()
)

@KotshiJsonAdapterFactory
object CognitoJsonAdapterFactory : JsonAdapter.Factory by KotshiCognitoJsonAdapterFactory
