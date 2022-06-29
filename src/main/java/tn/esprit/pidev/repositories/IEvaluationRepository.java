package tn.esprit.pidev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.pidev.entities.Evaluation;

public interface IEvaluationRepository extends JpaRepository<Evaluation, Integer> {
	/**********************************Find evaluation ByProductandUser***************************/
	@Query("SELECT e FROM Evaluation e WHERE e.product.id=:idProd AND e.user.id=:idUser ")
	public List<Evaluation> findEvaluationByUserAndProd(@Param("idProd")int idProd,@Param("idUser")int idUser);
	/**********************************Count evaluation by product ***************************/
	@Query("SELECT COUNT(e.idEvalution) FROM Evaluation e WHERE e.product.id =:idp")
	int countEvaluation(@Param("idp")int idp);
	/**********************************Find product By user***************************/
	@Query("SELECT COUNT(e.idEvalution) FROM Evaluation e WHERE e.product.id =:idp AND e.user.id =:idUser")
	int countUserRatingProduct(@Param("idp")int idp,@Param("idUser")int iduser);
}
