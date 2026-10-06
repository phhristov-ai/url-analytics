package com.phh.url.url_analytics.controller;

import com.phh.url.url_analytics.dto.UrlStatistics;
import com.phh.url.url_analytics.entity.ClickEvent;
import com.phh.url.url_analytics.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

	private final AnalyticsService analyticsService;

	@GetMapping("/{shortCode}/clicks")
	public long getClickCount(
			@PathVariable String shortCode
	) {
		return analyticsService.getClickCount(shortCode);
	}

	@GetMapping("/{shortCode}/recent")
	public List<ClickEvent> getRecentClicks(
			@PathVariable String shortCode
	) {
		return analyticsService.getRecentClicks(shortCode);
	}

	@GetMapping("/top")
	public List<UrlStatistics> getTopUrls(
			@RequestParam(defaultValue = "10") int limit
	) {
		return analyticsService.getTopUrls(limit);
	}
}