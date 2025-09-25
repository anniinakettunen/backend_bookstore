package bookstore.bookstoreapp.repository;

import org.springframework.data.repository.CrudRepository;

import bookstore.bookstoreapp.model.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	AppUser findByUsername(String username);
}