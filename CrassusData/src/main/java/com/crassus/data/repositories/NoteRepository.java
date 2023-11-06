package com.crassus.data.repositories;

import com.crassus.models.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, String> {}
