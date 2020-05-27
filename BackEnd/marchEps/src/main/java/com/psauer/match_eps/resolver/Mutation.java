package com.psauer.match_eps.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.psauer.match_eps.Repository.CommentRepository;
import com.psauer.match_eps.Repository.EpsRepository;
import com.psauer.match_eps.model.Comment;
import com.psauer.match_eps.model.Eps;
import lombok.AllArgsConstructor;

import javax.persistence.EntityExistsException;

@AllArgsConstructor
public class Mutation implements GraphQLMutationResolver {

  private CommentRepository commentRepository;
  private EpsRepository epsRepository;

    public Comment newComment(String description, Integer idEps, String author) {
    Eps eps = epsRepository.findById(idEps).orElseThrow(() -> new EntityExistsException("CanNot fin Eps with id: " + idEps));
    Comment comment = new Comment();
    comment.setDescription(description);
    comment.setEps(eps);
    comment.setAuthor(author);
    return commentRepository.save(comment);
  }

}
