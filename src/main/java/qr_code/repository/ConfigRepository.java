package qr_code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qr_code.models.model.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config,Long> {
}
