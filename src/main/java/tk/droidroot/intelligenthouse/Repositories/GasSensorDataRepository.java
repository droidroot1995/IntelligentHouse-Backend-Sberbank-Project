package tk.droidroot.intelligenthouse.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.droidroot.intelligenthouse.Models.GasSensorDataEntity;

@Repository
public interface GasSensorDataRepository extends JpaRepository<GasSensorDataEntity, Long> {
}
