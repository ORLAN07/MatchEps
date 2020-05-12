package com.psauer.match_eps.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookNotFoundException extends RuntimeException implements GraphQLError {

  private Map<String, Object> extensions = new HashMap<>();

  public BookNotFoundException(String message, Long invalidBookId) {
    super(message);
    extensions.put("invalidEpsId", invalidBookId);
  }

  @Override
  public List<SourceLocation> getLocations() {
    return List.of();
  }

  @Override
  public Map<String, Object> getExtensions() {
    return extensions;
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.DataFetchingException;
  }

}