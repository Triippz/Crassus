package Repositories.src.main.java.com.crassus.data.repositories2;

import com.crassus.models.models2.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationRepository
  extends JpaRepository<Notification, String>, JpaSpecificationExecutor<Notification> {}
