package tk.droidroot.intelligenthouse.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.droidroot.intelligenthouse.Models.TemperatureSensorDataEntity;

@Repository
public interface TemperatureSensorDataRepository extends JpaRepository<TemperatureSensorDataEntity, Long> {
}
