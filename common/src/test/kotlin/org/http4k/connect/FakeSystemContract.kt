package org.http4k.connect

import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.connect.common.ChaosFake
import org.http4k.core.Request
import org.http4k.core.Status.Companion.I_M_A_TEAPOT
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.Test

abstract class FakeSystemContract(private val fake: ChaosFake) {
    protected abstract val anyValidRequest: Request

    @Test
    fun `returns error when told to misbehave`() {
        val originalStatus = fake(anyValidRequest).status
        fake.returnStatus(I_M_A_TEAPOT)
        assertThat(fake(anyValidRequest), hasStatus(I_M_A_TEAPOT))
        fake.behave()
        assertThat(fake(anyValidRequest), hasStatus(originalStatus))
    }
}