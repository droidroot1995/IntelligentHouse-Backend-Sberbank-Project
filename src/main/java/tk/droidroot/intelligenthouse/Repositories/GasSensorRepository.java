package tk.droidroot.intelligenthouse.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.droidroot.intelligenthouse.Models.GasSensorEntity;

@Repository
public interface GasSensorRepository extends JpaRepository<GasSensorEntity, Long> {
}
