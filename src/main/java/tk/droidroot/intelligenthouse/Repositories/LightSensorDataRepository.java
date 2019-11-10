package tk.droidroot.intelligenthouse.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.droidroot.intelligenthouse.Models.LightSensorDataEntity;

@Repository
public interface LightSensorDataRepository extends JpaRepository<LightSensorDataEntity, Long> {
}