package com.phh.url.url_analytics.repository;

import com.phh.url.url_analytics.entity.ClickEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClickEventRepository
		extends JpaRepository<ClickEvent, Long> {

	long countByShortCode(String shortCode);

	List<ClickEvent> findByShortCodeOrderByClickedAtDesc(
			String shortCode
	);

	@Query("""
            SELECT c.shortCode, COUNT(c)
            FROM ClickEvent c
            GROUP BY c.shortCode
            ORDER BY COUNT(c) DESC
            """)
	List<Object[]> findTopUrls(Pageable pageable);
}