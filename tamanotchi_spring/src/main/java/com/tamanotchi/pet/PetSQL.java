package com.tamanotchi.pet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tamanotchi.food.Food;
import com.tamanotchi.house.House;
import com.tamanotchi.variant.Variant;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository("petSQL")
public class PetSQL implements PetDAO{

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert insertIntoPets;

    public PetSQL(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        insertIntoPets = new SimpleJdbcInsert(jdbc).withTableName("pets").usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Pet> getAll() {

        String sql = "SELECT id, name, house, variant, happiness, energy, mood, exp, money FROM pets";

        return jdbc.query(sql, (rs, rowNum) ->
            new Pet(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("house"),
                rs.getInt("variant"),
                rs.getInt("happiness"),
                rs.getInt("energy"),
                rs.getInt("mood"),
                rs.getInt("exp"),
                rs.getInt("money")
            )
        );
    }

    @Override
    public Pet getById(Integer id) {

        String sql = "SELECT id, name, house, variant, happiness, energy, mood, exp, money FROM pets WHERE id = ?";

        try {
            return jdbc.queryForObject(sql, (rs, rowNum) ->
                new Pet(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("house"),
                    rs.getInt("variant"),
                    rs.getInt("happiness"),
                    rs.getInt("energy"),
                    rs.getInt("mood"),
                    rs.getInt("exp"),
                    rs.getInt("money")
                ),
                id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int add(Pet pet) {

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", pet.getName());
        parameters.put("house", pet.getHouse());
        parameters.put("variant", pet.getVariant());
        parameters.put("happiness", pet.getHappiness());
        parameters.put("energy", pet.getEnergy());
        parameters.put("mood", pet.getMood());
        parameters.put("exp", pet.getExp());
        parameters.put("money", pet.getMoney());

        return insertIntoPets.executeAndReturnKey(parameters).intValue();
    }

    @Override
    public int updateById(Integer id, Pet update) {

        String sql = "UPDATE pets SET (name, house, variant, happiness, energy, mood, exp, money)=(?, ?, ?, ?, ?, ?, ?, ?) WHERE id = ?";

        Pet original = getById(id);

        String newName = update.getName();
        if (newName == null) newName = original.getName();
        Integer newHouse = update.getHouse();
        if (newHouse == null) newHouse = original.getHouse();
        Integer newVariant = update.getVariant();
        if (newVariant == null) newVariant = original.getVariant();
        Integer newHappiness = update.getHappiness();
        if (newHappiness == null) newHappiness = original.getHappiness();
        Integer newEnergy = update.getEnergy();
        if (newEnergy == null) newEnergy = original.getEnergy();
        Integer newMood = update.getMood();
        if (newMood == null) newMood = original.getMood();
        Integer newExp = update.getExp();
        if (newExp == null) newExp = original.getExp();
        Integer newMoney = update.getMoney();
        if (newMoney == null) newMoney = original.getMoney();

        return jdbc.update(sql,
            newName,
            newHouse,
            newVariant,
            newHappiness,
            newEnergy,
            newMood,
            newExp,
            newMoney,
            id
        );
    }

    @Override
    public int deleteById(Integer id) {

        String sql = "DELETE FROM pets WHERE id = ?";

        return jdbc.update(sql, id);
    }

    @Override
    public House getHouseById(Integer id) {

        String sql = "SELECT id, name, price, happiness_bonus, size, upgrade FROM houses WHERE id = ?";

        try{
            return jdbc.queryForObject(sql, (rs, rowNum) ->
                new House(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getInt("happiness_bonus"),
                    rs.getInt("size"),
                    rs.getInt("upgrade")
                ),
                id
            );
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Variant getVariantById(Integer id) {

        String sql = "SELECT id, name, stage, fave_food, max_exp, upgrade FROM variants WHERE id = ?";
        
        try {
            return jdbc.queryForObject(sql, (rs, rowNum) ->
                new Variant(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("stage"),
                    rs.getInt("fave_food"),
                    rs.getInt("max_exp"),
                    rs.getInt("upgrade")
                ),
                id
            );
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Food getFoodById(Integer id) {

        String sql = "SELECT id, name, price, energy, happiness, isUnhealthy, heals FROM foods WHERE id = ?";

        try {
            return jdbc.queryForObject(sql, (rs, rowNum) ->
                new Food(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getInt("energy"),
                    rs.getInt("happiness"),
                    rs.getBoolean("isUnhealthy"),
                    rs.getBoolean("heals")
                ),
                id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
