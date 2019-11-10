package tk.droidroot.intelligenthouse.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.droidroot.intelligenthouse.Models.TemperatureSensorEntity;

@Repository
public interface TemperatureSensorRepository extends JpaRepository<TemperatureSensorEntity, Long> {
}
