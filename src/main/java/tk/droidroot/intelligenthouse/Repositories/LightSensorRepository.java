package tk.droidroot.intelligenthouse.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.droidroot.intelligenthouse.Models.LightSensorEntity;

@Repository
public interface LightSensorRepository extends JpaRepository<LightSensorEntity, Long> {
}
