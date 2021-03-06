package com.tamanotchi.variant;

import java.util.Objects;

public class Variant {

    private Integer id;
    private String name;
    private Integer stage;
    private Integer fave_food;
    private Integer max_exp;
    private Integer upgrade;

    public Variant(Integer id, String name, Integer stage, Integer fave_food, Integer max_exp, Integer upgrade) {
        this.id = id;
        this.name = name;
        this.stage = stage;
        this.fave_food = fave_food;
        this.max_exp = max_exp;
        this.upgrade = upgrade;
    }

    public Variant() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public Integer getFave_food() {
        return fave_food;
    }

    public void setFave_food(Integer fave_food) {
        this.fave_food = fave_food;
    }

    public Integer getMax_exp() {
        return max_exp;
    }

    public void setMax_exp(Integer max_exp) {
        this.max_exp = max_exp;
    }

    public Integer getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(Integer upgrade) {
        this.upgrade = upgrade;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stage=" + stage +
                ", fave_food=" + fave_food +
                ", max_exp=" + max_exp +
                ", upgrade=" + upgrade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variant variant = (Variant) o;
        return Objects.equals(id, variant.id) && Objects.equals(name, variant.name) && Objects.equals(stage, variant.stage) && Objects.equals(fave_food, variant.fave_food) && Objects.equals(max_exp, variant.max_exp) && Objects.equals(upgrade, variant.upgrade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stage, fave_food, max_exp, upgrade);
    }
}
