package com.psauer.match_eps;

import com.coxautodev.graphql.tools.SchemaParser;
import com.psauer.match_eps.Repository.CommentRepository;
import com.psauer.match_eps.exception.GraphQLErrorAdapter;
import com.psauer.match_eps.Repository.EpsRepository;
import com.psauer.match_eps.resolver.Mutation;
import com.psauer.match_eps.resolver.Query;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.ExecutionStrategy;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLErrorHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import graphql.servlet.GraphQLServlet;
import graphql.servlet.SimpleGraphQLServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class MatchEpsApplication {

	@Autowired
	EpsRepository epsRepository;

	@Autowired
	CommentRepository commentRepository;


	public static void main(String[] args) {
		SpringApplication.run(MatchEpsApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {

		GraphQLSchema schema  = SchemaParser.newParser()
				.resolvers(mutation(commentRepository, epsRepository), query(epsRepository, commentRepository))
				.file("graphql/eps.graphqls")
				.build().makeExecutableSchema();
		ExecutionStrategy executionStrategy = new AsyncExecutionStrategy();
		GraphQLServlet servlet = new SimpleGraphQLServlet(schema, executionStrategy);
		ServletRegistrationBean bean = new ServletRegistrationBean(servlet, "/graphql");
		return bean;
	}

	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> clientErrors = errors.stream()
						.filter(this::isClientError)
						.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors.stream()
						.filter(e -> !isClientError(e))
						.map(GraphQLErrorAdapter::new)
						.collect(Collectors.toList());

				List<GraphQLError> e = new ArrayList<>();
				e.addAll(clientErrors);
				e.addAll(serverErrors);
				return e;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
	}

	@Bean
	public Mutation mutation( CommentRepository commentRepository, EpsRepository epsRepository) {
		return new Mutation(commentRepository, epsRepository);
	}

	@Bean
	public Query query(EpsRepository epsRepository, CommentRepository commentRepository) {
		return new Query(epsRepository, commentRepository);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("*").allowedOrigins("http://localhost:*");
			}
		};
	}

}
