package com.bazooka.jobhunter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazooka.jobhunter.entity.Job;
import com.bazooka.jobhunter.entity.Note;
import com.bazooka.jobhunter.exceptions.ResourceNotFoundException;
import com.bazooka.jobhunter.repository.JobRepository;
import com.bazooka.jobhunter.repository.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private JobService jobService;

	@Override
	public Note findNote(long id, long jobId, String username) {
		Job job = jobService.findJob(jobId, username);
		return findNoteByIdAndJob(id, job);
	}
	
	@Override
	public Note save(Note note, long jobId, String username) {
		Job job = jobService.findJob(jobId, username);
		note.setJob(job);
		return noteRepository.save(note);
	}

	@Override
	public void delete(long noteId, long jobId, String username) {
		Job job = jobService.findJob(jobId, username);
		Note note = findNoteByIdAndJob(noteId, job);
		noteRepository.delete(note);
	}
	
	private Note findNoteByIdAndJob(long id, Job job) {
		Optional<Note> result = noteRepository.findByIdAndJob(id, job);
		
		if (result.isEmpty()) {
			throw new ResourceNotFoundException("Note with id " + id + " does not exist for this job");
		}
		
		return result.get();
	}

}
