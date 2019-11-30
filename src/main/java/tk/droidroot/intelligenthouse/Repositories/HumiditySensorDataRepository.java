package tk.droidroot.intelligenthouse.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.droidroot.intelligenthouse.Models.HumiditySensorDataEntity;

import java.util.List;

@Repository
public interface HumiditySensorDataRepository extends JpaRepository<HumiditySensorDataEntity, Long> {
    List<HumiditySensorDataEntity> findAllByHumiditySensorId(Long id);
}
