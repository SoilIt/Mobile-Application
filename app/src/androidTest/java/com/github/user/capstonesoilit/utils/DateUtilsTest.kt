package com.github.user.capstonesoilit.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class DateUtilsTest {
    @Test
    fun testFormatDateTime() {
        // Test case with valid date-time string
        val dateTime = "2023-06-01T09:30:00.000Z"
        val expectedOutput = "09/01/06/23"
        val formattedDateTime = DateUtils.formatDateTime(dateTime)
        assertEquals(expectedOutput, formattedDateTime)

        // Test case with invalid date-time string
        val invalidDateTime = "2023-06-01T09:30:00"
        val expectedInvalidOutput = invalidDateTime
        val formattedInvalidDateTime = DateUtils.formatDateTime(invalidDateTime)
        assertEquals(expectedInvalidOutput, formattedInvalidDateTime)
    }
}
