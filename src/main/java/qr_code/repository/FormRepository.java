package qr_code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qr_code.models.model.FormModel;

import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<FormModel, Long> {
    int countAllByToAddressIgnoreCase(String toAddress);

    Optional<FormModel> findByQrCodeUrl(String qrCode);

}
