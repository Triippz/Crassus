package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NoteRepository
  extends JpaRepository<Note, String>, JpaSpecificationExecutor<Note> {}
