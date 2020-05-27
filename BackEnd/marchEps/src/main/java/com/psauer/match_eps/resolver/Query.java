package com.psauer.match_eps.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.psauer.match_eps.Repository.CommentRepository;
import com.psauer.match_eps.Repository.PqrsRepository;
import com.psauer.match_eps.model.Comment;
import com.psauer.match_eps.model.Eps;
import com.psauer.match_eps.Repository.EpsRepository;
import com.psauer.match_eps.model.Pqrs;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Query implements GraphQLQueryResolver {

  private EpsRepository epsRepository;
  private CommentRepository commentRepository;

  public Iterable<Eps> findAllEps() {
    return epsRepository.findAll();
  }

  public Iterable<Comment> findAllComments(){
    return commentRepository.findAll();
  }


}
