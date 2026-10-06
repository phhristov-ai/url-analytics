package com.phh.url.url_analytics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(
		name = "url_clicks",
		indexes = {
				@Index(name = "idx_click_short_code", columnList = "short_code")
		}
)
@Getter
@Setter
@NoArgsConstructor
public class ClickEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String shortCode;

	@Column(nullable = false)
	private Instant clickedAt;

	public ClickEvent(
			String shortCode,
			Instant clickedAt
	) {
		this.shortCode = shortCode;
		this.clickedAt = clickedAt;
	}
}