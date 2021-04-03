package org.http4k.connect.amazon.dynamodb

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.http4k.connect.amazon.model.AttributeName
import org.http4k.connect.amazon.model.IndexName
import org.http4k.connect.amazon.model.TableName
import org.http4k.format.AwsJsonAdapterFactory
import org.http4k.format.ConfigurableMoshi
import org.http4k.format.asConfigurable
import org.http4k.format.value
import org.http4k.format.withAwsCoreMappings
import org.http4k.format.withStandardMappings
import se.ansman.kotshi.KotshiJsonAdapterFactory

object DynamoDbMoshi : ConfigurableMoshi(
    Moshi.Builder()
        .add(KotshiDynamoDbJsonAdapterFactory)
        .add(AwsJsonAdapterFactory())
        .asConfigurable()
        .withStandardMappings()
        .withAwsCoreMappings()
        .value(AttributeName)
        .value(IndexName)
        .value(TableName)
        .done()
)

@KotshiJsonAdapterFactory
abstract class DynamoDbJsonAdapterFactory : JsonAdapter.Factory
