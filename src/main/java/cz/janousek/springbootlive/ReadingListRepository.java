package cz.janousek.springbootlive;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Inderits basic methods from JpaRepository (deleteAllInBatch, deleteInBatch, findAll, findAllById, flush, getOne,
 * saveAll, saveAndFlush)
 *
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {
	/**
	 * Toto nikdo nemusi implementovat, protoze Spring Data to dokaze odvodit podle nazvu metody.
	 * */
	List<Book> findByReader(String reader);
}
