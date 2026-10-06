package com.phh.url.url_analytics.events;

import java.time.Instant;

public record UrlClickedEvent(
		String shortCode,
		Instant clickedAt
) {
}