package com.phh.url.url_analytics.consumer;

import com.phh.url.url_analytics.events.UrlClickedEvent;
import com.phh.url.url_analytics.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClickEventConsumer {

	private final AnalyticsService analyticsService;

	@KafkaListener(
			topics = "url-clicked",
			groupId = "analytics-service"
	)
	public void consume(UrlClickedEvent event) {

		log.info(
				"Received click event: shortCode={}, clickedAt={}",
				event.shortCode(),
				event.clickedAt()
		);

		analyticsService.recordClick(event);
	}
}