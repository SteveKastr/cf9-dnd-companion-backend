package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.Monster;
import gr.aueb.cf9.dndcompanion.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonsterService {

    private final MonsterRepository monsterRepository;
    private final MongoTemplate mongoTemplate;

    public Page<Monster> getAllMonsters(Pageable pageable, String type, Double challengeRating, String search) {

        List<Criteria> criteriaList = new ArrayList<>();

        if (StringUtils.hasText(type)) {
            criteriaList.add(Criteria.where("type").is(type));
        }
        if (challengeRating != null) {
            criteriaList.add(Criteria.where("challengeRating").is(challengeRating));
        }
        if (StringUtils.hasText(search)) {
            criteriaList.add(Criteria.where("name").regex(search, "i"));
        }

        Query query = new Query();
        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        long total = mongoTemplate.count(query, Monster.class);
        query.with(pageable);
        List<Monster> monsters = mongoTemplate.find(query, Monster.class);

        return PageableExecutionUtils.getPage(monsters, pageable, () -> total);
    }

    public Monster getMonsterByIndex(String index) {
        return monsterRepository.findByIndex(index)
                .orElseThrow(() -> new EntityNotFoundException("Monster not found: " + index));
    }
}