package pl.coderslab.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.provider.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long>
{

}
