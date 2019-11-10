package tk.droidroot.intelligenthouse.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.droidroot.intelligenthouse.Models.HumiditySensorDataEntity;

@Repository
public interface HumiditySensorDataRepository extends JpaRepository<HumiditySensorDataEntity, Long> {
}
