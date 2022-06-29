package tn.esprit.pidev.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.pidev.entities.Role;

public interface IRoleRepository extends CrudRepository<Role, Integer> {

}
