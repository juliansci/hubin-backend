package ar.com.fiuba.tpprof.hubin.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.fiuba.tpprof.hubin.model.Feedback;

public interface FeedbackDao extends CrudRepository<Feedback, Integer> {

}
