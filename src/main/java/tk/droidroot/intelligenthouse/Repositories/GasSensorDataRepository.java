package tk.droidroot.intelligenthouse.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.droidroot.intelligenthouse.Models.GasSensorDataEntity;

import java.util.List;

@Repository
public interface GasSensorDataRepository extends JpaRepository<GasSensorDataEntity, Long> {
    List<GasSensorDataEntity> findAllByGasSensorId(Long id);
}
