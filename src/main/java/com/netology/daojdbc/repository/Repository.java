package com.netology.daojdbc.repository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.sql.Select;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@FieldDefaults(level = AccessLevel.PRIVATE)
@org.springframework.stereotype.Repository
public class Repository {

    @PersistenceContext
    EntityManager entityManager;

    public List<String> getProductName(String customerName) {
        String productName = read("/select_product.sql");
        final List<String> result = entityManager.createQuery(productName)
                    .setParameter("name", customerName)
                    .getResultList();
        return result;
    }

    private static String read(String scriptFileName) {
        try (final InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
