package qr_code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qr_code.models.QRCode;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
}
