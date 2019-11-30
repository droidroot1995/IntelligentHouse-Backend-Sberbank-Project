package tk.droidroot.intelligenthouse.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.droidroot.intelligenthouse.Models.TemperatureSensorDataEntity;

import java.util.List;

@Repository
public interface TemperatureSensorDataRepository extends JpaRepository<TemperatureSensorDataEntity, Long> {
    List<TemperatureSensorDataEntity> findAllByTemperatureSensorId(Long id);
}
