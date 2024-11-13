package CRUD.example.CRUD.repository;

import CRUD.example.CRUD.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
