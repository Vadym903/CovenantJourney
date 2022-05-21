package com.example.covenant.journey.services.apartment.feedback;

import com.example.covenant.journey.model.apartment.feedback.Feedback;
import com.example.covenant.journey.repositories.BaseRepository;
import com.example.covenant.journey.repositories.apartment.feedback.FeedbackRepository;
import com.example.covenant.journey.services.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService extends AbstractService<Feedback> {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Override
	protected BaseRepository<Feedback> getRepository() {
		return feedbackRepository;
	}
}
