package org.http4k.connect.kafka.rest.action

import org.http4k.connect.Http4kConnectAction
import org.http4k.connect.kClass
import org.http4k.connect.kafka.rest.KafkaRestMoshi.auto
import org.http4k.connect.kafka.rest.model.ConsumerGroup
import org.http4k.connect.kafka.rest.model.ConsumerInstance
import org.http4k.connect.kafka.rest.model.Subscription
import org.http4k.connect.kafka.rest.model.Topic
import org.http4k.core.Body
import org.http4k.core.ContentType
import org.http4k.core.KAFKA_JSON_V2
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.with

@Http4kConnectAction
data class SubscribeToTopics(
    val group: ConsumerGroup,
    val instance: ConsumerInstance,
    val topics: List<Topic>,
) : KafkaRestAction<Unit>(kClass()) {
    override fun toRequest() = Request(POST, "/consumers/$group/instances/$instance/subscription")
        .with(Body.auto<Subscription>(contentType = ContentType.KAFKA_JSON_V2).toLens() of Subscription(topics))
}