package com.phh.url.url_analytics.service;

import com.phh.url.url_analytics.dto.UrlStatistics;
import com.phh.url.url_analytics.entity.ClickEvent;
import com.phh.url.url_analytics.events.UrlClickedEvent;
import com.phh.url.url_analytics.repository.ClickEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

	private final ClickEventRepository clickEventRepository;

	@Transactional
	public void recordClick(UrlClickedEvent event) {

		ClickEvent clickEvent = new ClickEvent(
				event.shortCode(),
				event.clickedAt()
		);

		clickEventRepository.save(clickEvent);
	}

	@Transactional(readOnly = true)
	public long getClickCount(String shortCode) {

		return clickEventRepository.countByShortCode(shortCode);
	}

	@Transactional(readOnly = true)
	public List<ClickEvent> getRecentClicks(String shortCode) {

		return clickEventRepository
				.findByShortCodeOrderByClickedAtDesc(shortCode);
	}

	@Transactional(readOnly = true)
	public List<UrlStatistics> getTopUrls(int limit) {

		return clickEventRepository
				.findTopUrls(PageRequest.of(0, limit))
				.stream()
				.map(row -> new UrlStatistics(
						(String) row[0],
						(Long) row[1]
				))
				.toList();
	}
}