package com.bazooka.jobhunter.service;

import com.bazooka.jobhunter.entity.Note;

public interface NoteService {
	
	Note findNote(long id, long jobId, String username);

	Note save(Note note, long jobId, String username);

	void delete(long noteId, long jobId, String username);

}
