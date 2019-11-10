package tk.droidroot.intelligenthouse.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.droidroot.intelligenthouse.Models.HumiditySensorEntity;

@Repository
public interface HumiditySensorRepository extends JpaRepository<HumiditySensorEntity, Long> {
}
