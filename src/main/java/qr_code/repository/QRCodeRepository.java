package qr_code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qr_code.models.model.QRCode;

import java.util.Optional;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    int countAllByToAddressIgnoreCase(String toAddress);

    Optional<QRCode> findByQrCodeUrl(String qrCode);
}
