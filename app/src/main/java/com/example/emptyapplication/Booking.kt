package com.example.emptyapplication

import androidx.annotation.Keep

@Keep
data class Booking(
    val canIssueTicketChecking: Boolean = false,
    val duration: Int = 0,
    val expiryTime: String = "",
    val segments: List<Segment> = emptyList(),
    val shipReference: String = "",
    val shipToken: String = ""
) {
    override fun toString(): String {
        return "Booking(canIssueTicketChecking=$canIssueTicketChecking, " +
                "duration=$duration, " +
                "expiryTime='$expiryTime', " +
                "segments=$segments, " +
                "shipReference='$shipReference', " +
                "shipToken='$shipToken')"
    }

    @Keep
    data class Segment(
        val id: Int = 0,
        val originAndDestinationPair: OriginAndDestinationPair = OriginAndDestinationPair()
    ) {
        override fun toString(): String {
            return "Segment(id=$id, " +
                    "originAndDestinationPair=$originAndDestinationPair)"
        }

        @Keep
        data class OriginAndDestinationPair(
            val destination: Destination = Destination(),
            val destinationCity: String = "",
            val origin: Origin = Origin(),
            val originCity: String = ""
        ) {
            override fun toString(): String {
                return "OriginAndDestinationPair(destination=$destination, " +
                        "destinationCity='$destinationCity', " +
                        "origin=$origin, " +
                        "originCity='$originCity')"
            }

            @Keep
            data class Destination(
                val code: String = "",
                val displayName: String = "",
                val url: String = ""
            ) {
                override fun toString(): String {
                    return "Destination(code='$code', displayName='$displayName', url='$url')"
                }
            }

            @Keep
            data class Origin(
                val code: String = "",
                val displayName: String = "",
                val url: String = ""
            ) {
                override fun toString(): String {
                    return "Origin(code='$code', displayName='$displayName', url='$url')"
                }
            }
        }
    }
}
