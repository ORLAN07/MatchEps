package com.psauer.match_eps.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.psauer.match_eps.model.Eps;
import com.psauer.match_eps.Repository.EpsRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Query implements GraphQLQueryResolver {

  private EpsRepository epsRepository;

  public Iterable<Eps> findAllEps() {
    return epsRepository.findAll();
  }


}
