package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String> {

    Optional<Item> findByIndex(String index);

    List<Item> findByItemType(String itemType);

    List<Item> findByEquipmentCategoryName(String equipmentCategoryName);

    Page<Item> findByItemTypeNot(String itemType, Pageable pageable);

    Page<Item> findByEquipmentCategoryName(String equipmentCategoryName, Pageable pageable);

    Page<Item> findByEquipmentCategoryNameAndItemTypeNot(
            String equipmentCategoryName, String itemType, Pageable pageable);

    Page<Item> findByEquipmentCategoryNameAndItemType(
            String equipmentCategoryName, String itemType, Pageable pageable);

    Page<Item> findByItemType(String itemType, Pageable pageable);
}